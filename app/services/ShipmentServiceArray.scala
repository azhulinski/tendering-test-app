package services

import dao.ProvidersDAO

import javax.inject._

@Singleton
class ShipmentServiceArray @Inject()(providersDAO: ProvidersDAO) extends ShipmentService {

  override def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]] = {
    val pricesA = providersDAO.getAnotherA
    val pricesB = providersDAO.getAnotherB
    val pricesC = providersDAO.getAnotherC

    rows.map { singleRow =>
      val country = singleRow("Country")
      val weight = singleRow("Weight").toInt

      val providerAPrice = pricesA(country)(weight)
      val providerBPrice = pricesB(country)(weight)
      val providerCPrice = pricesC(country)(weight)

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
}
