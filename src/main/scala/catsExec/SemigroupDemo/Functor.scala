package catsExec.SemigroupDemo

import cats.Functor

object Functor extends App {

  trait MyFunctor[F[_]] {
    def map[A, B](initialValue: F[A])(f: A => B): F[B]
  }

  def do10xList(list: List[Int]): List[Int] = list.map(_ * 10)

  // generalize
  def do10x[F[_]](container: F[Int])(implicit functor: Functor[F]): F[Int] = functor.map(container)(_ * 10)

  println(do10x(List(2, 3, 4)))
  println(do10x(Option(2)))
}
