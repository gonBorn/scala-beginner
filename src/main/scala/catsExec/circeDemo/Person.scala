package catsExec.circeDemo

import io.circe.syntax.EncoderOps
import io.circe.{Encoder, Json}

case class Person(name: String, age: Int, pet: Pet)

object Person {
  implicit val encoder: Encoder[Person] = p => {
    Json.obj(
      "outer" -> Json.obj(
        "name" -> p.name.asJson,
        "age" -> p.age.asJson,
        "pet" -> p.pet.asJson,
      )
    )
  }

  def main(args: Array[String]): Unit = {
    println(Person("zeyan", 10, Cat).asJson)
  }
}

