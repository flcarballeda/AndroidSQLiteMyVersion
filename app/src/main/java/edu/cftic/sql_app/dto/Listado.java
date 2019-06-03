package edu.cftic.sql_app.dto;

public class Listado {
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
}
