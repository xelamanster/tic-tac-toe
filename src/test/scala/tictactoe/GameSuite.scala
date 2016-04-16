package tictactoe

import org.scalatest.{BeforeAndAfter, FunSuite}

class GameSuite extends FunSuite with BeforeAndAfter{
  var ticTacToe: TicTacToe = null

  before {
    ticTacToe = new TicTacToe
  }

  test("whenXOutsideBoardThenRuntimeException") {
    intercept[RuntimeException] {
      ticTacToe.play(5, 2)
    }
  }

  test("whenYOutsideBoardThenRuntimeException") {
    intercept[RuntimeException] {
      ticTacToe.play(2, 5)
    }
  }

  test("whenOccupiedThenRuntimeException") {
    intercept[RuntimeException] {
      ticTacToe.play(2, 1)
      ticTacToe.play(2, 1)
    }
  }

  test("givenFirstTurnWhenNextPlayerThenX") {
    assertResult(TicTacToe.x){
      ticTacToe.nextPlayer
    }
  }

  test("givenLastTurnWasXThenNextPlayerO") {
    ticTacToe.play(2, 2)
    assertResult(TicTacToe.o)(ticTacToe.nextPlayer)
  }

  test("whenPlayThenNoWinner") {
    assertResult(TicTacToe.noWinner)(ticTacToe.play(1,1))
  }

  test("whenPlayAndWholeHorizontalLineThenWinner") {
    ticTacToe.play(1,1) //X
    ticTacToe.play(1,2)
    ticTacToe.play(2,1) //X
    ticTacToe.play(2,2)
    assertResult(TicTacToe.x + TicTacToe.winner)(ticTacToe.play(3,1))
  }

  test("whenPlayAndWholeVerticalLineThenWinner") {
    ticTacToe.play(1,1) //X
    ticTacToe.play(2,1)
    ticTacToe.play(1,2) //X
    ticTacToe.play(2,2)
    assertResult(TicTacToe.x + TicTacToe.winner)(ticTacToe.play(1,3))
  }

  test("whenPlayAndWholeTopBottomDiagonalThenWinner") {
    ticTacToe.play(1,1) //X
    ticTacToe.play(2,1)
    ticTacToe.play(2,2) //X
    ticTacToe.play(2,3)
    assertResult(TicTacToe.x + TicTacToe.winner)(ticTacToe.play(3,3))
  }

  test("whenPlayAndWholeBottomTopDiagonalThenWinner") {
    assertResult(TicTacToe.x + TicTacToe.winner){
      ticTacToe.play(3,1) //X
      ticTacToe.play(2,1)
      ticTacToe.play(2,2) //X
      ticTacToe.play(2,3)
      ticTacToe.play(1,3)
    }
  }
}
