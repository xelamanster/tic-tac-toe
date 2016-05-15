package tictactoe.connection

import com.mongodb.async.client.Observable
import connection.{GameBean, GameCollection}
import org.mongodb.scala.{Completed, MongoCollection}
import org.mongodb.scala.bson.collection.immutable.Document
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import tictactoe.TicTacToe

class DBSuite extends Specification with Mockito {
  val testBean = GameBean(2, 2, 2, TicTacToe.X)

  val mockMongoCollection = mock[MongoCollection[Document]]
  mockMongoCollection.insertOne(org.mockito.Matchers.any[Document]) returns mock[Observable[Completed]]

  val gameCollection = spy(new GameCollection)
  gameCollection.collection returns mockMongoCollection

//  "When instantiate collection" should {
//    s"return '${GameCollection.dbName}' in name" in {
//      gameCollection.name
//    }
//  }

  "When save game" should {
    s"call insertOne one time" in {
      gameCollection.save(testBean)
      there was one(mockMongoCollection).insertOne(org.mockito.Matchers.any[Document])
    }
  }
}
