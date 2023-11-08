package catsExec.loops

import scala.annotation.tailrec

object Loops {
  def factorial(n: Int): Int = {
    // If all recursive calls made by a function are in tail position
    // Scala automatically com- piles the recursion to iterative loops that don’t consume call stack frames for each iteration.
    @tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n-1, n * acc)

    go(n, 1)
  }
}
