package catsExec.circeDemo.decoder

import catsExec.circeDemo.Person
import io.circe.literal.JsonStringContext
import io.circe.generic.auto._

object AutoPerson {
  val personJson =
    json"""
     {
         "name": "zeyan",
         "age": 10,
         "pet": "cat"
     }
     """

  def main(args: Array[String]): Unit = {
    println(personJson.as[Person])
  }
}
