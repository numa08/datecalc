package jp.teres.numa.DateCalculator.jp.teres.numa.tercalc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.teres.numa.DateCalculator.R;

public class CalcResult extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.calc_result, null);
        return content;
    }
}
