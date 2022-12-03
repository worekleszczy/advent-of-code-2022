package com.worekleszczy.advent.utils

import munit.FunSuite

class CollectionsTest extends FunSuite {

  test("split by values in the middle") {
    val data = Vector("a", "b", "", "c", "", "d", "e")
    assertEquals(Collections.splitBy(data)(""), Vector(Vector("a", "b"), Vector("c"), Vector("d", "e")))
  }

}
