package services

class CalculationService {

  def calculatePriceForSuppliers(shipments: List[Map[String, String]], suppliersPrices: List[Map[String, Int]]) = {

    suppliersPrices.map { suppliers =>

      var bestSuppliersPair = List.empty[(String, Int)]

      var minShipmentCost = Int.MaxValue
      var minSupplierPrice = Int.MaxValue

      var totalCost = 0

      val supplierPairsCombinations = suppliers.toList.combinations(2).toList :+ suppliers.toList
      var minCostForShipment = 0
      supplierPairsCombinations.foreach { combination =>

        minCostForShipment = combination.map(_._2).min

        val priceFromCombination = combination.unzip match {
          case (_, price) => price.sum
        }

        if (minCostForShipment < minSupplierPrice) {
          minSupplierPrice = minCostForShipment
        }

        if (priceFromCombination < minShipmentCost) {
          minShipmentCost = priceFromCombination
          bestSuppliersPair = combination
        }
        totalCost += minShipmentCost
      }

      (bestSuppliersPair, minShipmentCost, minSupplierPrice)
    }
  }
}
