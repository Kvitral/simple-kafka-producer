import sbt.Keys.mainClass

name := "simple-kafka-producer"

version := "0.1"

scalaVersion := "2.12.4"


val akka = Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.5.9",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.19"
)


libraryDependencies ++= akka


libraryDependencies += "com.typesafe" % "config" % "1.3.2"



lazy val main = (project in file(".")).settings(
  assemblyJarName := "kafka-producer.jar",
  mainClass := Some("ru.kvitral.KafkaProducer")
)


