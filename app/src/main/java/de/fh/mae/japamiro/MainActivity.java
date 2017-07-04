package de.fh.mae.japamiro;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDbHelper dbHelper;
    public static final String EXTRA_ID = "de.fh.mae.japamiro.ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new AppDbHelper(this);
        Log.i("mainActivity", "on Create");
        this.initListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.initListView();
    }


    private void initListView() {

        ListView lvProfiles = (ListView) findViewById(R.id.profilListView);
        Cursor cursor = dbHelper.getProfilesCursor();
        ProfilCursorAdapter adapter = new ProfilCursorAdapter(this, cursor);
        lvProfiles.setAdapter(adapter);

        lvProfiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                // The id parameter holds the _id from the Database
                Log.i("MainActivity", "send Intent to Profilactivity with id = " + id);
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                intent.putExtra(MainActivity.EXTRA_ID, id);
                startActivity(intent);
            }

        });

    }


    public void neuesProfilErstellen(View v) {
        Log.i("MainActivity", "neuesProfilErstellen, Intent send");
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }


    public void zeigeInfos(View v) {
        Log.i("MainActivity", "zeige Info, Intent send");
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void zeigeWindHistory( View v ) {
        Intent intent = new Intent( this, WindVerlaufActivity.class);
        startActivity( intent );
    }


    private class ProfilCursorAdapter extends CursorAdapter {

        public ProfilCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.profil_entry, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // Find fields to populate in inflated template
            TextView textView = (TextView) view.findViewById(R.id.profil_name);
            String name = cursor.getString(cursor.getColumnIndexOrThrow(AppContract.ProfilEntry.COLUMN_NAME_NAME));
            textView.setText(name);
        }


    }
}
