package catsExec.ADT

object AdtDemo {
  sealed trait Response

  case class Result(v: Double) extends Response

  case class DivisionError(error: String, input: (Double, Double)) extends Response

  def division(v1: Double, v2: Double): Response = {
    if (v2 != 0) Result(v1/v2)
    else DivisionError("v2 cannot be zero", (v1, v2))
  }

  def main(args: Array[String]): Unit = {
    println(division(1, 2))
    println(division(1, 0))
  }

}
