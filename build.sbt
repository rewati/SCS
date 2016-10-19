name := "SCS"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.typesafe.play" % "play_2.11" % "2.5.6",
  "com.sksamuel.elastic4s" %% "elastic4s-core" % "2.3.0",
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  ws
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
