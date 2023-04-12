package com.curso_simulaciones.mitrigesimasegundaapp.actividades_secundarias;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.curso_simulaciones.mitrigesimasegundaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mitrigesimasegundaapp.datos.GuardarDatosPersistentesTXT;
import com.curso_simulaciones.mitrigesimasegundaapp.gui_auxiliares.DialogoSalir;
import com.curso_simulaciones.mitrigesimasegundaapp.utilidades.Luxometro;
import com.curso_simulaciones.mitrigesimasegundaapp.utilidades.TablaSimple;

public class ActividadDesplegadoraDatos extends Activity {


    private Luxometro luxometro;
    private TablaSimple tabla;

    private Button botonGuardar, botonEmpezar;

    //hilo
    private HiloAnimacion hilo;

    //hilo para actualizar tabla
    //esto evita que se reviente la app
    private final Handler myHandler = new Handler();
    public boolean activarBotones = false;

    /*
    Para que tiempo empiece en cero.
    Esto por la forma como se diseñó
    la tabla de datos en donde la primera
    fila es un título
    */
    private int nDatos = -1;

    //para guardar los datos en un documento txt
    private GuardarDatosPersistentesTXT archivoTxt;

    private int tamanoLetraResolucionIncluida;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestionandoResolucion();

        //para crear elementos de la GUI
        crearElementosGUI();

        /*
        Para informar cómo se debe pegar el administrador de
        diseño LinearLayout obtenido con el método crearGui()
        */
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        //para guardar datos
        archivoTxt = new GuardarDatosPersistentesTXT( );

        eventos();


    }//fin del método onCreate


    private void gestionandoResolucion() {

        tamanoLetraResolucionIncluida = AlmacenDatosRAM.tamanoLetraResolucionIncluida;


    }//fin método gestionandoResolucion()


    private void crearElementosGUI() {


        //gauge
        luxometro = new Luxometro(this);
        luxometro.setUnidades("lx");
        luxometro.setAngulosSectores(20,190,40);
        luxometro.setColorSectores(Color.GREEN,Color.argb(100,200,200,0), Color.YELLOW);
        luxometro.setColorFranjaDinámica(Color.RED);

        //graficador
        tabla = new TablaSimple(this);
        tabla.setEtiquetaColumnas("Tiempo (s)", "Iluminancia (lx)");


        botonGuardar = new Button(this);
        botonGuardar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonGuardar.getBackground().setColorFilter(Color.rgb(255, 255, 100), PorterDuff.Mode.MULTIPLY);
        botonGuardar.setText("GUARDAR");
        botonGuardar.setEnabled(false);

        botonEmpezar = new Button(this);
        botonEmpezar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonEmpezar.getBackground().setColorFilter(Color.rgb(255, 255, 100), PorterDuff.Mode.MULTIPLY);
        botonEmpezar.setText("EMPEZAR");

    }


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        LinearLayout linear_layout_principal = new LinearLayout(this);
        linear_layout_principal.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.FILL);
        linear_layout_principal.setBackgroundColor(Color.WHITE);
        linear_layout_principal.setWeightSum(10.0f);


        //LinearLayout primera columna
        LinearLayout linear_layout_primera_columna = new LinearLayout(this);
        linear_layout_primera_columna.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_primera_columna.setGravity(Gravity.FILL);
        linear_layout_primera_columna.setBackgroundColor(Color.rgb(245, 245, 245));


        //LinearLayout segunda columna
        LinearLayout linear_layout_segunda_columna = new LinearLayout(this);
        linear_layout_segunda_columna.setOrientation(LinearLayout.VERTICAL);
        linear_layout_segunda_columna.setGravity(Gravity.FILL);
        linear_layout_segunda_columna.setBackgroundColor(Color.rgb(245, 245, 245));
        linear_layout_segunda_columna.setWeightSum(10.0f);

        //LinearLayout primera fila segunda columna
        LinearLayout linear_layout_primera_fila_segunda_columna = new LinearLayout(this);
        linear_layout_primera_fila_segunda_columna.setOrientation(LinearLayout.VERTICAL);
        linear_layout_primera_fila_segunda_columna.setGravity(Gravity.FILL);


        //LinearLayout segunda fila segunda columna
        LinearLayout linear_layout_segunda_fila_segunda_columna = new LinearLayout(this);
        linear_layout_segunda_fila_segunda_columna.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_fila_segunda_columna.setGravity(Gravity.FILL);
        linear_layout_segunda_fila_segunda_columna.setWeightSum(2.0f);


        //pegar columna primera al principal
        LinearLayout.LayoutParams parametros_primera_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_primera_columna.weight = 5.0f;
        parametros_primera_columna.setMargins(20, 20, 20, 20);
        linear_layout_primera_columna.setLayoutParams(parametros_primera_columna);
        linear_layout_principal.addView(linear_layout_primera_columna);


        //pegar columna segunda al principal
        LinearLayout.LayoutParams parametros_segunda_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_segunda_columna.weight = 5.0f;
        parametros_segunda_columna.setMargins(20, 20, 20, 20);
        linear_layout_segunda_columna.setLayoutParams(parametros_segunda_columna);
        linear_layout_principal.addView(linear_layout_segunda_columna);


        //pegar luxometro en primera columna
        linear_layout_primera_columna.addView(luxometro);


        //pegar primera fila en segunda columna
        LinearLayout.LayoutParams parametros_primera_fila_segunda_columna = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_primera_fila_segunda_columna.weight = 8.0f;
        parametros_primera_fila_segunda_columna.setMargins(20, 20, 20, 20);
        linear_layout_primera_fila_segunda_columna.setLayoutParams(parametros_primera_fila_segunda_columna);
        linear_layout_segunda_columna.addView(linear_layout_primera_fila_segunda_columna);

        //pegar segunda fila en segunda columna en segunda columna
        LinearLayout.LayoutParams parametros_segunda_fila_segunda_columna = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_segunda_fila_segunda_columna.weight = 2.0f;
        parametros_segunda_fila_segunda_columna.setMargins(20, 20, 20, 20);
        linear_layout_segunda_fila_segunda_columna.setLayoutParams(parametros_segunda_fila_segunda_columna);
        linear_layout_segunda_columna.addView(linear_layout_segunda_fila_segunda_columna);

        //pegar tabla en la primera fila de la segunda columna
        linear_layout_primera_fila_segunda_columna.addView(tabla);

        //pegar los botones en la segunda fila de la segunda columna
        LinearLayout.LayoutParams parametros_botones_segunda_fila_segunda_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_botones_segunda_fila_segunda_columna.weight = 1.0f;
        botonEmpezar.setLayoutParams(parametros_botones_segunda_fila_segunda_columna);
        botonGuardar.setLayoutParams(parametros_botones_segunda_fila_segunda_columna);
        linear_layout_segunda_fila_segunda_columna.addView(botonEmpezar);
        linear_layout_segunda_fila_segunda_columna.addView(botonGuardar);


        return linear_layout_principal;


    }

    /*Administra los eventos de la GUI*/
    private void eventos() {

        botonGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                guardar();


            }
        });


        botonEmpezar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                empezar();

            }
        });


    }



    private void empezar() {

        //destruir hilo si ya existía
        if(hilo!=null)
            hilo=null;

        activarBotones = false;
        borrarDatos();

        ///crear nuevo hilo
        hilo = new HiloAnimacion(this);
        hilo.corriendo = true;
        hilo.start();

    }


    private void guardar() {

        String carpeta = AlmacenDatosRAM.path;
        archivoTxt.guardar(this, carpeta);

    }

    private void borrarDatos() {

        tabla.borrar();
        nDatos = -1;
    }


    /*
     Para el asunto de la table es mejor usar un
     hilo manejador para que no se reviente la aplicación
   */
    public void hacerTrabajoDuro() {
        //.... realizar el trabajo duro

        //Actualiza la UI usando el handler y el runnable
        myHandler.post(updateRunnable);

    }

    final Runnable updateRunnable = new Runnable() {
        public void run() {

            //tiempo en segundos
            float tiempo = 0.001f*AlmacenDatosRAM.tiempo;
            //tiempo en segundos y medida con hasta dos decimales
            tiempo = (float) (Math.floor(100 * tiempo) / 100);
            float medida = AlmacenDatosRAM.datoActual;//medida;
            medida = (float) (Math.floor(100 * medida) / 100f);

            tabla.enviarDatos(tiempo, medida);

            //guardar los datos de a vs t en documento .txt
            archivoTxt.llenarDatos(tiempo, medida);

            if (activarBotones == true) {

                botonGuardar.setEnabled(true);
                botonEmpezar.setEnabled(true);

            } else {

                botonGuardar.setEnabled(false);
                botonEmpezar.setEnabled(false);
            }


        }
    };


    /*
      Se deben borrar los datos cuando se sale de la aplicación
     */
    protected void onPause() {
        super.onPause();
        archivoTxt.borrarDatos();

    }


    /*
      Desplegar diálogo de advertencia para decidir si se
      sale de la actividad o no. Esto cuando se presiona
      el botón hacia atrás del dispositivo móvil
     */

    public void onClick(DialogInterface arg0, int arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            // Esto es lo que hace mi botón al pulsar ir a atrás
            DialogoSalir dialogo_salir = new DialogoSalir(this);
            dialogo_salir.mostrarPopMenu();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
