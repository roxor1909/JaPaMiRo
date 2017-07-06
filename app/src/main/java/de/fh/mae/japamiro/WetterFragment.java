package de.fh.mae.japamiro;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class WetterFragment extends Fragment {

    private ListView listView;
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
        //listView = (ListView) view.findViewById(R.id.wListView);
        textView = (TextView) view.findViewById(R.id.textName);
        Bundle args = getArguments();
        this.startID = args.getLong(MainActivity.EXTRA_ID, -1);
        if (startID != -1) {
            String name = dbHelper.getNameFromId(startID);
            textView.setText(name);
        }
        List<Weather> weatherList = null;
        Log.i("WindVerlaufFragment", "onAttach");
        Context context = inflater.getContext();
        /*
        try {
            XMLPullParserHandler parser = new XMLPullParserHandler();
            weatherList = parser.parse(context.getAssets().open("wind_history.xml"));
            ArrayAdapter<Weather> adapter =
                    new ArrayAdapter<Weather>(context, R.layout.list_item, weatherList);

            listView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        TableRow row = new TableRow(tableLayout.getContext());
        row.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //ToDo add padding and Margin to row and textView
        TextView textView = new TextView(this.getContext());
        textView.setText("Test");
        row.addView(textView);
        TextView textView2 = new TextView(this.getContext());
        textView2.setText("Test");
        row.addView(textView2);
        TextView textView3 = new TextView(this.getContext());
        textView3.setText("Test");
        row.addView(textView3);
        TextView textView4 = new TextView(this.getContext());
        textView4.setText("Test");
        row.addView(textView4);
        tableLayout.addView(row);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.dbHelper.close();
    }


}
