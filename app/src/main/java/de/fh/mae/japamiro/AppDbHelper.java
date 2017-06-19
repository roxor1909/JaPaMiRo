package de.fh.mae.japamiro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    // ToDo nach Finalisierung des Datenmodells erneut anpassen
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProfilEntry.TABLE_NAME + "( " + ProfilEntry._ID + " INTEGER PRIMARY KEY, " + ProfilEntry.COLUMN_NAME_NAME + " TEXT)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ProfilEntry.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "App.db";


    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ToDo Updatestrategie überlegen und implementieren
        // Momentan wird einfach alles gelöscht
        db.execSQL(SQL_CREATE_ENTRIES);
        onCreate(db);
    }

    public void addProfile(Profil profil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProfilEntry.COLUMN_NAME_NAME, profil.getName());
        long newRowId = db.insert(ProfilEntry.TABLE_NAME, null, values);
        Log.i("add-profiles", Long.toString(newRowId));
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
}
