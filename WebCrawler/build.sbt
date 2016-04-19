name := "WebCrawler"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(

  "org.apache.spark" %% "spark-core" % "1.6.1",
  // testing
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",

  "org.scalactic" %% "scalactic" % "2.2.6"

  )

