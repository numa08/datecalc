package jp.teres.numa.termcalc

import android.app.Fragment
import android.view._
import android.widget._
import android.os.Bundle
import android.util.Log

import jp.teres.numa.DateCalculator._
import jp.teres.numa.utils.Conversions._
import jp.teres.numa.termcalc.calculator.TermCalclator._

case class CalcResult extends Fragment with CompoundButton.OnCheckedChangeListener{

	private var showResult : String => Unit = _
	private implicit var mIsExcldeHoliday : Boolean = false

	override def onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstance : Bundle) : View = {
		val content = inflater.inflate(R.layout.calc_result, null)
		content.findViewById(R.id.term).asOpt[TextView].foreach {tv => 
			val text = getResources.getText(R.string.term_of_calc)
								   .toString()
								   .format("2014/01/31", "2014/02/01")
			tv.setText(text)

		}

		content.findViewById(R.id.result).asOpt[TextView].foreach {tv =>
			tv.setText("１日")
			val show = (n : String, tv : TextView) => tv.setText(n.toString)
			showResult = show(_ : String, tv)
		}

		content.findViewById(R.id.is_exclude_holiday).asOpt[CheckBox].foreach(_.setOnCheckedChangeListener(this))

		content
	}

	override def onCheckedChanged (buttonView : CompoundButton, isChecked : Boolean) : Unit = { 
		mIsExcldeHoliday = isChecked
		calcTerm
	}

	def calcTerm : Unit = {
		Log.d("CalcResult", "calc term")
		val isExcludeHoliday : Boolean = getView.findViewById(R.id.is_exclude_holiday)
									  .asOpt[CheckBox]
									  .map(_.isChecked)
									  .getOrElse(false)

		Option(getActivity).collect{ case a : MainActivity => a}
						   .foreach { act =>
						   	for{
						   		start <- act.startDate()
						   		end   <- act.endDate()
						   	} yield {
						   		val diff = start diff(end, isExcludeHoliday)
						   		showResult(s"${diff}")
						   	}
						   }
	}
}