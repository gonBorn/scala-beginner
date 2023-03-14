package catsExec.Applicative

import cats.implicits.catsSyntaxSemigroup
import cats.{Monoid, Semigroupal}
import cats.syntax.apply._
import cats.instances.invariant._

object SemigroupalDemo {
  case class Password(p1: Int, P2: Int, P3: Int)

  case class Pet(name: String,
                 yearOfBirth: Int,
                 favoriteFoods: List[String])

  val petToTuple: Pet => (String, Int, List[String]) =
    pet => (pet.name, pet.yearOfBirth, pet.favoriteFoods)

  implicit val catMonoid: Monoid[Pet] = (
    Monoid[String], Monoid[Int], Monoid[List[String]]
    ).imapN(Pet.apply)(petToTuple)

  trait SemigroupalDefine[F[_]] {
    // semigroupal包含了组合多个context的概念
    // 我们fa与fb无关，我们并不在意这两个函数的执行顺序
    def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
  }

  def main(args: Array[String]): Unit = {
    println(Semigroupal[Option].product(Some(123), Some("aaabc")))
    //如果有一个是none，最终结果是none
    println(Semigroupal[Option].product(Some(123), None))

    println(Semigroupal.tuple3(Option(1), Option(2), Option(3)))

    println(Semigroupal.map2(Option(1), Option(2))(_ + _))

    // from cats.syntax.apply._
    println((Option(1), Option(2), Option(3)).tupled)
    // Some(Password(1,2,3))
    // mapN 使用了Semigroupal将数值从Option中抽出
    println((Option(1), Option(2), Option(3)).mapN(Password.apply))

    println(Pet("Garfield", 1978, List("Lasagne")) |+| Pet("Garfield", 1978, List("Lasagne")))

    /**
     * List and Either 都是Monads
     * Semigroupal 作用在它们上的效果和预期不太一样
     */
    println(
      Semigroupal[List].product(List(1, 2), List(3, 4)))
  }
}
