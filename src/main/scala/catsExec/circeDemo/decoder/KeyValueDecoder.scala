package catsExec.circeDemo.decoder

import io.circe.{Decoder, DecodingFailure, HCursor, Json}
import io.circe.literal.JsonStringContext
import io.circe.syntax.EncoderOps

object KeyValueDecoder {
  case class Employee(name: String, number: Option[Int])

  val listJson =
    json"""
   [{
      "key": "Name",
      "value": "zeyan"
    },
    {
      "key": "Number",
      "value": 123
    }]
   """

  val keyValueDecoder: Decoder[(String, Json)] = c =>
    for {
      key <- c.get[String]("key")
      value <- c.get[Json]("value")
    } yield (key, value)

  val employeeDecoder: Decoder[Employee] = c =>
    for {
      name <- c.get[String]("Name")
      number <- c.get[Option[Int]]("Number")
    } yield Employee(name, number)

  private def decode[T](hCursor: HCursor, employeeDecoder: Decoder[T]): Either[DecodingFailure, T] = for {
    keyValueMap <- hCursor.as[List[(String, Json)]](Decoder.decodeList(keyValueDecoder)).map {
      _.toMap.asJson
    }
    employee <- employeeDecoder.decodeJson(keyValueMap)
  } yield employee

  implicit val trueDecoder: Decoder[Employee] = decode(_, employeeDecoder)

  def main(args: Array[String]): Unit = {
    println(listJson.as[Employee])
  }
}
