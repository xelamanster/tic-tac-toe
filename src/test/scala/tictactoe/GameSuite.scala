package tictactoe

import org.specs2.mutable.Specification
import org.specs2.specification.BeforeEach

class GameSuite extends Specification with BeforeEach {
  sequential

  var ticTacToe: TicTacToe = null

  override def before(): Unit = {
    ticTacToe = new TicTacToe
  }

  "When play game then" should {
    "throw exception when x outside board" in {
      ticTacToe.play(5, 2) must throwA[RuntimeException]
    }

    "throw exception when y outside board" in {
      ticTacToe.play(2, 5) must throwA[RuntimeException]
    }

    "throw exception when occupied" in {
      ticTacToe.play(2, 1)
      ticTacToe.play(2, 1) must throwA[RuntimeException]
    }
  }

  "Check the next player" should {
    "return X in first turn" in {
      ticTacToe.nextPlayer mustEqual TicTacToe.X
    }

    "return Y if previous was X" in {
      ticTacToe.play(2, 2)
      ticTacToe.nextPlayer mustEqual TicTacToe.O
    }
  }

  "Check game results" should {
    s"return '${TicTacToe.NO_WINNER}' if play first time" in {
      ticTacToe.play(1,1) mustEqual TicTacToe.NO_WINNER
    }

    s"return '${TicTacToe.X + TicTacToe.WINNER}' if whole horizontal line" in {
      ticTacToe.play(1,1) //X
      ticTacToe.play(1,2)
      ticTacToe.play(2,1) //X
      ticTacToe.play(2,2)
      ticTacToe.play(3,1) mustEqual TicTacToe.X + TicTacToe.WINNER
    }

    s"return '${TicTacToe.X + TicTacToe.WINNER}' if whole vertical line" in {
      ticTacToe.play(1,1) //X
      ticTacToe.play(2,1)
      ticTacToe.play(1,2) //X
      ticTacToe.play(2,2)
      ticTacToe.play(1,3) mustEqual TicTacToe.X + TicTacToe.WINNER
    }

    s"return '${TicTacToe.X + TicTacToe.WINNER}' if whole top bottom diagonal" in {
      ticTacToe.play(1,1) //X
      ticTacToe.play(2,1)
      ticTacToe.play(2,2) //X
      ticTacToe.play(2,3)
      ticTacToe.play(3,3) mustEqual TicTacToe.X + TicTacToe.WINNER
    }

    s"return '${TicTacToe.X + TicTacToe.WINNER}' if whole bottom top diagonal" in {
      ticTacToe.play(3,1) //X
      ticTacToe.play(2,1)
      ticTacToe.play(2,2) //X
      ticTacToe.play(2,3)
      ticTacToe.play(1,3) mustEqual TicTacToe.X + TicTacToe.WINNER
    }
  }
}