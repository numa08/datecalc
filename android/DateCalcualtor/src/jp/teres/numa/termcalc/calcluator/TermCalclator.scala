package jp.teres.numa.termcalc.calculator

import android.util.Log
import ajd4jp.{AJD,Holiday}

import scala.annotation.tailrec
import scala.math._

object TermCalclator {
	implicit class TermCalclator(val day : AJD) extends AnyVal with Ordered[AJD]{
		
		def diff (that : AJD, isExcludeHoliday : Boolean) : Int = {

			def allDay(start : AJD, end : AJD) : List[AJD] = {
				@tailrec
				def _allDay(days : List[AJD], start : AJD, end : AJD) : List[AJD] = { start match {
						case start if start == end => days
						case _ => _allDay(days :+ end.addDay(-1), start , end.addDay(-1))
					}
				}
				start match {
					case start if start == end => Nil
					case _ => _allDay(end :: Nil, start.addDay(1), end)
				}
			}

			val days = (day match {
							case day if day < that => allDay(day, that)
							case _ => allDay(that, day)
						}).size
			

			day match {
				case day if day < that => days
				case _ => days * -1
			}

		}

		def compare(that: AJD) : Int = day.compareTo(that)
	}
}