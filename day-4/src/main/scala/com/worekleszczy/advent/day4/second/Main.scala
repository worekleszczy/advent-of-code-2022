package com.worekleszczy.advent.day4.second

import cats.syntax.traverse._
import com.worekleszczy.advent.day4.Parser
import com.worekleszczy.advent.day4.model.Range

import scala.io.Source

object Main extends App {

  def contains(first: Range, second: Range): Boolean =
    if (first.start < second.start) {
      first.stop >= second.start
    } else second.stop >= first.start

  val lines = Source.fromResource("input").getLines().toVector

  println(lines.traverse(Parser.parse).map(_.count(Function.tupled(contains))))

}
