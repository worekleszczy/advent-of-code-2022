package com.worekleszczy.advent.day4.first

import scala.io.Source
import cats.syntax.traverse._
import com.worekleszczy.advent.day4.Parser
import com.worekleszczy.advent.day4.model.Range
object Main extends App {
//496
  def contains(first: Range, second: Range): Boolean =
    math.max(first.start, second.start) > math.min(first.stop, second.stop)

  val lines = Source.fromResource("input").getLines().toVector

  println(lines.traverse(Parser.parse).map(_.count(Function.tupled(contains))))

}
