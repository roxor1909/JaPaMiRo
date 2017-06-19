package de.fh.mae.japamiro;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<String> profilname;
    public AppDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new AppDbHelper(this);
        Log.i("mainActivity", "on Create");
        this.profilname = new ArrayList<String>();
        this.readProfilesFromDatabase();
        this.initListView();
    }

    private void readProfilesFromDatabase() {
        Log.i("read profiles", "beginn");
        List<Profil> list = dbHelper.getProfiles();
        for (Profil p : list) {
            this.profilname.add(p.getName());
        }

    }

    private void addProfilesToDatabase(String name) {

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
