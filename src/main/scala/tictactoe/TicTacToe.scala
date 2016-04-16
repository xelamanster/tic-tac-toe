package tictactoe

import tictactoe.TicTacToe.empty
import tictactoe.TicTacToe.x
import tictactoe.TicTacToe.o

object TicTacToe {
  val noWinner = "No Winner"
  val winner = "Winner"

  val empty = "-"
  val x = "x"
  val o = "o"
}

class TicTacToe() {
  var lastPlayer = empty
  val board = Array(
    Array(empty, empty, empty),
    Array(empty, empty, empty),
    Array(empty, empty, empty)
  )

  def play(x: Int, y: Int): String = {
    def checkAxis(coordinate: Int): Unit = if (coordinate < 1 || coordinate > 3) throw new RuntimeException(s"coordinate is outside board $coordinate")

    checkAxis(x)
    checkAxis(y)

    val arrayX = x - 1
    val arrayY = y - 1

    def setBox: Unit = {
      if (board(arrayY)(arrayX) != empty) throw new RuntimeException(s"$x $y is occupied")
      else board(arrayY)(arrayX) = nextPlayer
      lastPlayer = nextPlayer
    }
    setBox
    checkWinner(checkResults)
  }

  def checkResults: Array[String] = {
    val horizontalResults = board.collect{case a: Array[String] => a.reduce(_+_)}

    def verticalCollector[A](a: Array[A], i: Int): A = a(i)
    def topBottomCollector[A](a: Array[A], i: Int): A = a(board.indexOf(a))
    def bottomTopCollector[A](a: Array[A], i: Int): A = a((board.size - 1) - board.indexOf(a))

    def collectResults(pf: (Array[String], Int) => String): Array[String] = {
      (for(i <- 0 until 3) yield board.collect{case a: Array[String] => pf(a, i)}.reduce(_+_)).toArray
    }

    horizontalResults ++
      collectResults(verticalCollector) ++
      collectResults(topBottomCollector) ++
      collectResults(bottomTopCollector)
  }

  def checkWinner(results: Array[String]): String = {
    val winningResults = results.filter(f => f == TicTacToe.o * 3 || f == TicTacToe.x * 3)
    if(winningResults.isEmpty) TicTacToe.noWinner else winningResults.head.charAt(0) + TicTacToe.winner
  }

  def nextPlayer: String = if(lastPlayer == x) o else x
}
