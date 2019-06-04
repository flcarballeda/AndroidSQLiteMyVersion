package edu.cftic.sql_app.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dao.BaseDatosCochesPersona;
import edu.cftic.sql_app.dto.Listado;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recView;

    private ArrayList<Listado> datos;

    private AdapterListado adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recView = (RecyclerView) findViewById(R.id.recView);

        BaseDatosCochesPersona baseDatosCochesPersona;
        baseDatosCochesPersona = new BaseDatosCochesPersona(this, "MiDB", null, 1);
        //consulto los coches de la persona
        ArrayList<Listado> listacoches = (ArrayList<Listado>) baseDatosCochesPersona.listarDatos();

        Log.d(getClass().getCanonicalName(), "Los coches registrados son :");
        if (null != listacoches) {
            for (Listado coche : listacoches) {
                Log.d(getClass().getCanonicalName(), coche.getPersona() + " -> " + coche.getCoche());
            }
            datos = listacoches;
        } else {
            Log.d(getClass().getCanonicalName(), getResources().getString(R.string.strNoCars));
        }
        adaptador = new AdapterListado(datos);

        recView.setAdapter(adaptador);

        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));    }
}
