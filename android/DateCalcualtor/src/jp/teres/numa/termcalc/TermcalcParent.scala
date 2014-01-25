package jp.teres.numa.termcalc

import android.app.{Fragment, FragmentManager}
import android.view._
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v13.app.FragmentPagerAdapter

import jp.teres.numa.DateCalculator._

class TermcalcParent extends Fragment {

	override def onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstance : Bundle) : View = {
		val fragments = getResources().getStringArray(R.array.term_calcs).map(SelectDate(_)).toList

		val adapter = new TermcalcParentAdapter(getFragmentManager, fragments)
		val pager = new ViewPager(getActivity)
		pager.setAdapter(adapter)
		pager.setId(R.id.viewPager)
		pager
	}
}

private class TermcalcParentAdapter(fm : FragmentManager, fragments : List[Fragment]) extends FragmentPagerAdapter(fm) {
	
	override def getCount : Int = {
		fragments.size
	}

	override def getItem(position: Int) : Fragment = {
		fragments(position)
	}
}