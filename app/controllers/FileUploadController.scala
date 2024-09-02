package controllers

import data.FileData
import play.api.mvc._
import services.FileUploadService

import javax.inject._

class FileUploadController @Inject()(val controllerComponents: ControllerComponents,
                                     fileUploadService: FileUploadService) extends BaseController with RestResponseProvider {

  def importShipments(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    JsonValidator.validateJson[FileData](request.body) { fileRequest =>
      jsonResponse(play.api.mvc.Results.Ok)(fileUploadService.calculatePrices(fileRequest.content))
    }
  }
}
