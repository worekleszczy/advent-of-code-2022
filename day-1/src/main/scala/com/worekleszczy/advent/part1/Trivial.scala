package com.worekleszczy.advent.part1

import cats.syntax.foldable._
import com.worekleszczy.advent.service.ElfService

import scala.io.Source
import scala.util.Try
import scala.util.chaining._

object Trivial extends App {
  val elfService: ElfService[Try] = ElfService[Try]
  val lines: Vector[String]      = Source.fromResource("input").getLines().toVector

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
}
