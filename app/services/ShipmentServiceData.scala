package services

import dao.ProvidersDAO
import data.{PricesRange, WeightRange}

import javax.inject._

@Singleton
class ShipmentServiceData @Inject()(providersDAO: ProvidersDAO) extends ShipmentService {

  override def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]] = {
    val pricesA = providersDAO.getProviderA
    val pricesB = providersDAO.getProviderB
    val pricesC = providersDAO.getProviderC

    rows.map { singleRow =>
      val country = singleRow("Country")
      val weight = singleRow("Weight").toInt

      val providerAPrice = priceFor(pricesA(country), weight)
      val providerBPrice = priceFor(pricesB(country), weight)
      val providerCPrice = priceFor(pricesC(country), weight)

      val providerPrices = Map(
        "Provider A" -> providerAPrice,
        "Provider B" -> providerBPrice,
        "Provider C" -> providerCPrice
      )

      val bestProvider = providerPrices.minBy(_._2)

      singleRow ++ Map(
        "Provider A" -> providerAPrice.toString,
        "Provider B" -> providerBPrice.toString,
        "Provider C" -> providerCPrice.toString,
        "Best Provider" -> bestProvider._1,
        "Best Price" -> bestProvider._2.toString
      )
    }
  }

  private def priceFor(pricesRange: PricesRange, weight: Int): Int = {
    pricesRange.prices.collectFirst {
      case (range @ WeightRange(_, _), price) if range.contains(weight) => price
    }.getOrElse {
      throw new IllegalArgumentException(s"No provider price found for weight [$weight]")
    }
  }
}
