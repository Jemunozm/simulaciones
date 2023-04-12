package com.curso_simulaciones.mitrigesimaapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActividadPrincipalMiTrigesimaApp extends Activity {


    //variable tamaño de las letras basado en resolución de pantalla
    private int tamanoLetraResolucionIncluida;
    private EditText editTextEntrada;
    private TextView textViewSalida;
    private Button botonGuardar, botonAbrir;
    private String texto_entrada = "Escribe aquí ...";
    private String texto_salida = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestionandoResolucion();

        /*llamada al método para crear los elementos de la interfaz gráfica de usuario (GUI)*/
        crearElementosGui();

        eventos();


        /*para informar cómo se debe adaptar la GUI a la pantalla del dispositivo*/
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        /*pegar al contenedor la GUI: en el argumento se está llamando al método crearGui()*/
        this.setContentView(crearGui(), parametro_layout_principal);


    }


    /*Método auxiliar para asuntos de resolución*/
    private void gestionandoResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;

        /*
         Tamaño de las letras con independencia de
         tamaño y resolución de pantalla
        */
        int dimensionReferencia;

        //tomar el menor entre alto y ancho de pantalla
        if (alto > ancho) {

            dimensionReferencia = ancho;
        } else {

            dimensionReferencia = alto;
        }


        //una estimación de un buen tamaño
        int tamanoLetra = dimensionReferencia / 25;

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);


    }

    //crear los objetos de la interfaz gráfica de usuario (GUI)
    private void crearElementosGui() {

        editTextEntrada = new EditText(this);
        editTextEntrada.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        editTextEntrada.setTextColor(Color.YELLOW);
        editTextEntrada.setGravity(Gravity.START);
        editTextEntrada.setBackgroundColor(Color.BLACK);
        editTextEntrada.setText(texto_entrada);

        textViewSalida = new TextView(this);
        textViewSalida.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textViewSalida.setTextColor(Color.YELLOW);
        textViewSalida.setBackgroundColor(Color.BLACK);

        botonGuardar = new Button(this);
        botonGuardar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonGuardar.setTextColor(Color.BLACK);
        botonGuardar.setText("GUARDAR");
        botonGuardar.getBackground().setColorFilter(Color.argb(180, 250, 170, 50), PorterDuff.Mode.MULTIPLY);


        botonAbrir = new Button(this);
        botonAbrir.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonAbrir.setTextColor(Color.BLACK);
        botonAbrir.setText("ABRIR");
        botonAbrir.getBackground().setColorFilter(Color.argb(180, 250, 170, 50), PorterDuff.Mode.MULTIPLY);
        botonAbrir.setEnabled(false);

    }

    //organizar la distribución de los objetos de de la GUI usando administradores de diseño
    private LinearLayout crearGui() {

        //administrador de diseño
        //linear principal
        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setGravity(Gravity.CENTER_HORIZONTAL);
        linearPrincipal.setGravity(Gravity.FILL);
        linearPrincipal.setWeightSum(10.0f);

        //linear secundarios
        LinearLayout linearArriba = new LinearLayout(this);
        linearArriba.setOrientation(LinearLayout.VERTICAL);
        linearArriba.setBackgroundColor(Color.RED);
        linearArriba.setWeightSum(1.0f);

        LinearLayout linearCentro = new LinearLayout(this);
        linearCentro.setOrientation(LinearLayout.VERTICAL);
        linearCentro.setBackgroundColor(Color.BLUE);
        linearCentro.setWeightSum(1.0f);

        LinearLayout linearAbajo = new LinearLayout(this);
        linearAbajo.setOrientation(LinearLayout.HORIZONTAL);
        linearAbajo.setBackgroundColor(Color.BLACK);
        linearAbajo.setWeightSum(2.0f);


        //parámetro de pegada
        LinearLayout.LayoutParams parametrosPrincipalArribaCentro = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        int margen = (int) (tamanoLetraResolucionIncluida);
        parametrosPrincipalArribaCentro.setMargins(margen, margen, margen, margen);
        parametrosPrincipalArribaCentro.weight = 4.5f;
        linearPrincipal.addView(linearArriba, parametrosPrincipalArribaCentro);
        linearPrincipal.addView(linearCentro, parametrosPrincipalArribaCentro);

        LinearLayout.LayoutParams parametrosPrincipalAbajo = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPrincipalAbajo.setMargins(margen, margen, margen, margen);
        parametrosPrincipalAbajo.weight = 1.0f;
        linearPrincipal.addView(linearAbajo, parametrosPrincipalAbajo);


        //parámetro de pegada de elementos arriba y centro
        LinearLayout.LayoutParams parametrosPegadaElementosArribaCentro = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadaElementosArribaCentro.setMargins(margen, margen, margen, margen);
        parametrosPegadaElementosArribaCentro.weight = 1.0f;
        //pegar etidtext y textview
        linearArriba.addView(editTextEntrada, parametrosPegadaElementosArribaCentro);
        linearCentro.addView(textViewSalida, parametrosPegadaElementosArribaCentro);


        //parámetro de pegada de elemenos abajo
        LinearLayout.LayoutParams parametrosPegadaElementosAbajo = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadaElementosAbajo.weight = 1.0f;
        //pegar button
        linearAbajo.addView(botonGuardar, parametrosPegadaElementosAbajo);
        linearAbajo.addView(botonAbrir, parametrosPegadaElementosAbajo);


        return linearPrincipal;

    }


    private void eventos() {


        //evento del boton activar
        botonGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                botonGuardar.setEnabled(false);
                botonAbrir.setEnabled(true);

                guardarArchivoEnMemoriaInterna();

            }
        });

        //evento del boton activar
        botonAbrir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                botonGuardar.setEnabled(true);
                botonAbrir.setEnabled(false);

                abrirArchivoDeMemoriaInterna();

            }
        });


    }


    private void guardarArchivoEnMemoriaInterna() {


        OutputStreamWriter escritor = null;

        try {

            /*
              Paso 1 y Paso 2: Crear y abrir flujo de salida
              Esto es, crear y abrir el canal de salida
            */

            FileOutputStream flujoSalida = this.openFileOutput("pruebaArchivo.txt", Context.MODE_PRIVATE);

            /*
             Agregar filtro. En este caso se le pasa a
             OutputStreamWriter para que escriba
           */
            escritor = new OutputStreamWriter(flujoSalida);

            /*
              Paso 3: Escribir información mientras haya
            */
            escritor.write(String.valueOf(editTextEntrada.getText()));

            /*
            Paso 4: cerrar canal
           */
            escritor.flush();
            escritor.close();


        } catch (Exception ex) {


        }


    }

    private void abrirArchivoDeMemoriaInterna() {

        InputStreamReader lector = null;
        BufferedReader lectorBuffer = null;


        try {

             /*
              Paso 1 y Paso 2: Crear y abrir flujo de entrada
              Esto es, crear y abrir el canal de entrada
            */
            FileInputStream flujoEntrada = openFileInput("pruebaArchivo.txt");//si existe



            /*
             Agregar dos filtros. En este caso se le pasa a
             InputStreamReader y luego a BufferedReader
             para que leer
           */

            lector = new InputStreamReader(flujoEntrada);
            lectorBuffer = new BufferedReader(lector);

            /*
              Paso 3: Leer información mientras haya
            */

            //texto_salida =lectorBuffer.readLine();

            StringBuilder result = new StringBuilder();
            String linea = null;

            while ((linea = lectorBuffer.readLine()) != null) {

                result.append(linea + "\n");

            }

            texto_salida = result.toString();
            textViewSalida.setText(texto_salida);

            /*
            Paso 4: cerrar canal
           */

            lectorBuffer.close();
        } catch (Exception ex) {


        }

    }

}
