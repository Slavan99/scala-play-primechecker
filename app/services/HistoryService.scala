package services

import java.sql.Timestamp

import javax.inject.{Inject, Singleton}
import primechecker.fermat.FermatHandler
import primechecker.millerrabin.MillerRabinHandler
import primechecker.solovaystrassen.SolovayStrassenHandler
import primechecker.trialdivision.TrialDivisionHandler
import repository.HistoryRepository

@Singleton
class HistoryService @Inject()(historyRepository: HistoryRepository, algorithmService: AlgorithmService) {

  def checkNumber(number: Long, iterationNumber: Int, algorithmName: String): Int = {
    val handler = algorithmName match {
      case "Trial" => new TrialDivisionHandler
      case "Fermat" => new FermatHandler
      case "Miller-Rabin" => new MillerRabinHandler
      case "Solovay-Strassen" => new SolovayStrassenHandler
    }

    val checkResult = handler.isPrimeNumber(number, iterationNumber)
    val algoIdByName = algorithmService.getAlgorithmIdByName(algorithmName)

    historyRepository.addHistory(number, iterationNumber, checkResult, algoIdByName)

  }

  def getAllHistoriesResult: Seq[(Timestamp, Long, Int, Boolean, Int)] = historyRepository.getAllHistoriesResult

}
