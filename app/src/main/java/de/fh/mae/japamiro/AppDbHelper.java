package de.fh.mae.japamiro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.fh.mae.japamiro.AppContract.*;

/**
 * Created by Robin on 16.06.2017.
 */

public class AppDbHelper extends SQLiteOpenHelper {


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProfilEntry.TABLE_NAME + "( "
                    + ProfilEntry._ID + " INTEGER PRIMARY KEY, "
                    + ProfilEntry.COLUMN_NAME_NAME + " TEXT unique, "
                    + ProfilEntry.COLUMN_NAME_SELECTED_WINDRICHTUNG + " TEXT, "
                    + ProfilEntry.COLUMN_NAME_WINDRICHTUNG + " TEXT, "
                    + ProfilEntry.COLUMN_NAME_AKKU_WARNUNG + " INTEGER, "
                    + ProfilEntry.COLUMN_NAME_SELECTED_WARNUNG + " BOOLEAN, "
                    + ProfilEntry.COLUMN_NAME_MIN_WINDGESCHWINDIGKEIT + " INTEGER, "
                    + ProfilEntry.COLUMN_NAME_SELECTED_MIN_WIND + " BOOLEAN, "
                    + ProfilEntry.COLUMN_NAME_ZEITRAUM + " INTEGER, "
                    + ProfilEntry.COLUMN_NAME_SELECTED_ZEITRAUM + " BOOLEAN, "
                    + ProfilEntry.COLUMN_NAME_MIN_TEMP + " INTEGER, "
                    + ProfilEntry.COLUMN_NAME_SELECTED_MIN_TEMP + " BOOLEAN, "
                    + ProfilEntry.COLUMN_NAME_STATION + " STRING"
                    + ")";
    private static final String SQL_CREATE_WEATHER_ENTRIES =
            "CREATE TABLE " + WeatherEntry.TABLE_NAME + " ( "
                    + WeatherEntry._ID + " INTEGER PRIMARY KEY, "
                    + WeatherEntry.COLUMN_NAME_BFT + " INTEGER, "
                    + WeatherEntry.COLUMN_NAME_METER_PRO_SEKUNDE + " DOUBLE, "
                    + WeatherEntry.COLUMN_NAME_K_M_H + " DOUBLE "
                    + WeatherEntry.COLUMN_NAME_KNOTEN + " DOUBLE, "
                    + WeatherEntry.COLUMN_NAME_WINDRICHTUNG + " TEXT, "
                    + WeatherEntry.COLUMN_NAME_TEMPERATUR + " DOUBLE, "
                    + WeatherEntry.COLUMN_NAME_HUM + " DOUBLE, "
                    + WeatherEntry.COLUMN_NAME_ZEITPUNKT + " DATE "
                    + ")";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ProfilEntry.TABLE_NAME;
    private static final String SQL_DELETE_WEATHER_ENTRIES = "DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME;
    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "App.db";


    public AppDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("AppDbHelper", SQL_CREATE_ENTRIES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_WEATHER_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ToDo Updatestrategie überlegen und implementieren
        // Momentan wird einfach alles gelöscht
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_WEATHER_ENTRIES);
        onCreate(db);
    }

    public long addProfile(Profil profil) throws SQLException {
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProfilEntry.COLUMN_NAME_NAME, profil.getName());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_WINDRICHTUNG, profil.isSelectedWindrichtung());
        values.put(ProfilEntry.COLUMN_NAME_WINDRICHTUNG, profil.getWindrichtung());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_MIN_WIND, profil.isSelectedMinWindgeschwindigkeit());
        values.put(ProfilEntry.COLUMN_NAME_MIN_WINDGESCHWINDIGKEIT, profil.getMinWindgeschwindigkeit());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_ZEITRAUM, profil.isSelectedZeitraum());
        values.put(ProfilEntry.COLUMN_NAME_ZEITRAUM, profil.getZeitraum());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_MIN_TEMP, profil.isSelectedMinTemperatur());
        values.put(ProfilEntry.COLUMN_NAME_MIN_TEMP, profil.getMinTemperatur());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_WARNUNG, profil.isSelectedWarnung());
        values.put(ProfilEntry.COLUMN_NAME_AKKU_WARNUNG, profil.getWarnung());
        values.put(ProfilEntry.COLUMN_NAME_STATION, profil.getStation());
        try {
            id = db.insertOrThrow(ProfilEntry.TABLE_NAME, null, values);

        } finally {
            if (db != null) {
                db.close();
            }
        }
        Log.i("AppDbHelper ", "ID: " + id);
        return id;
    }

    public List<Profil> getProfiles() {
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cursor = sql.query(ProfilEntry.TABLE_NAME, null, null, null, null, null, null, null);
        List<Profil> list = new ArrayList<Profil>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(this.cursorToProfil(cursor));
            cursor.moveToNext();
        }
        return list;
    }

    private Profil cursorToProfil(Cursor cursor) {
        Profil tmp = new Profil();
        tmp.setId(cursor.getInt(cursor.getColumnIndex(ProfilEntry._ID)));
        tmp.setName(cursor.getString(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_NAME)));
        return tmp;
    }

    public Cursor getProfilesCursor() {
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cursor = sql.query(ProfilEntry.TABLE_NAME, null, null, null, null, null, null, null);
        return cursor;
    }

    public Profil getProfilFromId(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                ProfilEntry._ID,
                ProfilEntry.COLUMN_NAME_NAME,
                ProfilEntry.COLUMN_NAME_WINDRICHTUNG,
                ProfilEntry.COLUMN_NAME_SELECTED_WINDRICHTUNG,
                ProfilEntry.COLUMN_NAME_MIN_WINDGESCHWINDIGKEIT,
                ProfilEntry.COLUMN_NAME_SELECTED_MIN_WIND,
                ProfilEntry.COLUMN_NAME_ZEITRAUM,
                ProfilEntry.COLUMN_NAME_SELECTED_ZEITRAUM,
                ProfilEntry.COLUMN_NAME_MIN_TEMP,
                ProfilEntry.COLUMN_NAME_SELECTED_MIN_TEMP,
                ProfilEntry.COLUMN_NAME_AKKU_WARNUNG,
                ProfilEntry.COLUMN_NAME_SELECTED_WARNUNG,
                ProfilEntry.COLUMN_NAME_STATION
        };
        String selection = ProfilEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        Cursor cursor = db.query(
                ProfilEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Profil profil = null;
        while (cursor.moveToNext()) {
            long profil_id = cursor.getLong(cursor.getColumnIndex(ProfilEntry._ID));
            String name = cursor.getString(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_NAME));
            String windrichtung = cursor.getString(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_WINDRICHTUNG));
            boolean sel_richtung = cursor.getInt(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_SELECTED_WINDRICHTUNG)) > 0;
            int min_wind = cursor.getInt(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_MIN_WINDGESCHWINDIGKEIT));
            boolean sel_min_wind = cursor.getInt(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_SELECTED_MIN_WIND)) > 0;
            int zeitraum = cursor.getInt(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_ZEITRAUM));
            boolean sel_zeitraum = cursor.getInt(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_SELECTED_ZEITRAUM)) > 0;
            int min_temp = cursor.getInt(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_MIN_TEMP));
            boolean sel_min_temp = cursor.getInt(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_SELECTED_MIN_TEMP)) > 0;
            int warnung = cursor.getInt(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_AKKU_WARNUNG));
            boolean sel_warnung = cursor.getInt(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_SELECTED_WARNUNG)) > 0;
            String station = cursor.getString(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_STATION));
            profil = new Profil(name, windrichtung, sel_richtung, min_wind, sel_min_wind, zeitraum, sel_zeitraum, min_temp, sel_min_temp, warnung, sel_warnung, station);
            Log.i("AppDbHelper", "getProfilFromID");
            Log.i("AppDbHelper", profil.toString());
        }

        return profil;
    }

    public int updateProfil(Profil profil, long id) throws SQLiteConstraintException {
        Log.i("AppDbHelper", "updateProfile");
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProfilEntry.COLUMN_NAME_NAME, profil.getName());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_WINDRICHTUNG, profil.isSelectedWindrichtung());
        values.put(ProfilEntry.COLUMN_NAME_WINDRICHTUNG, profil.getWindrichtung());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_MIN_WIND, profil.isSelectedMinWindgeschwindigkeit());
        values.put(ProfilEntry.COLUMN_NAME_MIN_WINDGESCHWINDIGKEIT, profil.getMinWindgeschwindigkeit());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_ZEITRAUM, profil.isSelectedZeitraum());
        values.put(ProfilEntry.COLUMN_NAME_ZEITRAUM, profil.getZeitraum());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_MIN_TEMP, profil.isSelectedMinTemperatur());
        values.put(ProfilEntry.COLUMN_NAME_MIN_TEMP, profil.getMinTemperatur());
        values.put(ProfilEntry.COLUMN_NAME_SELECTED_WARNUNG, profil.isSelectedWarnung());
        values.put(ProfilEntry.COLUMN_NAME_AKKU_WARNUNG, profil.getWarnung());
        values.put(ProfilEntry.COLUMN_NAME_STATION, profil.getStation());

        String selection = ProfilEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        int rows = -1;
        try {
            rows = db.update(ProfilEntry.TABLE_NAME, values, selection, selectionArgs);
        } catch (SQLiteConstraintException sqlLiteConstraintException) {
            throw sqlLiteConstraintException;
        }
        return rows;
    }

    public String getNameFromId(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                ProfilEntry.COLUMN_NAME_NAME
        };
        String selection = ProfilEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        Cursor cursor = db.query(
                ProfilEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        cursor.moveToNext();
        return cursor.getString(cursor.getColumnIndex(ProfilEntry.COLUMN_NAME_NAME));
    }

    public long addWeather(Weather weather) throws SQLException {
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeatherEntry.COLUMN_NAME_BFT, weather.getBft());
        values.put(WeatherEntry.COLUMN_NAME_KNOTEN, weather.getKnoten());
        values.put(WeatherEntry.COLUMN_NAME_K_M_H, weather.getK_m_h());
        values.put(WeatherEntry.COLUMN_NAME_METER_PRO_SEKUNDE, weather.getM_s());
        values.put(WeatherEntry.COLUMN_NAME_WINDRICHTUNG, weather.getWindrichtung());
        values.put(WeatherEntry.COLUMN_NAME_TEMPERATUR, weather.getTemperatur());
        values.put(WeatherEntry.COLUMN_NAME_ZEITPUNKT, weather.getZeit().toString());
        values.put(WeatherEntry.COLUMN_NAME_HUM, weather.getLuftdruck());

        try {
            id = db.insertOrThrow(WeatherEntry.TABLE_NAME, null, values);
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return id;
    }

    public List<Weather> getWeather() {
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cursor = sql.query(WeatherEntry.TABLE_NAME, null, null, null, null, null, null, null);
        List<Weather> list = new ArrayList<Weather>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(this.cursorToWeather(cursor));
            cursor.moveToNext();
        }
        return list;
    }

    private Weather cursorToWeather(Cursor cursor) {
        Weather weather = new Weather();
        weather.setBft(cursor.getInt(cursor.getColumnIndex(WeatherEntry.COLUMN_NAME_BFT)));
        weather.setK_m_h(cursor.getInt(cursor.getColumnIndex(WeatherEntry.COLUMN_NAME_K_M_H)));
        weather.setKnoten(cursor.getInt(cursor.getColumnIndex(WeatherEntry.COLUMN_NAME_KNOTEN)));
        weather.setTemperatur(cursor.getDouble(cursor.getColumnIndex(WeatherEntry.COLUMN_NAME_TEMPERATUR)));
        weather.setZeit(cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_NAME_ZEITPUNKT)));
        // ToDo Rest setzen
        return weather;
    }
}
