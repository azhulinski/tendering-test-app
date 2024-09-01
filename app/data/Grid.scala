package data

import play.api.libs.json.{Json, OFormat}

case class Grid(rows: List[Map[String, String]], columns: Seq[String])

case class Row(columns: Map[String, String])

object Grid {
  implicit val fmt: OFormat[Grid] = Json.format[Grid]
}

object Row {
  implicit val fmt: OFormat[Row] = Json.format[Row]
}