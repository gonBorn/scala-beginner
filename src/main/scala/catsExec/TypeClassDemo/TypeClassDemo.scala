package catsExec.TypeClassDemo

import cats.Show
import AreaInstances.rectangleArea
import ShapeAreaSyntax.ShapeAreaOps
import cats.implicits.toShow

import java.util.Date

// https://www.baeldung.com/scala/cats-intro
// 类似spring的autowire
object TypeClassDemo extends App {
  ShapeArea.areaOf(Rectangle(1, 2))
  Rectangle(2, 3).areaOf

  // cats
  implicit val showRectangle: Show[Rectangle] = a => s"width is ${a.width} and length is ${a.length}"

  println(Show[Rectangle].show(Rectangle(2, 3)))

  implicit val customShow: Show[Date] = Show.show(date => s"${date.getTime}ms since the epoch")

  println(new Date().show)
}
