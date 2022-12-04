package com.worekleszczy.advent.day4.second

import munit.FunSuite

import com.worekleszczy.advent.day4.model.Range

class MainTest extends FunSuite {

  test("mark overlap selection") {
    assert(Main.contains(Range(1, 10), Range(10, 20)))
  }
  test("mark overlap selection") {
    assert(Main.contains(Range(10, 20), Range(1, 10)))
  }

  test("mark overlap selection") {
    assert(Main.contains(Range(1, 10), Range(5, 20)))
  }

  test("mark overlap selection") {
    assert(Main.contains(Range(5, 20), Range(1, 10)))
  }

  test("mark overlap selection") {
    assert(Main.contains(Range(5, 7), Range(7, 9)))
  }

  test("mark overlap selection") {
    assert(Main.contains(Range(2, 8), Range(3, 7)))
  }

  test("mark overlap selection") {
    assert(Main.contains(Range(6, 6), Range(4, 6)))
  }

  test("mark overlap selection") {
    assert(Main.contains(Range(2, 6), Range(4, 8)))
  }

  test("mark overlap selection") {
    assert(Main.contains(Range(4, 8), Range(2, 6)))
  }

  test("not mark overlap selection") {
    assert(!Main.contains(Range(2, 4), Range(6, 8)))
  }

  test("not mark overlap selection") {
    assert(!Main.contains(Range(6, 8), Range(2, 4)))
  }

  test("not mark overlap selection") {
    assert(!Main.contains(Range(2, 3), Range(4, 5)))
  }
}
