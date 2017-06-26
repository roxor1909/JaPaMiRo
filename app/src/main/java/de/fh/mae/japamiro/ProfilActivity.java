package de.fh.mae.japamiro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.EXTRA_ID)) {
            long id = intent.getLongExtra(MainActivity.EXTRA_ID, -1);
            Log.i("ProfilActivity", "start Activity with id= " + id);
        } else {
            Log.i("ProfilActivity", "start Activity without id");
        }
    }
}
