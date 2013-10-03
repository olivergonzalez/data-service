name := "dataservice"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.1"

resolvers ++= Seq("scala-tools.org" at "https://oss.sonatype.org/content/groups/scala-tools/")

libraryDependencies ++= Seq("org.scala-lang" % "scala-library" % "2.10.1",
    "net.databinder.dispatch" % "dispatch-core_2.10" % "0.11.0",
    "org.mongodb" % "casbah-core_2.10" % "2.5.0",
    "org.scalatest" % "scalatest_2.10" % "1.9.1",
    "junit" % "junit" % "4.11")

