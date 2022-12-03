package com.worekleszczy.advent.day3.first

import scala.util.{ Failure, Success, Try }
import com.worekleszczy.advent.day3.model.{ Backpack, Item }
import cats.syntax.functor._
import cats.instances.tuple._
import cats.syntax.traverse._

import scala.io.Source

object Main extends App {

  val priorityMap: Map[Char, Int] =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".zipWithIndex.toMap.view.mapValues(_ + 1).toMap
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

  println(lines.traverse { line =>
    for {
      b@Backpack(first, second) <- parseBackpack(line)
      _ = println(b)
      repeated = first.intersect(second).distinct
      _ = println(repeated)
      priorities <- repeated.traverse(itemPriority)
    } yield priorities.sum
  }.map(_.sum))

}
