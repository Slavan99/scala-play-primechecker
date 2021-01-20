package repository

import database.Tables._
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.ControllerComponents
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

@Singleton
class AlgorithmRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                                    cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAllAlgorithms: Future[Seq[Option[String]]] = db.run(Algorithm.map(_.name).result)

  def getAllAlgorithmsResult: Seq[String] = Await.result(getAllAlgorithms, Duration.Inf).map(_.getOrElse("Unknown"))

  def getAlgorithmIdByName(algorithmName: String): Option[Int] = Await.result(
    db.run(Algorithm.filter(_.name === algorithmName).map(_.id).result), Duration.Inf
  ).headOption


}
