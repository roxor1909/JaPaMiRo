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

import java.util.List;

public class WindVerlaufFragment extends Fragment {

    ListView listView;
    private AppDbHelper dbHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbHelper = new AppDbHelper(context);
        List<Weather> weatherList = null;
        Log.i("WindVerlaufFragment", "onAttach");
        try {
            XMLPullParserHandler parser = new XMLPullParserHandler();
            weatherList = parser.parse(context.getAssets().open("wind_history.xml"));
            ArrayAdapter<Weather> adapter =
                    new ArrayAdapter<Weather>(context, R.layout.list_item, weatherList);

            listView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.EXTRA_ID)) {
            long id = intent.getLongExtra(MainActivity.EXTRA_ID, -1);
            String name = dbHelper.getNameFromId(id);
            TextView textView = (TextView) findViewById(R.id.textName);
            textView.setText(name);
        }
        */

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {

        View view = inflater.inflate(R.layout.fragment_wind_verlauf, group, false);
        listView = (ListView) view.findViewById(R.id.wListView);
        return view;
    }


}
