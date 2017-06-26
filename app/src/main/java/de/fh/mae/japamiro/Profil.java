package de.fh.mae.japamiro;

/**
 * Created by Robin on 19.06.2017.
 */

public class Profil {
    private int id;
    private String name;

    Profil(){

    }

    public Profil(int id, String name) {
        this.id = id;
        this.name = name;
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
