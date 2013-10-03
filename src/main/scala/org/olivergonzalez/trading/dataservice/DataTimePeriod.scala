package org.olivergonzalez.trading.dataservice

/**
 * Defines enumeration for time periods
 */
sealed trait DataTimePeriod
case object Daily extends DataTimePeriod
case object Weekly extends DataTimePeriod
case object Monthly extends DataTimePeriod


