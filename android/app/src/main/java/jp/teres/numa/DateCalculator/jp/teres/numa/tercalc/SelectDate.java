package jp.teres.numa.DateCalculator.jp.teres.numa.tercalc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import jp.teres.numa.DateCalculator.R;

abstract public class SelectDate extends Fragment implements DatePicker.OnDateChangedListener{
    public enum SelectDateArgs {
        Start(R.string.title_start_date),
        End(R.string.title_end_date),
        ;
        final int titleId;

        SelectDateArgs(int titleId) {
            this.titleId = titleId;
        }
    }
    private static final String ARGS_TITLE = SelectDate.class.getSimpleName() + ".ARGS_TITLE";

    public static SelectDate createInstance(SelectDateArgs args) {
        final Bundle fragmentArgs = new Bundle();
        fragmentArgs.putInt(SelectDate.ARGS_TITLE, args.titleId);

        final SelectDate fragment;
        if (args == SelectDateArgs.Start) {
            fragment = new StartDate();
        } else {
            fragment = new EndDate();
        }

        fragment.setArguments(fragmentArgs);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.select_data, null);

        final String title;
        if (getArguments() != null) {
            final int resId = getArguments().getInt(SelectDate.ARGS_TITLE);
            if (resId > 0) {
                title = getString(resId);
            } else {
                title = "";
            }
        } else {
            title = "";
        }

        final TextView textView = (TextView)content.findViewById(R.id.title);
        textView.setText(title);

        return content;
    }

    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {}

    public static class StartDate extends SelectDate{

        private StartDate() {}
    }

    public static class EndDate extends SelectDate{

        private EndDate() {}
    }

}
