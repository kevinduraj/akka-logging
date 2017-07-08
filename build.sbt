name := "akka-logging"

version := "1.0"

scalaVersion := "2.11.6"

crossScalaVersions := Seq("2.10.4", "2.11.6")

lazy val root = Project("akka-logging", file("."))
  .configs(IntegrationTest)
  .settings(
    scalariformSettings ++
      CommonSettings.settings ++
      Defaults.itSettings: _*).settings(
//    javaOptions in Universal ++= Seq("-J-agentlib:TakipiAgent")  // enable this when takipi is installed
  ).enablePlugins(JavaServerAppPackaging)


libraryDependencies ++= Seq(
  Library.akkaActor,
  Library.akkaCluster,
  Library.akkaContrib,
  Library.akkaLog4j,
  Library.akkaRemote,
  Library.akkaTestkit,
  Library.cassandraDriver,
  Library.akkaCluster,
  Library.config,
  Library.logBack,
  Library.scalacheck,
  Library.scalaLogging,
  Library.scalaTest,
  Library.snappy
)
