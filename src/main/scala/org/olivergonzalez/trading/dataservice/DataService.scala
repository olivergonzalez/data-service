package org.olivergonzalez.trading.dataservice

import org.olivergonzalez.trading.dataservice.data._
import org.olivergonzalez.trading.dataservice.providers.persistence.{NoPersistenceProvider, MongoPersistenceProvider}
import org.olivergonzalez.trading.dataservice.providers.data.{DataProvider, YahooDataProvider}
import org.olivergonzalez.trading.dataservice.data.SecurityQuery
import org.olivergonzalez.trading.dataservice.data.DividendQuery

/**
 * Service for providing information of a security through a data provider
 */
abstract class DataService(dataProvider: DataProvider) {
  def priceQuery(query: PriceQuery) = retrieve(query) match {
    case Nil => dataProvider.prices(query)
    case prices => prices.asInstanceOf[List[Price]]
  }

  def dividendQuery(query: DividendQuery) = retrieve(query) match {
    case Nil => dataProvider.dividends(query)
    case dividends => dividends.asInstanceOf[List[Dividend]]
  }

  def securityQuery(query: SecurityQuery) = retrieve(query) match {
    case Nil => dataProvider.securityInfo(query)
    case securities => securities.head.asInstanceOf[Security]
  }

  def persist(beans: List[Data])

  def retrieve(query: Query) : List[Data]
}

object DataService {
  lazy val defaultInstance = yahooInstanceWithoutPersistence
  lazy val yahooInstanceWithPersistence = new DataService(new YahooDataProvider) with MongoPersistenceProvider
  lazy val yahooInstanceWithoutPersistence = new DataService(new YahooDataProvider) with NoPersistenceProvider
}
