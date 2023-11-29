package catsExec.circeDemo.decoder

import catsExec.circeDemo
import catsExec.circeDemo.{Person, Pet}
import io.circe.{Decoder, HCursor}
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

  implicit val decoder3: Decoder[Person] = (c: HCursor) => {
    for {
      age <- c.get[Int]("age")
      name <- c.get[String]("name")
      pet <- c.get[Pet]("pet")
      // 但数组为空时，比起option，nil可能更好
      attr <- c.getOrElse[List[Int]]("attr")(Nil)
    } yield Person(name, age, pet, attr)
  }

  def main(args: Array[String]): Unit = {
    println(personJson.as[Person](decoder))
    println(personJson.as[Person](decoder2))
    println(personJson.as[Person](decoder3))
  }
}
