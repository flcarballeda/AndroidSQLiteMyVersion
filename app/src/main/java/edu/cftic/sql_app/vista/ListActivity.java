package edu.cftic.sql_app.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dao.BaseDatosCochesPersona;
import edu.cftic.sql_app.dto.ComparatorListado;
import edu.cftic.sql_app.dto.Listado;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private RecyclerView recView;

    private BaseDatosCochesPersona baseDatosCochesPersona;

    private ArrayList<Listado> datos;

    private AdapterListado adaptador;

    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recView = (RecyclerView) findViewById(R.id.recView);

        baseDatosCochesPersona = new BaseDatosCochesPersona(this, "MiDB", null, 1);
        //consulto los coches de la persona
        datos = (ArrayList<Listado>) baseDatosCochesPersona.listarDatos();

        Log.d(getClass().getCanonicalName(), "Los coches registrados son :");
        if (null != datos) {
            for (Listado coche : datos) {
                Log.d(getClass().getCanonicalName(), coche.getPersona() + " -> " + coche.getCoche());
            }
        } else {
            Log.d(getClass().getCanonicalName(), getResources().getString(R.string.strNoCars));
        }
        adaptador = new AdapterListado(datos);

        recView.setAdapter(adaptador);

        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        spinner1 = (Spinner) findViewById(R.id.sortSpinner);
        spinner1.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (pos) {
            case 0: {
                datos = (ArrayList<Listado>) baseDatosCochesPersona.listarDatos();
            } break;
            case 1: {
                Collections.sort(datos);
            } break;
            case 2: {
                Collections.sort( datos, new ComparatorListado());
            } break;
            default: {
                Log.d("MYAPP", "No se conoce el método de ordenación para la posición '" + Integer.toString(pos) + "'.");
            } break;
        }
        adaptador = new AdapterListado(datos);
        recView.setAdapter(adaptador);
//        adaptador.notifyDataSetChanged();
//        recView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
