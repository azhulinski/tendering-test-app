package data

import play.api.libs.json.{Json, OFormat}

case class CountryProviderCost(
  country: String,
  providerA: Int,
  providerB: Int,
  providerC: Int
)

case class CountryProviderAllocation(
  country: String,
  providerA: Int,
  providerB: Int,
  providerC: Int,
  total: Int
)

case class Grid(
  rows: List[Map[String, String]],
  columns: Seq[String],
  countrySummary: List[CountryProviderCost],
  cherryPickSummary: List[CountryProviderAllocation]
)

case class Row(columns: Map[String, String])

object CountryProviderCost {
  implicit val fmt: OFormat[CountryProviderCost] = Json.format[CountryProviderCost]
}

object CountryProviderAllocation {
  implicit val fmt: OFormat[CountryProviderAllocation] = Json.format[CountryProviderAllocation]
}

object Grid {
  implicit val fmt: OFormat[Grid] = Json.format[Grid]
}

object Row {
  implicit val fmt: OFormat[Row] = Json.format[Row]
}