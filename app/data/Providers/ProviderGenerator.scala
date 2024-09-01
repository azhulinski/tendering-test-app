package data.Providers

import data.{PricesRange, WeightRange}

object ProviderGenerator {

  def generateProviderA(): Map[String, PricesRange] = {
    val NLPrices = PricesRange(Map(
      WeightRange(0, 100) -> 50,
      WeightRange(101, 250) -> 110,
      WeightRange(251, 500) -> 170,
      WeightRange(501, 750) -> 290,
      WeightRange(751, 1000) -> 650
    ))

    val BEPrices = PricesRange(Map(
      WeightRange(0, 100) -> 75,
      WeightRange(101, 250) -> 160,
      WeightRange(251, 500) -> 240,
      WeightRange(501, 750) -> 480,
      WeightRange(751, 1000) -> 700
    ))

    val DEPrices = PricesRange(Map(
      WeightRange(0, 100) -> 50,
      WeightRange(101, 250) -> 150,
      WeightRange(251, 500) -> 440,
      WeightRange(501, 750) -> 780,
      WeightRange(751, 1000) -> 900
    ))

    Map("NL" -> NLPrices, "BE" -> BEPrices, "DE" -> DEPrices)
  }

  def generateProviderB(): Map[String, PricesRange] = {
    val NLPrices = PricesRange(Map(
      WeightRange(0, 250) -> 100,
      WeightRange(251, 500) -> 190,
      WeightRange(501, 750) -> 270,
      WeightRange(751, 1000) -> 350
    ))

    val BEPrices = PricesRange(Map(
      WeightRange(0, 250) -> 150,
      WeightRange(251, 500) -> 250,
      WeightRange(501, 750) -> 490,
      WeightRange(751, 1000) -> 650
    ))

    val DEPrices = PricesRange(Map(
      WeightRange(0, 250) -> 100,
      WeightRange(251, 500) -> 390,
      WeightRange(501, 750) -> 580,
      WeightRange(751, 1000) -> 790
    ))

    Map("NL" -> NLPrices, "BE" -> BEPrices, "DE" -> DEPrices)
  }

  def generateProviderC(): Map[String, PricesRange] = {
    val NLPrices = PricesRange(Map(
      WeightRange(0, 100) -> 50,
      WeightRange(101, 200) -> 80,
      WeightRange(201, 300) -> 120,
      WeightRange(301, 400) -> 200,
      WeightRange(401, 500) -> 300,
      WeightRange(501, 600) -> 500,
      WeightRange(601, 700) -> 600,
      WeightRange(701, 800) -> 800,
      WeightRange(801, 900) -> 950,
      WeightRange(901, 1000) -> 950,
    ))

    val BEPrices = PricesRange(Map(
      WeightRange(0, 100) -> 100,
      WeightRange(101, 200) -> 120,
      WeightRange(201, 300) -> 300,
      WeightRange(301, 400) -> 330,
      WeightRange(401, 500) -> 360,
      WeightRange(501, 600) -> 390,
      WeightRange(601, 700) -> 500,
      WeightRange(701, 800) -> 550,
      WeightRange(801, 900) -> 600,
      WeightRange(901, 1000) -> 800,
    ))

    val DEPrices = PricesRange(Map(
      WeightRange(0, 100) -> 40,
      WeightRange(101, 200) -> 80,
      WeightRange(201, 300) -> 130,
      WeightRange(301, 400) -> 400,
      WeightRange(401, 500) -> 450,
      WeightRange(501, 600) -> 500,
      WeightRange(601, 700) -> 550,
      WeightRange(701, 800) -> 600,
      WeightRange(801, 900) -> 700,
      WeightRange(901, 1000) -> 850,
    ))

    Map("NL" -> NLPrices, "BE" -> BEPrices, "DE" -> DEPrices)
  }

}
