package jp.teres.numa.termcalc

import android.app._
import android.view._
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.{TextView, Toast, DatePicker} 

import java.util.{Calendar, Date}
import scala.concurrent.future
import scala.concurrent.ExecutionContext.Implicits.global

import org.scaloid.common._

import ajd4jp.AJD

import jp.teres.numa.DateCalculator._
import jp.teres.numa.utils.Conversions._
import jp.teres.numa.termcalc.calculator.TermCalclator._

class TermCalcFragment extends Fragment {

  var startDate : Option[AJD] = _
  var endDate : Option[AJD] = _

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
                        val listener = t.getId match {
                          case R.id.start_date => startDateSet
                          case _ => endDateSet
                        }
                          val d = new AJD()
                        t.onClick{
                          new DatePickerDialog(getActivity, listener,d.getYear , d.getMonth - 1, d.getDay).show
                        }
                      }
    view.findViewById(R.id.calc)
        .asOpt[View]
        .foreach { b =>
          b.onClick {v => 
            ((startDate, endDate) match {
              case (Some(s),Some(e)) => calcDate(s, e)
              case _ => showError
            })()
          }
        }
  }

  val calcDate = (start : AJD, end : AJD) => () => {
    future {
      val term = start.diff(end, false)
      getActivity.runOnUiThread {
        Toast.makeText(getActivity, s"${term}", Toast.LENGTH_SHORT).show
      }
    }
  }

  val showError = () => {
    Toast.makeText(getActivity, "日付の入力をお願いします", Toast.LENGTH_SHORT).show
  }

  val startDateSet = new DatePickerDialog.OnDateSetListener {
    override def onDateSet(view : DatePicker, year : Int, monthOfYear : Int, dayOfMonth : Int) = {
        startDate = Option(new AJD(year, monthOfYear + 1, dayOfMonth))
        for {
          tv <- getView.findViewById(R.id.start_date).asOpt[TextView]
          d  <- startDate
        } yield {
          val date = new Date(d.getTime)
          tv.setText(DateFormat.format("yyyy/MM/dd", date))
        }
    }
  }

  val endDateSet = new DatePickerDialog.OnDateSetListener {
    override def onDateSet(view : DatePicker, year : Int, monthOfYear : Int, dayOfMonth : Int) = {
        endDate = Option(new AJD(year, monthOfYear + 1, dayOfMonth))
        for {
          tv <- getView.findViewById(R.id.end_date).asOpt[TextView]
          d  <- endDate
        } yield {
          val date = new Date(d.getTime)
          tv.setText(DateFormat.format("yyyy/MM/dd", date))
        }

    }
  }

}