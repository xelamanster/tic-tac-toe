package tictactoe.connection

import connection.GameBean
import org.specs2.mutable.Specification
import tictactoe.TicTacToe

class GameBeanSpec extends Specification {
  val turn = 0
  val x = 1
  val y = 2
  val player = TicTacToe.X

  val testGameBean = new GameBean(turn, x, y, player)

  "When create new bean" should {
    "return proper values" in {
      testGameBean.turn mustEqual turn
      testGameBean.x mustEqual x
      testGameBean.y mustEqual y
      testGameBean.player mustEqual player
    }
  }
}
