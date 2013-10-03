package org.olivergonzalez.trading.dataservice.data

import org.olivergonzalez.trading.dataservice.DataTimePeriod
import java.util.Calendar
import java.text.SimpleDateFormat

/**
 * Represents the data used to encapsulate query parameters
 */
sealed abstract class Query(val symbol: String)
case class PriceQuery(override val symbol: String, val from: Calendar, val to: Calendar, period: DataTimePeriod) extends Query(symbol)
case class DividendQuery(override val symbol: String, val from: Calendar, val to: Calendar) extends Query(symbol)
case class SecurityQuery(override val symbol: String) extends Query(symbol)

object PriceQuery {
  def ofTicker(ticker: String) = new PriceQuery.Builder(Map("ticker" -> ticker))

  final class Builder(arguments: Map[String, AnyRef]) {

    def withPeriod(period: DataTimePeriod) = new Builder(arguments + ("period" -> period))

    def from(from: Calendar) = new Builder(arguments + ("from" -> from))

    def from(from: String) = new Builder(arguments + ("from" -> toCalendar(from)))

    def to(to: Calendar) = new Builder(arguments + ("to" -> to))

    def to(to: String) = new Builder(arguments + ("to" -> toCalendar(to)))

    def build =
      if (missingArguments.isEmpty)
        new PriceQuery(
          arguments("ticker").asInstanceOf[String],
          arguments("from").asInstanceOf[Calendar],
          arguments("to").asInstanceOf[Calendar],
          arguments("period").asInstanceOf[DataTimePeriod]
        )
      else throw new IllegalArgumentException(s"Missing arguments for PriceQuery: ${missingArguments.mkString(", ")}")

    private def missingArguments = List("ticker", "from", "to", "period").filterNot(key => arguments.contains(key))

    private def toCalendar(dateString: String) = {
      val date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString)
      val calendar = Calendar.getInstance()

      calendar.setTime(date)
      calendar
    }
  }
}
