package com.worekleszczy.advent

import cats.syntax.foldable._
import com.worekleszczy.advent.service.ElfService

import scala.io.Source
import scala.util.Success
import scala.util.Try
import scala.util.chaining._

object Day1 extends App {
  final case class ElfState(max: Option[Long], currentElfEntries: Vector[String])

  val elfService: ElfService[Try] = ElfService[Try]
  val lines: Vector[String]       = Source.fromResource("input").getLines().toVector

  val elfsLists: Vector[Vector[String]] = (lines
    .foldLeft((Vector.empty[Vector[String]], Vector.empty[String])) { case ((lists, current), line) =>
      if (line == "") (lists :+ current, Vector.empty) else (lists, current :+ line)
    })
    .pipe { case (lists, last) =>
      lists :+ last
    }

  println(
    elfsLists.foldM(0L)((max, elfList) => elfService.sumCalories(elfList).map(sum => if (sum > max) sum else max))
  )

  println(
    (lines.toVector :+ "")
      .foldM(ElfState(None, Vector.empty)) { (state, line) =>
        if (line == "") elfService.sumCalories(state.currentElfEntries).map { calories =>
          if (state.max.forall(_ < calories)) ElfState(Some(calories), Vector.empty)
          else state.copy(currentElfEntries = Vector.empty)
        }
        else Success(state.copy(currentElfEntries = state.currentElfEntries :+ line))
      }
  )
}
