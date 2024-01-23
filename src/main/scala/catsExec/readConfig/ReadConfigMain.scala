package catsExec.readConfig

import cats.effect.unsafe.implicits.global
import cats.effect.{IO, Sync}
import io.circe.yaml.parser.parse

import scala.io.{BufferedSource, Source}

object ReadConfigMain {
  def main(args: Array[String]): Unit = {
    val map = for {
      str <- Sync[IO].bracket(
        Sync[IO].delay(Source.fromResource("mappings.yml")))(x => Sync[IO].pure(x.mkString))((handler: BufferedSource) => Sync[IO].delay(handler.close()))
      map <- if (str.isEmpty) {
        Sync[IO].pure(Map[KeyName, String]())
      } else {
        Sync[IO].rethrow(Sync[IO].delay(parse(str).flatMap(_.as[Map[KeyName, String]])))
      }
    } yield map

    println(map.unsafeRunSync())
  }
}
