package controllers

import play.api.libs.json.{JsError, JsSuccess, Reads}
import play.api.mvc.Results.BadRequest
import play.api.mvc.{AnyContent, Result}

object JsonValidator {

  def validateJson[T: Reads](jsObject: AnyContent)
                            (success: T => Result): Result = {
    jsObject.asJson match {
      case Some(value) => value.validate[T] match {
        case JsSuccess(value, _) => success(value)
        case JsError(_) => BadRequest
      }
      case None => BadRequest
    }
  }

}
