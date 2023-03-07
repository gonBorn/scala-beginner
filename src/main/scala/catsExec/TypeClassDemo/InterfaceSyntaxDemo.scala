package catsExec.TypeClassDemo

import catsExec.TypeClassDemo.AreaInstances.rectangleArea
import catsExec.TypeClassDemo.JsonWriterInstances.jsonWriter

// type class interfaces2: interface syntax
// 为现有类扩展方法
object InterfaceSyntaxDemo {
  implicit class ShapeAreaOps[A](a: A) {
    def areaOf(implicit shape: Area[A]): Double = shape.area(a)
  }

  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
  }

  def main(args: Array[String]): Unit = {
    val rectangle = Rectangle(1, 2)
    println(rectangle.areaOf)
    println(rectangle.toJson)
  }
}
