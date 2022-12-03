package com.worekleszczy.advent.utils
import cats.syntax.apply._
import fs2._
object Streaming {
  def delimiterBy[F[_]](delimiter: String): Pipe[F, String, Vector[String]] = stream => {
    def go(stream: Stream[F, String], state: Vector[String]): Pull[F, Vector[String], Unit] =
      stream.pull.uncons.flatMap {
        case None => if (state.nonEmpty) Pull.output1(state) else Pull.pure(())
        case Some((elements, tail)) =>
          val (toEmit, nextState) = elements.foldLeft((Vector.empty[Vector[String]], state)) {
            case (currentState @ (completed, partial), elem) =>
              if (elem == delimiter) {
                if (partial.nonEmpty) (completed :+ partial, Vector.empty)
                else currentState
              } else (completed, partial :+ elem)
          }
          Pull.output[F, Vector[String]](Chunk.seq(toEmit)) *> go(tail, nextState)
      }

    go(stream, Vector.empty).stream
  }
}
