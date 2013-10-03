package org.olivergonzalez.trading.dataservice.providers.persistence

import org.olivergonzalez.trading.dataservice.data.{Query, Data}

/**
 * Implements data persistence using MongoDB
 */
trait MongoPersistenceProvider {
  def persist(bean: List[Data]) = ???

  def retrieve(query: Query) : List[Data] = Nil
}
