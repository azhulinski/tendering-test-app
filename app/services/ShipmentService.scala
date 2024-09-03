package services

trait ShipmentService {

  def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]]

}
