package org.olivergonzalez.trading.dataservice.data

import java.util.Calendar
import java.text.SimpleDateFormat

/**
 * Represents the data to encapsulate the provider response
 */
sealed abstract class Data(symbol: String)

case class Price(symbol: String, open: Double, close: Double, high: Double, low: Double, volume: Long, date: Calendar) extends Data(symbol) {
  val df = new SimpleDateFormat()
  df.applyPattern("dd/MM/yyyy")
  val formattedDate = df.format(date.getTime)

  override def toString = s"Symbol: ${symbol} Open: ${open} Close: ${close} High: ${high} Low: ${low} Volume: ${volume} Date: ${formattedDate}"
}

case class Security(symbol: String) extends Data(symbol: String)
case class Dividend(symbol: String) extends Data(symbol: String)
