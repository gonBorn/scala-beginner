package catsExec.TypeClassDemo

// 给现有类扩展方法
object ShapeAreaSyntax {
  implicit class ShapeAreaOps[A](a: A) {
    def areaOf(implicit shape: Area[A]): Double = shape.area(a)
  }
}
