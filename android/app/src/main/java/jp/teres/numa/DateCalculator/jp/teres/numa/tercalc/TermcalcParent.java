package jp.teres.numa.DateCalculator.jp.teres.numa.tercalc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import jp.teres.numa.DateCalculator.R;

public class TermCalcParent extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewPager viewPager = new ViewPager(getActivity());
        viewPager.setId(R.id.view_pager);
        return viewPager;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        final List<Fragment> fragments = Arrays.asList(SelectDate.createInstance(SelectDate.SelectDateArgs.Start),
                                                       SelectDate.createInstance(SelectDate.SelectDateArgs.End),
                                                       new CalcResult());

        final FragmentPagerAdapter adapter = new TermcalcParentAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    private static class TermcalcParentAdapter extends FragmentPagerAdapter {

        final List<Fragment> fragments;

        public TermcalcParentAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
            super(fragmentManager);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
