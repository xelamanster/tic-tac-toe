package tictactoe

import tictactoe.TicTacToe.EMPTY
import tictactoe.TicTacToe.X
import tictactoe.TicTacToe.O

object TicTacToe {
  val NO_WINNER = "No Winner"
  val WINNER = "Winner"

  val EMPTY = "-"
  val X = "x"
  val O = "o"
}

class TicTacToe() {
  var lastPlayer = EMPTY
  val size = 3

  val board = Array(
    Array(EMPTY, EMPTY, EMPTY),
    Array(EMPTY, EMPTY, EMPTY),
    Array(EMPTY, EMPTY, EMPTY)
  )

  def play(x: Int, y: Int): String = {
    def checkAxis(coordinate: Int) = if (coordinate < 1 || coordinate > 3) throw new RuntimeException(s"coordinate is outside board $coordinate")

    checkAxis(x)
    checkAxis(y)

    val arrayX = x - 1
    val arrayY = y - 1

    def setBox {
      if (board(arrayY)(arrayX) != EMPTY) throw new RuntimeException(s"$x $y is occupied")
      else board(arrayY)(arrayX) = nextPlayer
      lastPlayer = nextPlayer
    }
    setBox

    def collectResults(chooseFunc: Int => String): String = {
      (0 until size).map(chooseFunc(_)).reduce(_+_)
    }

    val results = Array(
      collectResults(board(arrayY)(_)),
      collectResults(board(_)(arrayX)),
      collectResults(i => board(i)(i)),
      collectResults(i => board(i)(size - 1 - i))
    )

    checkWinner(results)
  }

  def checkWinner(results: Array[String]): String = {
    val winningResults = results.filter(f => f == TicTacToe.O * size || f == TicTacToe.X * size)
    if(winningResults.isEmpty) TicTacToe.NO_WINNER else winningResults.head.charAt(0) + TicTacToe.WINNER
  }

  def nextPlayer: String = if(lastPlayer == X) O else X
}
