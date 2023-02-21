package catsExec.SemigroupDemo

import cats.Monad

object MonadsDemo extends App {
  /**
   * Monad pattern
   * - wrap a value into a M value
   * - the flatMap mechanism
   */
  trait MyMonads[M[_]] {
    def pure[A](value: A): M[A]
    def flatMap[A, B](ma: M[A])(f: A=>M[B]): M[B]
  }

  val numbers = List(1, 2, 3)
  val chars = List('a', 'b', 'c')

  // create all combinations of (number, char)

  val value: List[(Int, Char)] = numbers.flatMap(n => chars.map(c => (n, c)))
  for {
    n <- numbers
    c <- chars
  } yield (n, c)

//  import cats.instances.list._
  val listMonad: Monad[List] = Monad[List]
  println(listMonad.pure(3))
  println(listMonad.flatMap(List(1, 2, 3, 4))(a => List(a, a + 1)))

  def getPair[M[_], A, B](ma: M[A], mb: M[B])(implicit monad: Monad[M]): M[(A, B)] =
    monad.flatMap(ma)(a => monad.map(mb)(b => (a, b)))

  println(getPair(numbers, chars))
}
