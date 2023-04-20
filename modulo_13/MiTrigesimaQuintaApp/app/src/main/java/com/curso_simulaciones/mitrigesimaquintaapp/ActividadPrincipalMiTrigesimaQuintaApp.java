package com.curso_simulaciones.mitrigesimaquintaapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.curso_simulaciones.mitrigesimaquintaapp.actividades_secundarias.ActividadComunicacion;
import com.curso_simulaciones.mitrigesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mitrigesimaquintaapp.utilidades.Boton;

public class ActividadPrincipalMiTrigesimaQuintaApp extends Activity {


    private Boton entrar, salir;

    private BluetoothAdapter BA;

    LinearLayout linear_layout_segunda_fila;


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


        eventos();

        //permisos
        verificacionPermisos();


    }//fin del método onCreate

    /*Método auxiliar para asuntos de resolución*/
    private void gestionarResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;
        AlmacenDatosRAM.ancho = ancho;
        AlmacenDatosRAM.alto = alto;
        int dimensionReferencia;

        //tomar el menor valor entre alto y ancho de pantalla
        if (alto > ancho) {

            dimensionReferencia = ancho;
        } else {

            dimensionReferencia = alto;
        }

        AlmacenDatosRAM.dimensionReferencia = dimensionReferencia;

        //una estimación de un buen tamaño
        int tamanoLetra = dimensionReferencia / 20;


        //tamano de letra para usar acomodado a la resolución de pantalla
        int tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);

        //guardar en el almacen de datos para que otras clases la accedan fácilmente
        AlmacenDatosRAM.tamanoLetraResolucionIncluida = tamanoLetraResolucionIncluida;

    }


    private void crearElementosGUI() {

        entrar = new Boton(this);
        entrar.setImagen(R.drawable.entrar);

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
        Drawable fondo = getResources().getDrawable(R.drawable.comunicacion_cliente_servidor);
        linear_layout_primera_fila.setBackgroundDrawable(fondo);

        //LinearLayout segunda fila
        linear_layout_segunda_fila = new LinearLayout(this);
        linear_layout_segunda_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_fila.setGravity(Gravity.FILL);
        LinearLayout.LayoutParams parametros_segunda_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_segunda_fila.weight = 2.0f;
        linear_layout_segunda_fila.setWeightSum(1.0f);
        linear_layout_segunda_fila.setLayoutParams(parametros_segunda_fila);


        LinearLayout.LayoutParams parametros_pegado_boton = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_boton.weight = 1.0f;
        entrar.setLayoutParams(parametros_pegado_boton);
        salir.setLayoutParams(parametros_pegado_boton);
        linear_layout_segunda_fila.addView(entrar);
        //linear_layout_segunda_fila.addView(salir);


        linear_layout_principal.addView(linear_layout_primera_fila);
        linear_layout_principal.addView(linear_layout_segunda_fila);


        return linear_layout_principal;


    }


    private void eventos() {

        entrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                activarBluetooth();
                lanzarActividadComunicacion();
                linear_layout_segunda_fila.removeAllViews();
                linear_layout_segunda_fila.addView(salir);


            }
        });

        salir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                finish();
            }
        });


    }


    private void activarBluetooth() {

        BA = BluetoothAdapter.getDefaultAdapter();
        //activar bluetooth si está desactivado
        if (!BA.isEnabled()) {
            BA.enable();
        }

    }

    private void lanzarActividadComunicacion() {

        Intent intent = new Intent(this, ActividadComunicacion.class);
        startActivity(intent);

    }


    protected void onPause() {
        super.onPause();

    }

    protected void onDestroy() {
        super.onDestroy();
        BA.disable();
        AlmacenDatosRAM.conexion_bluetooth = "  ";
        finish();

    }

    /*
    A partir de Android 12 es necesario agregar estos permisos para usar el Bluetooth
     */
    private void verificacionPermisos() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            Toast.makeText(this, "This version is not Android 12 or later " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
        } else {

            int hasReadWritePermission = checkSelfPermission(Manifest.permission.BLUETOOTH);
            int hasBluetoothScan = checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN);

            if (hasReadWritePermission != PackageManager.PERMISSION_GRANTED || hasBluetoothScan != PackageManager.PERMISSION_GRANTED) {


                /* Request user permissions in runtime */
                ActivityCompat.requestPermissions(ActividadPrincipalMiTrigesimaQuintaApp.this,
                        new String[]{
                                Manifest.permission.BLUETOOTH_ADMIN,
                                Manifest.permission.BLUETOOTH_ADVERTISE,
                                Manifest.permission.BLUETOOTH_SCAN,
                                Manifest.permission.BLUETOOTH_CONNECT

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
                        || grantResults[1] == PackageManager.PERMISSION_GRANTED
                        || grantResults[2] == PackageManager.PERMISSION_GRANTED
                        || grantResults[3] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    Toast.makeText(ActividadPrincipalMiTrigesimaQuintaApp.this, "Permiso denegado.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
        }
    }
}
