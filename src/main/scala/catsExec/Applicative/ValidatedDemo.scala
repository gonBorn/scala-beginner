package catsExec.Applicative

import cats.data.Validated
import cats.implicits.{catsSyntaxApplicativeErrorId, catsSyntaxApplicativeId, catsSyntaxValidatedId}
import cats.syntax.validated._

object ValidatedDemo {
  /**
   * Validated.Valid == Either.Right
   * Validated.Invalid == Either.Left
   */

  // 如何创建实例
  // 1.
  Validated.Valid(123)
  Validated.Invalid(List("error"))

  // 2.
  Validated.valid[List[String], Int](123)
  Validated.invalid[List[String], Int](List("error"))

  // 3. syntax
  123.valid[List[String]]
  List("error").invalid[Int]

  // 4. pure and raiseError
  type ErrorsOr[A] = Validated[List[String], A]
  123.pure[ErrorsOr]
  List("error").raiseError[ErrorsOr, Int]

  // 5.
  Validated.catchOnly[NumberFormatException]("foo".toInt)
  Validated.catchNonFatal("foo".toInt)
  Validated.fromEither(Left("123"))
  Validated.fromOption(None, "none")
}
