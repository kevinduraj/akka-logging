package com.mdinsider.infrastructure

import com.datastax.driver.core.{ ProtocolOptions, Cluster }
import com.mdinsider.domain.{ Activity, ActivityRepository }
import com.typesafe.config.Config
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class CassandraDB(config: Config) extends ActivityRepository {

  private def connect() = {

    val port = config.getInt("port")
    val hosts = config.getStringList("hosts")
    val keyspace = config.getString("keyspace")

    import scala.collection.JavaConversions._

    val cluster: Cluster = Cluster.builder().
      addContactPoints(hosts: _*).
      withCompression(ProtocolOptions.Compression.SNAPPY).
      withPort(port).
      build()

    (cluster, cluster.connect(), keyspace)
  }

  val (cluster, session, keyspace) = connect()

  def close(): Unit = {
    session.close()
    cluster.close()
  }

  /**
   * Query Big Log Table
   */
  def getQueryResult(): Unit = {

    println(session.getCluster().getClusterName + " connection successful\n")

    val list = session.execute("SELECT * FROM activity.big_log").all()

    val size = list.size()
    var counter = 0;
    for (x <- 0 to size - 1) {
      println(counter + ":\t"
        + list.get(x).getString("customer_id") + "\t"
        + list.get(x).getString("period") + "\t"
        + list.get(x).getString("agent"))
      counter += 1;
    }
  }

  /**
   * Disconnect from Cassandra
   */
  def disconnect(str: String) {
    cluster.close()
    session.close()
    println("\n" + str + " successfully closed")
  }

  /**
   * Generate Random String
   */
  private def randomAlphaNumericString(length: Int): String = {
    val chars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
    randomStringFromCharList(length, chars)
  }

  private def randomStringFromCharList(length: Int, chars: Seq[Char]): String = {
    val sb = new StringBuilder
    for (i <- 1 to length) {
      val randomNum = util.Random.nextInt(chars.length)
      sb.append(chars(randomNum))
    }
    sb.toString
  }

  /**
   * Create Cassandra Keyspace
   */
  def create_keyspace(): Future[Unit] = Future {

    val SQL = "CREATE KEYSPACE activity " +
      "WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '2'}  " +
      "AND durable_writes = true;"

    println(SQL)
    session.execute(SQL)

  }

  /**
   * Create Cassandra Table
   */
  def create_table(): Future[Unit] = Future {

    val SQL = "CREATE TABLE activity.big_log ( " +
      /*01*/ "customer_id text, " +
      /*02*/ "period timeuuid, " +
      /*03*/ "agent text, " +
      /*04*/ "doctor text, " +
      /*05*/ "language text, " +
      /*06*/ "os text, " +
      /*07*/ "request_type text, " +
      /*08*/ "search_term text, " +
      /*09*/ "session_id text, " +
      /*10*/ "specialty text, " +
      /*11*/ "success text, " +
      /*12*/ "url text, " +
      /*13*/ "user_id text, " +
      /*14*/ "user_ip text, " +
      /*15*/ "user_lat text, " +
      /*16*/ "user_long text, " +
      "PRIMARY KEY (customer_id, period) " +
      ") WITH CLUSTERING ORDER BY (period DESC)"

    println(SQL)
    session.execute(SQL)

  }

  def insertRecord(activity: Activity): Future[Unit] = Future {

    val SQL = "INSERT INTO activity.big_log ( " +
      /*------------------ NAMES ---------------------*/
      /*01*/ "customer_id , " +
      /*02*/ "period , " +
      /*03*/ "agent , " +
      /*04*/ "doctor , " +
      /*05*/ "language , " +
      /*06*/ "os , " +
      /*07*/ "request_type , " +
      /*08*/ "search_term , " +
      /*09*/ "session_id , " +
      /*10*/ "specialty , " +
      /*11*/ "success , " +
      /*12*/ "url , " +
      /*13*/ "user_id , " +
      /*14*/ "user_ip , " +
      /*15*/ "user_lat , " +
      /*16*/ "user_long  " +
      " ) VALUES ('     " +
      /*------------------ VALUES ---------------------*/
      /*01*/ activity.customer_id + "', " +
      /*02*/ "now(), '" +
      /*03*/ activity.agent + "', '" +
      /*04*/ activity.doctor + "', '" +
      /*05*/ activity.language + "', '" +
      /*06*/ activity.os + "', '" +
      /*07*/ activity.request_type + "', '" +
      /*08*/ activity.search_term + "', '" +
      /*09*/ activity.session_id + "', '" +
      /*10*/ activity.specialty + "', '" +
      /*11*/ activity.success + "', '" +
      /*12*/ activity.url + "', '" +
      /*13*/ activity.user_id + "', '" +
      /*14*/ activity.user_ip + "', '" +
      /*15*/ activity.user_lat + "', '" +
      /*16*/ activity.user_long + "')"
    /*------------------- END -----------------------*/
    println(SQL)
    session.execute(SQL)
  }
}
