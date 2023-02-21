package catsExec.TypeClassDemo

// type class
trait Area[A] {
  def area(a: A): Double
}
