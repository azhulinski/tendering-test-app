import org.scalatest.PrivateMethodTester
import org.scalatest.flatspec.AnyFlatSpec
import services.FileUploadService

class CalculationSpec extends AnyFlatSpec with PrivateMethodTester {

  val uploadService = new FileUploadService

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

}
