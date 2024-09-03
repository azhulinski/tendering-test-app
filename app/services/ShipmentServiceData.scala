package services

import data.Providers.ProviderGenerator
import data.WeightRange

import javax.inject._

@Singleton
class ShipmentServiceData extends ShipmentService {

  override def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]] = {
    val pricesA = ProviderGenerator.generateProviderA()
    val pricesB = ProviderGenerator.generateProviderB()
    val pricesC = ProviderGenerator.generateProviderC()

    rows.flatMap(singleRow => {
      pricesA(singleRow("Country")).prices.collect {
        case (range@WeightRange(_: Int, _: Int), price) if range.contains(singleRow("Weight").toInt) =>
          singleRow + ("Provider A" -> price.toString)
      }.flatMap(singleRow => {
        pricesB(singleRow("Country")).prices.collect {
          case (range@WeightRange(_: Int, _: Int), price) if range.contains(singleRow("Weight").toInt) =>
            singleRow + ("Provider B" -> price.toString)
        }
      }).flatMap(singleRow => {
        pricesC(singleRow("Country")).prices.collect {
          case (range@WeightRange(_: Int, _: Int), price) if range.contains(singleRow("Weight").toInt) =>
            singleRow + ("Provider C" -> price.toString)
        }
      })
    })
  }
}
