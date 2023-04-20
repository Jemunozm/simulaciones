package com.curso_simulaciones.mitrigesimaquintaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.mitrigesimaquintaapp.R;
import com.curso_simulaciones.mitrigesimaquintaapp.comunicaciones.ServidorBluetooth;
import com.curso_simulaciones.mitrigesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mitrigesimaquintaapp.utilidades.Acelerometro;
import com.curso_simulaciones.mitrigesimaquintaapp.utilidades.Boton;
import com.curso_simulaciones.mitrigesimaquintaapp.utilidades.Luxometro;

import org.json.JSONException;
import org.json.JSONObject;

public class ActividadComoServidorBluetooth extends Activity implements Runnable {


    private Boton ax, ay, az, a;

    private float medida_acelerometro, medida_luxometro;

    private Acelerometro acelerometro;
    private Luxometro luxometro;
    private int componente_aceleracion;

    private EditText editTextRecibir;
    private TextView textviewAviso;

    private TextView textviewRol;
    private Button botonEmpezar;
    private int tamanoLetraResolucionIncluida;
    private int COLOR_2 = Color.rgb(156, 220, 80);

    //hilo para actualizar tabla
    //esto evita que se reviente la app
    private final Handler myHandler = new Handler();


    private ServidorBluetooth servidor;

    /*
    hilo que está pendiente de leer mensaje
    enviado por el cliente y enviar mensaje
    a éste
    */
    private boolean corriendo;
    private long periodo_muestreo = 100;//pausas de 100 ms
    private Thread hilo;

    private float medida_campo_magnetico, medida_aceleracion;


    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        gestionarResolucion();

        creacionElementosGUI();

        setContentView(crearGUI());

        eventos();

        hilo = new Thread(this);

    }


    private void gestionarResolucion() {

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (0.8 * AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }//fin método gestionarResolucion()solucion()


    private void creacionElementosGUI() {

        //botones
        ax = new Boton(this);
        ax.setImagen(R.drawable.ax);
        ay = new Boton(this);
        ay.setImagen(R.drawable.ay);
        az = new Boton(this);
        az.setImagen(R.drawable.az);
        a = new Boton(this);
        a.setImagen(R.drawable.a);

        acelerometro = new Acelerometro(this);
        acelerometro.setUnidades(" m.s-2");
        acelerometro.setRango(0, 50);//????
        acelerometro.setAngulosSectores(50, 100, 100);
        acelerometro.setColorFranjaDinámica(Color.rgb(0, 255, 0));
        acelerometro.captarSensor(this);

        luxometro = new Luxometro(this);
        luxometro.setUnidades("lx");
        luxometro.captarSensor(this);

        textviewRol = new TextView(this);
        textviewRol.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) (0.8 * tamanoLetraResolucionIncluida));
        textviewRol.setBackgroundColor(Color.YELLOW);
        textviewRol.setText("SERVIDOR");
        textviewRol.setTextColor(Color.RED);
        textviewRol.setGravity(Gravity.CENTER);
        textviewRol.setEnabled(false);

        textviewAviso = new TextView(this);
        textviewAviso.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) (0.8 * tamanoLetraResolucionIncluida));
        textviewAviso.setBackgroundColor(Color.YELLOW);
        textviewAviso.setTextColor(Color.RED);
        textviewAviso.setGravity(Gravity.CENTER);
        textviewAviso.setText(AlmacenDatosRAM.conexion_bluetooth);

        editTextRecibir = new EditText(this);
        editTextRecibir.setBackgroundColor(Color.BLACK);
        editTextRecibir.setTextColor(Color.YELLOW);
        editTextRecibir.setTextSize((int) (0.8 * tamanoLetraResolucionIncluida));
        editTextRecibir.setText("Despliega la magnitud de la aceleración sensada por el dispositivo móvil CLIENTE");
        editTextRecibir.setEnabled(false);
        editTextRecibir.setKeyListener(null);//para no desplegar trclado

        botonEmpezar = new Button(this);
        botonEmpezar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonEmpezar.setText("EMPEZAR");
        botonEmpezar.getBackground().setColorFilter(COLOR_2, PorterDuff.Mode.MULTIPLY);

        //crear el Servidor
        crearServidor();

    }


    private LinearLayout crearGUI() {

        //LinearLayoutPrincipal
        LinearLayout linearLayoutPrincipal = new LinearLayout(this);
        linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearLayoutPrincipal.setBackgroundColor(Color.BLACK);
        linearLayoutPrincipal.setWeightSum(10f);

        //LinearLayout primera fila
        LinearLayout linearLayoutFilaUno = new LinearLayout(this);
        linearLayoutFilaUno.setBackgroundColor(Color.WHITE);

        //LinearLayout segunda fila
        LinearLayout linearLayoutFilaDos = new LinearLayout(this);
        linearLayoutFilaDos.setBackgroundColor(Color.WHITE);
        linearLayoutFilaDos.setWeightSum(10f);

        //linear segunda columna izquierda
        LinearLayout linearLayoutColumnaDosIzquierda = new LinearLayout(this);

        //linear segunda columna derecha
        LinearLayout linearLayoutColumnaDosDerecha = new LinearLayout(this);
        linearLayoutColumnaDosDerecha.setOrientation(LinearLayout.VERTICAL);
        linearLayoutColumnaDosDerecha.setWeightSum(4f);

        //LinearLayout tercera fila
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

        //pegar las filas en el principal
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

        //pegar columnas izquierda y derecha a la fila dos
        LinearLayout.LayoutParams parametrosColumnaDosIzquierda = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosColumnaDosIzquierda.weight = 8.0f;
        linearLayoutFilaDos.addView(linearLayoutColumnaDosIzquierda, parametrosColumnaDosIzquierda);

        LinearLayout.LayoutParams parametrosColumnaDosDerecha = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosColumnaDosDerecha.weight = 2.0f;
        linearLayoutFilaDos.addView(linearLayoutColumnaDosDerecha, parametrosColumnaDosDerecha);

        //Adicionar acelerometro a la columna dos izquierda
        linearLayoutColumnaDosIzquierda.addView(acelerometro);

        //pegar botones
        LinearLayout.LayoutParams parametrosPegadoBotonesAceleracion = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadoBotonesAceleracion.weight = 1.0f;
        linearLayoutColumnaDosDerecha.addView(ax, parametrosPegadoBotonesAceleracion);
        linearLayoutColumnaDosDerecha.addView(ay, parametrosPegadoBotonesAceleracion);
        linearLayoutColumnaDosDerecha.addView(az, parametrosPegadoBotonesAceleracion);
        linearLayoutColumnaDosDerecha.addView(a, parametrosPegadoBotonesAceleracion);


        //Adicionar a la fila 1 EditText rol
        LinearLayout.LayoutParams parametrosPegadoEditTextRol = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutFilaUno.setGravity(Gravity.CENTER);
        textviewRol.setPadding(20, 20, 20, 20);
        parametrosPegadoEditTextRol.setMargins(20, 20, 20, 20);
        textviewRol.setLayoutParams(parametrosPegadoEditTextRol);
        linearLayoutFilaUno.addView(textviewRol);


        //Adicionar a la fila tres el acelerómetro
        LinearLayout.LayoutParams parametrosPegadoLuxometro = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutFilaTres.setGravity(Gravity.CENTER);
        linearLayoutFilaTres.addView(luxometro, parametrosPegadoLuxometro);

        //Adicionar a la fila 4 el aviso
        LinearLayout.LayoutParams parametrosPegadoAviso = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutFilaCuatro.addView(textviewAviso, parametrosPegadoAviso);

        //pegado botones a la fila 4
        LinearLayout.LayoutParams parametrosPegadoBotones = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadoBotones.weight = 1.0f;

        //Adicionar a la fila 4 botón
        linearLayoutFilaCinco.addView(botonEmpezar, parametrosPegadoBotones);


        return linearLayoutPrincipal;
    }


    private void eventos() {


        //evento cliente
        botonEmpezar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //crear el Servidor
                empezarComunicacionConCliente();
                //hilo comunicación con el cliente
                hilo.start();
                botonEmpezar.setEnabled(false);

            }
        });

        ax.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                acelerometro.setComponenteAcelerometro(1);

                componente_aceleracion = 1;


            }
        });

        ay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                acelerometro.setComponenteAcelerometro(2);

                componente_aceleracion = 2;


            }
        });

        az.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                acelerometro.setComponenteAcelerometro(3);

                componente_aceleracion = 3;


            }
        });

        a.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                acelerometro.setComponenteAcelerometro(4);

                componente_aceleracion = 4;


            }
        });


    }//fin eventos()


    private void crearServidor() {

        servidor = new ServidorBluetooth();
        /*
          el servidor abre socket BluetoothServerSocket
          y lo pone a escucha de peticiones de conexxión
          de clientes
        */
        servidor.abrirSocketServidor();


    }

    private void empezarComunicacionConCliente() {

        /*
         el servidor se mentiene en espera
         ocupada  atendiendo solicitudes de conexión
         mediante el método accept()
         Este método bloque hilo hasta que una
         conexión entrante sea recibida.

         Aceptada la conexion el servidor abre un socket
         de tipo BlutoothSocket. Por aquí se
         comunicará con el cliente (clienteSocket)
         */
        servidor.abrirSocketCliente();
        /*
         abre los flujos de entrada y salida
         de este socket por donde fluirán (tubería)
         los datos desde y hacia el cliente
        */
        servidor.abrirFlujoSalida();
        servidor.abrirFlujoEntrada();

    }

    private void terminarComunicacionConCliente() {

        servidor.cerrarFlujoSalida();
        servidor.cerrarFlujoEntrada();
        servidor.cerrarSocketCliente();

    }

    public void detener() {

        corriendo = false;
        terminarComunicacionConCliente();

    }


   /*
      En este hilo se maneja la comunicación en lo que respecta
      a envío de datos al cliente (escribir) y recibo de datos
      enviados por el cliente(leer).
      En esta app ser hará medinate bytes
     */

    public void run() {

        corriendo = true;

        while (corriendo) {//estaba corriendo


            try {
                Thread.sleep(periodo_muestreo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            escribir();

            AlmacenDatosRAM.conexion_bluetooth = "  Enviando datos al cliente ...";
            hacerTrabajoDuro();

        }


        AlmacenDatosRAM.conexion_bluetooth = " ";


    }


    //lo cambie por este
    private void escribir() {

        //escribir (enviar) datos al cliente
        String dato = getStringJSON();
        byte[] dato_en_byte = dato.getBytes();
        if (dato_en_byte != null) {
            servidor.escribirBytes(dato_en_byte);

        }
    }


    private String getStringJSON() {

        JSONObject obj = new JSONObject();

        try {

            medida_acelerometro = acelerometro.getMedida();
            medida_luxometro = luxometro.getMedida();
            obj.put("componente", componente_aceleracion);
            obj.put("valor_acelerometro", medida_acelerometro);
            obj.put("valor_luxometro", medida_luxometro);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //convertir a String
        return obj.toString();

    }


    protected void onPause() {
        super.onPause();
        //detener hilo
        corriendo = false;

    }

    protected void onDestroy() {
        super.onDestroy();
        terminarComunicacionConCliente();


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

