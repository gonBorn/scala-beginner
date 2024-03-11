package catsExec.TypeClassDeduction

object TypeClassDemo {
  // 可以把函数类型包裹在trait里

  // interface
  trait AddInterface[A] {
    def add(value: A, delta: Int): A
  }

  trait SubInterface[A] {
    def sub(value: A, delta: Int): A
  }

  // syntax
  object AddSyntax {
    implicit class AddOps[A](v: A)(implicit f: AddInterface[A]) {
      def add(delta: Int): A = f.add(v, delta)
    }
  }

  object SubSyntax {
    implicit class SubOps[A](v: A)(implicit f: SubInterface[A]) {
      def sub(delta: Int): A = f.sub(v, delta)
    }
  }

  // instance
  implicit val addFunction = new AddInterface[Age2] {
    override def add(value: Age2, delta: Int): Age2 = Age2(value.value + delta)
  }

  implicit val subInterface = new SubInterface[Age2] {
    override def sub(value: Age2, delta: Int): Age2 = Age2(value.value - delta)
  }

  def main(args: Array[String]): Unit = {
    import catsExec.TypeClassDeduction.TypeClassDemo.SubSyntax.SubOps
    println(Age2(2).sub(1))

    import catsExec.TypeClassDeduction.TypeClassDemo.AddSyntax.AddOps
    println(Age2(2).add(1))
  }
}
