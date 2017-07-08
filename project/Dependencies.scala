import sbt._

object Version {
  val akka                 = "2.3.11"
  val akkaLog4j            = "0.2.0"
  val cassandraDriver      = "2.1.6"
  val scala                = "2.11.6"
  val scalaTest            = "2.2.5"
  val logBack              = "1.1.2"
  val scalaLogging         = "3.1.0"
  val config               = "1.3.0"
  val scalacheck           = "1.12.2"
  val snappy               = "1.1.1.3"
}

object Library {
  val akkaActor            = "com.typesafe.akka"          %% "akka-actor"                     % Version.akka
  val akkaLog4j            = "com.typesafe.akka"          %% "akka-slf4j"                     % Version.akka
  val akkaCluster          = "com.typesafe.akka"          %% "akka-cluster"                   % Version.akka
  val akkaRemote           = "com.typesafe.akka"          %% "akka-remote"                    % Version.akka
  val akkaContrib          = "com.typesafe.akka"          %% "akka-contrib"                   % Version.akka
  val akkaTestkit          = "com.typesafe.akka"          %% "akka-testkit"                   % Version.akka
  val cassandraDriver      = "com.datastax.cassandra"     % "cassandra-driver-core"           % Version.cassandraDriver
  val scalaTest            = "org.scalatest"              %% "scalatest"                      % Version.scalaTest
  val logBack              = "ch.qos.logback"             % "logback-classic"                 % Version.logBack
  val scalaLogging         = "com.typesafe.scala-logging" %% "scala-logging"                  % Version.scalaLogging
  val snappy               = "org.xerial.snappy"          % "snappy-java"           				  % Version.snappy
  val config               = "com.typesafe"                % "config"                         % Version.config
  val scalacheck           = "org.scalacheck"             %% "scalacheck"                     % Version.scalacheck
}
