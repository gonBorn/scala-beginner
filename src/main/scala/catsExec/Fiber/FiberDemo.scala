package catsExec.Fiber

import cats.effect.IO
import cats.effect.unsafe.implicits.global

import scala.concurrent.duration.DurationInt

object FiberDemo {
  def task(name: String): IO[Unit] =
    IO.sleep(1.second) >> IO(println(s"$name completed"))

  def main(args: Array[String]): Unit = {
    val tasks = for {
      // 并发两个task
      fiber1 <- task("Task1").start
      fiber2 <- task("Task2").start
      // 等task complete

      r1 <- fiber1.join
      r2 <- fiber2.join

      _ <- IO(println("Both tasks completed"))
    } yield (r1, r2)
    tasks.unsafeRunSync()
  }
}
