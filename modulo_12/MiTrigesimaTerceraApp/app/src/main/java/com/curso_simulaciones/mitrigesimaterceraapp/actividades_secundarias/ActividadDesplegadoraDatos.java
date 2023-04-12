package com.curso_simulaciones.mitrigesimaterceraapp.actividades_secundarias;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.curso_simulaciones.mitrigesimaterceraapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mitrigesimaterceraapp.datos.LeerDatosTXT;
import com.curso_simulaciones.mitrigesimaterceraapp.gui_auxiliares.DialogoSalir;
import com.curso_simulaciones.mitrigesimaterceraapp.utilidades.Graficador;
import com.curso_simulaciones.mitrigesimaterceraapp.utilidades.TablaSimple;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class ActividadDesplegadoraDatos extends Activity {

    private TablaSimple tabla;
    private Button botonPathArchivoTXT, botonLeer;
    private LeerDatosTXT datosTXT;
    private Graficador graficador;
    private ArrayList datos;
    private int tamanoLetraResolucionIncluida;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestionarResolucion();

        //para crear elementos de la GUI
        crearElementosGUI();

        /*
        Para informar cómo se debe pegar el administrador de
        diseño LinearLayout obtenido con el método crearGui()
        */
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        datosTXT = new LeerDatosTXT();

        eventos();

    }//fin del método onCreate


    private void gestionarResolucion() {


        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (0.6* AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }//fin método gestionandoResolucion()


    private void crearElementosGUI() {


        //graficador
        graficador = new Graficador(this);
        graficador.setTituloEjeX("Tiempo (s)");
        graficador.setTituloEjeY("Iluminancia (lux)");
        graficador.setGrosorLinea(2f);
        graficador.setColorLinea(Color.RED);
        graficador.setColorValores(Color.YELLOW);
        graficador.setColorMarcadores(Color.GREEN);
        graficador.setColorFondo(Color.BLACK);
        graficador.setColorTextoEjes(Color.WHITE);


        //graficador: los datos son Iluminacia (lx) vs Tiempo (s)
        tabla = new TablaSimple(this);
        tabla.setEtiquetaColumnas("Tiempo (s)", "Iluminancia (lx)");


        botonPathArchivoTXT = new Button(this);
        botonPathArchivoTXT.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonPathArchivoTXT.getBackground().setColorFilter(Color.rgb(255, 255, 100), PorterDuff.Mode.MULTIPLY);
        botonPathArchivoTXT.setText("PATH ARCHIVO");

        botonLeer = new Button(this);
        botonLeer.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonLeer.getBackground().setColorFilter(Color.rgb(255, 255, 100), PorterDuff.Mode.MULTIPLY);
        botonLeer.setText("LEER DATOS");
        botonLeer.setEnabled(false);

    }


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        LinearLayout linear_layout_principal = new LinearLayout(this);
        linear_layout_principal.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.FILL);
        linear_layout_principal.setBackgroundColor(Color.WHITE);
        //linear_layout_principal.setPadding(20, 20, 20, 20);
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
        //linear_layout_primera_fila_segunda_columna.setBackgroundColor(Color.RED);

        //LinearLayout segunda fila segunda columna
        LinearLayout linear_layout_segunda_fila_segunda_columna = new LinearLayout(this);
        linear_layout_segunda_fila_segunda_columna.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_fila_segunda_columna.setGravity(Gravity.FILL);
        //linear_layout_segunda_fila_segunda_columna.setBackgroundColor(Color.BLUE);
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

        //pegra garficador a la primera columna
        LinearLayout.LayoutParams parametros_grafica = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_grafica.weight = 1.0f;
        linear_layout_primera_columna.addView(graficador, parametros_grafica);

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
        botonLeer.setLayoutParams(parametros_botones_segunda_fila_segunda_columna);
        botonPathArchivoTXT.setLayoutParams(parametros_botones_segunda_fila_segunda_columna);
        linear_layout_segunda_fila_segunda_columna.addView(botonLeer);
        linear_layout_segunda_fila_segunda_columna.addView(botonPathArchivoTXT);


        return linear_layout_principal;


    }

    /*Administra los eventos de la GUI*/
    private void eventos() {

        botonPathArchivoTXT.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                abrirFolder();
                botonLeer.setEnabled(true);

            }
        });


        botonLeer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                desplegarDatos();

            }
        });


    }


    public void abrirFolder() {

        //esta actividad localiza el path del archivo TXT que tiene los datos
        Intent intent = new Intent(this, ActividadExploradoraArchivos.class);
        startActivity(intent);

    }


    public void desplegarDatos() {

        //limpiar datos antes de nueva consulta
        borrarDatos();

        //arreglo para los datos de la gráfica
        datos = new ArrayList<>();

        /*
          leer el archivo de texto  que se encuentra
           en este path
        */
        datosTXT.leer(AlmacenDatosRAM.path);

        //número de datos
        int longitud = AlmacenDatosRAM.n;

        for (int i = 0; i < longitud; i = i + 1) {
            float x = (float) AlmacenDatosRAM.x[i];
            float y = (float) AlmacenDatosRAM.y[i];

            //datos para la tabla
            tabla.enviarDatos(x, y);

            //datos para la gráfica
            datos.add((new Entry(x, y)));
            graficador.setDatos(datos);

        }

    }


    private void borrarDatos() {

        //borrar arreglos en AlmacenDatosRAM
        for (int i = 0; i < AlmacenDatosRAM.x.length; i = i + 1) {

            AlmacenDatosRAM.x[i] = 0;
            AlmacenDatosRAM.y[i] = 0;


        }

        //borrar tabla
        tabla.borrar();

    }


    /*
      Se deben borrar los datos cuando se sale de la aplicación
     */
    protected void onPause() {
        super.onPause();

    }


    /*
      Desplegar diálogo de avertencia para decidir sei se sale de la actividad o no.
      Esto cuando se presiona el botón hacia atrás del dispositivo móvil
     */

    public void onClick(DialogInterface arg0, int arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            // Esto es lo que hace mi botón al pulsar ir a atrás
            DialogoSalir dialogo_salir=new DialogoSalir(this);
            dialogo_salir.mostrarPopMenu();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
