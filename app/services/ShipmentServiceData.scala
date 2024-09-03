package services

import data.Providers.ProviderGenerator
import data.WeightRange

class ShipmentServiceData extends ShipmentService {

  override def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]] =
  {
    val pricesA = ProviderGenerator.generateProviderA()
    val pricesB = ProviderGenerator.generateProviderB()
    val pricesC = ProviderGenerator.generateProviderC()

    rows.flatMap(singleRow => {
      pricesA(singleRow("Country")).prices.map {
        case (range@WeightRange(_: Int, _: Int), price) => if (range.contains(singleRow("Weight").toInt)) {
          singleRow + ("Provider A" -> price.toString)
        }
      }.filter(_ != ()).asInstanceOf[List[Map[String, String]]].flatMap(singleRow =>
        pricesB(singleRow("Country")).prices.map {
          case (range@WeightRange(_: Int, _: Int), price) => if (range.contains(singleRow("Weight").toInt)) {
            singleRow + ("Provider B" -> price.toString)
          }
        }.filter(_ != ()).asInstanceOf[List[Map[String, String]]].flatMap(singleRow =>
          pricesC(singleRow("Country")).prices.map {
            case (range@WeightRange(_: Int, _: Int), price) => if (range.contains(singleRow("Weight").toInt)) {
              singleRow + ("Provider C" -> price.toString)
            }
          }.filter(_ != ()).asInstanceOf[List[Map[String, String]]]
        )
      )
    })
  }
}
