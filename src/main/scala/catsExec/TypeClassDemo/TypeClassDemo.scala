package catsExec.TypeClassDemo

import cats.Show
import AreaInstances.rectangleArea
import InterfaceSyntaxDemo.ShapeAreaOps
import cats.implicits.toShow

import java.util.Date

object TypeClassDemo extends App {
  ShapeArea.areaOf(Rectangle(1, 2))
  Rectangle(2, 3).areaOf

  // cats提供了show的type class和interface，我们只需要实现type class instances
  implicit val showRectangle: Show[Rectangle] = a => s"width is ${a.width} and length is ${a.length}"

  println(Show[Rectangle].show(Rectangle(2, 3)))

  implicit val customShow: Show[Date] = Show.show(date => s"${date.getTime}ms since the epoch")

  println(new Date().show)
}
