package edu.cftic.sql_app.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.List;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dao.BaseDatosCochesPersona;
import edu.cftic.sql_app.dto.Coche;
import edu.cftic.sql_app.dto.Persona;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creo el objeto de la base de datos

    }

    public void cargaInicial(View v) {

        BaseDatosCochesPersona baseDatosCochesPersona;
        baseDatosCochesPersona = new BaseDatosCochesPersona(this, "MiDB", null, 1);
        if (null == baseDatosCochesPersona.buscarPersona("Juan")) {
            Persona persona1 = new Persona(1, "Juan");
            Persona persona2 = new Persona(2, "Conchi");
            Persona persona3 = new Persona(3, "Manolo");

            //inserto las personas
            baseDatosCochesPersona.insertarPersona(persona1);
            baseDatosCochesPersona.insertarPersona(persona2);
            baseDatosCochesPersona.insertarPersona(persona3);

            Coche coche1 = new Coche("Ferrari", persona2);
            Coche coche2 = new Coche("Renault", persona2);
            Coche coche3 = new Coche("Fiat", persona1);

            //inserto los coches
            baseDatosCochesPersona.insertarCoche(coche1);
            baseDatosCochesPersona.insertarCoche(coche2);
            baseDatosCochesPersona.insertarCoche(coche3);
        }
        Button boton = findViewById(R.id.buttonLoadCars);
        boton.setEnabled(true);
    }

    public void buscarCochesPersona(View v) {
        EditText campo = findViewById( R.id.textViewOwnerName);
        BaseDatosCochesPersona baseDatosCochesPersona;
        baseDatosCochesPersona = new BaseDatosCochesPersona(this, "MiDB", null, 1);
        Persona persona2 = baseDatosCochesPersona.buscarPersona("Conchi");
        if (null != persona2) {
            //consulto los coches de la persona
            List<Coche> listacoches = baseDatosCochesPersona.buscarCochesPersona(persona2);

            Log.d(getClass().getCanonicalName(), "Los coches de " + persona2.getNombre() + " son :");
            if (null != listacoches) {
                for (Coche coche : listacoches) {
                    Log.d(getClass().getCanonicalName(), coche.getModelo());
                }
            } else {
                Log.d(getClass().getCanonicalName(), getResources().getString(R.string.strNoCars));
            }
        } else {
            Log.d(getClass().getCanonicalName(), getResources().getString(R.string.strUnknownOwner));
        }
    }
}
