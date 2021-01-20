package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.{AlgorithmService, HistoryService}

@Singleton
class MainController @Inject()(cc: ControllerComponents,
                               algorithmService: AlgorithmService,
                               historyService: HistoryService)(implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {


  def index: Action[AnyContent] = Action {
    _ => {

      Ok(views.html.mymain("", historyService.getAllHistoriesResult, algorithmService.getAllAlgorithmsResult))
    }
  }

  def postRequest: Action[AnyContent] = Action {
    request => {
      val reqBody = request.body
      val number = reqBody.asFormUrlEncoded.get("number").head.toLong
      val iterationNumber = reqBody.asFormUrlEncoded.get("iterations").head.toInt
      val algorithmName = reqBody.asFormUrlEncoded.get("name").head


      historyService.checkNumber(number, iterationNumber, algorithmName)
      Redirect("/")
    }
  }


}
