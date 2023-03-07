package catsExec.TypeClassDemo

case class Rectangle(width: Double, length: Double)
case class Circle(radius: Double)

// type class instances
// 为不同的类实现了type class，用implicit标记实现方法
// 类似于为java类实现了不同的interface，扩展了这个类的行为，
// 如为不同的形状提供了计算面积的行为，为不同类提供了转化为json的能力
object AreaInstances {
  implicit val rectangleArea: Area[Rectangle] = a => {
    val area = a.width * a.length
    println(s"rectangle's area is $area")
    area
  }
  implicit val circleArea: Area[Circle] = a => a.radius * a.radius * Math.PI
}

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] = value => JsString(value)
  implicit val jsonWriter: JsonWriter[Rectangle] =
    value => JsObject(
      Map(
        "width" -> JsString(value.width.toString),
        "length" -> JsString(value.length.toString)
      ))
}
