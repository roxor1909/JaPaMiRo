package de.fh.mae.japamiro;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class WetterFragment extends Fragment {

    private TextView textView;
    private AppDbHelper dbHelper;
    private long startID;
    private TableLayout tableLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbHelper = new AppDbHelper(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_wetter, group, false);
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayoutWetter);
        textView = (TextView) view.findViewById(R.id.textName);
        Bundle args = getArguments();
        this.startID = args.getLong(MainActivity.EXTRA_ID, -1);
        if (startID != -1) {
            String name = dbHelper.getNameFromId(startID);
            textView.setText(name);
        }
        Context context = inflater.getContext();
        List<Weather> liste = this.dbHelper.getWeather();
        for (Weather w : liste) {
            if (w != null)
                this.addWeather(context, w);
        }
        this.addWeather(context, null);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.dbHelper.close();
    }


    private void addWeather(Context context, Weather w) {
        if (w != null) {
            TableRow row = new TableRow(context);
            row.setOrientation(LinearLayout.VERTICAL);
            TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);

            Resources r = getResources();
            float margin2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());
            float margin6 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics());
            float margin4 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, r.getDisplayMetrics());

            rowParams.setMargins(Math.round(margin2), 0, 0, Math.round(margin2));
            row.setLayoutParams(rowParams);

            TableRow.LayoutParams textParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
            textParams.setMargins(Math.round(margin4), 0, 0, Math.round(margin4));
            TextView textView = new TextView(this.getContext());
            textView.setText(w.getZeit());

            textView.setPaddingRelative(Math.round(margin6), Math.round(margin6), Math.round(margin6), Math.round(margin6));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textView.setLayoutParams(textParams);
            row.addView(textView);

            TextView textView2 = new TextView(this.getContext());
            textView2.setText(formatDouble(w.getLuftdruck()) + "hPa");
            textView2.setPaddingRelative(Math.round(margin6), Math.round(margin6), Math.round(margin6), Math.round(margin6));
            textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            TableRow.LayoutParams textParams2 = textParams;
            textView2.setLayoutParams(textParams2);
            row.addView(textView2);

            TextView textView3 = new TextView(this.getContext());
            textView3.setText(formatDouble(w.getTemperatur()) + "°C");
            textView3.setPaddingRelative(Math.round(margin6), Math.round(margin6), Math.round(margin6), Math.round(margin6));
            textView3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textView3.setLayoutParams(textParams);

            row.addView(textView3);

            TextView textView4 = new TextView(this.getContext());
            textView4.setText(formatDouble(w.getRegen()) + "%");
            textView4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textView4.setPaddingRelative(Math.round(margin6), Math.round(margin6), Math.round(margin6), Math.round(margin6));
            textView4.setLayoutParams(textParams);
            row.addView(textView4);
            tableLayout.addView(row);
        }
    }

    private String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(d);
    }


}
