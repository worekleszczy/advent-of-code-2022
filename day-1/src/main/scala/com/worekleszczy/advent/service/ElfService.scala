package com.worekleszczy.advent.service

import cats.ApplicativeError
import cats.syntax.traverse._
import cats.syntax.applicativeError._
import cats.syntax.applicative._
import cats.syntax.functor._

trait ElfService[F[_]] {
  def sumCalories(entries: Vector[String]): F[Long]
}

object ElfService {

  def apply[F[_]: ApplicativeError[*[_], Throwable]]: ElfService[F] = new ElfService[F] {
    def sumCalories(entries: Vector[String]): F[Long] =
      entries
        .traverse(line =>
          line.toLongOption.fold[F[Long]](new IllegalArgumentException(s"$line is not a number").raiseError)(_.pure)
        )
        .map(_.sum)
  }
}
