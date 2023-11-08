package catsExec.implicitDemo

import cats.Order
import catsExec.implicitDemo.PersonAgeAndNameOrder.ordering
case class Person(name: String, age: Int, iaActive: Boolean)

object PersonAgeOrder {
  // implicit val 推荐写类型
  implicit val ordering: Ordering[Person] = Ordering.fromLessThan(_.age < _.age)
}

object PersonNameOrder {
  implicit val ordering: Ordering[Person] = Ordering.fromLessThan(_.name < _.name)
}

object PersonAgeAndNameOrder {
  implicit val ordering: Ordering[Person] = Order.by[Person, (Int, String, Boolean)](p => (p.age, p.name, p.iaActive)).toOrdering
}

object ImplicitValDemo {

  def main(args: Array[String]): Unit = {
    val value = List(Person("zeyan", 10, true), Person("du", 18, false), Person("du", 10, false))

    println(value.sorted)
  }
}
