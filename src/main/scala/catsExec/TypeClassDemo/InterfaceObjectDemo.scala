package catsExec.TypeClassDemo

import catsExec.TypeClassDemo.AreaInstances.rectangleArea
import catsExec.TypeClassDemo.JsonWriterInstances.jsonWriter

// type class interface, 把我们在type class instances的实现暴露给用户
// 有interface object和interface syntax两种方式
// 这个文件主要举例interface object
// interface object: 最简单的方式，把方法放在单例对象中
// 一个泛型方法，以type class instances 为隐式入参

object ShapeArea {
  def areaOf[A](a: A)(implicit shape: Area[A]): Double = shape.area(a)
}

object JsonDemo {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value)
}

object Test {
  def main(args: Array[String]): Unit = {
    ShapeArea.areaOf(Rectangle(1, 2))
    JsonDemo.toJson(Rectangle(1, 2))
  }
}
