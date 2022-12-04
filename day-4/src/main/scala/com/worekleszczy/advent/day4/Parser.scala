package com.worekleszczy.advent.day4

import scala.util.{ Failure, Success, Try }
import cats.syntax.option._
import cats.syntax.apply._
import cats.syntax.applicative._
import cats.syntax.traverse._

trait Parser {
  def parse(line: String): Try[(model.Range, model.Range)]
}

object Parser extends Parser {
  def parse(line: String): Try[(model.Range, model.Range)] = line.split(',').map(_.split('-')) match {
    case Array(Array(start1, stop1), Array(start2, stop2)) =>
      (
        (start1.trim.toIntOption, stop1.trim.toIntOption).mapN(model.Range),
        (start2.trim.toIntOption, stop2.trim.toIntOption).mapN(model.Range)
      ).tupled
        .fold[Try[(model.Range, model.Range)]](Failure(new IllegalArgumentException(s"Invalid line $line")))(Success(_))
    case _ => Failure(new IllegalArgumentException(s"Invalid line $line"))
  }
}
