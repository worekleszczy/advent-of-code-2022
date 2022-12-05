package com.worekleszczy.advent

import cats.syntax.foldable._
import com.worekleszczy.advent.service.ElfService

import scala.io.Source
import scala.util.Success
import scala.util.Try

object Day1PartTwo extends App {
  final case class ElfState(results: Vector[Long], currentElfEntries: Vector[String])

  val elfService: ElfService[Try] = ElfService[Try]
  val lines: Iterator[String]     = Source.fromResource("input").getLines()

  println(
    (lines.toVector :+ "")
      .foldM(ElfState(Vector.empty, Vector.empty)) { (state, line) =>
        if (line == "") elfService.sumCalories(state.currentElfEntries).map { calories =>
          val results = (state.results :+ calories).sorted.takeRight(3)
          state.copy(results = results, currentElfEntries = Vector.empty)
        }
        else Success(state.copy(currentElfEntries = state.currentElfEntries :+ line))
      }
      .map(_.results.sum)
  )
}
