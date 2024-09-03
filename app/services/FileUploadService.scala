package services

import com.github.tototoshi.csv.CSVReader
import data.Grid

import java.util.Base64
import javax.inject.Inject
import scala.io.Source

class FileUploadService @Inject()(shipmentService: ShipmentServiceData) {

  def calculatePrices(content: String): Grid = {
    uploadFile(content)
  }

  private def uploadFile(content: String): Grid = {

    val decoded = Base64.getDecoder.decode(content)
    val source = Source.fromBytes(decoded)
    val opened = CSVReader.open(source)
    val data = opened.allWithOrderedHeaders()

    val rows = shipmentService.calculatePrices(data._2)

    Grid(rows, data._1 ++ Seq("Provider A", "Provider B", "Provider C"))
  }

}
