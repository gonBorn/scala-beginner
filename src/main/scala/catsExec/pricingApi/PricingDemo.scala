package catsExec.pricingApi

import catsExec.pricingApi.LowType.{LongProduct, LongExpiredProduct}

object PricingDemo {
  def main(args: Array[String]): Unit = {
    val value: Map[ProductType, List[Int]] = Map(LongProduct -> List(1, 2, 3), LongExpiredProduct -> List(4, 5, 6))

    val option:Option[List[Int]] = value.filter(_._1.isLong).values.reduceOption(_ ++ _)
    println(option)
    println(value.filter(_._1.isGreen).values.reduceOption(_ ++ _))
    println(value.filter(_._1.isExpired).values.reduceOption(_ ++ _))
  }

}
