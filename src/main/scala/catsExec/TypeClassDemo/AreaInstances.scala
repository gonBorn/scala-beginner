package catsExec.TypeClassDemo

case class Rectangle(width: Double, length: Double)
case class Circle(radius: Double)

// type class instances
object AreaInstances {
  implicit val rectangleArea: Area[Rectangle] = a => a.width * a.length
  implicit val circleArea: Area[Circle] = a => a.radius * a.radius * Math.PI
}
