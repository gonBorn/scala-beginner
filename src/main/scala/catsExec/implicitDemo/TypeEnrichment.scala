package catsExec.implicitDemo

import scala.annotation.tailrec

object TypeEnrichment {
  implicit class StringEnrichment(str: String) {
    def encrypt(implicit cypherDistance: Int): String = str.map(s => (s + cypherDistance).toChar)

    def greet(): Unit = println(s"Hi, ${str}")
  }

  implicit val cypherDistance: Int = 4

  implicit class IntEnrichment(num: Int) {
    def times(f: () => Unit): Unit = {
      @tailrec
      def invoke(num: Int): Unit = {
        if (num > 0) {
          f()
          invoke(num - 1)
        }
      }
      invoke(num)
    }

    def *[T](list: List[T]): List[T] = {
      def concatenate(n: Int): List[T] = {
        if (n <= 0) List()
        else concatenate(n - 1) ++ list
      }

      concatenate(num)
    }
  }

  def main(args: Array[String]): Unit = {
    println("zeyan".encrypt)
    "zeyan".greet()

    3 times {()=>println("Hi Zeyan")}

    println(3 * List(1, 2))
  }
}
