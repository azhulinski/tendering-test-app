package data

import play.api.libs.json.{Json, OFormat}

case class FileData(content: String)

object FileData {
  implicit val fmt: OFormat[FileData] = Json.format[FileData]
}
