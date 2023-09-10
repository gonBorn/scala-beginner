package catsExec.TypeClassDeduction

import catsExec.TypeClassDeduction.Age.AgeOps

case class Age2(value: Int)

case class Person(age: Age2)

object Age {
  // implicit 必须定义在object或trait中
  implicit class AgeOps(v: Age2) {
    def add(delta: Int): Age2 = Age2(v.value + delta)
  }
}

object Person {
  implicit class PersonOps(v: Person) {
    def add(delta: Int): Person = Person(v.age.add(delta))
  }
}

object Main extends App {
  // Age(0) 会被看做 AgeOps(Age2(0))
  println(Age2(0).add(2))

  //这里展现出了痛点2，如果隐式类中的方法签名改了，这个方法就挂了
  def processAdd[A](a: A, delta: Int): A = {
    a match {
      case x: Age2 => x.add(delta).asInstanceOf[A]
      case x: Person => x.add(delta).asInstanceOf[A]
      case _ => throw new Exception(s"cannot process")
    }
  }
}
