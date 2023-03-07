package catsExec.TypeClassDemo

import cats.Show
import AreaInstances.rectangleArea
import InterfaceSyntaxDemo.ShapeAreaOps
import cats.implicits.toShow
import catsExec.TypeClassDemo.JsonWriterInstances.stringWriter

import java.util.Date

object TypeClassDemo extends App {
  ShapeArea.areaOf(Rectangle(1, 2))
  Rectangle(2, 3).areaOf

  // cats提供了show的type class和interface，我们只需要实现type class instances
  implicit val showRectangle: Show[Rectangle] = a => s"width is ${a.width} and length is ${a.length}"

  println(Show[Rectangle].show(Rectangle(2, 3)))

  implicit val customShow: Show[Date] = date => s"${date.getTime}ms since the epoch"

  println(new Date().show)

  // scala标准库提供了implicitly来召唤隐式变量，可以在debug的时候使用确保编译器找到了正确的变量
  println(implicitly[JsonWriter[String]])

  // implicit 必须定义在object或trait中

  println(1.show)
}
