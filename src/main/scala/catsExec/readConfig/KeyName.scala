package catsExec.readConfig

import io.circe.{Encoder, KeyDecoder}

sealed trait KeyName

object KeyName {
  case object Lu extends KeyName

  case object Hi extends KeyName

  case object Pr extends KeyName

  case object Fe extends KeyName

  case object St extends KeyName

  case object Pa extends KeyName

  case class OtherDepth(value: String) extends KeyName

  implicit val depthByDealEncoder: Encoder[KeyName] = Encoder[String].contramap[KeyName] {
    case Lu => "Luxe"
    case Pr => "Premiere"
    case Hi => "Highlight"
    case Fe => "Feature"
    case St => "Standard"
    case Pa => "Pay on sale"
    case OtherDepth(value) => value
  }

  implicit val depthByDealDecoder: KeyDecoder[KeyName] = {
    case "Lu" => Some(Lu)
    case "Pr" => Some(Pr)
    case "Hi" => Some(Hi)
    case "Fe" => Some(Fe)
    case "St" => Some(St)
    case "Pay on sale" => Some(Pa)
    case x => Some(OtherDepth(x))
  }
}
