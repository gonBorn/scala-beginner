package catsExec.circeDemo.decoder

import catsExec.circeDemo
import catsExec.circeDemo.{Person, Pet}
import io.circe.Decoder
import io.circe.literal.JsonStringContext

object OuterPerson {
  val personJson =
    json"""
       {
        "outer": {
           "name": "zeyan",
           "age": 10,
           "pet": "cat"
        }
       }
       """

  implicit val decoder: Decoder[Person] = cursor =>
    for {
      name <- cursor.downField("outer").get[String]("name")
      age <- cursor.downField("outer").get[Int]("age")
      pet <- cursor.downField("outer").get[Pet]("pet")
    } yield circeDemo.Person(name, age, pet)


  private val nested: Decoder[Person] = cursor =>
    for {
      name <- cursor.get[String]("name")
      age <- cursor.get[Int]("age")
      pet <- cursor.get[Pet]("pet")
    } yield circeDemo.Person(name, age, pet)
  implicit val decoder1: Decoder[Person] = nested.prepare(_.downField("outer"))

  def main(args: Array[String]): Unit = {
    val person = personJson.as[Person](decoder)
    println(person)
    val person1 = personJson.as[Person](decoder1)
    println(person1)
  }
}
