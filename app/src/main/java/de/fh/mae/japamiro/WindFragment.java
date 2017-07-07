package de.fh.mae.japamiro;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class WindFragment extends Fragment implements View.OnClickListener {
    private TextView textView;
    private TextView anzeige;
    private TextView tZeit;
    private long startID;
    private AppDbHelper dbHelper;
    private Weather aktullesWettter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.dbHelper = new AppDbHelper(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {

        View view = inflater.inflate(R.layout.fragment_wind, group, false);
        textView = (TextView) view.findViewById(R.id.textName);
        anzeige = (TextView) view.findViewById(R.id.anzeige);
        tZeit = (TextView) view.findViewById(R.id.tViewZeit);

        this.setKmh();

        Bundle args = getArguments();
        this.startID = args.getLong(MainActivity.EXTRA_ID, -1);
        if (startID != -1) {
            String name = dbHelper.getNameFromId(startID);
            textView.setText(name);
        }
        initButtonListener(view);
        return view;
    }


    private void initButtonListener(View view) {
        Button kmh = (Button) view.findViewById(R.id.buttonKMH);
        kmh.setOnClickListener(this);
        Button kn = (Button) view.findViewById(R.id.buttonKNOTEN);
        kn.setOnClickListener(this);
        Button bft = (Button) view.findViewById(R.id.buttonBFT);
        bft.setOnClickListener(this);
        Button ms = (Button) view.findViewById(R.id.buttonMS);
        ms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonKMH:
                setKmh();
                break;
            case R.id.buttonBFT:
                setBft();
                break;
            case R.id.buttonKNOTEN:
                setKnoten();
                break;
            case R.id.buttonMS:
                setMs();
                break;
        }
    }

    private void setMs() {
        this.aktullesWettter = this.dbHelper.getAktuellsteWeather();
        if (this.aktullesWettter != null) {
            double ms = this.aktullesWettter.getK_m_h() * 0.27777777777778;
            this.anzeige.setText(formatDouble(ms) + " m/s");
            updateZeit();
        } else {
            this.anzeige.setText("Es sind noch keine Wetteraten vorhanden");
        }
    }

    private void setKnoten() {
        this.aktullesWettter = this.dbHelper.getAktuellsteWeather();
        if (this.aktullesWettter != null) {
            double knoten = aktullesWettter.getK_m_h() * 0.53995680346039;
            this.anzeige.setText(formatDouble(knoten) + " kn");
            updateZeit();
        } else {
            this.anzeige.setText("Es sind noch keine Wetteraten vorhanden");
        }
    }

    private void setBft() {
        this.aktullesWettter = this.dbHelper.getAktuellsteWeather();
        if (this.aktullesWettter != null) {
            int bft;
            double kmh = this.aktullesWettter.getK_m_h();
            if (kmh == 1.0) {
                bft = 0;
            } else if (kmh > 1.0 && kmh <= 5.0) {
                bft = 1;
            } else if (kmh > 5.0 && kmh <= 11.0) {
                bft = 2;
            } else if (kmh > 11.0 && kmh <= 19.0) {
                bft = 3;
            } else if (kmh > 19.0 && kmh <= 28.0) {
                bft = 4;
            } else if (kmh > 28.0 && kmh <= 38.0) {
                bft = 5;
            } else if (kmh > 38.0 && kmh <= 49.0) {
                bft = 6;
            } else if (kmh > 49.0 && kmh <= 61.0) {
                bft = 7;
            } else if (kmh > 61.0 && kmh <= 74.0) {
                bft = 8;
            } else if (kmh > 74.0 && kmh <= 88.0) {
                bft = 9;
            } else if (kmh > 88.0 && kmh <= 102.0) {
                bft = 10;
            } else if (kmh > 102.00 && kmh <= 117.0) {
                bft = 11;
            } else {
                bft = 12;
            }
            this.anzeige.setText(bft + " Beaufort");
            updateZeit();
        } else {
            this.anzeige.setText("Es sind noch keine Wetteraten vorhanden");
        }
    }

    private void setKmh() {
        this.aktullesWettter = this.dbHelper.getAktuellsteWeather();
        if (this.aktullesWettter != null) {
            this.anzeige.setText(aktullesWettter.getK_m_h() + " Km/h");
            updateZeit();
        } else {
            this.anzeige.setText("Es sind noch keine Wetteraten vorhanden");
        }
    }

    private void updateZeit() {
        tZeit.setText("Aktueller Wind (" + aktullesWettter.getZeit() + ")\n");
        Log.e("WindFragment", aktullesWettter.toString());
    }

    private String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(d);
    }

}
