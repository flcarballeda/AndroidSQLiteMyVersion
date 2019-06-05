package edu.cftic.sql_app.vista;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dto.Listado;

public class AdapterListado extends RecyclerView.Adapter<ListadoViewHolder> {

    private ArrayList<Listado> datos;

    @Override
    public ListadoViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ListadoViewHolder listadoViewHolder = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.layout_listado_item, parent, false);

        listadoViewHolder = new ListadoViewHolder(itemView);
        listadoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), ViewItemActivity.class);
                Gson gson = new Gson();
                intent.putExtra( ViewItemActivity.JSON_LISTADO, gson.toJson( datos.get( (int)v.getTag())));
                v.getContext().startActivity( intent);
            }
        });

        return listadoViewHolder;
    }

    @Override
    public void onBindViewHolder(ListadoViewHolder holder, int position) {

        Listado libro = datos.get(position);
        holder.cargarCocheEnHolder(libro);
        holder.itemView.setTag( position);
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
