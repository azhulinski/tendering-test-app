package services

import dao.ProvidersDAO
import data.WeightRange

import javax.inject._

@Singleton
class ShipmentServiceData @Inject()(providersDAO: ProvidersDAO) extends ShipmentService {

  override def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]] = {
    val pricesA = providersDAO.getProviderA
    val pricesB = providersDAO.getProviderB
    val pricesC = providersDAO.getProviderC

    for {
      singleRow <- rows
      pricesA <- pricesA(singleRow("Country")).prices.collect {
        case (range@WeightRange(_: Int, _: Int), price) if range.contains(singleRow("Weight").toInt) =>
          singleRow + ("Provider A" -> price.toString)
      }
      pricesB <- pricesB(singleRow("Country")).prices.collect {
        case (range@WeightRange(_: Int, _: Int), price) if range.contains(singleRow("Weight").toInt) =>
          pricesA + ("Provider B" -> price.toString)
      }
      pricesC <- pricesC(singleRow("Country")).prices.collect {
        case (range@WeightRange(_: Int, _: Int), price) if range.contains(singleRow("Weight").toInt) =>
          pricesB + ("Provider C" -> price.toString)
      }
    } yield pricesC

  }
}
