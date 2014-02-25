package jp.teres.numa.termcalc

import android.app.Fragment
import android.view._
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TextView

import java.util.Calendar

import org.scaloid.common._

import jp.teres.numa.DateCalculator._
import jp.teres.numa.utils.Conversions._

class TermCalcFragment extends Fragment {
	
	override def onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstance : Bundle) : View = {	
		inflater.inflate(R.layout.term_calc, null)
	}

  override def onViewCreated(view : View, savedInstance : Bundle) : Unit = {

    List(R.id.start_date, 
        R.id.end_date).map(view.findViewById(_))
                      .collect{case t : TextView => t}
                      .map { t=> 
                        val date = DateFormat.format("yyyy/MM/dd", Calendar.getInstance)
                        t.setHint(date)
                        t
                      }.foreach { t =>
                        t.onClick(/*open dialog*/)
                      }
    view.findViewById(R.id.calc)
        .asOpt[View]
        .foreach { b =>
          b.onClick(/* calc*/)
        }
  }
}
