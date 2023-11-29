package catsExec.circeDemo

import io.circe.{Decoder, Encoder}

trait Pet

case object Cat extends Pet
case object Dog extends Pet
case class Other(str: String) extends Pet

object Pet {
  implicit val decoder: Decoder[Pet] = Decoder[String].emap {
    // 注意emap的返回值是either, 如果不想要either可以用map
    case "cat" => Right(Cat)
    case "dog" => Right(Dog)
    case _: String => Left("error")
  }

  implicit val decoder: Decoder[Pet] = Decoder[String].map {
    case "cat" => Cat
    case "dog" => Dog
    case _: String => Other("error")
  }

  implicit val encoder: Encoder[Pet] = Encoder[String].contramap {
    case Cat => "cat"
    case Dog => "dog"
  }
}
