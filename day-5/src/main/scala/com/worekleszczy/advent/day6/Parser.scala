package com.worekleszczy.advent.day6

import com.worekleszczy.advent.day6.model.{ Crate, Stacks, Step }

import scala.util._

trait Parser {

  def parseState(lines: Vector[String]): Try[Stacks]

  def parseSteps(lines: Vector[String]): Try[Vector[Step]]

}

object Parser extends Parser {
  import cats.syntax.apply._
  import cats.syntax.traverse._

  def parseState(lines: Vector[String]): Try[Stacks] = {
    val indexes = lines.last.zipWithIndex.collect {
      case (c, i) if !Character.isWhitespace(c) =>
        i
    }.zipWithIndex.map { case (filePos, index) => (filePos, index + 1) }.toVector

    val init = Stacks(
      Vector
        .tabulate(indexes.size) { index =>
          (index + 1, List.empty[Crate])
        }
        .toMap
    )

    val result = lines.dropRight(1).foldLeft(init) { (state, line) =>
      indexes.collect { case (filePos, index) if line.length >= filePos => (index, line(filePos)) }.filterNot {
        case (_, c) =>
          Character.isWhitespace(c)
      }
        .foldLeft(state) { case (acc, (index, c)) =>
          val crates = acc.rows(index) ::: List(Crate(c))
          acc.copy(rows = acc.rows.updated(index, crates))
        }
    }

    Success(result)
  }

  def parseSteps(lines: Vector[String]): Try[Vector[Step]] = lines.traverse(parseStep)

  private def parseStep(raw: String): Try[Step] = raw.split("(move|from|to)").drop(1) match {
    case Array(move, from, to) =>
      (move.trim.toIntOption, from.trim.toIntOption, to.trim.toIntOption)
        .mapN(Step.apply)
        .fold[Try[Step]](Failure(new IllegalStateException(s"Invalid line $raw")))(Success(_))
    case other => Failure(new IllegalStateException(s"Invalid line $raw $other"))
  }
}
