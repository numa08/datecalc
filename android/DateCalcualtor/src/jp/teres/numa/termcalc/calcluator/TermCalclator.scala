package jp.teres.numa.termcalc.calculator

import ajd4jp.{AJD,Holiday}

object TermCalclator {
	implicit class TermCalclator(val day : AJD) extends AnyVal{
		
		def diff (that : AJD, isExcludeHoliday : Boolean) : Int = {
			val diff = day.compareTo(that)
			diff match {
				case 0 => 0 // same day
				case n if n > 0 => 1
				case n if n < 0 => -1
			}
			
			// if(!isExcludeHoliday){
			// 	diff
			// } else {
			// 	diff match {
			// 		case 0 => 0
			// 		case n if n > 0 => {
			// 			(for( i <- 1 until n) yield{that.addDay(i)}).map{Holiday.getHoliday(_)}
			// 														.collect{case h : Holiday => h}
			// 														.size
			// 		}
			// 		case n => {
			// 			(for( i <- n - 1 to 1 by - 1) yield {day.addDay(i)}).map{Holiday.getHoliday(_)}
			// 																.collect{case h : Holiday => h}
			// 																.size
			// 		}
			// 	}
			// }
		}
	}
}