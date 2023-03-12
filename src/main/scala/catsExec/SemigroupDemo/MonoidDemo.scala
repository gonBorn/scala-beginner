package catsExec.SemigroupDemo

import cats.Monoid
import cats.implicits.catsSyntaxSemigroup

object MonoidDemo extends App {
//  trait Semigroup[A] {
//    def combine(x: A, y: A): A
//  }
//  trait Monoid[A] extends Semigroup[A] {
//    def empty: A
//  }

  // monoid需要遵守两条规则
  // 1. 满足结合律，如加法乘法满足，减法不满足
  // 2. 需要定义empty(如没有empty则可以使用semigroup类型)

  val fullName = "zeyan" |+| "du"
  println(Monoid[Option[Int]].combine(Option(22), Option(10)))

  val intMonoid: Monoid[Int] = Monoid[Int]
  println(intMonoid.combine(2, 4))
  val zero = intMonoid.empty

  // interface object
  def combineFold[T](list: List[T])(implicit monoid: Monoid[T]): T =
    list.foldLeft(monoid.empty)(_ |+| _)

  // interface syntax
  implicit class addAllOps[T](list: List[T]) {
    def addAll(implicit monoid: Monoid[T]): T = list.foldLeft(monoid.empty)(_ |+| _)
  }

  val phoneMaps: List[Map[String, Int]] = List(
    Map("zeyan1" -> 1),
    Map("Zeyan2" -> 2),
    Map("zeyan3" -> 3)
  )

  println(combineFold(phoneMaps))
  println(phoneMaps.addAll)

  case class ShoppingCart(items: List[String], total: Double)
  implicit val shoppingCart: Monoid[ShoppingCart] = Monoid.instance(
    ShoppingCart(List(), 0.0), (sc1, sc2) => ShoppingCart(sc1.items ++ sc2.items, sc1.total + sc2.total)
  )

  println(combineFold(
    List(
      ShoppingCart(List("ze", "yan"), 20),
      ShoppingCart(List("TV", "online"), 50))
    ))
}
