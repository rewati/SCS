name := "SCS"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.typesafe.play" % "play_2.11" % "2.5.6",
  ws
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
