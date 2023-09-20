package catsExec.DataType

import cats.effect.IO
import cats.effect.unsafe.implicits.global

object IODemo extends App {
  val value: IO[Unit] = IO {
    println("hey")
  }

  val program: IO[Unit] =
    for {
      _ <- value
      _ <- value
    } yield ()

  program.unsafeRunSync()
}
