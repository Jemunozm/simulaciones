package com.curso_simulaciones.micuadragesimaprimeraapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.micuadragesimaprimeraapp.R;
import com.curso_simulaciones.micuadragesimaprimeraapp.comunicaciones.ClientePubSubMQTT;
import com.curso_simulaciones.micuadragesimaprimeraapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.micuadragesimaprimeraapp.utilidades.Acelerometro;
import com.curso_simulaciones.micuadragesimaprimeraapp.utilidades.Boton;
import com.curso_simulaciones.micuadragesimaprimeraapp.utilidades.Luxometro;

import org.json.JSONException;
import org.json.JSONObject;

public class ActividadComoClientePubMQTT extends Activity implements Runnable {


    private int tamanoLetraResolucionIncluida;
    private int COLOR_1 = Color.rgb(220, 156, 80);
    private Boton ax, ay, az, a;
    private Acelerometro acelerometro;
    private Luxometro luxometro;

    protected TextView textviewRol;
    private TextView textviewAviso;

    protected Button botonConectar;
    private float medida_acelerometro, medida_luxometro;
    private int componente_aceleracion;

    private ClientePubSubMQTT cliente;

    private Thread hilo;

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

        crearCliente();

        hilo = new Thread(this);


    }//fin del método onCreate

    private void gestionarResolucion() {

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (0.8 * AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }


    private void crearElementosGUI() {

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
        acelerometro.captarSensor(this);
        acelerometro.setUnidades(" m.s-2");
        acelerometro.setRango(0, 50);//????
        acelerometro.setAngulosSectores(50, 100, 100);
        acelerometro.setColorFranjaDinámica(Color.rgb(0, 255, 0));
        acelerometro.captarSensor(this);

        luxometro = new Luxometro(this);
        luxometro.captarSensor(this);
        luxometro.setUnidades("lx");
        luxometro.captarSensor(this);

        textviewRol = new TextView(this);
        textviewRol.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) (2f * tamanoLetraResolucionIncluida));
        textviewRol.setBackgroundColor(Color.YELLOW);
        textviewRol.setText("PUB");
        textviewRol.setTextColor(Color.RED);
        textviewRol.setGravity(Gravity.CENTER);
        textviewRol.setEnabled(false);

        textviewAviso = new TextView(this);
        textviewAviso.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) (0.8 * tamanoLetraResolucionIncluida));
        textviewAviso.setBackgroundColor(Color.YELLOW);
        textviewAviso.setTextColor(Color.RED);
        textviewAviso.setGravity(Gravity.CENTER);


        botonConectar = new Button(this);
        botonConectar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonConectar.setText("CONECTAR");
        botonConectar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonConectar.setTextSize(tamanoLetraResolucionIncluida);

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
        //textviewRol.setPadding(20, 20, 20, 20);
        //parametrosPegadoEditTextRol.setMargins(20, 20, 20, 20);
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

        //Adicionar a la fila 5 botón
        linearLayoutFilaCinco.addView(botonConectar, parametrosPegadoBotones);


        return linearLayoutPrincipal;
    }


    protected void eventos() {

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

        //evento cliente
        botonConectar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (botonConectar.getText() == "CONECTAR") {
                    botonConectar.setText("EMPEZAR");
                    //conectar cliente
                    cliente.conectar();


                } else {


                    hilo.start();
                    botonConectar.setEnabled(false);

                }

            }
        });

    }


    public void crearCliente() {

        cliente = new ClientePubSubMQTT(this);


    }


    protected void onResume() {
        super.onResume();

    }


    protected void onPause() {
        super.onPause();
        hilo = null;
        if (cliente != null)
            cliente.desconectar();//no estaba, se está reventando

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void run() {

        while (true) {

            try {
                Thread.sleep(200);//estaba 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //escribir IoT
            escribir();
            //actualizar aqui estado de conexión
            actualizarAviso();

        }


    }


    /*
    comunicaciones IoT PUB
     */


    //lo cambie por este
    private void escribir() {

        //escribir (enviar) datos al cliente
        String dato = getStringJSON();
        byte[] dato_en_byte = dato.getBytes();
        if (dato_en_byte != null) {
            cliente.setEnviarMensajes(dato_en_byte);

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


    private void actualizarAviso() {

        textviewAviso.setText("Estado conexión IoT:" + AlmacenDatosRAM.conectado_PubSub);

    }


}
