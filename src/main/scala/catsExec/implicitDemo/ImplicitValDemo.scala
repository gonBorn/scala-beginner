package catsExec.implicitDemo

import cats.Order
import catsExec.implicitDemo.PersonAgeAndNameOrder.ordering
case class Person(name: String, age: Int)

object PersonAgeOrder {
  // implicit val 推荐写类型
  implicit val ordering: Ordering[Person] = Ordering.fromLessThan(_.age < _.age)
}

object PersonNameOrder {
  implicit val ordering: Ordering[Person] = Ordering.fromLessThan(_.name < _.name)
}

object PersonAgeAndNameOrder {
  implicit val ordering: Ordering[Person] = Order.by[Person, (Int, String)](p => (p.age, p.name)).toOrdering
}

object ImplicitValDemo {

  def main(args: Array[String]): Unit = {
    val value = List(Person("zeyan", 10), Person("du", 18), Person("du", 10))

    println(value.sorted)
  }
}
