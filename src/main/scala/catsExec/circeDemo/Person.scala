package catsExec.circeDemo

import io.circe.syntax.EncoderOps
import io.circe.{Encoder, Json}

case class Person(name: String, age: Int, pet: Pet = Cat, attr: List[Int] = Nil)

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

    val people = List(Person("z", 1), Person("d", 12), Person("dsd", 2), Person("dsd", 13), Person("dsd", 1))

    val value = people.groupBy(_.name)

    val (unique, duplicate) = value.values.partition(_.length == 1)

    println(unique.flatten.toList)
    println(duplicate.flatten.toList)

  }
}

