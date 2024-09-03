package services

import dao.ProvidersDAO
import data.WeightRange

import javax.inject._

@Singleton
class ShipmentServiceData @Inject()(val providersDAO: ProvidersDAO) extends ShipmentService {

  override def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]] = {
    val pricesA = providersDAO.getProviderA
    val pricesB = providersDAO.getProviderB
    val pricesC = providersDAO.getProviderC

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
