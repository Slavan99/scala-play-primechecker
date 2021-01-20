package services

import javax.inject.{Inject, Singleton}
import repository.AlgorithmRepository

@Singleton
class AlgorithmService @Inject()(algorithmRepository: AlgorithmRepository) {

  def getAlgorithmIdByName(algorithmName: String): Option[Int] = algorithmRepository.getAlgorithmIdByName(algorithmName)

  def getAllAlgorithmsResult: Seq[String] = algorithmRepository.getAllAlgorithmsResult

}
