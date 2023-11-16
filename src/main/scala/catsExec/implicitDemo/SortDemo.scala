package catsExec.implicitDemo

import cats.Order
import catsExec.implicitDemo.PersonAgeAndNameOrder.ordering

import java.time.Instant
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

object SortDemo {

  def main(args: Array[String]): Unit = {
    val value = List(Person("zeyan", 10, true), Person("du", 18, false), Person("du", 10, false))

    println(value.sorted)

    // 高阶函数sortedBy，按什么排序，怎么排序, 按时间降序需要加reverse
    val sortedTime = List(
      (Instant.parse("2020-12-01T12:12:12Z"), Some(Instant.parse("2020-12-06T12:12:12Z"))),
      (Instant.parse("2021-12-03T12:12:12Z"), Some(Instant.parse("2020-12-07T12:12:12Z"))),
      (Instant.parse("2021-12-01T12:12:12Z"), None)
    ).sortBy(tuple => (tuple._2.getOrElse(Instant.MAX), tuple._1))(
      Ordering[(Instant, Instant)].reverse
    )

    println(sortedTime)
  }
}
