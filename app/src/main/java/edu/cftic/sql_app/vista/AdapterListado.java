package edu.cftic.sql_app.vista;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dto.Listado;

public class AdapterListado extends RecyclerView.Adapter<ListadoViewHolder> {

    private ArrayList<Listado> datos;

    @Override
    public ListadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListadoViewHolder listadoViewHolder = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.layout_listado_item, parent, false);

        listadoViewHolder = new ListadoViewHolder(itemView);

        return listadoViewHolder;
    }

    @Override
    public void onBindViewHolder(ListadoViewHolder holder, int position) {

        Listado libro = datos.get(position);
        holder.cargarCocheEnHolder(libro);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public AdapterListado (ArrayList<Listado> libros)
    {
        this.datos = libros;
    }
}
