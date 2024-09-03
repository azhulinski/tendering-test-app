import dao.ProvidersDAO
import org.scalatest.PrivateMethodTester
import org.scalatest.flatspec.AnyFlatSpec
import services.{CalculationService, FileUploadService, ShipmentServiceArray, ShipmentServiceData}

class CalculationSpec extends AnyFlatSpec with PrivateMethodTester {

  val providersDAO = new ProvidersDAO
  val shipmentService = new ShipmentServiceData(providersDAO)
  val shipmentServiceArray = new ShipmentServiceArray(providersDAO)

  val fileUploadService = new FileUploadService(shipmentService)

  val calculationService = new CalculationService

  "calculate prices for providers" should "return correct price" in {

    val priceForBEProviderA = "240"
    val priceForBEProviderB = "250"
    val priceForBEProviderC = "300"

    val shipments = List(Map("Shipment #" -> "1", "Country" -> "BE", "Weight" -> "300"))

    val result = shipmentService.calculatePrices(shipments)

    assertResult(result.head("Provider A"))(priceForBEProviderA)
    assertResult(result.head("Provider B"))(priceForBEProviderB)
    assertResult(result.head("Provider C"))(priceForBEProviderC)
  }

  "calculate prices for providers with object model approach" should "return correct price" in {

    val priceForBEProviderA = "240"
    val priceForBEProviderB = "250"
    val priceForBEProviderC = "300"

    val shipments = List(Map("Shipment #" -> "1", "Country" -> "BE", "Weight" -> "300"))

    val result = shipmentServiceArray.calculatePrices(shipments)

    assertResult(result.head("Provider A"))(priceForBEProviderA)
    assertResult(result.head("Provider B"))(priceForBEProviderB)
    assertResult(result.head("Provider C"))(priceForBEProviderC)
  }

  "file upload" should "decode input data and add three Providers" in {
    val content = "U2hpcG1lbnQgIyxDb3VudHJ5LFdlaWdodA0KMSxOTCwxMDANCjIsQkUsMzAwDQozLERFLDUwMA0K"

    val result = fileUploadService.calculatePrices(content)

    assertResult(result.rows.length)(3)
    assertResult(result.columns.length)(6)
    assertResult(result.rows.head("Country"))("NL")
  }

  "calculate prices for supplier combination" should "return the best price" in {
    val suppliers = List(
      Map("Provider A" -> 50, "Provider B" -> 100, "Provider C" -> 50),
      Map("Provider A" -> 170, "Provider B" -> 190, "Provider C" -> 300),
      Map("Provider A" -> 650, "Provider B" -> 350, "Provider C" -> 950)
    )

    val bestPrices = List(50, 170, 350)

    val bestCosts = List(100, 360, 1000)

    val shipments = List(
      Map("NL" -> "100"),
      Map("NL" -> "500"),
      Map("NL" -> "1000")
    )

    val result = calculationService.calculatePriceForSuppliers(shipments, suppliers)

    result.zipWithIndex.foreach { case (value, index) => assertResult(value._2)(bestCosts(index)) }
    result.zipWithIndex.foreach { case (value, index) => assertResult(value._3)(bestPrices(index)) }
  }

}
