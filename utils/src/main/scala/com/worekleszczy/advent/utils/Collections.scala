package com.worekleszczy.advent.utils
import cats.syntax.option._
object Collections {

  def splitBy[A](vector: Vector[A])(item: A): Vector[Vector[A]] = Vector.unfold(vector) {
    case Vector()       => none[(Vector[A], Vector[A])]
    case `item` +: tail => tail.span(_ != item).some
    case items          => items.span(_ != item).some
  }
}
