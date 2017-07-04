package de.fh.mae.japamiro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class WindVerlaufActivity extends AppCompatActivity {

    ListView listView;
    private AppDbHelper dbHelper;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        dbHelper = new AppDbHelper(this);
        setContentView(R.layout.activity_wind_verlauf);

        listView = ( ListView ) findViewById( R.id.wListView );

        List<Weather> weatherList = null;

        try {
                XMLPullParserHandler parser = new XMLPullParserHandler();
                weatherList = parser.parse( getAssets().open( "wind_history.xml" ) );
            ArrayAdapter<Weather> adapter =
                    new ArrayAdapter<Weather>( this, R.layout.list_item, weatherList );

            listView.setAdapter(adapter);

        } catch ( Exception e ) { e.printStackTrace(); }

        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.EXTRA_ID)) {
            long id = intent.getLongExtra(MainActivity.EXTRA_ID, -1);
            String name = dbHelper.getNameFromId(id);
            TextView textView = (TextView) findViewById(R.id.textName);
            textView.setText(name);
        }
    }
}
