package catsExec.FoldableDemo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TraverseDemo {

  def getUptime(hostname: String): Future[Int] =
    Future(hostname.length * 60)

  val hostnames: List[String] = List("1231.com", "234.com")

  def main(args: Array[String]): Unit = {
    // standard library
    // 1. traverse
    // starts with List[A]
    // provide A -> Future[B]
    // ends with Future[List[B]]
    val future: Future[List[Int]] = Future.traverse(hostnames)(getUptime)
    println(future)

    // 2. sequence
    // start with a List[Future[A]];
    // end up with a Future[List[A]].
    println(Future.sequence(List(Future(1), Future(2))))
  }
}
