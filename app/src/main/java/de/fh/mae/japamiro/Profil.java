package de.fh.mae.japamiro;

/**
 * Created by Robin on 19.06.2017.
 */

public class Profil {
    private int id;
    private String name;
    private String windrichtung;
    private boolean selectedWindrichtung;
    private Integer minWindgeschwindigkeit;
    private boolean selectedMinWindgeschwindigkeit;
    private Integer zeitraum;
    private boolean selectedZeitraum;
    private Integer minTemperatur;
    private boolean selectedMinTemperatur;
    private Integer warnung;
    private boolean selectedWarnung;
    private String station;

    public Profil() {

    }

    public Profil(String name, String windrichtung, boolean selectedWindrichtung,
                  Integer minWindgeschwindigkeit, boolean selectedMinWindgeschwindigkeit, Integer zeitraum,
                  boolean selectedZeitraum, Integer minTemperatur, boolean selectedMinTemperatur, Integer warnung,
                  boolean selectedWarnung, String station) {
        this.id = id;
        this.name = name;
        this.windrichtung = windrichtung;
        this.selectedWindrichtung = selectedWindrichtung;
        this.minWindgeschwindigkeit = minWindgeschwindigkeit;
        this.selectedMinWindgeschwindigkeit = selectedMinWindgeschwindigkeit;
        this.zeitraum = zeitraum;
        this.selectedZeitraum = selectedZeitraum;
        this.minTemperatur = minTemperatur;
        this.selectedMinTemperatur = selectedMinTemperatur;
        this.warnung = warnung;
        this.selectedWarnung = selectedWarnung;
        this.station = station;
    }

    public String getWindrichtung() {
        return windrichtung;
    }

    public void setWindrichtung(String windrichtung) {
        this.windrichtung = windrichtung;
    }

    public boolean isSelectedWindrichtung() {
        return selectedWindrichtung;
    }

    public void setSelectedWindrichtung(boolean selectedWindrichtung) {
        this.selectedWindrichtung = selectedWindrichtung;
    }

    public Integer getMinWindgeschwindigkeit() {
        return minWindgeschwindigkeit;
    }

    public void setMinWindgeschwindigkeit(Integer minWindgeschwindigkeit) {
        this.minWindgeschwindigkeit = minWindgeschwindigkeit;
    }

    public boolean isSelectedMinWindgeschwindigkeit() {
        return selectedMinWindgeschwindigkeit;
    }

    public void setSelectedMinWindgeschwindigkeit(boolean selectedMinWindgeschwindigkeit) {
        this.selectedMinWindgeschwindigkeit = selectedMinWindgeschwindigkeit;
    }

    public Integer getZeitraum() {
        return zeitraum;
    }

    public void setZeitraum(Integer zeitraum) {
        this.zeitraum = zeitraum;
    }

    public boolean isSelectedZeitraum() {
        return selectedZeitraum;
    }

    public void setSelectedZeitraum(boolean selectedZeitraum) {
        this.selectedZeitraum = selectedZeitraum;
    }

    public Integer getMinTemperatur() {
        return minTemperatur;
    }

    public void setMinTemperatur(Integer minTemperatur) {
        this.minTemperatur = minTemperatur;
    }

    public boolean isSelectedMinTemperatur() {
        return selectedMinTemperatur;
    }

    public void setSelectedMinTemperatur(boolean selectedMinTemperatur) {
        this.selectedMinTemperatur = selectedMinTemperatur;
    }

    public Integer getWarnung() {
        return warnung;
    }

    public void setWarnung(Integer warnung) {
        this.warnung = warnung;
    }

    public boolean isSelectedWarnung() {
        return selectedWarnung;
    }

    public void setSelectedWarnung(boolean selectedWarnung) {
        this.selectedWarnung = selectedWarnung;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ID: " + this.id
                + "\nName " + this.name
                + "\nWindrichtung " + this.windrichtung
                + "\nSelWindrichtung " + this.selectedWindrichtung
                + "\nMinWInd: " + this.minWindgeschwindigkeit
                + "\nSelminWInd: " + this.selectedMinWindgeschwindigkeit
                + "\nZeitraum: " + this.zeitraum
                + "\nMinTemp: " + this.minTemperatur
                + "\nSelMinTemp: " + this.selectedMinTemperatur
                + "\nWarnung: " + this.warnung
                + "\nSelWarnung: " + this.selectedWarnung
                + "\nStation: " + this.station;
    }
}
