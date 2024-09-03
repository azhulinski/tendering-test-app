package services

import com.google.inject.ImplementedBy

@ImplementedBy(classOf[ShipmentServiceData])
trait ShipmentService {

  def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]]

}
