package com.worekleszczy.advent.service

import com.worekleszczy.advent.model.Result.{ Draws, Wins }
import com.worekleszczy.advent.model.{ Paper, Rock, Scissors }
import munit.FunSuite

import scala.util.Try

class RPSServiceTest extends FunSuite {

  private val sut: RPSService = RPSService
  test("Rock beats Scissors") {
    assertEquals(sut.playRound(Rock, Scissors), 3)
  }

  test("Paper get beaten Scissors") {
    assertEquals(sut.playRound(Paper, Scissors), 9)
  }

  test("Scissors draw Scissors") {
    assertEquals(sut.playRound(Scissors, Scissors), 6)
  }

  test("Rock get beaten by Paper") {
    assertEquals(sut.playRound(Rock, Paper), 8)
  }

  test("Paper beats Scissors") {
    assertEquals(sut.playRound(Paper, Paper), 5)
  }

  test("Scissors beats Scissors") {
    assertEquals(sut.playRound(Scissors, Paper), 2)
  }

  test("Rock draws with Rock") {
    assertEquals(sut.playRound(Rock, Rock), 4)
  }

  test("Paper beats Rock") {
    assertEquals(sut.playRound(Paper, Rock), 1)
  }

  test("Scissors get beaten by rock") {
    assertEquals(sut.playRound(Scissors, Rock), 7)
  }
  // ---

  test("Rock with win") {
    assertEquals(sut.playRound(Rock, Wins), 8)
  }

  test("Paper get beaten Scissors") {
    assertEquals(sut.playRound(Rock, Draws), 4)
  }
}
