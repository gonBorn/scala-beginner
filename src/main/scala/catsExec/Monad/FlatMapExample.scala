package catsExec.Monad

import cats.data.NonEmptyList
import cats.implicits._
import io.circe.literal.JsonStringContext
import io.circe.{Decoder, DecodingFailure}

import java.time.Instant
import java.time.format.DateTimeFormatter
import scala.util.Try

object FlatMapExample {
  final case class Stu(name: String, attr: NonEmptyList[String], time: Instant, optionTime: Option[Instant])

  object Stu {
    implicit val decoder: Decoder[Stu] = Decoder.instance(c =>
      for {
        // c.get的返回类型是Either
        name <- c.get[String]("name")
        time <- c.get[String]("time").flatMap(
          parseInstantStr(_).leftMap(_ => DecodingFailure("time error", c.history)))
        optionTime <- c.get[Option[String]]("optionTime").flatMap({
          case None => Right(None)
          case Some(maybe) => parseInstantStr(maybe).fold(_ => Left(DecodingFailure("time error", c.history)), r => Right(Some(r)))
        })
      } yield Stu(name, NonEmptyList("111", Nil), time, optionTime)
    )
  }

  def main(args: Array[String]): Unit = {
    val stu = Stu("111", NonEmptyList("pro", Nil), Instant.now(), None)
    val value: List[String] = List(stu, stu).flatMap(a => a.attr.toList)
    println(value)
    println(stuJson.as[Stu])
  }

  // string转instant可能抛异常有副作用
  private def parseInstantStr(instantStr: String): Either[Throwable, Instant] = Try {
    Instant.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(instantStr))
  }.toEither

  val stuJson =
    json"""
       {
          "name": "111",
          "time": "1111"
       }
     """
}
