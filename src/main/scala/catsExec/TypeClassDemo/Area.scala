package catsExec.TypeClassDemo

// type class

/**
 *
 * type class 就是定义了一些我们想实现的功能的一个interface或者API
 * 如
 */
trait Area[A] {
  def area(a: A): Double
}

sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json

trait JsonWriter[A] {
  def write(value: A): Json
}
