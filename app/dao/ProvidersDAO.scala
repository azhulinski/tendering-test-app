package dao

import data.PricesRange
import data.Providers.ProviderGenerator

class ProvidersDAO {

  def getProviderA: Map[String, PricesRange] = {
    ProviderGenerator.generateProviderA()
  }

  def getProviderB: Map[String, PricesRange] = {
    ProviderGenerator.generateProviderB()
  }

  def getProviderC: Map[String, PricesRange] = {
    ProviderGenerator.generateProviderC()
  }

  def getAnotherA: Map[String, Array[Int]] = {
    ProviderGenerator.generateAnotherA()
  }

  def getAnotherB: Map[String, Array[Int]] = {
    ProviderGenerator.generateAnotherB()
  }

  def getAnotherC: Map[String, Array[Int]] = {
    ProviderGenerator.generateAnotherC()
  }

}
