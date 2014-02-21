package jp.teres.numa.termcalc

import android.app.Fragment
import android.view._
import android.os.Bundle

import jp.teres.numa.DateCalculator._

class TermCalcFragment extends Fragment {
	
	override def onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstance : Bundle) : View = {	
		inflater.inflate(R.layout.term_calc, null)
	}

}