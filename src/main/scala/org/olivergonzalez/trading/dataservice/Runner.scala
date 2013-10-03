package org.olivergonzalez.trading.dataservice

import org.olivergonzalez.trading.dataservice.data.PriceQuery
import dispatch.Http

/**
 * Temporary runner object for testing purposes
 */
object Runner {
  private val GOOGLE_TICKER = "GOOG"

  private val yahooService: DataService = DataService.defaultInstance

  def main(args: Array[String]) {
    val priceQuery: PriceQuery =
      PriceQuery ofTicker GOOGLE_TICKER withPeriod Daily from "1/1/2013" to "1/2/2013" build

    try yahooService.priceQuery(priceQuery) match {
        case Nil => println("The query did not produce any results")
        case list => println(list.mkString("\n"))
    } catch {
      case e: Throwable => println(e.getMessage)
    }

    Http.client.close()
  }
}
