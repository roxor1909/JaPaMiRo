package de.fh.mae.japamiro;

import android.provider.BaseColumns;

/**
 * Contract class welche alle Schemas der Datenbank und weitere Konstanten enthält
 */

public final class AppContract {

    private AppContract() {
    }

    public static class ProfilEntry implements BaseColumns {
        //ToDo profil mit selectedWIndrichtung boolean usw
        public static final String TABLE_NAME = "profile";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_WINDRICHTUNG = "windrichtung";
        public static final String COLUMN_NAME_SELECTED_WINDRICHTUNG = "selected_windrichtung";
        public static final String COLUMN_NAME_MIN_WINDGESCHWINDIGKEIT = "min_wind";
        public static final String COLUMN_NAME_SELECTED_MIN_WIND = "selected_min_wind";
        public static final String COLUMN_NAME_ZEITRAUM = "zeitraum";
        public static final String COLUMN_NAME_SELECTED_ZEITRAUM = "selected_zeitraum";
        public static final String COLUMN_NAME_AKKU_WARNUNG = "akku_Warnung";
        public static final String COLUMN_NAME_SELECTED_WARNUNG = "selected_warnung";
        public static final String COLUMN_NAME_STATION = "station";
        public static final String COLUMN_NAME_MIN_TEMP = "min_temperatur";
        public static final String COLUMN_NAME_SELECTED_MIN_TEMP = "selected_min_temp";
    }
}
