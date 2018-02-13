package ru.kvitral

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

import scala.util.Random

object KafkaProducer extends App {

  //stream initialization
  initSink(ConfigFactory.load)

  def initSink(config: Config): Unit = {
    implicit val system: ActorSystem = ActorSystem("kafka-producer")
    implicit val materializer: ActorMaterializer = ActorMaterializer.create(system)

    val appConfig = new AppConfig(config)

    val producerSettings = ProducerSettings(system, new ByteArraySerializer, new StringSerializer)
      .withBootstrapServers(appConfig.bootstrapServers)

    /*
    * Generate random number between 0 and random-upper-bound every tick
    * Converting it to String and sink it to kafka topic
    * */
    val done = Source.tick(appConfig.streamInitialDelay, appConfig.streamInterval, () => Random.nextInt(appConfig.randomUpperBound))
      .map(_.apply())
      .map(_.toString)
      .map { elem =>
        new ProducerRecord[Array[Byte], String](appConfig.sinkTopic, elem)
      }
      .runWith(Producer.plainSink(producerSettings))


    /*
       Here how u can catch some event on stream completion

       done.onComplete {
       case Failure(ex) => throw ex
       case Success(ex) => println("success")
     }*/
  }


}
