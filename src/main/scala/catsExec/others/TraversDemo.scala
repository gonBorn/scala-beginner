package catsExec.others

import cats.effect.IO
import cats.implicits._

object TraversDemo {
  def main(args: Array[String]): Unit = {
    println(demo1(List(1, 2, 3, 4)).unsafeRunSync())
    println(demo2(Some(1), None))
    println(demo2(Some(1), Some(2)))
    println(demo3(Some(1), None).unsafeRunSync())
    println(demo3(Some(1), Some(2)).unsafeRunSync())
  }

  def demo1(list: List[Int]): IO[List[String]] =
    list.traverseFilter {
      case n if n % 2 == 0 => IO.pure(Some(n.toString))
      case _ => IO.pure(None)
    }

  def demo2(option1: Option[Int], option2: Option[Int]): Option[(Int, Int)] =
    (option1, option2).mapN((option1, option2) => (option1, option2))

  def demo3(option1: Option[Int], option2: Option[Int]): IO[Option[(Int, Int)]] =
    (option1, option2).traverseN((option1, option2) => IO.pure(option1, option2))

  private val ints: Set[String] = Set("123", "1233")

  println(ints("123"))
}

object TraverseExample {
  def main(args: Array[String]): Unit = {
    val numbers: List[Int] = List(1, 2, 3, 4, 5)

    val doubledNumbersList: Option[List[Int]] = numbers.traverse(doubleAndWrapInOption)

    println(doubledNumbersList)
  }

  private def doubleAndWrapInOption(n: Int): Option[Int] = {
    if (n >= 0) Some(n * 2)
    else None
  }
}
