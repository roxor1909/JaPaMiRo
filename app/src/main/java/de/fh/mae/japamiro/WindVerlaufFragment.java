package de.fh.mae.japamiro;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class WindVerlaufFragment extends Fragment {
    private TextView textView;
    private long startID;
    private AppDbHelper dbHelper;
    private LineChart lineChart;
    private List<Weather> wetterDaten;


    public WindVerlaufFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbHelper = new AppDbHelper(context);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wind_verlauf, container, false);
        textView = (TextView) view.findViewById(R.id.textName);
        lineChart = (LineChart) view.findViewById(R.id.lineChart);

        this.wetterDaten = this.dbHelper.getWeather();
        List<Entry> entries = new ArrayList<Entry>();
        Log.e("WindVerlaufFragment", wetterDaten.toString());
        for (Weather w : this.wetterDaten) {
            String[] zeitArray = w.getZeit().split(":");
            entries.add(new Entry(Integer.parseInt(zeitArray[1].replace(" ", "")), Math.round(w.getK_m_h())));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Wind");
        dataSet.setDrawFilled(true);
        dataSet.setColor(Color.BLUE);

        LineData data = new LineData(dataSet);

        lineChart.setData(data);
        lineChart.invalidate();

        Bundle args = getArguments();
        this.startID = args.getLong(MainActivity.EXTRA_ID, -1);
        if (startID != -1) {
            String name = dbHelper.getNameFromId(startID);
            textView.setText(name);
        }
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        this.dbHelper.close();
    }

}




