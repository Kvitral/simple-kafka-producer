package ru.kvitral

import com.typesafe.config.Config

import scala.concurrent.duration.FiniteDuration


class AppConfig(config: Config) {

  import AppConfigUtils._

  val bootstrapServers: String = config.getString("kafka.bootstrap-servers")
  val sinkTopic: String = config.getString("kafka.sink-topic")
  val streamInitialDelay: FiniteDuration = config.getDuration("stream-initial-delay")
  val streamInterval: FiniteDuration = config.getDuration("stream-interval")
  val randomUpperBound = config.getInt("random-upper-bound")
}
