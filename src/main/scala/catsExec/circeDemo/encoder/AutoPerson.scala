package catsExec.circeDemo.encoder

import catsExec.circeDemo.{Cat, Person}
import io.circe.literal.JsonStringContext
import io.circe.syntax.EncoderOps

object AutoPerson {
  val personJson =
    json"""
     {
         "name": "zeyan",
         "age": 10,
         "pet": "cat"
     }
     """

  val person = Person("zeyan", 10, Cat)

  def main(args: Array[String]): Unit = {
    println(person.asJson equals personJson)
  }
}
