package catsExec.circeDemo

import io.circe.{Decoder, Encoder}

trait Pet

case object Cat extends Pet
case object Dog extends Pet

object Pet {
  implicit val decoder: Decoder[Pet] = Decoder[String].emap {
    case "cat" => Right(Cat)
    case "dog" => Right(Dog)
    case _: String => Left("error")
  }

  implicit val encoder: Encoder[Pet] = Encoder[String].contramap {
    case Cat => "cat"
    case Dog => "dog"
  }
}
