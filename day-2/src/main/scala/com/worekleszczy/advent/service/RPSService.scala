package com.worekleszczy.advent.service

import com.worekleszczy.advent.model.{ Paper, Result, Rock, Scissors, Shape }

trait RPSService {

  def playRound(opponent: Shape, player: Shape): Int

  def playRound(opponent: Shape, result: Result): Int
}

object RPSService extends RPSService {

  private def pointsForShape(shape: Shape): Int = shape match {
    case Rock     => 1
    case Paper    => 2
    case Scissors => 3
  }

  private def pointsForResult(result: Result): Int = result match {
    case Result.Wins  => 6
    case Result.Draws => 3
    case Result.Lose  => 0
  }

  def playRound(opponent: Shape, player: Shape): Int = {

    val roundResult = player.resultFor(opponent)

    pointsForShape(player) + pointsForResult(roundResult)
  }

  def playRound(opponent: Shape, result: Result): Int = {
    val forShape = opponent.shapeFor(result.inverse)

    pointsForShape(forShape) + pointsForResult(result)
  }

}
