package de.fh.mae.japamiro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL(SQL_CREATE_ENTRIES);onCreate(db);
    }
}
