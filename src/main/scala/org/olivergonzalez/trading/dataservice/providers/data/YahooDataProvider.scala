package org.olivergonzalez.trading.dataservice.providers.data

import org.olivergonzalez.trading.dataservice.data._
import org.olivergonzalez.trading.dataservice._
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import java.text.SimpleDateFormat
import java.util.Calendar
import dispatch._

/**
 * Implements Yahoo data provider
 */
class YahooDataProvider extends DataProvider {
  private val YAHOO_ICHART_URL = "http://ichart.yahoo.com/table.csv?"

  override def prices(query: PriceQuery) : List[Price] = {
    def pricesFromPayload(payload: String) = removeHeaders(payload.lines.toList).map(parsePriceLines)

    def removeHeaders(lines: List[String]) = lines.filterNot(line => line.startsWith("Date"))

    def parsePriceLines(line: String) = {
      val items = line.split(",")

      val date = new SimpleDateFormat("yyyy-MM-dd").parse(items(0))
      val calendar = Calendar.getInstance()
      calendar.setTime(date)

      new Price(query.symbol, items(1).toDouble, items(4).toDouble, items(2).toDouble, items(3).toDouble, items(5).toLong, calendar)
    }

    val request = buildIChartRequest(query)

    val response: Either[Throwable, String] = Http(request OK as.String).either()
    response match {
      case Right(payload) => pricesFromPayload(payload)
      case Left(e) => throw new DataProviderException(s"Unavailable data from Yahoo. ${e.getMessage}", e)
    }
  }

  override def dividends(query: DividendQuery) : List[Dividend] = ???

  override def securityInfo(query: SecurityQuery) : Security = ???

  private def buildIChartRequest(query: PriceQuery) = {
    val formattedRequest =
      s"${YAHOO_ICHART_URL}" +
      s"s=${query.symbol}" +
      s"&a=${month(query.from)}" +
      s"&b=${day(query.from)}" +
      s"&c=${year(query.from)}" +
      s"&d=${month(query.to)}" +
      s"&e=${day(query.to)}" +
      s"&f=${year(query.to)}" +
      s"&g=${period(query.period)}"

    url(formattedRequest)
  }

  private def period(period: DataTimePeriod) = period match {
    case Daily => "d"
    case Weekly => "w"
    case Monthly => "m"
    case _ => throw new UnsupportedOperationException("Unexpected time period")
  }

  private def day(calendar: Calendar) = calendar.get(Calendar.DAY_OF_MONTH)

  private def month(calendar: Calendar) = calendar.get(Calendar.MONTH)

  private def year(calendar: Calendar) = calendar.get(Calendar.YEAR)
}
