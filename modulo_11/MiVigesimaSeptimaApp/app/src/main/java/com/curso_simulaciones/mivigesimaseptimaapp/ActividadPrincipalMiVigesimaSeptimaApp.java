package com.curso_simulaciones.mivigesimaseptimaapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.curso_simulaciones.mivigesimaseptimaapp.actividades_secundarias.ActividadDesplegadoraDatos;
import com.curso_simulaciones.mivigesimaseptimaapp.utilidades.Boton;

public class ActividadPrincipalMiVigesimaSeptimaApp extends Activity {


    private Boton consultar, salir;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para crear elementos de la GUI
        crearElementosGUI();

        /*
        Para informar cómo se debe pegar el administrador de
        diseño LinearLayout obtenido con el método crearGui()
        */
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        existenciaSensor();

        eventos();


    }//fin del método onCreate


    private void crearElementosGUI() {

        consultar = new Boton(this);
        consultar.setImagen(R.drawable.consultar);

        salir = new Boton(this);
        salir.setImagen(R.drawable.salir);

    }


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {


        LinearLayout linear_layout_principal = new LinearLayout(this);
        linear_layout_principal.setOrientation(LinearLayout.VERTICAL);
        linear_layout_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.FILL);
        linear_layout_principal.setBackgroundColor(Color.WHITE);
        linear_layout_principal.setWeightSum(10);


        //LinearLayout primera fila
        LinearLayout linear_layout_primera_fila = new LinearLayout(this);
        linear_layout_primera_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_primera_fila.setGravity(Gravity.FILL);
        linear_layout_primera_fila.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams parametros_primera_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);//ViewGroup.LayoutParams.WRAP_CONTENT);
        parametros_primera_fila.weight = 8.0f;
        linear_layout_primera_fila.setLayoutParams(parametros_primera_fila);


        //fondo primera fila
        Drawable fondo = getResources().getDrawable(R.drawable.imagen_entrada_app_27);
        linear_layout_primera_fila.setBackgroundDrawable(fondo);

        //LinearLayout segunda fila
        LinearLayout linear_layout_segunda_fila = new LinearLayout(this);
        linear_layout_segunda_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_fila.setGravity(Gravity.FILL);
        LinearLayout.LayoutParams parametros_segunda_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_segunda_fila.weight = 2.0f;
        linear_layout_segunda_fila.setWeightSum(2.0f);
        linear_layout_segunda_fila.setLayoutParams(parametros_segunda_fila);


        LinearLayout.LayoutParams parametros_pegado_boton = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_boton.weight = 1.0f;
        consultar.setLayoutParams(parametros_pegado_boton);
        salir.setLayoutParams(parametros_pegado_boton);
        linear_layout_segunda_fila.addView(consultar);
        linear_layout_segunda_fila.addView(salir);


        linear_layout_principal.addView(linear_layout_primera_fila);
        linear_layout_principal.addView(linear_layout_segunda_fila);


        return linear_layout_principal;


    }


    private void eventos() {

        consultar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                lanzarDatos();


            }
        });

        salir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                finish();
            }
        });


    }


    private void lanzarDatos() {

        Intent intent = new Intent(this, ActividadDesplegadoraDatos.class);
        startActivity(intent);

    }


    //preguntar si el sensor existe
    private boolean existenciaSensor() {

        boolean existe = false;
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {

            existe = true;


        } else {

            desplegarAviso();

        }

        return existe;
    }

    private void desplegarAviso() {

        Toast toast = Toast.makeText(getApplicationContext(), "SU DISPOSITIVO NO POSEE LUXOMETRO", Toast.LENGTH_SHORT);
        toast.show();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }

}

