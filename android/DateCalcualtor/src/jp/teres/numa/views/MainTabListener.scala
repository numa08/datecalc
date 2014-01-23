package jp.teres.numa.views

import android.content.Context
import android.app.ActionBar.{Tab , TabListener}
import android.app.{Fragment, FragmentTransaction}

class MainTabListener[T <: Fragment](ctx : Context,clazz : Class[T]) extends TabListener {

	private var mFragment : Fragment = _
	
	override def onTabReselected(tab : Tab, ft : FragmentTransaction) {}

	override def onTabSelected(tab : Tab, ft : FragmentTransaction) {
		mFragment = Option(mFragment).map {fr =>
			ft.attach(fr)
			fr
		}.getOrElse {
			val fragment = Fragment.instantiate(ctx, clazz.getName)
			ft.add(android.R.id.content, fragment)
			fragment
		}

	}

	override def onTabUnselected(tab : Tab, ft : FragmentTransaction) {
		Option(mFragment).foreach (ft.detach(_))
	}
} 