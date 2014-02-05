package jp.teres.numa.termcalc

import android.app.{Fragment, FragmentManager}
import android.view._
import android.widget._
import android.os.Bundle

import java.util.{Calendar, Locale}

import jp.teres.numa.DateCalculator._
import jp.teres.numa.utils.Conversions._

import ajd4jp.AJD

abstract class SelectDate extends Fragment with DatePicker.OnDateChangedListener{

	var mPicker : Option[DatePicker] = _

	override def onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstance : Bundle) : View = {
		val content = inflater.inflate(R.layout.select_data, null)
		val title = Option(getArguments()).map(_.getInt("title"))
		val text = content.findViewById(R.id.title).asOpt[TextView]

		for {
			ti <- title
			te <- text
		} yield {
			te.setText(ti)
		}

		mPicker = content.findViewById(R.id.date).asOpt[DatePicker].map {picker => 
			val today = Calendar.getInstance(Locale.JAPAN)
			val y = today.get(Calendar.YEAR)
			val m = today.get(Calendar.MONTH)
			val d = today.get(Calendar.DAY_OF_MONTH)
			picker.init(y, m, d, this)
			picker
		}
		
		content
	}

	override def onDateChanged(picker : DatePicker, year : Int, monthOfYear : Int, dayOfMonth : Int) : Unit = {}

	val ajd = () => {
		mPicker.map {p =>
			new AJD(p.getYear, p.getMonth + 1, p.getDayOfMonth)
		}
	}
}

class StartDate extends SelectDate {

	override def onResume : Unit = {
		super.onResume
		Option(getActivity).collect {case a : MainActivity => a}
						   .foreach {_.startDate = ajd}		
	}

}
object StartDate extends SelectDate{

	def apply() = {
		val f = new StartDate
		val args = new Bundle()
		args.putInt("title", R.string.start_date)
		f.setArguments(args)

		f
	}
}

class EndDate extends SelectDate {

	override def onResume : Unit = {
		super.onResume
		Option(getActivity).collect { case a : MainActivity => a}
	 					   .foreach {_.endDate = ajd}		
	}

}
object EndDate extends SelectDate {

	def apply() = {
		val f = new EndDate
		val args = new Bundle()
		args.putInt("title", R.string.end_date)
		f.setArguments(args)

		f
	}
}