package de.fh.mae.japamiro;

import java.util.Date;

/**
 * Created by shaidex on 03.07.2017.
 */

public class Weather {

    private String zeit;
    private double luftdruck;
    private double temperatur;
    private double regen;
    private String windrichtung;
    private double k_m_h;
    private int bft;
    private double knoten;
    private double m_s;

    public Weather() {
    }

    public String getZeit() {
        return zeit;
    }

    public void setZeit(String zeit) {
        this.zeit = zeit;
    }

    public double getLuftdruck() {
        return luftdruck;
    }

    public void setLuftdruck(double luftdruck) {
        this.luftdruck = luftdruck;
    }

    public double getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(double temperatur) {
        this.temperatur = temperatur;
    }

    public double getRegen() {
        return regen;
    }

    public void setRegen(double regen) {
        this.regen = regen;
    }

    public String getWindrichtung() {
        return windrichtung;
    }

    public void setWindrichtung(String windrichtung) {
        this.windrichtung = windrichtung;
    }

    public double getK_m_h() {
        return k_m_h;
    }

    public void setK_m_h(double k_m_h) {
        this.k_m_h = k_m_h;
    }

    public int getBft() {
        return bft;
    }

    public void setBft(int bft) {
        this.bft = bft;
    }

    public double getKnoten() {
        return knoten;
    }

    public void setKnoten(double knoten) {
        this.knoten = knoten;
    }

    public double getM_s() {
        return m_s;
    }

    public void setM_s(double m_s) {
        this.m_s = m_s;
    }

    @Override
    public String toString() {
        return zeit + "  " + luftdruck + "  hPa  " +
                temperatur + " °C  " + regen + " %  ";
    }
}




