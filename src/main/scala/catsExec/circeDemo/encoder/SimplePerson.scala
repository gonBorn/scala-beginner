package catsExec.circeDemo.encoder

import cats.Show
import cats.implicits.toShow
import catsExec.circeDemo.{Cat, Person}
import io.circe.{Encoder, Json}
import io.circe.generic.semiauto.deriveEncoder
import io.circe.literal.JsonStringContext
import io.circe.syntax.EncoderOps

object SimplePerson {
  val personJson =
    json"""
     {
         "name": "zeyan",
         "age": 10,
         "pet": "cat"
     }
     """

  val person = Person("zeyan", 10, Cat)

  implicit val encoder: Encoder[Person] = deriveEncoder


  implicit val encoder1: Encoder[Person] = Encoder[String].contramap(_.show)

  implicit val showPerson: Show[Person] = new Show[Person] {
    override def show(t: Person): String = s"${t.name}, ${t.age}, ${t.pet}"
  }

//  implicit val customShowPerson: Show[Person] = new Show[Person] {
//    override def show(t: Person): String = {
//      val personJson: Json =
//        json"""
//        {
//          "name": ${t.name},
//          "age": ${t.age},
//          "pet": ${t.pet}
//        }
//      """
//      // Convert the JSON object to a string
//      personJson.noSpaces
//    }
//  }

  def main(args: Array[String]): Unit = {
    println(person.asJson(encoder))
    println(person.asJson(encoder1))
  }
}
