package com.worekleszczy.advent.service
import munit.CatsEffectSuite

import scala.util.Success
import scala.util.Try

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
