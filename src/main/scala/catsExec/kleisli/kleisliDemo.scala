package catsExec.kleisli

import cats.data.Kleisli

object kleisliDemo extends App {
  val twice: Int => Int =
    x => x * 2

  val countCats: Int => String =
    x => if (x == 1) "1 cat" else s"$x cats"

  val twiceAsManyCats: Int => String =
    twice andThen countCats // equivalent to: countCats compose twice

  println(twiceAsManyCats(1))

  // Refer to https://typelevel.org/cats/datatypes/kleisli.html
  val parse: Kleisli[Option, String, Int] =
    Kleisli((s: String) => if (s.matches("-?[0-9]+")) Some(s.toInt) else None)

  val reciprocal: Kleisli[Option, Int, Double] =
    Kleisli((i: Int) => if (i != 0) Some(1.0 / i) else None)

  val parseAndReciprocal: Kleisli[Option, String, Double] =
    reciprocal.compose(parse)
}
