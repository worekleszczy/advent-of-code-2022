package com.worekleszczy.advent.day6.first

import scala.io.Source
import cats.syntax.traverse._
import cats.syntax.foldable._
import cats.syntax.apply._
import com.worekleszczy.advent.day6.Parser
import com.worekleszczy.advent.day6.model.Step
object Main extends App {

  val stateLines = Source.fromResource("state").getLines().toVector
  val stepsLines = Source.fromResource("steps").getLines().toVector

  val result = (Parser.parseState(stateLines), Parser.parseSteps(stepsLines)).mapN { (state, steps) =>
    println(state)
    println(steps)
    println()

    steps.foldLeft(state) { case (acc, Step(move, from, to)) =>
      val (removed, remained) = acc.rows(from).splitAt(move)
      val added               = removed.reverse ::: acc.rows(to)

      val result = acc.copy(rows = acc.rows.updated(from, remained).updated(to, added))

      println(result)
      result

    }

  }

  println()

  println(result.map(_.rows.map { case (i, values) =>
    (i, values.head)
  }.toVector.sortBy(_._1).map(_._2.name).mkString("")))

}
