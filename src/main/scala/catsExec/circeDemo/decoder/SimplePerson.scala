package catsExec.circeDemo.decoder

import catsExec.circeDemo
import catsExec.circeDemo.{Person, Pet}
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import io.circe.literal.JsonStringContext

object SimplePerson {
  val personJson =
    json"""
     {
         "name": "zeyan",
         "age": 10,
         "pet": "cat"
     }
     """


  implicit val decoder: Decoder[Person] =
    Decoder.forProduct3[Person, String, Int, Pet]("name", "age", "pet")(circeDemo.Person(_, _, _))

  implicit val decoder2: Decoder[Person] = deriveDecoder

  def main(args: Array[String]): Unit = {
    println(personJson.as[Person](decoder))
    println(personJson.as[Person](decoder2))
  }
}
