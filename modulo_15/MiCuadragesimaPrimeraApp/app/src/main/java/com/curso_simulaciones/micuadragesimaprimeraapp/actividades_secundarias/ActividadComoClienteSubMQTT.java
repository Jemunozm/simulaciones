package com.curso_simulaciones.micuadragesimaprimeraapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.micuadragesimaprimeraapp.comunicaciones.ClientePubSubMQTT;
import com.curso_simulaciones.micuadragesimaprimeraapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.micuadragesimaprimeraapp.utilidades.Acelerometro;
import com.curso_simulaciones.micuadragesimaprimeraapp.utilidades.Luxometro;

import org.json.JSONException;
import org.json.JSONObject;

public class ActividadComoClienteSubMQTT extends Activity implements Runnable {


    private int tamanoLetraResolucionIncluida;
    private int COLOR_1 = Color.rgb(220, 156, 80);

    private Acelerometro acelerometro;
    private Luxometro luxometro;

    private Button botonConectar;
    private TextView textviewRol;
    private TextView textviewAviso;

    private ClientePubSubMQTT cliente;

    private JSONObject obj;

    private Thread hilo;

    //hilo para actualizar estado de comunicación
    private final Handler myHandler = new Handler();


    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        gestionarResolucion();

        creacionElementosGUI();

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

    }

    private void gestionarResolucion() {

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (0.8 * AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }


    private void creacionElementosGUI() {

        acelerometro = new Acelerometro(this);
        acelerometro.setUnidades(" a m/s^2");
        acelerometro.setAngulosSectores(50, 100, 100);
        acelerometro.setColorFranjaDinámica(Color.rgb(0, 255, 0));

        luxometro = new Luxometro(this);
        luxometro.setUnidades("lx");

        textviewRol = new TextView(this);
        textviewRol.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) (2 * tamanoLetraResolucionIncluida));
        textviewRol.setBackgroundColor(Color.YELLOW);
        textviewRol.setText("SUB");
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
        linearLayoutFilaCinco.setWeightSum(1f);

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
        //textviewRol.setPadding(20, 20, 20, 20);
        //parametrosPegadoEditTextRol.setMargins(20, 20, 20, 20);
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
        linearLayoutFilaCinco.addView(botonConectar, parametrosPegadoBotones);


        return linearLayoutPrincipal;
    }


    protected void eventos() {

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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void run() {

        while (true) {

            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //leer IoT
            leer();
            //actualizar aqui estado de conexión
            actualizarAviso();

        }


    }



     /*
    comunicaciones IoT SUB
     */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void leer() {

        //String JSON
        String nuevo_dato_string = cliente.leerString();

        if (nuevo_dato_string != null) {
            convertirStrigJson(nuevo_dato_string);

        } else {


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

    private void actualizarAviso() {

        textviewAviso.setText("Estado conexión IoT:" + AlmacenDatosRAM.conectado_PubSub);

    }


}
