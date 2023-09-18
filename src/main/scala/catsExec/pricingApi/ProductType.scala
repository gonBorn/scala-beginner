package catsExec.pricingApi

sealed trait ProductType {
  def isGreen: Boolean = false
  def isLong: Boolean = false
  def isExpired: Boolean = false
}

sealed trait LowType extends ProductType

sealed trait HighType extends ProductType

object LowType {
  case object ManualType extends LowType
  case object GreenProduct extends LowType {
    override def isGreen: Boolean = true
  }
  case object LongProduct extends LowType {
    override def isLong: Boolean = true
  }
  case object GreenExpiredProduct extends LowType {
    override def isGreen: Boolean = true
    override def isExpired: Boolean = true
  }
  case object LongExpiredProduct extends LowType {
    override def isLong: Boolean = true
    override def isExpired: Boolean = true
  }
}

object HighType {
  case object AllHigh extends HighType
  case object ManualHigh extends HighType
}
