import sbt.Keys._
import sbt._


/**
 * Defines settings for the projects:
 */
object CommonSettings {

  val settings: Seq[Def.Setting[_]] =
      Seq(
//        (unmanagedResourceDirectories in Compile)  <+= baseDirectory( _ / "src/main/scala" ),
        organization := "com.mdinsider",
        scalaVersion := "2.11.6",
        scalacOptions ++= Seq("-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation", "-Xlint", "-unchecked", "-Ywarn-dead-code", "-feature", "-language:postfixOps"),
        javacOptions  ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-Xlint:deprecation", "-Xlint:-options"),
        fork := true,
        resolvers := ResolverSettings.resolvers
      )

}
