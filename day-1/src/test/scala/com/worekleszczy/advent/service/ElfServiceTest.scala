package com.worekleszczy.advent.service

import cats.data.NonEmptyVector
import cats.effect.IO
import cats.{Alternative, Comonad, NonEmptyTraverse, Traverse, catsParallelForId}
import munit.{CatsEffectSuite, FunSuite}

import scala.util.{Success, Try}
import cats.syntax.option._
import fs2.{Chunk, Pipe, Pull}
import fs2.io.file.{Files, Path}

class ElfServiceTest extends CatsEffectSuite {

  private val sut = ElfService[Try]

  test("sum all entires") {

    val input =
      """|2000
         |3000
         |4000
         |5000
         |""".stripMargin.split('\n').toVector

    assertEquals(sut.sumCalories(input), Success(14000L))

  }

  test("fail if entries are not numbers") {

    val input =
      """|2000
         |300l
         |4000
         |5000
         |""".stripMargin.split('\n').toVector

    assert(sut.sumCalories(input).isFailure)

  }

  test("fail on black lines") {

    val input =
      """|2000
         |3000
         |
         |4000
         |5000
         |""".stripMargin.split('\n').toVector

    assert(sut.sumCalories(input).isFailure)

  }

}
