package services

import com.google.inject.ImplementedBy
import dao.ProvidersDAO

@ImplementedBy(classOf[ShipmentServiceData])
trait ShipmentService {

  def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]]

}
