package jp.teres.numa.DateCalculator

import org.scaloid.common._
import android.app.ActionBar
import android.app.ActionBar.Tab
import android.app.Fragment
import jp.teres.numa.views.MainTabListener

class MainActivity extends SActivity {

  onCreate {
    val actionBar = getActionBar
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS)
    actionBar.setDisplayShowTitleEnabled(true)

    getResources.getStringArray(R.array.tab_names).foreach {tabName => 
    	val tab = actionBar.newTab
    	tab.setText(tabName)
    	tab.setTabListener(new MainTabListener(this, classOf[Fragment]))
    	actionBar.addTab(tab)
    }
  }	
}
