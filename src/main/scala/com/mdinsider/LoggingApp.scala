package com.mdinsider

import akka.actor.ActorSystem
import com.mdinsider.domain.Activity
import com.mdinsider.domain.Protocol.AddActivity
import com.mdinsider.infrastructure.{ CassandraDB, ActivityActor }
import com.typesafe.config.ConfigFactory
import scala.util.Random

object LoggingApp extends App {

  import com.mdinsider.domain.Protocol.{ CreateTable, CreateSchema }

  val conf =
    """akka.remote.netty.tcp.hostname="%hostname%"
      |akka.remote.netty.tcp.port=%port%
      |akka.cluster.roles = [backend]
      | """.stripMargin

  val argumentsError = """
   Please run the service with the required arguments: <configuration> <hostIpAddress> <port>, eg. dev localhost 2551 """

  assert(args.length == 3, argumentsError)

  // Override the configuration of the port when specified as program argument

  val hostname = args(1)
  val port = args(2).toInt

  val config = ConfigFactory.parseString(conf.replaceAll("%hostname%", hostname)
    .replaceAll("%port%", port.toString)).withFallback(ConfigFactory.load(args(0)))

  val cassandraConfig = config.getConfig("akka-cassandra.main.db.cassandra")
  val cassandra = new CassandraDB(cassandraConfig)

  val system = ActorSystem("ClusterSystem", config)
  val mainActor = system.actorOf(ActivityActor.props(cassandra), name = "activity-actor")

  // mainActor ! CreateSchema()
  // mainActor ! CreateTable()

  val counter = Random.nextInt(1000000000)

  for (userId <- 1 + counter to 10 + counter) {
    {

      val customerId = userId * 1000

      val activity = Activity(

        /*01*/ customerId.toString,
        /*02 Period supplied by now() function*/
        /*03*/ "Firefox/Agent",
        /*04*/ "Medical Doctor",
        /*05*/ "English",
        /*06*/ "MacOS",
        /*07*/ "GET",
        /*08*/ "Metastasis Cancer",
        /*09*/ randomAlphaNumericString(16),
        /*10*/ "Surgery",
        /*11*/ "true",
        /*12*/ "http://52.8.146.205:8983/solr/health-search",
        /*13*/ userId.toString(),
        /*14*/ "63.110.224.66",
        /*15*/ Random.nextInt(1000).toString,
        /*16*/ Random.nextInt(1000).toString
      )

      mainActor ! AddActivity(activity)

    }

    /**
     * Generate Random String
     */
    def randomAlphaNumericString(length: Int): String = {
      val chars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
      randomStringFromCharList(length, chars)
    }

    def randomStringFromCharList(length: Int, chars: Seq[Char]): String = {
      val sb = new StringBuilder
      for (i <- 1 to length) {
        val randomNum = util.Random.nextInt(chars.length)
        sb.append(chars(randomNum))
      }
      sb.toString
    }
  }

}

