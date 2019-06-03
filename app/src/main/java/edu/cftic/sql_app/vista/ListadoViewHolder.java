package edu.cftic.sql_app.vista;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dto.Listado;

public class ListadoViewHolder extends RecyclerView.ViewHolder {
    private TextView text_view_persona;
    private TextView text_view_coche;

    public ListadoViewHolder (View itemView) {

        super(itemView);
        text_view_coche = (TextView)itemView.findViewById(R.id.LblPersona);
        text_view_persona = (TextView)itemView.findViewById(R.id.LblCoche);
    }


    public void cargarCocheEnHolder(Listado l) {
        text_view_persona.setText(l.getPersona());
        text_view_coche.setText(l.getCoche());
    }
}
