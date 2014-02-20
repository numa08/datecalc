package jp.teres.numa.DateCalculator;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

class MainTabListener<T extends Fragment> implements ActionBar.TabListener{

    final Context context;
    final Class<T> clazz;

    Fragment mFragment;

    public MainTabListener(Context context, Class<T> clazz) {
        this.context = context;
        this.clazz = clazz;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (mFragment == null) {
            mFragment = Fragment.instantiate(context, clazz.getName());
            fragmentTransaction.add(android.R.id.content, mFragment);
        } else {
            fragmentTransaction.attach(mFragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (mFragment == null) {
            return;
        }
        fragmentTransaction.detach(mFragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
}
