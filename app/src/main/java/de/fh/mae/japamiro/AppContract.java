package de.fh.mae.japamiro;

import android.provider.BaseColumns;

/**
 * Contract class welche alle Schemas der Datenbank und weitere Konstanten enthält
 */

public final class AppContract {

    private AppContract() {
    }

    public static class ProfilEntry implements BaseColumns {
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

    public static class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "wetterdaten";
        public static final String COLUMN_NAME_METER_PRO_SEKUNDE = "Metter_pro_Sekude";
        public static final String COLUMN_NAME_K_M_H = "k_m_h";
        public static final String COLUMN_NAME_KNOTEN = "Knoten";
        public static final String COLUMN_NAME_BFT = "BFT";
        public static final String COLUMN_NAME_WINDRICHTUNG = "Windrichtung";
        public static final String COLUMN_NAME_TEMPERATUR = "Temperatur";
        public static final String COLUMN_NAME_HUM = "Hum";
        public static final String COLUMN_NAME_ZEITPUNKT = "Zeitpunkt";
        public static final String COLUMN_NAME_LUFTDRUCK = "Luftdruck";
    }
}
