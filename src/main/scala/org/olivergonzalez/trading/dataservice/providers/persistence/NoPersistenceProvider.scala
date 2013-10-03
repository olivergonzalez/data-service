package org.olivergonzalez.trading.dataservice.providers.persistence

import org.olivergonzalez.trading.dataservice.data.{Query, Data}

/**
 * Dummy implementation for providers requiring persistence by contract
 */
trait NoPersistenceProvider {
  def persist(bean: List[Data]) = ???

  def retrieve(query: Query) : List[Data] = Nil
}
