package controllers

import play.api.libs.json.{Json, Writes}
import play.api.mvc.Result
import play.api.mvc.Results.Status

trait RestResponseProvider {

  def jsonResponse[R: Writes](status: Status)(supplier: R): Result = {
    status(Json.toJson(supplier))
  }

}
