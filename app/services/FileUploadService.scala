package services

import com.github.tototoshi.csv.CSVReader
import data.{CountryProviderAllocation, CountryProviderCost, Grid}

import java.util.Base64
import javax.inject.Inject
import scala.io.Source

class FileUploadService @Inject()(
  shipmentService: ShipmentService,
  calculationService: CalculationService
) {

  private val providerColumns = Seq("Provider A", "Provider B", "Provider C")
  private val optimizationColumns = Seq(
    "Best Provider",
    "Best Price",
    "Best 2-Suppliers",
    "Best 2-Price",
    "Best 3-Suppliers",
    "Best 3-Price"
  )

  def calculatePrices(content: String): Grid = {
    uploadFile(content)
  }

  private def uploadFile(content: String): Grid = {
    val decoded = Base64.getDecoder.decode(content)
    val source = Source.fromBytes(decoded)
    val opened = CSVReader.open(source)

    try {
      val data = opened.allWithOrderedHeaders()
      val pricedRows = shipmentService.calculatePrices(data._2)
      val rows = addSupplierCombinationOptimizations(pricedRows)

      Grid(
        rows = rows,
        columns = data._1 ++ providerColumns ++ optimizationColumns,
        countrySummary = buildCountrySummary(rows),
        cherryPickSummary = buildCherryPickSummary(rows)
      )
    } finally {
      opened.close()
      source.close()
    }
  }

  private def addSupplierCombinationOptimizations(rows: List[Map[String, String]]): List[Map[String, String]] = {
    rows.map { row =>
      val prices = Map(
        "Provider A" -> row("Provider A").toInt,
        "Provider B" -> row("Provider B").toInt,
        "Provider C" -> row("Provider C").toInt
      )

      val bestTwoSupplierCombination = calculationService.bestTwoSupplierCombination(prices)
      val threeSupplierCombination = calculationService.threeSupplierCombination(prices)

      row ++ Map(
        "Best 2-Suppliers" -> bestTwoSupplierCombination._1.mkString(", "),
        "Best 2-Price" -> bestTwoSupplierCombination._2.toString,
        "Best 3-Suppliers" -> threeSupplierCombination._1.mkString(", "),
        "Best 3-Price" -> threeSupplierCombination._2.toString
      )
    }
  }

  private def buildCountrySummary(rows: List[Map[String, String]]): List[CountryProviderCost] = {
    rows
      .groupBy(_("Country"))
      .toList
      .sortBy(_._1)
      .map { case (country, countryRows) =>
        CountryProviderCost(
          country = country,
          providerA = sumColumn(countryRows, "Provider A"),
          providerB = sumColumn(countryRows, "Provider B"),
          providerC = sumColumn(countryRows, "Provider C")
        )
      }
  }

  private def buildCherryPickSummary(rows: List[Map[String, String]]): List[CountryProviderAllocation] = {
    rows
      .groupBy(_("Country"))
      .toList
      .sortBy(_._1)
      .map { case (country, countryRows) =>
        val allocatedSpend = countryRows
          .groupBy(_("Best Provider"))
          .view
          .mapValues(providerRows => providerRows.map(_("Best Price").toInt).sum)
          .toMap

        val providerA = allocatedSpend.getOrElse("Provider A", 0)
        val providerB = allocatedSpend.getOrElse("Provider B", 0)
        val providerC = allocatedSpend.getOrElse("Provider C", 0)

        CountryProviderAllocation(
          country = country,
          providerA = providerA,
          providerB = providerB,
          providerC = providerC,
          total = providerA + providerB + providerC
        )
      }
  }

  private def sumColumn(rows: List[Map[String, String]], column: String): Int = {
    rows.map(row => row(column).toInt).sum
  }
}
