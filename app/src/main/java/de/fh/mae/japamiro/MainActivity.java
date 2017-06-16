package de.fh.mae.japamiro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fh.mae.japamiro.AppContract.*;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    public List<String> profilname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("onCreate", "on Create");
        this.readProfilesFromDatabase();
        this.initListView();
    }

    private void readProfilesFromDatabase() {
        Log.i("read profiles", "beginn");
        String[] projection = {ProfilEntry.COLUMN_NAME_NAME};
        //ToDo laut doku getContext() wird aber nicht gefunden
        AppDbHelper dbHelper = new AppDbHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(ProfilEntry.TABLE_NAME, projection, null, null, null, null, null, null);
        List profilNamen = new ArrayList<String>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(ProfilEntry.COLUMN_NAME_NAME));
            profilNamen.add(name);
        }
        cursor.close();
        Log.i("read profiles", profilNamen.toString());
        this.profilname = profilNamen;
    }

    private void addProfilesToDatabase(String name) {
        AppDbHelper dbHelper = new AppDbHelper(this);
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProfilEntry.COLUMN_NAME_NAME, name);
        long newRowId = db.insert(ProfilEntry.TABLE_NAME, null, values);
        Log.i("add-profiles", Long.toString(newRowId));
    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.listView);
        StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, profilname);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Log.i("init-list", Integer.toString(position));
                Log.i("init-list", Long.toString(id));
            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
