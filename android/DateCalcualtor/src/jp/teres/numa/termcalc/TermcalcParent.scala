package jp.teres.numa.termcalc

import android.app.{Fragment, FragmentManager}
import android.view._
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v13.app.FragmentPagerAdapter
import android.util.Log

import jp.teres.numa.DateCalculator._
import jp.teres.numa.utils.Conversions._

class TermcalcParent extends Fragment {

	override def onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstance : Bundle) : View = {
		val pager = new ViewPager(getActivity)
		pager.setId(R.id.viewPager)
		pager
	}

	override def onResume : Unit = {
		super.onResume
		val fragments = StartDate() :: EndDate() :: CalcResult() :: Nil
		val adapter = new TermcalcParentAdapter(getFragmentManager, fragments)
		getActivity.findViewById(R.id.viewPager).asOpt[ViewPager].foreach(_.setAdapter(adapter))
	}

	override def onPause : Unit = {
		super.onPause
		getView.findViewById(R.id.viewPager)
					.asOpt[ViewPager]
					.map(_.getAdapter)
					.collect{case a : TermcalcParentAdapter => a}
					.foreach { a=> 
						val transaction = getFragmentManager.beginTransaction
						for( i <- 0 until a.getCount()) {
							transaction.remove(a.getItem(i))
						}
						transaction.commitAllowingStateLoss
					}
	}
}

private class TermcalcParentAdapter(fm : FragmentManager, fragments : List[Fragment]) extends FragmentPagerAdapter(fm) {
	
	override def getCount : Int = fragments.size

	override def getItem(position: Int) : Fragment = fragments(position)	
}