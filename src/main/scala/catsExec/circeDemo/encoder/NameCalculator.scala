package catsExec.circeDemo.encoder

import io.circe.{Encoder, Json}
import io.circe.generic.semiauto.deriveEncoder
import io.circe.literal.JsonStringContext
import io.circe.syntax.EncoderOps

// name作为一个被计算出的成员变量，我们不希望它出现在构造方法中，但是encode的时候希望能显示出来
object NameCalculatorDemo {
  case class NameCalculator(
                             firstName: String,
                             lastName: String) {
    val name = firstName + lastName
  }

  object NameCalculator {
    // 推荐用val而非def
    implicit val encoder: Encoder[NameCalculator] = o =>
      deriveEncoder[NameCalculator]
        .apply(o)
        .deepMerge(Json.obj {
          "name" -> o.name.asJson
        })
  }

  def main(args: Array[String]): Unit = {
    println(NameCalculator("zeyan", "du").asJson)

    val json1 =
      json"""
        {
          "name": "John",
          "age": 25,
          "address": {
            "city": "New York",
            "zip": "10001"
          }
        }
      """

    val json2 =
      json"""
        {
          "age": 26,
          "address": {
            "zip": "10002"
          },
          "phone": "123-456-7890"
        }
      """

    // 在合并的过程中，如果有相同键的情况，json2 中的值将覆盖 json1 中的值
    println(json1.deepMerge(json2))
  }
}
