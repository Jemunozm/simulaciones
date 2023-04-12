package com.curso_simulaciones.mitrigesimasegundaapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.curso_simulaciones.mitrigesimasegundaapp.actividades_secundarias.ActividadConfiguracion;
import com.curso_simulaciones.mitrigesimasegundaapp.actividades_secundarias.ActividadDesplegadoraDatos;
import com.curso_simulaciones.mitrigesimasegundaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mitrigesimasegundaapp.utilidades.Boton;

import java.io.File;

public class ActividadPrincipalMiTrigesimaSegundaApp extends Activity {


    private int tamanoLetraResolucionIncluida;

    private Boton consultar,ajustes, salir;

    private String ruta=null;


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

        existenciaSensor();

        eventos();

        //esto es necesario hacerlo a partir de la API 23 de android
        verificacionPermisos();

        //crear los directorio para almecenar datos
        crearDirectorioAlmacenamientoDatos();


    }//fin del método onCreate

    /*Método auxiliar para asuntos de resolución*/
    private void gestionarResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;
        AlmacenDatosRAM.ancho=ancho;
        AlmacenDatosRAM.alto=alto;
        int dimensionReferencia;

        //tomar el menor valor entre alto y ancho de pantalla
        if (alto > ancho) {

            dimensionReferencia = ancho;
        } else {

            dimensionReferencia = alto;
        }

        AlmacenDatosRAM.dimensioReferencia =dimensionReferencia;

        //una estimación de un buen tamaño
        int tamanoLetra = dimensionReferencia / 20;


        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);

        //guardar en el almacen de datos para que otras clases la accedan fácilmente
        AlmacenDatosRAM.tamanoLetraResolucionIncluida = tamanoLetraResolucionIncluida;

    }


    private void crearElementosGUI() {

        consultar = new Boton(this);
        consultar.setImagen(R.drawable.consultar);

        ajustes = new Boton(this);
        ajustes.setImagen(R.drawable.configuracion);

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
        Drawable fondo = getResources().getDrawable(R.drawable.imagen_entrada_app_32);
        linear_layout_primera_fila.setBackgroundDrawable(fondo);

        //LinearLayout segunda fila
        LinearLayout linear_layout_segunda_fila = new LinearLayout(this);
        linear_layout_segunda_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_fila.setGravity(Gravity.FILL);
        LinearLayout.LayoutParams parametros_segunda_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_segunda_fila.weight = 2.0f;
        linear_layout_segunda_fila.setWeightSum(3.0f);
        linear_layout_segunda_fila.setLayoutParams(parametros_segunda_fila);


        LinearLayout.LayoutParams parametros_pegado_boton = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_boton.weight = 1.0f;
        consultar.setLayoutParams(parametros_pegado_boton);
        ajustes.setLayoutParams(parametros_pegado_boton);
        salir.setLayoutParams(parametros_pegado_boton);
        linear_layout_segunda_fila.addView(consultar);
        linear_layout_segunda_fila.addView(ajustes);
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

        ajustes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                lanzarAjustes();


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


    private void lanzarAjustes() {

        Intent intent = new Intent(this, ActividadConfiguracion.class);
        startActivity(intent);

    }



    private void  crearDirectorioAlmacenamientoDatos(){


        File path = null;

        ruta="almacen_mis_datos/luxometro/";
        AlmacenDatosRAM.path=ruta;

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

    //preguntar si el sensor existe
    private boolean existenciaSensor(){

        boolean existe=false;
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!=null){

            existe =true;


        } else {

            desplegarAviso();

        }

        return existe;
    }

    private void desplegarAviso(){

        Toast toast = Toast.makeText(getApplicationContext(), "SU DISPOSITIVO NO POSEE SENSOR DE LUZ", Toast.LENGTH_SHORT);
        toast.show();

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
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
                ActivityCompat.requestPermissions(ActividadPrincipalMiTrigesimaSegundaApp.this,
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

                    Toast.makeText(ActividadPrincipalMiTrigesimaSegundaApp.this, "Permiso denegado.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
        }
    }


}
