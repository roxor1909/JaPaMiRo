package de.fh.mae.japamiro;

/**
 * Created by shaidex on 03.07.2017.
 */

public class Weather {

    private String zeit;
    private int luftdruck;
    private double temperatur;
    private double regen;

    public Weather(){ }

      // set

    public void setZeit( String zeit ) { this.zeit = zeit; }

    public void setLuftdruck( int luftdruck ) { this.luftdruck = luftdruck; }

    public void setTemperatur( double temperatur ) { this.temperatur = temperatur; }

    public void setRegen( double regen ) { this.regen = regen; }

    // get

    public String getZeit() { return zeit; }

    public int getLuftdruck() { return luftdruck; }

    public double getTemperatur() { return temperatur; }

    public double getRegen() { return regen; }

    @Override
    public String toString() { return  zeit + "  " + luftdruck + "  hPa  " +
                              temperatur + " °C  " + regen + " %  "; }
}




