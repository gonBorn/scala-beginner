package catsExec.ADT

object ReferentialTransparency extends App {
  def method(input: Int): Int = input

  // One 打印一次
  val value = method({
    println("more evil"); 1
  })
  println(value)
  println(value)

  // Two 打印2次
  println(method({
    println("more evil"); 1
  }))
  println(method({
    println("more evil"); 1
  }))

  // 两种改进思路
  // 1. lazy
  // 2. Either
  println(() => {
    println("more evil"); Right(1)
  })
  println(() => {
    println("more evil"); Right(1)
  })
}
