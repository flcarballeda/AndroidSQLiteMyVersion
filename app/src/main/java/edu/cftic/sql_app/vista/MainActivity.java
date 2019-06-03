package edu.cftic.sql_app.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.List;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dao.BaseDatosCochesPersona;
import edu.cftic.sql_app.dto.Coche;
import edu.cftic.sql_app.dto.Listado;
import edu.cftic.sql_app.dto.Persona;

public class MainActivity extends AppCompatActivity {

    private BaseDatosCochesPersona baseDatosCochesPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creo el objeto de la base de datos

        baseDatosCochesPersona = new BaseDatosCochesPersona(this, "MiDB", null, 1);
    }

    public void cargaInicial(View v) {

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
        TextView textResultado = findViewById( R.id.textViewResults);
        String resultado = "";
        if (campo.getText().toString().trim().isEmpty()) {
            final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
            campo.startAnimation(animShake);
            //consulto los coches de la persona
            List<Listado> listacoches = baseDatosCochesPersona.listarDatos();

            Log.d(getClass().getCanonicalName(), "Los coches registrados son :");
            if (null != listacoches) {
                for (Listado coche : listacoches) {
                    resultado += coche.getPersona() + " -> \"" + coche.getCoche() + "\"\n";
                    Log.d(getClass().getCanonicalName(), coche.getPersona() + " -> " + coche.getCoche());
                }
            } else {
                resultado += getResources().getString(R.string.strNoCars) + "\n";
                Log.d(getClass().getCanonicalName(), getResources().getString(R.string.strNoCars));
            }
        } else {
            Persona persona2 = baseDatosCochesPersona.buscarPersona(campo.getText().toString().trim());
            if (null != persona2) {
                //consulto los coches de la persona
                List<Coche> listacoches = baseDatosCochesPersona.buscarCochesPersona(persona2);

                resultado += "Los coches de " + persona2.getNombre() + " son :" + "\n";
                Log.d(getClass().getCanonicalName(), "Los coches de " + persona2.getNombre() + " son :");
                if (null != listacoches) {
                    for (Coche coche : listacoches) {
                        resultado += "\t" + coche.getModelo() + "\n";
                        Log.d(getClass().getCanonicalName(), coche.getModelo());
                    }
                } else {
                    resultado += getResources().getString(R.string.strNoCars) + "\n";
                    Log.d(getClass().getCanonicalName(), getResources().getString(R.string.strNoCars));
                }
            } else {
                resultado += getResources().getString(R.string.strUnknownOwner) + "\n";
                Log.d(getClass().getCanonicalName(), getResources().getString(R.string.strUnknownOwner));
            }
        }
        textResultado.setText( resultado);
    }
}
