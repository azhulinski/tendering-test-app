package services

import com.github.tototoshi.csv.CSVReader
import data.Providers.ProviderGenerator
import data.{Grid, WeightRange}

import java.util.Base64
import scala.io.Source

class FileUploadService {

  def calculatePrices(content: String): Grid = {
    uploadFile(content)
  }

  private def uploadFile(content: String): Grid = {

    val decoded = Base64.getDecoder.decode(content)
    val source = Source.fromBytes(decoded)
    val opened = CSVReader.open(source)
    val data = opened.allWithOrderedHeaders()

    val rows = calculatePrices(data._2)

    Grid(rows, data._1 ++ Seq("Provider A", "Provider B", "Provider C"))
  }

  private def calculatePrices(rows: List[Map[String, String]]): List[Map[String, String]] = {
    val pricesA = ProviderGenerator.generateProviderA()
    val pricesB = ProviderGenerator.generateProviderB()
    val pricesC = ProviderGenerator.generateProviderC()

    rows.flatMap(singleRow => {
      pricesA(singleRow("Country")).prices.map {
        case (range@WeightRange(_: Int, _: Int), price) => if (range.contains(singleRow("Weight").toInt)) {
          singleRow + ("Provider A" -> price.toString)
        }
      }.filter(_ != ()).asInstanceOf[List[Map[String, String]]].flatMap(singleRow =>
        pricesB(singleRow("Country")).prices.map {
          case (range@WeightRange(_: Int, _: Int), price) => if (range.contains(singleRow("Weight").toInt)) {
            singleRow + ("Provider B" -> price.toString)
          }
        }.filter(_ != ()).asInstanceOf[List[Map[String, String]]].flatMap(singleRow =>
          pricesC(singleRow("Country")).prices.map {
            case (range@WeightRange(_: Int, _: Int), price) => if (range.contains(singleRow("Weight").toInt)) {
              singleRow + ("Provider C" -> price.toString)
            }
          }.filter(_ != ()).asInstanceOf[List[Map[String, String]]]
        )
      )
    })
  }

}
