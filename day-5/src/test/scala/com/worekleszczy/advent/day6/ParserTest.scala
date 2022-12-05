package com.worekleszczy.advent.day6

import com.worekleszczy.advent.day6.model.{ Crate, Stacks, Step }
import munit.FunSuite

import scala.util.Success

class ParserTest extends FunSuite {

  test("parse single step") {
    assertEquals(Parser.parseSteps(Vector("move 4 from 9 to 1")), Success(Vector(Step(4, 9, 1))))
  }

  test("parse multiple steps") {
    val input = Vector(
      "move 2 from 6 to 2",
      "move 9 from 5 to 9",
      "move 2 from 4 to 5"
    )
    val expected = Vector(Step(2, 6, 2), Step(9, 5, 9), Step(2, 4, 5))
    assertEquals(Parser.parseSteps(input), Success(expected))
  }

  test("parse crates state") {

    val input = Vector("[V] [Q] [Q]", "[B] [Z] [Z]", "[H] [D] [L]", "[F] [L] [H]", " 1   2   3 ")
    val expected = Stacks(
      Map(
        1 -> List(Crate('V'), Crate('B'), Crate('H'), Crate('F')),
        2 -> List(Crate('Q'), Crate('Z'), Crate('D'), Crate('L')),
        3 -> List(Crate('Q'), Crate('Z'), Crate('L'), Crate('H'))
      )
    )

    assertEquals(Parser.parseState(input), Success(expected))
  }

  test("parse crates state with misssing values") {

    val input = Vector("[P]     [C]", "[D]     [P] [B]", "[Q] [V] [R] [V]", "[R] [W] [G] [J]", " 1   2   3   4")

    val expected = Stacks(
      Map(
        1 -> List(Crate('P'), Crate('D'), Crate('Q'), Crate('R')),
        2 -> List(Crate('V'), Crate('W')),
        3 -> List(Crate('C'), Crate('P'), Crate('R'), Crate('G')),
        4 -> List(Crate('B'), Crate('V'), Crate('J'))
      )
    )

    assertEquals(Parser.parseState(input), Success(expected))
  }

}
