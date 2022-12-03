package com.worekleszczy.advent.second

import cats.syntax.apply._
import cats.syntax.traverse._
import com.worekleszczy.advent.model._
import com.worekleszczy.advent.service.RPSService

import scala.io.Source
import scala.util._

object Main extends App {

  def parseShape(input: String): Try[Shape] = input.toLowerCase() match {
    case "a" => Success(Rock)
    case "b" => Success(Paper)
    case "c" => Success(Scissors)
    case _   => Failure(new IllegalArgumentException(""))
  }

  def parseResult(input: String): Try[Result] = input.toLowerCase() match {
    case "x" => Success(Result.Lose)
    case "y" => Success(Result.Draws)
    case "z" => Success(Result.Wins)
    case _   => Failure(new IllegalArgumentException(""))
  }

  val service: RPSService = RPSService

  println(
    Source
      .fromResource("input")
      .getLines()
      .toVector
      .traverse { line =>
        line.split(' ') match {
          case Array(opponent, player) => (parseShape(opponent), parseResult(player)).mapN(service.playRound)
          case _                       => Failure(new IllegalArgumentException(s"Line $line is invalid"))
        }
      }
      .map(_.sum)
  )

}
