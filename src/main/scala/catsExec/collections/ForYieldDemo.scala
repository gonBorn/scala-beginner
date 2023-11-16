package catsExec.collections

object ForYieldDemo {

  def main(args: Array[String]): Unit = {
    // filter
    val filteredSeq: Seq[Int] = for (x <- Seq(-2, -1, 0, 1, 2) if x > 0) yield x
    println(filteredSeq.equals(Seq(1, 2)))

    val aList = List(1, 2, 3)
    val bList = List(4, 5, 6)

    //取a中的第一个元素，遍历b中的每个元素，再取a中的第二个元素,遍历b中的每个元素
    val res = for {
      a <- aList
      b <- bList
    } yield a + b
    println(res.equals(List(5, 6, 7, 6, 7, 8, 7, 8, 9)))

    // zip将两个集合中相应的元素组成pair, 如果两个集合长度不一样，多余的元素会被忽略
    val value = aList.zip(bList)
    println(value.equals(List((1,4), (2,5), (3,6))))

    // 如果想得到相关index相加的结果
    val res2 = for {
//      x <- aList.zip(bList)
      (a, b) <- aList.zip(bList)
    } yield {
//      val (a, b) = x
      a + b
    }
    println(res2.equals(List(5, 7, 9)))

    // with index
    val indexRes = for {
      x <- aList.zipWithIndex
    } yield x
    println(indexRes.equals(List((1, 0), (2, 1), (3, 2))))

    // Map
    val map1 = Map(1 -> "s", 2 -> "d")
    val updatedMap: Map[Int, String] = map1 + (3 -> "z") - 1
    println(updatedMap.equals(Map(2 -> "d", 3 -> "z")))

    // the ++ and -- methods return the union and intersec􏰀on of their argu- ments
    val map2 = Map(1 -> "s", 7 -> "d")

    val map3 = map1 ++ map2
    println(map3.equals(Map(1 -> "s", 2 -> "d", 7 -> "d")))

    println(1 to 3)
    println(1 until  3)

  }
}
