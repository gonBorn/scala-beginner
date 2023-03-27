package catsExec.FoldableDemo

import cats.Foldable

object FoldableDemo {

  def main(args: Array[String]): Unit = {
    // standard library
    List(1, 2, 3).foldLeft("Nil")((accum, item) => s"$item then $accum")

    Foldable[List].nonEmpty(List(1))
    Foldable[List].find(List(1, 2, 4))(_ % 2 == 0)
    Foldable[List].combineAll(List(1, 2, 4))

    // foldMap用用户提供的func组合
    // res: 123
    Foldable[List].foldMap(List(1, 2, 3))(_.toString)

    // 支持遍历嵌套的类型
    // res: 21
    val ints = List(Vector(1, 2, 3), Vector(4, 5, 6))
    println((Foldable[List] compose Foldable[Vector]).combineAll(ints))
  }

}
