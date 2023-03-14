package catsExec.Applicative

import cats.data.Validated
import cats.implicits.catsSyntaxTuple2Semigroupal
import cats.syntax.either._

object ValidatedExec {
  type FormData = Map[String, String]
  type FailFast[A] = Either[List[String], A]
  type FailSlow[A] = Validated[List[String], A]

  case class User(name: String, age: Int)

  def getValue(key: String)(data: FormData): FailFast[String] =
    data.get(key).toRight(List(s"$key field is not defined"))

  def getName = getValue("name") _

  def parseInt(key: String)(data: String): FailFast[Int] =
    Either.catchNonFatal(data.toInt)
      .leftMap(_ => List(s"$key must be an integer"))

  def nonBlank(name: String)(data: String): FailFast[String] =
    Right(data)
      .ensure(List(s"$name cannot be blank"))(_.nonEmpty)

  def nonNegative(name: String)(data: Int): FailFast[Int] = Right(data).
    ensure(List(s"$name must be non-negative"))(_ >= 0)

  def readName(data: FormData): FailFast[String] =
    getName(data).flatMap(nonBlank("name"))

  def readAge(data: FormData): FailFast[Int] =
    getValue("age")(data).
      flatMap(nonBlank("age")).
      flatMap(parseInt("age")).
      flatMap(nonNegative("age"))

  def readUser(data: FormData): FailSlow[User] =
    (
      readName(data).toValidated,
      readAge(data).toValidated
    ).mapN(User.apply)

  def main(args: Array[String]): Unit = {
    println(getName(Map("name" -> "zeyan")))

    println(readUser(Map("name" -> "zeyan", "age" -> "12")))
  }
}
