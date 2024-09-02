import org.scalatest.PrivateMethodTester
import org.scalatest.flatspec.AnyFlatSpec
import services.{CalculationService, FileUploadService}

class CalculationSpec extends AnyFlatSpec with PrivateMethodTester {

  val uploadService = new FileUploadService

  val calculationService = new CalculationService

  "calculate prices for providers" should "return correct price" in {

    val priceForBEProviderA = "240"
    val priceForBEProviderB = "250"
    val priceForBEProviderC = "300"

    val shipments = List(Map("Shipment #" -> 1, "Country" -> "BE", "Weight" -> "300"))

    val calculatePrices = PrivateMethod[List[Map[String, String]]](Symbol("calculatePrices"))

    val result = uploadService.invokePrivate(calculatePrices(shipments))

    assertResult(result.head("Provider A"))(priceForBEProviderA)
    assertResult(result.head("Provider B"))(priceForBEProviderB)
    assertResult(result.head("Provider C"))(priceForBEProviderC)
  }

  "calculate prices for providers with object model approach" should "return correct price" in {

    val priceForBEProviderA = "240"
    val priceForBEProviderB = "250"
    val priceForBEProviderC = "300"

    val shipments = List(Map("Shipment #" -> 1, "Country" -> "BE", "Weight" -> "300"))

    val calculatePrices = PrivateMethod[List[Map[String, String]]](Symbol("calculatePricesWithDataModel"))

    val result = uploadService.invokePrivate(calculatePrices(shipments))

    assertResult(result.head("Provider A"))(priceForBEProviderA)
    assertResult(result.head("Provider B"))(priceForBEProviderB)
    assertResult(result.head("Provider C"))(priceForBEProviderC)
  }

  "calculate prices for supplier combination" should "return the best price" in {
    val suppliers = List(
      Map("Provider A" -> 50, "Provider B" -> 100, "Provider C" -> 50),
      Map("Provider A" -> 170, "Provider B" -> 190, "Provider C" -> 300),
      Map("Provider A" -> 650, "Provider B" -> 350, "Provider C" -> 950)
    )

    val shipments = List(
      Map("NL" -> "100"),
      Map("NL" -> "500"),
      Map("NL" -> "1000")
    )

    val result = calculationService.calculatePriceForSuppliers(shipments, suppliers)
    result
  }

}
