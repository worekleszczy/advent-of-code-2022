package com.worekleszczy.advent.day3.model

final case class Backpack(compartment1: Vector[Item], compartment2: Vector[Item])

final case class Item(value: Char) extends AnyVal
