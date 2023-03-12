package catsExec.SemigroupDemo

import cats.Functor
import cats.implicits.toFunctorOps
import cats.instances.list._

// List Option Future Either 等都可看着Functor
object FunctorDemo extends App {

  /**
   * 规则：
   * 1. a=>a 不发生变化
   * 2. x => g(f(x)) == x => f(x) => g(y)
   */
  trait MyFunctor[F[_]] {
    // 接收俩入参，1.F[A] 2.transform func
    def map[A, B](initialValue: F[A])(f: A => B): F[B]
  }

  def do10xList(list: List[Int]): List[Int] = list.map(_ * 10)

  // generalize
  def do10x[F[_]](container: F[Int])(implicit functor: Functor[F]): F[Int] = functor.map(container)(_ * 10)

  println(do10x(List(2, 3, 4)))
  println(do10x(Option(2)))

  println(Functor[List].map(List(2, 3, 4))(_ * 2))

  // lift(先定义方法)
  val function: List[Int] => List[Int] = Functor[List].lift((x: Int) => x * 2)
  function(List(2, 3, 4))

  val func1 = (x: Int) => x * 2
  val func2 = (x: Int) => x + 2
  val func3 = (x: Int) => x
  val func4 = func1.map(func2).map(func3)
}
