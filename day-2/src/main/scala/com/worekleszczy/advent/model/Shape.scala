package com.worekleszczy.advent.model

import com.worekleszczy.advent.model.Result.Draws
import com.worekleszczy.advent.model.Result.Lose
import com.worekleszczy.advent.model.Result.Wins

sealed trait Result {
  def inverse: Result = this match {
    case Result.Wins  => Lose
    case Result.Draws => Draws
    case Result.Lose  => Wins
  }
}

object Result {
  case object Wins  extends Result
  case object Draws extends Result
  case object Lose  extends Result
}

sealed trait Shape {

  def resultFor(other: Shape): Result = if (other == winsWith) {
    Wins
  } else if (other == loseWith) Lose
  else Draws

  final def shapeFor(result: Result): Shape = result match {
    case Result.Wins  => winsWith
    case Result.Draws => this
    case Result.Lose  => loseWith
  }

  protected def winsWith: Shape
  protected def loseWith: Shape
}

case object Rock extends Shape {
  protected def winsWith: Shape = Scissors

  protected def loseWith: Shape = Paper
}

case object Paper extends Shape {
  protected def winsWith: Shape = Rock

  protected def loseWith: Shape = Scissors
}

case object Scissors extends Shape {
  protected def winsWith: Shape = Paper

  protected def loseWith: Shape = Rock
}
