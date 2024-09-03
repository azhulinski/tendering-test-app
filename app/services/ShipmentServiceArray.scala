package services

import data.Providers.ProviderGenerator

import javax.inject._

@Singleton
class ShipmentServiceArray extends ShipmentService {

  override def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]] = {
    val pricesA = ProviderGenerator.generateAnotherA()
    val pricesB = ProviderGenerator.generateAnotherB()
    val pricesC = ProviderGenerator.generateAnotherC()

    rows.map(singleRow => {
      singleRow + ("Provider A" -> pricesA(singleRow("Country"))(singleRow("Weight").toInt).toString) +
        ("Provider B" -> pricesB(singleRow("Country"))(singleRow("Weight").toInt).toString) +
        ("Provider C" -> pricesC(singleRow("Country"))(singleRow("Weight").toInt).toString)
    })
  }
}
