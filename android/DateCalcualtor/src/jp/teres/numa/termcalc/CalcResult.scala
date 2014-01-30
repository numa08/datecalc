package jp.teres.numa.termcalc

import android.app.Fragment
import android.view._
import android.widget._
import android.os.Bundle

import jp.teres.numa.DateCalculator._
import jp.teres.numa.utils.Conversions._

case class CalcResult extends Fragment {

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
		}

		content
	}
}