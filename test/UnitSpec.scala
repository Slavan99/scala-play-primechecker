import org.scalatestplus.play._
import primechecker.fermat.FermatHandler
import primechecker.millerrabin.MillerRabinHandler
import primechecker.solovaystrassen.SolovayStrassenHandler
import primechecker.trialdivision.TrialDivisionHandler

/**
 * Unit tests can run without a full Play application.
 */


class UnitSpec extends PlaySpec {

  "TrialDivisionHandler" should {
    "return a valid check result" in {
      val trialDivisionHandler = new TrialDivisionHandler
      val actual = trialDivisionHandler.isPrimeNumber(13, 1)
      actual mustBe true
    }
  }

  "FermatHandler" should {
    "return a valid check result" in {
      val fermatHandler = new FermatHandler
      val actual = fermatHandler.isPrimeNumber(99, 100)
      actual mustBe false
    }
  }

  "MillerRabinHandler" should {
    "return a valid check result" in {
      val millerRabinHandler = new MillerRabinHandler
      val actual = millerRabinHandler.isPrimeNumber(99, 100)
      actual mustBe false
    }
  }

  "SolovayStrassenHandler" should {
    "return a valid check result" in {
      val solovayStrassenHandler = new SolovayStrassenHandler
      val actual = solovayStrassenHandler.isPrimeNumber(99, 100)
      actual mustBe false
    }
  }

}

