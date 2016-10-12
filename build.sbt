name := "SCS"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.typesafe.play" % "play_2.11" % "2.5.6",
  "com.sksamuel.elastic4s" %% "elastic4s-core" % "2.3.0",
  ws
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
