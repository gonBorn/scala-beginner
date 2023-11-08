package catsExec.patternMatch

object patternMatch {
  private def judge(option: Option[String]): Int = {
    option match {
      case str@Some(_) if str.value.nonEmpty => 1
      case _ => 0
    }
  }

  def main(args: Array[String]): Unit = {
    println(judge(Some("ze")))
    println(judge(Some("")))
    println(judge(None))
  }
}
