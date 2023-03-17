package com.curso_simulaciones.mivigesimasegundaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.curso_simulaciones.mivigesimasegundaapp.controlador.ActividadControladora;
import com.curso_simulaciones.mivigesimasegundaapp.datos.AlmacenDatosRAM;

public class ActividadPrincipalMiVigesimaSegundaApp extends Activity {


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        gestionarResolucion();


        lanzarActividadControladora();
    }
    private void gestionarResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;

        //guardar en almacen annho y alto de pantalla
        AlmacenDatosRAM.ancho_pantalla =ancho;
        AlmacenDatosRAM.alto_pantalla =alto;
    }
    private void lanzarActividadControladora(){

        Intent intent = new Intent(this, ActividadControladora.class);
        startActivity(intent);

    }
}
