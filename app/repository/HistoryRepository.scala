package repository

import java.sql.Timestamp
import java.time.LocalDateTime

import database.Tables
import database.Tables._
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.ControllerComponents
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

@Singleton
class HistoryRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                                  cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  def historyView(history: History): (Rep[Timestamp],
    Tables.profile.api.Rep[Long],
    Tables.profile.api.Rep[Int],
    Tables.profile.api.Rep[Boolean],
    Rep[Int]) =
    (
      history.checkDateTime.get,
      history.number,
      history.iterations,
      history.result,
      history.algorithmId.get
    )

  def maxId: Int = Await.result(db.run(History.map(history => history.id).result), Duration.Inf).max

  def getAllHistories: Future[Seq[(Timestamp, Long, Int, Boolean, Int)]] =
    db.run(History.map(history => historyView(history)).result)

  def getAllHistoriesResult: Seq[(Timestamp, Long, Int, Boolean, Int)] =
    Await.result(getAllHistories, Duration.Inf).sortBy(_._1)(Ordering[Timestamp].reverse)

  def addHistory(number: Long, iterationNumber: Int, checkResult: Boolean, algoIdByName: Option[Int]): Int = {
    Await.result(db.run(History += HistoryRow(
      maxId + 1,
      Some(Timestamp.valueOf(LocalDateTime.now())),
      iterationNumber,
      number,
      checkResult,
      algoIdByName,
      Some("someuser")
    )), Duration.Inf)
  }

}
