package edu.cftic.sql_app.dto;

import java.util.Comparator;

public class ComparatorListado implements Comparator<Listado> {
    @Override
    public int compare(Listado o1, Listado o2) {
        if( 0 == (o1.getCoche().compareTo(o2.getCoche()))) {
            return o1.getPersona().compareTo(o2.getPersona());
        }
        return o1.getCoche().compareTo(o2.getCoche());
    }
}
