package controllers

import play.api.libs.json.{JsError, JsSuccess, JsValue, Reads}
import play.api.mvc.Result
import play.api.mvc.Results.BadRequest

object JsonValidator {

  def validateJson[T: Reads](jsObject: JsValue)
                            (success: T => Result): Result = {
    jsObject.validate[T] match {
      case JsSuccess(value, _) => success(value)
      case JsError(_) => BadRequest
    }
  }

}
