package services

import dao.ProvidersDAO

import javax.inject._

@Singleton
class ShipmentServiceArray @Inject()(val providersDAO: ProvidersDAO) extends ShipmentService {

  override def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]] = {
    val pricesA = providersDAO.getAnotherA
    val pricesB = providersDAO.getAnotherB
    val pricesC = providersDAO.getAnotherC

    rows.map(singleRow => {
      singleRow + ("Provider A" -> pricesA(singleRow("Country"))(singleRow("Weight").toInt).toString) +
        ("Provider B" -> pricesB(singleRow("Country"))(singleRow("Weight").toInt).toString) +
        ("Provider C" -> pricesC(singleRow("Country"))(singleRow("Weight").toInt).toString)
    })
  }
}
