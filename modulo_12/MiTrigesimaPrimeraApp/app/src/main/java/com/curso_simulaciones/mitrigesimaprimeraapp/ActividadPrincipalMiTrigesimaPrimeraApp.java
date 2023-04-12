package com.curso_simulaciones.mitrigesimaprimeraapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActividadPrincipalMiTrigesimaPrimeraApp extends Activity {


    //variable tamaño de las letras basado en resolución de pantalla
    private int tamanoLetraResolucionIncluida;
    private EditText editTextEntrada;
    private TextView textViewSalida;
    private Button botonGuardar, botonAbrir;
    private String texto_entrada="Escribe aquí ...";
    private String texto_salida=null;
    private String ruta=null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestionandoResolucion();

        //crear los directorio para almecenar datos
        crearDirectorioAlmacenamientoDatos();

        /*llamada al método para crear los elementos de la interfaz gráfica de usuario (GUI)*/
        crearElementosGui();

        eventos();

        //esto es necesario hacerlo a partir de la API 23 de android
        verificacionPermisos();


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
        if(alto>ancho){

            dimensionReferencia=ancho;
        }

        else {

            dimensionReferencia=alto;
        }


        //una estimación de un buen tamaño
        int tamanoLetra= dimensionReferencia / 25;

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);



    }
    //crear los objetos de la interfaz gráfica de usuario (GUI)
    private void crearElementosGui(){

        editTextEntrada=new EditText(this);
        editTextEntrada.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        editTextEntrada.setTextColor(Color.YELLOW);
        editTextEntrada.setGravity(Gravity.START);
        editTextEntrada.setBackgroundColor(Color.BLACK);
        editTextEntrada.setText(texto_entrada);

        textViewSalida=new TextView(this);
        textViewSalida.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textViewSalida.setTextColor(Color.YELLOW);
        textViewSalida.setBackgroundColor(Color.BLACK);

        botonGuardar=new Button(this);
        botonGuardar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonGuardar.setTextColor(Color.BLACK);
        botonGuardar.setText("GUARDAR");
        botonGuardar.getBackground().setColorFilter(Color.argb(180, 250, 170, 50), PorterDuff.Mode.MULTIPLY);


        botonAbrir=new Button(this);
        botonAbrir.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonAbrir.setTextColor(Color.BLACK);
        botonAbrir.setText("ABRIR");
        botonAbrir.getBackground().setColorFilter(Color.argb(180, 250, 170, 50), PorterDuff.Mode.MULTIPLY);
        botonAbrir.setEnabled(false);

    }

    //organizar la distribución de los objetos de de la GUI usando administradores de diseño
    private LinearLayout crearGui(){

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
        LinearLayout.LayoutParams parametrosPrincipalArribaCentro= new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        int margen=(int)(tamanoLetraResolucionIncluida);
        parametrosPrincipalArribaCentro.setMargins(margen, margen,margen,margen);
        parametrosPrincipalArribaCentro.weight = 4.5f;
        linearPrincipal.addView(linearArriba,parametrosPrincipalArribaCentro);
        linearPrincipal.addView(linearCentro,parametrosPrincipalArribaCentro);

        LinearLayout.LayoutParams parametrosPrincipalAbajo= new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametrosPrincipalAbajo.setMargins(margen, margen,margen,margen);
        parametrosPrincipalAbajo.weight = 1.0f;
        linearPrincipal.addView(linearAbajo,parametrosPrincipalAbajo);


        //parámetro de pegada de elementos arriba y centro
        LinearLayout.LayoutParams parametrosPegadaElementosArribaCentro= new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametrosPegadaElementosArribaCentro.setMargins(margen, margen, margen, margen);
        parametrosPegadaElementosArribaCentro.weight = 1.0f;
        //pegar etidtext y textview
        linearArriba.addView(editTextEntrada,parametrosPegadaElementosArribaCentro);
        linearCentro.addView(textViewSalida,parametrosPegadaElementosArribaCentro);


        //parámetro de pegada de elemenos abajo
        LinearLayout.LayoutParams parametrosPegadaElementosAbajo= new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadaElementosAbajo.weight = 1.0f;
        //pegar button
        linearAbajo.addView(botonGuardar,parametrosPegadaElementosAbajo);
        linearAbajo.addView(botonAbrir,parametrosPegadaElementosAbajo);



        return linearPrincipal;

    }


    /*
    Se aprovecho este método para crear
    las carrpetas.
    */
    protected void onPause() {
        super.onPause();

    }



    private void crearDirectorioAlmacenamientoDatos() {


        File path=null;

        ruta="almacen_mis_datos/ejemplo_mi_app_31/";

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q){
            //versiones con android menores a 10
            path = new File(Environment.getExternalStorageDirectory(), ruta);
            if (!path.exists()) {
                path.mkdirs();
            }

        } else{
            //para versiones de 10 en adelante
            path = new File(getExternalFilesDir(null), ruta);
            if (!path.exists()) {
                path.mkdirs();
            }

        }


    }


    private void eventos(){




        //evento del boton activar
        botonGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                botonGuardar.setEnabled(false);
                botonAbrir.setEnabled(true);

                guardarArchivoEnMemoriaExterna();

            }
        });

        //evento del boton activar
        botonAbrir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                botonGuardar.setEnabled(true);
                botonAbrir.setEnabled(false);

                abrirArchivoDeMemoriaExterna();

            }
        });


    }


    private void guardarArchivoEnMemoriaExterna(){


        OutputStreamWriter escritor=null;



        try  {

            /*
              Paso 1 y Paso 2: Crear y abrir flujo de salida
              Esto es, crear y abrir el canal de salida
            */

            File file=null;

            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q){
                //versiones inferiores a Android 10
                file =  new File(Environment.getExternalStorageDirectory(), ruta+"/pruebaArchivo.txt");

            } else {

                //versiones desde Android 10 en adelante
                file = new File(getExternalFilesDir(null), ruta+"pruebaArchivo.txt");

            }


            FileOutputStream flujoSalida= new FileOutputStream(file);

            /*
             Agregar filtro. En este caso se le pasa a
             OutputStreamWriter para que escriba
           */
            escritor=new OutputStreamWriter(flujoSalida);

            /*
              Paso 3: Escribir información mientras haya
            */
            escritor.write(String.valueOf(editTextEntrada.getText()));

            /*
            Paso 4: cerrar canal
           */
            escritor.flush();
            escritor.close();


        }

        catch (Exception ex)
        {


        }


        Toast toast1 = Toast.makeText(getApplicationContext(), "El archivo se guardó en"+ruta, Toast.LENGTH_LONG);
        toast1.show();

    }

    private void abrirArchivoDeMemoriaExterna(){

        InputStreamReader lector=null;
        BufferedReader lectorBuffer=null;


        try  {

            /*
              Paso 1 y Paso 2: Crear y abrir flujo de entrada
              Esto es, crear y abrir el canal de entrada
            */
            File file=null;
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q){
                //versiones inferiores a Android 10
                file =  new File(Environment.getExternalStorageDirectory(), ruta+"/pruebaArchivo.txt");

            } else {

                //versiones desde Android 10 en adelante
                file = new File(getExternalFilesDir(null), ruta+"pruebaArchivo.txt");

            }

            FileInputStream flujoEntrada=new FileInputStream(file);

           /*
             Agregar dos filtros. En este caso se le pasa a
             InputStreamReader y luego a BufferedReader
             para que leer
           */

            lector= new InputStreamReader(flujoEntrada);
            lectorBuffer = new BufferedReader(lector);

            /*
              Paso 3: Leer información mientras haya
            */

            //texto_salida =lectorBuffer.readLine();

            StringBuilder result = new StringBuilder();
            String linea=null;

            while ((linea = lectorBuffer.readLine()) != null) {

                result.append(linea+"\n");

            }

            texto_salida=result.toString();
            textViewSalida.setText(texto_salida);

            /*
            Paso 4: cerrar canal
           */

            lectorBuffer.close();
        }
        catch (Exception ex)
        {


        }



    }



     /*
      Los siguientes dos métodos son para confirmar los permisos.
      Esto se exige en las versiones Android 6.0 hacia adelante.
     */


    private void verificacionPermisos() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Toast.makeText(this, "This version is not Android 6 or later " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
        } else {

            int hasReadWritePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasReadWritePermission != PackageManager.PERMISSION_GRANTED) {


                /* Request user permissions in runtime */
                ActivityCompat.requestPermissions(ActividadPrincipalMiTrigesimaPrimeraApp.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        100);

            } else {

            }

        }

    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0
                        || grantResults[0] == PackageManager.PERMISSION_GRANTED
                        || grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    Toast.makeText(ActividadPrincipalMiTrigesimaPrimeraApp.this, "Permission is denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        finish();


    }

}
