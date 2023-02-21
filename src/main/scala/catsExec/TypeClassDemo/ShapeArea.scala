package catsExec.TypeClassDemo

// note that we need to import
// place methods in a singleton object
object ShapeArea {
  def areaOf[A](a: A)(implicit shape: Area[A]): Double = shape.area(a)
}
