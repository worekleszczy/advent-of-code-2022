package com.worekleszczy.advent.day3.second

import cats.syntax.traverse._
import com.worekleszczy.advent.day3.model.{ Backpack, Item }

import scala.io.Source
import scala.util.{ Failure, Success, Try }

object Main extends App {

  val priorityMap: Map[Char, Int] =
    ('a'.to('z').toVector ++ 'A'.to('Z').toVector).zipWithIndex.toMap.view.mapValues(_ + 1).toMap
  def parseBackpack(line: String): Try[Backpack] = {
    val (first, second) = line.splitAt(line.length / 2)
    if (first.length == second.length) {
      Success(Backpack(first.map(Item).toVector, second.map(Item).toVector))
    } else Failure(new IllegalArgumentException(""))
  }

  def itemPriority(item: Item): Try[Int] =
    priorityMap
      .get(item.value)
      .fold[Try[Int]](Failure(new IllegalArgumentException(s"Invalid item: $item")))(Success(_))

  val lines = Source.fromResource("input").getLines().toVector

  println(
    lines
      .grouped(3)
      .toVector
      .traverse {
        case Vector(firstBackpack, secondBackpack, thirdBackpack) =>
          for {
            b1 <- parseBackpack(firstBackpack)
            b2 <- parseBackpack(secondBackpack)
            b3 <- parseBackpack(thirdBackpack)
            badge <- (b1.compartment1 ++ b1.compartment2)
              .intersect(b2.compartment1 ++ b2.compartment2)
              .intersect(b3.compartment1 ++ b3.compartment2)
              .headOption
              .fold[Try[Item]](Failure(new IllegalArgumentException("No common items")))(Success(_))
            priority <- itemPriority(badge)
          } yield priority
        case _ => Failure(new IllegalArgumentException("Invalid elfs number"))
      }
      .map(_.sum)
  )

}
