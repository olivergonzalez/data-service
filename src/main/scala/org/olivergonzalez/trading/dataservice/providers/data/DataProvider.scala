package org.olivergonzalez.trading.dataservice.providers.data

import org.olivergonzalez.trading.dataservice.data._
import org.olivergonzalez.trading.dataservice.data.Security
import org.olivergonzalez.trading.dataservice.data.Price
import org.olivergonzalez.trading.dataservice.data.PriceQuery
import org.olivergonzalez.trading.dataservice.data.SecurityQuery

/**
 * Defines all available data providers
 */
trait DataProvider {
  def prices(query: PriceQuery) : List[Price]

  def dividends(query: DividendQuery) : List[Dividend]

  def securityInfo(query: SecurityQuery) : Security
}
