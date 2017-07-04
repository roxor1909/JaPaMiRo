package de.fh.mae.japamiro;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfilActivity extends AppCompatActivity {
    private EditText editTextName;
    private CheckBox cbWindrichtung;
    private CheckBox cbMinWind;
    private Spinner spinnerRichtung;
    private EditText editTextMinWind;
    private CheckBox cbZeitraum;
    private EditText editTextZeitraum;
    private CheckBox cbMinTemp;
    private EditText editTextMinTemp;
    private CheckBox cbAkku;
    private EditText editTextAkku;
    private Spinner spinnerStation;
    private AppDbHelper dbHelper;
    private Button button;
    // safes the id from the intent or -1 wheter the activity has been started without existing profil
    private long startWithID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        initAttributes();
        initTextWatchers();
        initCheckboxListener();
        initSpinner();

        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.EXTRA_ID)) {
            long id = intent.getLongExtra(MainActivity.EXTRA_ID, -1);
            this.startWithID = id;
            Log.i("ProfilActivity", "start Activity with id= " + id);
            //ToDO DIe Daten laden und ausfüllen
            Profil profil = this.dbHelper.getProfilFromId(id);
            initViewWithProfil(profil);
        } else {
            this.startWithID = -1;
            Log.i("ProfilActivity", "start Activity without id");
        }

        this.updateSubmitButton(findViewById(R.id.buttonProfilAnlegen));
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapterRichtung = ArrayAdapter.createFromResource(this,
                R.array.windrichtung, android.R.layout.simple_spinner_item);
        adapterRichtung.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerRichtung.setAdapter(adapterRichtung);

        ArrayAdapter<CharSequence> adapterStation = ArrayAdapter.createFromResource(this,
                R.array.stationen_namen, android.R.layout.simple_spinner_item);
        adapterStation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerStation.setAdapter(adapterStation);
    }

    // prüft ob alle Felder gültig ausgefüllt sind
    private boolean checkProfil() {
        boolean booleanName = editTextName.getText().length() != 0;
        //Log.i("ProfilActivity", "BooleanName: " + Boolean.toString(booleanName));

        boolean booleanWindrichtung = true;
        if (cbWindrichtung.isChecked()) {
            // Prüfe ob ein gültiger Wert bei der Windrichung eingetragen ist
            String richtung = spinnerRichtung.getSelectedItem().toString();
            booleanWindrichtung = richtung.length() != 0;
        }
        //Log.i("ProfilActivity", "BooleanWindrichtung: " + Boolean.toString(booleanWindrichtung));

        boolean booleanMinWind = true;
        if (cbMinWind.isChecked()) {
            // Prüfe ob ein gültiger Wert bei der Mindestgeschwindigkeit eingegeben wurde
            booleanMinWind = this.editTextMinWind.getText().length() != 0;
        }
        //Log.i("ProfilActivity", "BooleanMindWind: " + booleanMinWind);

        boolean booleanZeitraum = true;
        if (cbZeitraum.isChecked()) {
            booleanZeitraum = this.editTextZeitraum.getText().length() != 0;
        }
        //Log.i("ProfilActivity", "BooleanZeitraum: " + booleanZeitraum);

        boolean booleanMinTemp = true;
        if (cbMinTemp.isChecked()) {
            booleanMinTemp = this.editTextMinTemp.getText().length() != 0;
        }
        //Log.i("ProfilActivity", "BooleanMinTemp: " + booleanMinTemp);

        boolean booleanAkku = true;
        if (cbAkku.isChecked()) {
            booleanAkku = this.editTextAkku.getText().length() != 0;
        }
        //Log.i("ProfilActivity", "BooleanAkku: " + booleanAkku);

        boolean booleanGesamt = booleanName && booleanWindrichtung && booleanMinWind && booleanZeitraum && booleanMinTemp && booleanAkku;
        //Log.i("ProfilActivity", "Booleangesamt: " + booleanGesamt);
        return booleanGesamt;
    }

    public void updateSubmitButton(View view) {
        Button submitButton = (Button) findViewById(R.id.buttonProfilAnlegen);
        submitButton.setClickable(this.checkProfil());
        submitButton.setEnabled(this.checkProfil());
    }

    private void initAttributes() {
        this.editTextName = (EditText) findViewById(R.id.editTextName);
        this.cbWindrichtung = (CheckBox) findViewById(R.id.checkBoxRichtung);
        this.cbMinWind = (CheckBox) findViewById(R.id.checkBoxMinWind);
        this.editTextMinWind = (EditText) findViewById(R.id.editTextMinWind);
        this.spinnerRichtung = (Spinner) findViewById(R.id.spinnerRichtung);
        this.cbZeitraum = (CheckBox) findViewById(R.id.checkBoxZeitraum);
        this.editTextZeitraum = (EditText) findViewById(R.id.editTextZeitraum);
        this.cbMinTemp = (CheckBox) findViewById(R.id.checkBoxMinTemp);
        this.editTextMinTemp = (EditText) findViewById(R.id.editTextMinTemp);
        this.cbAkku = (CheckBox) findViewById(R.id.checkBoxAkku);
        this.editTextAkku = (EditText) findViewById(R.id.editTextAkku);
        this.spinnerStation = (Spinner) findViewById(R.id.spinnerStation);
        this.button = (Button) findViewById(R.id.buttonProfilAnlegen);

        dbHelper = new AppDbHelper(this);
    }

    private void initTextWatchers() {
        // Sorgt dafür das bei jedem Tastendruck der Zustand des Button ggf. angepasst wird
        this.editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProfilActivity.this.updateSubmitButton(editTextName);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.editTextAkku.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProfilActivity.this.updateSubmitButton(editTextAkku);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.editTextMinWind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProfilActivity.this.updateSubmitButton(editTextMinWind);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.editTextMinTemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProfilActivity.this.updateSubmitButton(editTextMinTemp);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.editTextZeitraum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProfilActivity.this.updateSubmitButton(editTextZeitraum);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initCheckboxListener() {
        this.cbMinWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ProfilActivity.this.editTextMinWind.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(ProfilActivity.this.editTextMinWind, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ProfilActivity.this.editTextMinWind.getWindowToken(), 0);
                }
            }
        });

        this.cbZeitraum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ProfilActivity.this.editTextZeitraum.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(ProfilActivity.this.editTextZeitraum, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ProfilActivity.this.editTextZeitraum.getWindowToken(), 0);
                }
            }
        });

        this.cbMinTemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ProfilActivity.this.editTextMinTemp.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(ProfilActivity.this.editTextMinTemp, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ProfilActivity.this.editTextMinTemp.getWindowToken(), 0);
                }
            }
        });

        this.cbAkku.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ProfilActivity.this.editTextAkku.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(ProfilActivity.this.editTextAkku, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ProfilActivity.this.editTextAkku.getWindowToken(), 0);
                }
            }
        });
    }

    public void zeigeInfos(View view) {
        Log.i("ProfilActivity", "zeige Info, Intent send ");
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void pressButton(View view) {
        if (this.startWithID >= 0) {
            this.updateProfil(view);
        } else {
            this.profilErstellen(view);
        }
    }

    //ToDo Das Auslesen der Profils in eine Methode ausgliedern
    //ToDo Die Werte in den nicht ausgeählten Feldern trotzdem speichern

    private void updateProfil(View view) {
        String name = this.editTextName.getText().toString();
        boolean selectedRichtung = this.cbWindrichtung.isChecked();
        String richtung = this.spinnerRichtung.getSelectedItem().toString();

        boolean selectedMinWind = this.cbMinWind.isChecked();
        int minWind = selectedMinWind ? Integer.parseInt(this.editTextMinWind.getText().toString()) : 0;

        boolean selectedZeitraum = this.cbZeitraum.isChecked();
        int zeitraum = selectedZeitraum ? Integer.parseInt(this.editTextZeitraum.getText().toString()) : 0;

        boolean selectedMinTemp = this.cbMinTemp.isChecked();
        int minTemp = selectedMinTemp ? Integer.parseInt(this.editTextMinTemp.getText().toString()) : 0;

        boolean selectedWarnung = this.cbAkku.isChecked();
        int warnung = selectedWarnung ? Integer.parseInt(this.editTextAkku.getText().toString()) : 0;

        String station = this.spinnerStation.getSelectedItem().toString();

        Profil profil = new Profil(name, richtung, selectedRichtung, minWind, selectedMinWind, zeitraum, selectedZeitraum, minTemp, selectedMinTemp, warnung, selectedWarnung, station);
        int ergebnis = this.dbHelper.updateProfil(profil, this.startWithID);
        if (ergebnis == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Das Profil wurde nicht geändert! ";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Das Profil wurde erfolgreich geändert!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private void profilErstellen(View view) {
        Log.i("ProfilActivity", "profilErstellen");
        String name = this.editTextName.getText().toString();
        boolean selectedRichtung = this.cbWindrichtung.isChecked();
        String richtung = this.spinnerRichtung.getSelectedItem().toString();

        boolean selectedMinWind = this.cbMinWind.isChecked();
        int minWind = selectedMinWind ? Integer.parseInt(this.editTextMinWind.getText().toString()) : 0;

        boolean selectedZeitraum = this.cbZeitraum.isChecked();
        int zeitraum = selectedZeitraum ? Integer.parseInt(this.editTextZeitraum.getText().toString()) : 0;

        boolean selectedMinTemp = this.cbMinTemp.isChecked();
        int minTemp = selectedMinTemp ? Integer.parseInt(this.editTextMinTemp.getText().toString()) : 0;

        boolean selectedWarnung = this.cbAkku.isChecked();
        int warnung = selectedWarnung ? Integer.parseInt(this.editTextAkku.getText().toString()) : 0;

        String station = this.spinnerStation.getSelectedItem().toString();

        Profil profil = new Profil(name, richtung, selectedRichtung, minWind, selectedMinWind, zeitraum, selectedZeitraum, minTemp, selectedMinTemp, warnung, selectedWarnung, station);
        Log.i("ProfilActivity", "profilErstellen");
        Log.i("ProfilActivity", profil.toString());
        long rowId = -2;
        try {
            rowId = this.dbHelper.addProfile(profil);
        } catch (SQLException e) {
            Log.e("ProfilActivity", e.toString());
            Log.i("ProfilActivity", e.toString().contains("profile.name") + "");
            if (e.toString().contains("profile.name")) {
                // Es ist ein Feler aufgetreten
                Context context = getApplicationContext();
                CharSequence text = "Der Profilname wird bereits verwendet!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        Log.i("ProfilActivity", Long.toString(rowId));
        if (rowId < 0) {
            // Es ist ein Feler aufgetreten
        } else {
            // Erfolgreich angelegt
            Context context = getApplicationContext();
            CharSequence text = "Profil erfolgreich angelegt!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private void initViewWithProfil(Profil profil) {
        this.editTextName.setText(profil.getName());
        this.cbWindrichtung.setChecked(profil.isSelectedWindrichtung());
        this.spinnerRichtung.setSelection(this.getIndex(this.spinnerRichtung, profil.getWindrichtung()));
        this.cbMinWind.setChecked(profil.isSelectedMinWindgeschwindigkeit());
        this.editTextMinWind.setText(profil.getMinWindgeschwindigkeit() + "");
        this.cbZeitraum.setChecked(profil.isSelectedZeitraum());
        this.editTextZeitraum.setText(profil.getZeitraum() + "");
        this.cbMinTemp.setChecked(profil.isSelectedMinTemperatur());
        this.editTextMinTemp.setText(profil.getMinTemperatur() + "");
        this.cbAkku.setChecked(profil.isSelectedWarnung());
        this.editTextAkku.setText(profil.getWarnung() + "");
        this.spinnerStation.setSelection(this.getIndex(this.spinnerStation, profil.getStation()));
        this.button.setText("Update Profil");
    }

    private int getIndex(Spinner spinner, String myString) {
        int index = -1;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
