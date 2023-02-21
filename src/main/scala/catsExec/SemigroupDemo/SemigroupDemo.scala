package catsExec.SemigroupDemo

import cats.Semigroup

// combine elements of the same type
object SemigroupDemo extends App {

  println(Semigroup[Int].combine(2, 46))
  println(Semigroup[String].combine("ze", "yan"))

  println(List(1,2,3).reduce(Semigroup[Int].combine))

  // general API
  def reduceThings[T](list: List[T])(implicit semigroup: Semigroup[T]):T =
    list.reduce(semigroup.combine)

  // 可以无需展开option
  val numberOptions: List[Option[Int]] = (1 to 10).toList.map(n => Option(n))
  println(reduceThings(numberOptions))

  // support a new type
  case class Expense(id: Long, amount: Double)

  implicit val expenseSemigroup: Semigroup[Expense] = Semigroup.instance[Expense] {
    (e1, e2) => Expense(Math.max(e1.id, e2.id), e1.amount + e2.amount)
  }

  println(reduceThings(List(Expense(1, 10), Expense(2, 20))))

  // extension methods from semigroup - |+|
  import cats.syntax.semigroup._
  val anIntSum = 2 |+| 3
  val anExpense = Expense(1, 10) |+| Expense(1, 10)


  def reduceThings2[T](list: List[T])(implicit semigroup: Semigroup[T]):T =
    list.reduce(_ |+| _)
}
