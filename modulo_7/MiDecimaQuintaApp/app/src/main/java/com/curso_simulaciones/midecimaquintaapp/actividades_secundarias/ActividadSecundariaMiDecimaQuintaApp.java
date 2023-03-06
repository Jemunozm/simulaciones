package com.curso_simulaciones.midecimaquintaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.curso_simulaciones.midecimaquintaapp.datos.AlmacenDatosRAM;

public class ActividadSecundariaMiDecimaQuintaApp extends Activity {

    private String metodo_ciclo_vida;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        metodo_ciclo_vida = "onCreate ActividadSecundaria";
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
        ViewGroup.LayoutParams parametro_layout_principal = new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        this.setContentView(gui(), parametro_layout_principal);

        //para administrar los eventos
        eventos();


    } //fin del método onCreate


    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {


    }//fin método crearElementosGUI


    //método responsable de la GUI
    private LinearLayout gui() {

        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setBackgroundColor(Color.YELLOW);

        return linear_principal;
    }//fin método gui

    /*Administra los eventos de la GUI*/
    private void eventos() {


    }//fin método eventos


        /*Métodos de una actividad que se pueden
      ser implementados y que automáticamente
      son llamados por el SO.
     */

    protected void onStart() {
        super.onStart();

        metodo_ciclo_vida = "onStart ActividadSecundaria";
         /*Aviso de estado del ciclo de vida.
         Como el toast está pasando muy rápido
         se optó por desplegar también el aviso
         en un textview en la actividad principal
         principal.
         */
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_SHORT).show();
        AlmacenDatosRAM.mensaje = AlmacenDatosRAM.mensaje + "\n\n" + metodo_ciclo_vida;

    }

    protected void onRestart() {
        super.onRestart();
        metodo_ciclo_vida = "onRestart ActividadSecundaria";
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_SHORT).show();
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
        metodo_ciclo_vida = "onResume ActividadSecundaria";
        Toast.makeText(this, metodo_ciclo_vida, Toast.LENGTH_SHORT).show();
         /*Aviso de estado del ciclo de vida.
         Como el toast está pasando muy rápido
         se optó por desplegar también el aviso
         en un textview en la actividad principal
         principal.
         */
        AlmacenDatosRAM.mensaje = AlmacenDatosRAM.mensaje + "\n\n" + metodo_ciclo_vida;

    }

    protected void onPause() {
        metodo_ciclo_vida = "onPause ActividadSecundaria";
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
        metodo_ciclo_vida = "onStop ActividadSecundaria";
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

        metodo_ciclo_vida = "onDestroy ActividadSecundaria";
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
