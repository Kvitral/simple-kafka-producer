package ru.kvitral

import scala.concurrent.duration.{Duration, FiniteDuration}

object AppConfigUtils {

  implicit def convertDurationToFiniteDuration(d: java.time.Duration): FiniteDuration = Duration.fromNanos(d.toNanos)

}
