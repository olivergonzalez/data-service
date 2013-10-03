package org.olivergonzalez.trading.dataservice.providers.data

/**
 * Generic exception to handle errors
 */
class DataProviderException(message: String, e: Throwable) extends Throwable(message, e)