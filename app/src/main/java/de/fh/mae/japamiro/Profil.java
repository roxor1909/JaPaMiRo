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
    private Integer mindTemperatur;
    private boolean selectedMinTemperatur;
    private Integer warnung;
    private boolean selectedWarnung;
    private String station;

    Profil() {

    }

    public Profil(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Integer getMindTemperatur() {
        return mindTemperatur;
    }

    public void setMindTemperatur(Integer mindTemperatur) {
        this.mindTemperatur = mindTemperatur;
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
}
