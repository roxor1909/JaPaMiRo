package de.fh.mae.japamiro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class WindVerlaufActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
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
    }
}
