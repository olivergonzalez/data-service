package org.olivergonzalez.trading.dataservice

import org.olivergonzalez.trading.dataservice.providers.data.DataProviderException
import org.olivergonzalez.trading.dataservice.data.PriceQuery
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * DataService Test
 */
@RunWith(classOf[JUnitRunner])
class DataServiceTest extends FunSuite {
  private val GOOGLE_TICKER = "GOOG"

  val yahooService: DataService = DataService.defaultInstance

  test("Tests Yahoo data provider with Google ticker, using daily time period") {
    val priceQuery: PriceQuery =
      PriceQuery ofTicker GOOGLE_TICKER withPeriod Daily from "1/1/2013" to "1/2/2013" build

    try if (yahooService.priceQuery(priceQuery).isEmpty) fail
    catch {
      case e: DataProviderException => fail
    }
  }

  test("Tests Yahoo data provider with Google ticker, using weekly time period") {
    val priceQuery: PriceQuery =
      PriceQuery ofTicker GOOGLE_TICKER withPeriod Weekly from "1/1/2013" to "1/2/2013" build

    try if (yahooService.priceQuery(priceQuery).isEmpty) fail
    catch {
      case e: DataProviderException => fail
    }
  }

  test("Tests Yahoo data provider with Google ticker, using monthly time period") {
    val priceQuery: PriceQuery =
      PriceQuery ofTicker GOOGLE_TICKER withPeriod Monthly from "1/1/2013" to "1/2/2013" build

    try if (yahooService.priceQuery(priceQuery).isEmpty) fail
    catch {
      case e: DataProviderException => fail
    }
  }
}
