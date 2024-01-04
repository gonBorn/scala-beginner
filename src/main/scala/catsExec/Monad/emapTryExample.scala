package catsExec.Monad

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import io.circe.literal.JsonStringContext

import java.time.Instant
import java.time.format.DateTimeFormatter
import scala.util.Try

object emapTryExample {
  final case class Stu2(name: String, time: Instant, optionTime: Option[Instant])

  object Stu2 {
    implicit val decoder: Decoder[Stu2] = deriveDecoder

    implicit val instantDecoder: Decoder[Instant] = Decoder.decodeString.emapTry(c =>
      Try {
        Instant.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(c))
      }
    )
  }

  def main(args: Array[String]): Unit = {
    val stuJson =
      json"""
        {
           "name": "111",
           "time": "1111"
        }
      """

    println(stuJson.as[Stu2])
  }
}
