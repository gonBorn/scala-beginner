package catsExec.Applicative
import cats.syntax.either._

import scala.util.control.NonFatal

object EitherDemo {
  // catchOnly 会捕获指定的异常
  def parseInt(str: String): Either[String, Int] =
    Either.catchOnly[NumberFormatException](str.toInt)
      .leftMap(_ => s"Could not read string: $str")

  // 如果想捕获多个异常
  def advancedParseInt(str: String): Either[String, Int] =
    Either.catchNonFatal(str.toInt)
      .leftMap {
        case _: NumberFormatException => s"Could read $str"
        case _: NullPointerException => "Input is null"
        case NonFatal(ex) => s"An unexpected error occurred: ${ex.getMessage}"
      }

  def main(args: Array[String]): Unit = {
    val stringOrInt = for {
      a <- parseInt("a")
      b <- parseInt("b")
      c <- parseInt("c")
    } yield (a + b + c)

    println(stringOrInt)
  }
}
