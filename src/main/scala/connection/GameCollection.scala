package connection

import connection.GameCollection.{collectionName, dbName}
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.{Completed, MongoClient}

object GameCollection {
  val dbName = "test"
  val collectionName = "game"
}

class GameCollection {
  implicit def beanToDoc(bean: GameBean): Document = Document("_id" -> bean.turn, "x" -> bean.x, "y" -> bean.y, "player" -> bean.player)
  val printResult = (c: Completed) => println(c)

  private val db = MongoClient().getDatabase(dbName)
  val collection = db.getCollection(collectionName)

  def save(game: GameBean): Unit = collection.insertOne(game).subscribe(printResult)
  def drop(): Unit = collection.drop().subscribe(printResult)
}

case class GameBean(turn: Int, x: Int, y: Int, player: String)
