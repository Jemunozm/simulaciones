package com.curso_simulaciones.mitrigesimaquintaapp.actividades_secundarias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.mitrigesimaquintaapp.comunicaciones.ClienteBluetooth;
import com.curso_simulaciones.mitrigesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mitrigesimaquintaapp.utilidades.Acelerometro;
import com.curso_simulaciones.mitrigesimaquintaapp.utilidades.Luxometro;

import org.json.JSONException;
import org.json.JSONObject;

public class ActividadComoClienteBluetooth extends Activity implements Runnable {


    private Acelerometro acelerometro;
    private Luxometro luxometro;

    private Button botonConectar, botonBuscar;
    private TextView textviewRol;
    private TextView textviewAviso;

    private int tamanoLetraResolucionIncluida;
    private int COLOR_1 = Color.rgb(220, 156, 80);

    //hilo para actualizar tabla
    //esto evita que se reviente la app
    private final Handler myHandler = new Handler();

    private ClienteBluetooth cliente;

    private JSONObject obj;

    private long periodo_muestreo = 50;//pausas de 50 ms

    private boolean corriendo;


    /*
   hilo resposable de estar pendiente
   del intercambio de datos con el servidor
   */
    private Thread hilo;


    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        gestionarResolucion();

        creacionElementosGUI();

        setContentView(crearGUI());

        eventos();


    }

    private void gestionarResolucion() {

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (0.8 * AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }//fin método gestionanrResolucion()solucion()


    private void creacionElementosGUI() {

        acelerometro = new Acelerometro(this);
        acelerometro.setUnidades(" a m/s^2");
        acelerometro.setAngulosSectores(50, 100, 100);
        acelerometro.setColorFranjaDinámica(Color.rgb(0, 255, 0));

        luxometro = new Luxometro(this);
        luxometro.setUnidades("lx");

        textviewRol = new TextView(this);
        textviewRol.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) (0.8 * tamanoLetraResolucionIncluida));
        textviewRol.setBackgroundColor(Color.YELLOW);
        textviewRol.setText("CLIENTE");
        textviewRol.setTextColor(Color.RED);
        textviewRol.setGravity(Gravity.CENTER);
        textviewRol.setEnabled(false);

        textviewAviso = new TextView(this);
        textviewAviso.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) (0.8 * tamanoLetraResolucionIncluida));
        textviewAviso.setBackgroundColor(Color.YELLOW);
        textviewAviso.setTextColor(Color.RED);
        textviewAviso.setGravity(Gravity.CENTER);
        textviewAviso.setText(AlmacenDatosRAM.conexion_bluetooth);


        botonBuscar = new Button(this);
        botonBuscar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonBuscar.setText("BUSCAR");
        botonBuscar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonBuscar.setEnabled(true);
        botonBuscar.setTextSize(tamanoLetraResolucionIncluida);


        botonConectar = new Button(this);
        botonConectar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonConectar.setText("CONECTAR");
        botonConectar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonConectar.setEnabled(false);
        botonConectar.setTextSize(tamanoLetraResolucionIncluida);

    }

    private LinearLayout crearGUI() {

        //LinearLayoutPrincipal
        LinearLayout linearLayoutPrincipal = new LinearLayout(this);
        linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearLayoutPrincipal.setBackgroundColor(Color.WHITE);
        linearLayoutPrincipal.setWeightSum(10f);

        //LinearLayout fila 1
        LinearLayout linearLayoutFilaUno = new LinearLayout(this);
        linearLayoutFilaUno.setBackgroundColor(Color.WHITE);

        //LinearLayout fila 2
        LinearLayout linearLayoutFilaDos = new LinearLayout(this);
        linearLayoutFilaDos.setBackgroundColor(Color.WHITE);

        //LinearLayout fila 3
        LinearLayout linearLayoutFilaTres = new LinearLayout(this);
        linearLayoutFilaTres.setBackgroundColor(Color.WHITE);

        //LinearLayout fila 4
        LinearLayout linearLayoutFilaCuatro = new LinearLayout(this);
        linearLayoutFilaCuatro.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutFilaCuatro.setBackgroundColor(Color.WHITE);


        //LinearLayout fila 5
        LinearLayout linearLayoutFilaCinco = new LinearLayout(this);
        linearLayoutFilaCinco.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutFilaCinco.setBackgroundColor(Color.WHITE);
        linearLayoutFilaCinco.setWeightSum(2f);

        //pegar las filas en el princpal
        LinearLayout.LayoutParams parametrosFilaUno = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosFilaUno.weight = 1.0f;
        linearLayoutFilaUno.setLayoutParams(parametrosFilaUno);

        LinearLayout.LayoutParams parametrosFilaDos = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosFilaDos.weight = 3.75f;
        linearLayoutFilaDos.setLayoutParams(parametrosFilaDos);

        LinearLayout.LayoutParams parametrosFilaTres = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosFilaTres.weight = 3.75f;
        linearLayoutFilaTres.setLayoutParams(parametrosFilaTres);

        LinearLayout.LayoutParams parametrosFilaCuatro = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosFilaCuatro.weight = 0.5f;
        linearLayoutFilaCuatro.setLayoutParams(parametrosFilaCuatro);

        LinearLayout.LayoutParams parametrosFilaCinco = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosFilaCinco.weight = 1.0f;
        linearLayoutFilaCinco.setLayoutParams(parametrosFilaCinco);

        linearLayoutPrincipal.addView(linearLayoutFilaUno);
        linearLayoutPrincipal.addView(linearLayoutFilaDos);
        linearLayoutPrincipal.addView(linearLayoutFilaTres);
        linearLayoutPrincipal.addView(linearLayoutFilaCuatro);
        linearLayoutPrincipal.addView(linearLayoutFilaCinco);

        //Adicionar a la fila 1 EditText rol
        LinearLayout.LayoutParams parametrosPegadoEditTextRol = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutFilaUno.setGravity(Gravity.CENTER);
        textviewRol.setPadding(20, 20, 20, 20);
        parametrosPegadoEditTextRol.setMargins(20, 20, 20, 20);
        textviewRol.setLayoutParams(parametrosPegadoEditTextRol);
        linearLayoutFilaUno.addView(textviewRol);

        //Adicionar a la fila 2 el acelerometro
        LinearLayout.LayoutParams parametrosPegadoAcelerometro = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutFilaDos.setGravity(Gravity.CENTER);
        linearLayoutFilaDos.addView(acelerometro, parametrosPegadoAcelerometro);

        //Adicionar a la fila 3 el luxometro
        LinearLayout.LayoutParams parametrosPegadoLuxometro = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutFilaTres.setGravity(Gravity.CENTER);
        linearLayoutFilaTres.addView(luxometro, parametrosPegadoLuxometro);

        //Adicionar a la fila 4 el aviso
        LinearLayout.LayoutParams parametrosPegadoAviso = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutFilaCuatro.addView(textviewAviso, parametrosPegadoAviso);


        //Adicionar a la fila 5 los botones
        LinearLayout.LayoutParams parametrosPegadoBotones = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadoBotones.weight = 1.0f;
        linearLayoutFilaCinco.addView(botonBuscar, parametrosPegadoBotones);
        linearLayoutFilaCinco.addView(botonConectar, parametrosPegadoBotones);


        return linearLayoutPrincipal;
    }

    private void eventos() {

        //evento cliente
        botonBuscar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarBuscandoDispositivos();
                botonConectar.setEnabled(true);
                botonBuscar.setEnabled(false);

            }
        });


        //evento cliente
        botonConectar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (botonConectar.getText() == "CONECTAR") {
                    botonConectar.setText("EMPEZAR");
                    botonBuscar.setEnabled(false);
                    crearCliente();
                    cliente.conectarSocketCliente();

                } else {


                    botonConectar.setEnabled(false);
                    //hilo de comunicación con servidor
                    empezarHilo();

                }

            }
        });


    }//fin eventos()

    private void lanzarBuscandoDispositivos() {

        Intent intent = new Intent(this, ActividadEscaneoDispositivos.class);
        startActivity(intent);

    }


    public void empezarHilo() {

        hilo = new Thread(this);
        hilo.start();

    }


    private void crearCliente() {

        //dirección del dispositivo elegido para emparejar
        String direccion = AlmacenDatosRAM.direccion;
        cliente = new ClienteBluetooth();

        /*
         el cliente instancia un dispositivo Bluetooth
         (BluetoohDevice) a partir de la dirección
         de este.Luego abre un socket de tipo  BuletoothSocket
         a partir del dispositivo enlazado.
         */
        cliente.abrirSocketCliente(direccion);
        empezarComunicacionConServidor();

    }

    private void empezarComunicacionConServidor() {

       /*
       Aceptada la conexión abre los flujos de entrada
       y salida de este socket por donde fluirán (tubería)
         los datos desde y hacia el servidor
       */

        cliente.abrirFlujoEntrada();
        cliente.abrirFlujoSalida();

    }

    private void terminarComunicacionConServidor() {

        cliente.cerrarFlujoEntrada();
        cliente.cerrarFlujoSalida();
        cliente.cerrarSocketCliente();

    }


    @Override
    public void run() {
        corriendo = true;


        while (corriendo) {

            try {
                Thread.sleep(periodo_muestreo);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            leer();
            AlmacenDatosRAM.conexion_bluetooth = "  Recibiendo datos del servidor ...";
            hacerTrabajoDuro();

        }

        AlmacenDatosRAM.conexion_bluetooth = " ";

    }


    private void leer() {

        String nuevo_dato_string = cliente.leerString();//String JSON

        if (nuevo_dato_string != null) {
            convertirStrigJson(nuevo_dato_string);

        }

    }

    //obtener la información del JSON
    public void convertirStrigJson(String datoString) {

        //convertir String a JSON
        try {
            obj = new JSONObject(datoString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //obtener la información
        //la medida
        try {
            int componente_aceleracion = obj.getInt("componente");
            acelerometro.setComponenteAcelerometro(componente_aceleracion);
            //acelerometro.setUnidades(componente_aceleracion+" (m/s^2)");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            float medida_aclerometro = (float) (obj.getDouble("valor_acelerometro"));
            acelerometro.setMedida(medida_aclerometro);
            float medida_luxometro = (float) (obj.getDouble("valor_luxometro"));
            luxometro.cambiarEscala(medida_luxometro);
            luxometro.setMedida(medida_luxometro);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void onPause() {
        super.onPause();
        //detener hilo
        corriendo = false;

    }


    protected void onDestroy() {
        super.onDestroy();
        terminarComunicacionConServidor();

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

            avisoEstadoComunicacion();


        }
    };

    private void avisoEstadoComunicacion() {

        textviewAviso.setText(AlmacenDatosRAM.conexion_bluetooth);

    }


}
