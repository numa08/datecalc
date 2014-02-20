package jp.teres.numa.DateCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;

import jp.teres.numa.DateCalculator.jp.teres.numa.tercalc.TermCalcParent;

public class MainActivity extends ActionBarActivity {

    private static final List<TitleWithFragment> FRAGMENT_WITH_TITLES = new ArrayList<TitleWithFragment>(){{
        add(new TitleWithFragment<>(R.string.tab_title_term_calc, TermCalcParent.class));
        add(new TitleWithFragment<>(R.string.tab_title_date_calc, Fragment.class));
        add(new TitleWithFragment<>(R.string.tab_title_calc_history, Fragment.class));
    }};


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);

        for (TitleWithFragment titleWithFragment : FRAGMENT_WITH_TITLES) {
            final ActionBar.Tab tab = actionBar.newTab()
                                                .setText(titleWithFragment.titleId)
                                                .setTabListener(new MainTabListener<Fragment>(this, titleWithFragment.fragment));
            actionBar.addTab(tab);
        }
    }


    private static class TitleWithFragment<T extends  Fragment> {
        final int titleId;
        final Class<T> fragment;

        public TitleWithFragment(int titleId, Class<T> fragment) {
            this.titleId = titleId;
            this.fragment = fragment;
        }
    }
}
