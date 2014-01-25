package jp.teres.numa.termcalc

import android.app.{Fragment, FragmentManager}
import android.view._
import android.widget._
import android.os.Bundle

import jp.teres.numa.DateCalculator._
import jp.teres.numa.utils.Conversions._

class SelectDate private(title : String) extends Fragment {

	override def onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstance : Bundle) : View = {
		val content = inflater.inflate(R.layout.select_data, null)
		val title = Option(getArguments()).map(_.getString("title"))
		val text = content.findViewById(R.id.title).asOpt[TextView]

		for {
			ti <- title
			te <- text
		} yield {
			te.setText(ti)
		}

		content
	}
}

object SelectDate {
	def apply(title: String) = {
		val f = new SelectDate(title)
		val args = new Bundle()
		args.putString("title", title)
		f.setArguments(args)

		f
	}
}