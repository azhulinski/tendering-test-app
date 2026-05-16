package services

class CalculationService {

  def bestSupplier(prices: Map[String, Int]): (String, Int) = {
    prices.minBy(_._2)
  }

  def bestTwoSupplierCombination(prices: Map[String, Int]): (Seq[String], Int) = {
    prices
      .toSeq
      .combinations(2)
      .map { combination =>
        val supplierNames = combination.map(_._1)
        val totalCombinationPrice = combination.map(_._2).sum

        supplierNames -> totalCombinationPrice
      }
      .minBy(_._2)
  }

  def threeSupplierCombination(prices: Map[String, Int]): (Seq[String], Int) = {
    prices.keys.toSeq -> prices.values.sum
  }

  def calculatePriceForSuppliers(
    shipments: List[Map[String, String]],
    suppliersPrices: List[Map[String, Int]]
  ): List[(Seq[String], Int, Int)] = {
    shipments
      .zip(suppliersPrices)
      .map { case (_, prices) =>
        val bestCombination = bestTwoSupplierCombination(prices)
        val bestShipmentPrice = bestSupplier(prices)

        (
          bestCombination._1,
          bestCombination._2,
          bestShipmentPrice._2
        )
      }
  }
}
