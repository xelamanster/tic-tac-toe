package tictactoe.connection

import com.mongodb.async.client.Observable
import connection.{GameBean, GameCollection}
import org.mongodb.scala.{Completed, MongoCollection}
import org.mongodb.scala.bson.collection.immutable.Document
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import tictactoe.TicTacToe

class DBSuite extends Specification with Mockito {
  val bean = GameBean(2, 2, 2, TicTacToe.x)

  val gameCollection = spy(new GameCollection)
  val mongoCollection = mock[MongoCollection[Document]]
  val observable = mock[Observable[Completed]]

  mongoCollection.insertOne(org.mockito.Matchers.any[Document]) returns observable
  gameCollection.collection returns mongoCollection

//  "When instantiate collection" should {
//    s"return '${GameCollection.dbName}' in name" in {
//      gameCollection.name
//    }
//  }

  "When save game" should {
    s"call insertOne one time" in {
      gameCollection.save(bean)
      there was one(mongoCollection).insertOne(org.mockito.Matchers.any[Document])
    }
  }
}
