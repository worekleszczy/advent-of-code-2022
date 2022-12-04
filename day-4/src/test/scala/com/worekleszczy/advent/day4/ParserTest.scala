package com.worekleszczy.advent.day4

import munit.FunSuite
import com.worekleszczy.advent.day4.model.Range

import scala.util.Success

class ParserTest extends FunSuite {
  test("parse line") {

    val line = "1-10,100-200"

    assertEquals(Parser.parse(line), Success((Range(1, 10), Range(100, 200))))
  }

  test("parse line with whitespaces") {

    val line = " 1 - 10 , 100 - 200 "

    assertEquals(Parser.parse(line), Success((Range(1, 10), Range(100, 200))))
  }
}
