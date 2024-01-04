package catsExec.attempt

import cats.effect.IO
import cats.effect.unsafe.implicits.global

object AttemptExample {

  private def isEven(num: Int): IO[Boolean] = num match {
    case 0 => IO(false)
    case x if x % 2 == 0 => IO(true)
    case _ => IO.raiseError(new Error("not even"))
  }

  def main(args: Array[String]): Unit = {
    // M[Either[Throwable, Boolean]]
    println(isEven(1).attempt.map(x => Either.cond(x.exists(identity), (), new Error("not good"))).unsafeRunSync())
    println(isEven(0).attempt.map(x => Either.cond(x.exists(identity), (), new Error("not good"))).unsafeRunSync())
    println(isEven(2).attempt.map(x => Either.cond(x.exists(identity), (), new Error("not good"))).unsafeRunSync())
  }
}
