package edu.cftic.sql_app.dto;

public class Listado implements Comparable<Listado> {
    private String persona;
    private String coche;
    public Listado(String persona, String coche) {
        this.persona = persona;
        this.coche = coche;
    }

    public String getPersona() {
        return persona;
    }

    public String getCoche() {
        return coche;
    }

    @Override
    public int compareTo(Listado o) {
        if( 0 == (this.getPersona().compareTo(o.getPersona()))) {
            return this.getCoche().compareTo(o.getCoche());
        }
        return this.getPersona().compareTo(o.getPersona());
    }
}
