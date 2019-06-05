package edu.cftic.sql_app.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dto.Listado;

public class ViewItemActivity extends AppCompatActivity {

    public static final String JSON_LISTADO = "JSON_LISTADO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        Intent intent = getIntent();
        if( null == intent) { return; }
        String jsonListado = intent.getStringExtra( JSON_LISTADO);
        if( null == jsonListado) { return; }
        Gson gson = new Gson();
        Listado listado = gson.fromJson( jsonListado, Listado.class);

        TextView persona = findViewById( R.id.idPersonaViewListado);
        persona.setText( listado.getPersona());
        TextView coche = findViewById( R.id.idCocheViewListado);
        coche.setText( listado.getCoche());
    }
}
