package jp.teres.numa.DateCalculator

import org.scaloid.common._
import android.app.ActionBar
import android.app.ActionBar.Tab
import android.app.Fragment

import jp.teres.numa.views.MainTabListener
import jp.teres.numa.termcalc._

class MainActivity extends SActivity {

  onCreate {
    val actionBar = getActionBar
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS)
    actionBar.setDisplayShowTitleEnabled(true)

    (getResources.getStringArray(R.array.tab_names), MainActivity.fragments).zipped.foreach {(tabName, fragment) => 
    	val tab = actionBar.newTab
    	tab.setText(tabName)
    	tab.setTabListener(new MainTabListener(this, fragment))
    	actionBar.addTab(tab)
    }
  }	
}

object MainActivity {
    lazy val fragments = classOf[TermcalcParent] :: classOf[Fragment] :: classOf[Fragment] :: Nil
}