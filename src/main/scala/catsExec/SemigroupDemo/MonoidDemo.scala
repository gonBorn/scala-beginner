package catsExec.SemigroupDemo

import cats.Monoid
import cats.implicits.catsSyntaxSemigroup

object MonoidDemo extends App {
  // 跟 semigroup类似，但是combine时可以定义初始值

  val intMonoid: Monoid[Int] = Monoid[Int]
  println(intMonoid.combine(2, 4))
  val zero = intMonoid.empty

  def combineFold[T](list: List[T])(implicit monoid: Monoid[T]): T =
    list.foldLeft(monoid.empty)(_ |+| _)

  val phoneMaps: List[Map[String, Int]] = List(
    Map("zeyan1" -> 1),
    Map("Zeyan2" -> 2),
    Map("zeyan3" -> 3)
  )

  println(combineFold(phoneMaps))

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
