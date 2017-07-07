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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

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
        this.initTimer();
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
                Intent intent = new Intent(MainActivity.this, WetterActivity.class);
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

    private void initTimer() {
        Log.e("MainActivity", "Timer init");
        Timer timer = new Timer();
        TimerTask minuteTask = new MinuteTask(this.dbHelper);
        timer.schedule(minuteTask, 0L, 1000 * 60);
    }

    private class MinuteTask extends TimerTask {
        private AppDbHelper dbHelper;
        private Weather last;

        public MinuteTask(AppDbHelper dbHelper) {
            super();
            this.dbHelper = dbHelper;
            this.dbHelper.deleteWheaterEntites();
            this.last = null;
        }

        @Override
        public void run() {
            if (last == null) {
                Weather weather = new Weather();
                Log.e("MainActivity", "Timer run");
                Calendar now = new GregorianCalendar();
                String zeit = now.get(Calendar.HOUR_OF_DAY) + " : " + now.get(Calendar.MINUTE);
                Log.e("MainActivity", zeit);
                weather.setZeit(zeit);

                double temp = ThreadLocalRandom.current().nextDouble(10.00, 30.00);
                weather.setTemperatur(temp);

                double regen = ThreadLocalRandom.current().nextDouble(10.0, 40.0);
                weather.setRegen(regen);

                double luftdruck = ThreadLocalRandom.current().nextDouble(1000.00, 1013.12);
                weather.setLuftdruck(luftdruck);

                double kmh = ThreadLocalRandom.current().nextDouble(15.00, 25.00);
                weather.setK_m_h(kmh);

                last = weather;
                this.dbHelper.addWeather(weather);
            } else {
                Weather weather = new Weather();
                Log.e("MainActivity", "Timer run");
                Calendar now = new GregorianCalendar();
                String zeit = now.get(Calendar.HOUR_OF_DAY) + " : " + now.get(Calendar.MINUTE);
                Log.e("MainActivity", zeit);
                weather.setZeit(zeit);

                double tempSchwankung = ThreadLocalRandom.current().nextDouble(-2.0, 2.0);
                weather.setTemperatur(last.getTemperatur() + tempSchwankung);

                double regenSchwankung = ThreadLocalRandom.current().nextDouble(-3.0, 3.0);
                weather.setRegen(last.getRegen() + regenSchwankung);

                double luftdruckSchwankung = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
                weather.setLuftdruck(last.getLuftdruck() + luftdruckSchwankung);

                double kmh_schwankung = ThreadLocalRandom.current().nextDouble(-2.0, 2.0);
                while (kmh_schwankung == 0.0) {
                    kmh_schwankung = ThreadLocalRandom.current().nextDouble(-2.0, 2.0);
                }
                weather.setK_m_h(last.getK_m_h() + kmh_schwankung);

                last = weather;
                this.dbHelper.addWeather(weather);
            }


        }
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
