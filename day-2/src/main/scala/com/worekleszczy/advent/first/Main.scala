package com.worekleszczy.advent.first

import com.worekleszczy.advent.model._
import com.worekleszczy.advent.service.RPSService

import scala.io.Source
import scala.util._
import cats.syntax.traverse._
import cats.syntax.apply._
import cats.syntax.applicative._

object Main extends App {

  def parse(input: String): Try[Shape] = input.toLowerCase() match {
    case "a" | "x" => Success(Rock)
    case "b" | "y" => Success(Paper)
    case "c" | "z" => Success(Scissors)
    case _         => Failure(new IllegalArgumentException(""))
  }

  val service: RPSService = RPSService

  println(Source.fromResource("input").getLines().toVector.traverse { line =>
    line.split(' ') match {
      case Array(opponent, player) => (parse(opponent), parse(player)).mapN(service.playRound)
      case _                       => Failure(new IllegalArgumentException(s"Line $line is invalid"))
    }
  }.map(_.sum))

}
