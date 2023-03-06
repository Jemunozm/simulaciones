package com.curso_simulaciones.midecimaquintaapp;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.curso_simulaciones.midecimaquintaapp.actividades_secundarias.ActividadSecundariaMiDecimaQuintaApp;
import com.curso_simulaciones.midecimaquintaapp.datos.AlmacenDatosRAM;

public class ActividadPrincipalMiDecimaQuintaApp extends Activity {

    private Button botonActivar;
    private TextView aviso;
    private ScrollView scroller;
    private int tamanoLetraResolucionIncluida;
    private int margenesResolucionIncluida;
    private String metodo_ciclo_vida;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestionarResolucion();

        metodo_ciclo_vida = "onCreate ActividadPrincipal";
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_SHORT).show();
         /*Aviso de estado del ciclo de vida.
         Como el toast está pasando muy rápido
         se optó por desplegar también el aviso
         en un textview en la actividad principal
         principal.
         */
        AlmacenDatosRAM.mensaje = AlmacenDatosRAM.mensaje + "\n\n" + metodo_ciclo_vida;


        //para crear elementos de la GUI
        crearElementosGUI();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);
        ;


        //para administrar los eventos
        eventos();

    } //fin del método onCreate

    private void gestionarResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;
        int dimensionReferencia;

        //tomar el menor valor entre alto y ancho de pantalla
        if (alto > ancho) {

            dimensionReferencia = ancho;
        } else {

            dimensionReferencia = alto;
        }


        //una estimación de un buen tamaño
        int tamanoLetra = dimensionReferencia / 20;


        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);
        margenesResolucionIncluida = (int) (5f * tamanoLetraResolucionIncluida);

    }//fin método gestionarResolucion()


    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {

        //crear botón
        botonActivar = new Button(this);
        botonActivar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonActivar.setText("IR A LA ACTIVAD SECUNDARIA");
        botonActivar.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);

        scroller = new ScrollView(this);
        aviso = new TextView(this);
        aviso.setTextSize(0.7f * tamanoLetraResolucionIncluida);
        aviso.setText(AlmacenDatosRAM.mensaje);
        scroller.addView(aviso);

    }//fin método crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setGravity(Gravity.CENTER_HORIZONTAL);
        linearPrincipal.setGravity(Gravity.FILL);
        linearPrincipal.setWeightSum(10f);

        //parámtero de pegado
        //pegado del scroll
        LinearLayout.LayoutParams parametro_pegado_scroll = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametro_pegado_scroll.weight = 9.0f;
        parametro_pegado_scroll.setMargins(margenesResolucionIncluida, 0, 0, 0);
        linearPrincipal.addView(scroller, parametro_pegado_scroll);

        //pegar el botón
        LinearLayout.LayoutParams parametro_pegado_boton = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametro_pegado_boton.weight = 1.0f;
        linearPrincipal.addView(botonActivar, parametro_pegado_boton);

        return linearPrincipal;
    }//fin método gui


    /*Administra los eventos de la GUI*/
    private void eventos() {

        //evento
        botonActivar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarAplicacionSecundaria();
            }
        });

    }//fin método eventos


    //método que lanza la segunda actividad
    private void lanzarAplicacionSecundaria() {

        Intent intent = new Intent(this, ActividadSecundariaMiDecimaQuintaApp.class);
        startActivity(intent);

    }

    /*Métodos de una actividad que se pueden
      ser implementados y que automáticamente
      son llamados por el SO.
     */

    protected void onStart() {
        super.onStart();

        metodo_ciclo_vida = "onStart ActividadPrincipal";
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_SHORT).show();

        /*Aviso de estado del ciclo de vida.
         Como el toast está pasando muy rápido
         se optó por desplegar también el aviso
         en un textview en la actividad principal
         principal.
         */
        AlmacenDatosRAM.mensaje = AlmacenDatosRAM.mensaje + "\n\n" + metodo_ciclo_vida;

    }

    protected void onRestart() {
        super.onRestart();

        metodo_ciclo_vida = "onRestart ActividadPrincipal";
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_LONG).show();
        /*Aviso de estado del ciclo de vida.
         Como el toast está pasando muy rápido
         se optó por desplegar también el aviso
         en un textview en la actividad principal
         principal.
         */
        AlmacenDatosRAM.mensaje = AlmacenDatosRAM.mensaje + "\n\n" + metodo_ciclo_vida;

    }

    protected void onResume() {
        super.onResume();

        metodo_ciclo_vida = "onResume ActividadPrincipal";
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_SHORT).show();

        /*Aviso de estado del ciclo de vida.
         Como el toast está pasando muy rápido
         se optó por desplegar también el aviso
         en un textview en la actividad principal
         principal.
         */
        AlmacenDatosRAM.mensaje = AlmacenDatosRAM.mensaje + "\n\n" + metodo_ciclo_vida;

        /* en este método actualizo el mensaje
           ya qe aqui aseguro que cuando la actividad está
           en primer plano llama a este método para actualizar
           la pantalla
        */
        aviso.setText(AlmacenDatosRAM.mensaje);
    }

    protected void onPause() {

        metodo_ciclo_vida = "onPause ActividadPrincipal";
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_SHORT).show();

        /*Aviso de estado del ciclo de vida.
         Como el toast está pasando muy rápido
         se optó por desplegar también el aviso
         en un textview en la actividad principal
         principal.
         */
        AlmacenDatosRAM.mensaje = AlmacenDatosRAM.mensaje + "\n\n" + metodo_ciclo_vida;

        super.onPause();


    }

    protected void onStop() {

        metodo_ciclo_vida = "onStop ActividadPrincipal";
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_SHORT).show();

        /*Aviso de estado del ciclo de vida.
         Como el toast está pasando muy rápido
         se optó por desplegar también el aviso
         en un textview en la actividad principal
         principal.
         */
        AlmacenDatosRAM.mensaje = AlmacenDatosRAM.mensaje + "\n\n" + metodo_ciclo_vida;

        super.onStop();

    }

    protected void onDestroy() {
        metodo_ciclo_vida = "onDestroy ActividadPrincipal";
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_SHORT).show();

         /*Aviso de estado del ciclo de vida.
         Como el toast está pasando muy rápido
         se optó por desplegar también el aviso
         en un textview en la actividad principal
         principal.
         */
        AlmacenDatosRAM.mensaje = AlmacenDatosRAM.mensaje + "\n\n" + metodo_ciclo_vida;

        super.onDestroy();
    }

}
