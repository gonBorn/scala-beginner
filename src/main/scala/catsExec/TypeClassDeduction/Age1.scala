package catsExec.TypeClassDeduction

case class Age1(age: Int) {
  def add(inc: Int): Age1 = Age1(age + inc)
}

object Demo1 extends App {
  def add(inc: Int)(age: Age1) = Age1(inc + age.age)

  private val result = add(3)(add(2)(add(1)(Age1(0))))
  println(result)

  println(Age1(1).add(1))
}



