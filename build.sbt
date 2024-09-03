name := """tendering-test-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.14"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.4.1"
libraryDependencies += "com.h2database" % "h2" % "2.2.224"
libraryDependencies += jdbc
libraryDependencies += evolutions
