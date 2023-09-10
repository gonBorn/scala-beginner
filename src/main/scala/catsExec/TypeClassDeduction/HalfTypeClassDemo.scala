package catsExec.TypeClassDeduction

import catsExec.TypeClassDeduction.HalfTypeClassDemo.AddSyntax.AddOps

object HalfTypeClassDemo{
  // 我们希望这个签名不能被改
  object AddSyntax {
    implicit class AddOps[A](v: A)(implicit f: (A, Int) => A) {
      def add(delta: Int): A = f(v, delta)
    }
  }

  implicit def ageAddFunction(age: Age2, delta: Int) = Age2(age.value + delta)
  implicit def personAddFunction(person: Person, delta: Int) = Person(person.age.add(delta))

  // 但是这样我们不能再增加一个sub的方法，因为隐式调用是根据类型推断的
}



