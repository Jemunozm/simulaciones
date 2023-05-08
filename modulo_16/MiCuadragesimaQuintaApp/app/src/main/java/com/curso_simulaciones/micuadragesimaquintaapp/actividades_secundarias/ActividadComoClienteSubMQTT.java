package com.curso_simulaciones.micuadragesimaquintaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.micuadragesimaquintaapp.comunicaciones.ClientePubSubMQTT;
import com.curso_simulaciones.micuadragesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.micuadragesimaquintaapp.utilidades.Graficador;

import org.json.JSONObject;

public class ActividadComoClienteSubMQTT extends Activity implements Runnable {


    private int tamanoLetraResolucionIncluida;
    private LinearLayout linear_layout_segunda_fila;
    private LinearLayout.LayoutParams parametros_tabla_grafica;
    private Button botonConectar, botonTablaGrafica;
    private TextView textviewAviso;

    private Luxometro luxometro;
    private TablaSimple tabla;
    private Graficador graficador;

    private ClientePubSubMQTT cliente;

    private JSONObject obj;

    private Thread hilo;

    private int periodo_muestreo;
    private int contador = 0;
    private int numero_datos=0;
    private int n=-1;
    private float medida;
    private int tiempo_base=0;
    private int tiempo_anterior;
    private int tiempo_real;

    //hilo para actualizar tabla
    private final Handler myHandler = new Handler();


    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        gestionarResolucion();

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

    }

    private void gestionarResolucion() {

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (0.8 * AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }

    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {

        //tacometro
        luxometro = new Luxometro(this);
        luxometro.setRango(0,100);
        luxometro.setUnidades("lx");

        //tabla
        tabla = new TablaSimple(this);
        tabla.setEtiquetaColumnas("Tiempo (s)", AlmacenDatosRAM.unidades);//"Medida");


        //graficador
        graficador = new Graficador(this);
        //se está muestreando cada segundo (1000 ms)
        graficador.setTituloEjeX("Tiempo (s)");
        graficador.setTituloEjeY(AlmacenDatosRAM.unidades);
        graficador.setGrosorLinea(2f);
        graficador.setColorLinea(Color.RED);
        graficador.setColorValores(Color.YELLOW);
        graficador.setColorMarcadores(Color.GREEN);
        graficador.setColorFondo(Color.BLACK);
        graficador.setColorTextoEjes(Color.WHITE);

        botonConectar = new Button(this);
        botonConectar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonConectar.setText("CONECTAR");
        botonConectar.getBackground().setColorFilter(Color.rgb(183, 216, 199), PorterDuff.Mode.MULTIPLY);


        botonTablaGrafica = new Button(this);
        botonTablaGrafica.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonTablaGrafica.setText("GRAFICA");
        botonTablaGrafica.getBackground().setColorFilter(Color.rgb(183, 216, 199), PorterDuff.Mode.MULTIPLY);
        botonTablaGrafica.setEnabled(false);

        textviewAviso = new TextView(this);
        textviewAviso.setGravity(Gravity.FILL_VERTICAL);
        textviewAviso.setBackgroundColor(Color.rgb(183,216,199));
        textviewAviso.setTextSize(0.8f*tamanoLetraResolucionIncluida);
        textviewAviso.setText(AlmacenDatosRAM.conectado_PubSub);
        textviewAviso.setTextColor(Color.BLACK);


    }//fin crearElemnetosGUI




    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        LinearLayout linear_layout_principal = new LinearLayout(this);
        linear_layout_principal.setOrientation(LinearLayout.VERTICAL);
        linear_layout_principal.setBackgroundColor(Color.rgb(183,216,199));
        linear_layout_principal.setWeightSum(10.0f);

        //LinearLayout primera fila
        LinearLayout linear_layout_primera_fila = new LinearLayout(this);
        linear_layout_primera_fila.setOrientation(LinearLayout.VERTICAL);
        linear_layout_primera_fila.setGravity(Gravity.FILL);
        linear_layout_primera_fila.setBackgroundColor(Color.rgb(245, 245, 245));

        //LinearLayout segunda fila
        linear_layout_segunda_fila = new LinearLayout(this);
        linear_layout_segunda_fila.setOrientation(LinearLayout.VERTICAL);
        linear_layout_segunda_fila.setGravity(Gravity.FILL);
        linear_layout_segunda_fila.setBackgroundColor(Color.rgb(245, 245, 245));
        linear_layout_segunda_fila.setWeightSum(1.0f);

        //LinearLayout tercera fila
        LinearLayout linear_layout_tercera_fila = new LinearLayout(this);
        linear_layout_tercera_fila.setBackgroundColor(Color.RED);

        //LinearLayout cuarta fila
        LinearLayout linear_layout_cuarta_fila = new LinearLayout(this);
        linear_layout_cuarta_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_cuarta_fila.setBackgroundColor(Color.rgb(183,216,199));
        linear_layout_cuarta_fila.setWeightSum(2.0f);

        //pegar primera fila al principal
        LinearLayout.LayoutParams parametros_primera_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_primera_fila.weight = 4.25f;
        parametros_primera_fila.setMargins(20, 20, 20, 20);
        linear_layout_primera_fila.setLayoutParams(parametros_primera_fila);
        linear_layout_principal.addView(linear_layout_primera_fila);

        //pegar segunda fila al principal
        LinearLayout.LayoutParams parametros_segunda_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_segunda_fila.weight = 4.25f;
        parametros_segunda_fila.setMargins(20, 20, 20, 20);
        linear_layout_segunda_fila.setLayoutParams(parametros_segunda_fila);
        linear_layout_principal.addView(linear_layout_segunda_fila);

        //pegar tercera fila al principal
        LinearLayout.LayoutParams parametros_tercera_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_tercera_fila.weight =0.5f;
        parametros_tercera_fila.setMargins(20, 0, 20, 0);
        linear_layout_tercera_fila.setLayoutParams(parametros_tercera_fila);
        linear_layout_principal.addView(linear_layout_tercera_fila);

        //pegar cuarta fila al principal
        LinearLayout.LayoutParams parametros_cuarta_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_cuarta_fila.weight = 1.0f;
        parametros_cuarta_fila.setMargins(20, 20, 20, 20);
        linear_layout_cuarta_fila.setLayoutParams(parametros_cuarta_fila);
        linear_layout_principal.addView(linear_layout_cuarta_fila);

        //pegar gauge en primera fila
        linear_layout_primera_fila.addView(luxometro);
        //pegar tabla en segunda fila
        parametros_tabla_grafica = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametros_tabla_grafica.weight = 1.0f;
        linear_layout_segunda_fila.addView(tabla,parametros_tabla_grafica);

        //pegado textView en tercera fila
        LinearLayout.LayoutParams parametrosPegadoTextView = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadoTextView.weight = 1.0f;
        linear_layout_tercera_fila.addView(textviewAviso , parametrosPegadoTextView);

        //pegar los botones en la cuarta fila
        LinearLayout.LayoutParams parametros_botones_cuarta_fila = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_botones_cuarta_fila.weight = 1.0f;
        botonConectar.setLayoutParams(parametros_botones_cuarta_fila);
        botonTablaGrafica.setLayoutParams(parametros_botones_cuarta_fila);

        linear_layout_cuarta_fila.addView(botonConectar);
        linear_layout_cuarta_fila.addView(botonTablaGrafica);


        return linear_layout_principal;
    }//fin gui




    protected void eventos(){

        //evento cliente
        botonConectar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (botonConectar.getText() == "CONECTAR") {
                    botonConectar.setText("EMPEZAR");
                    //conectar cliente
                    cliente.conectar();
                    AlmacenDatosRAM.estado_conexion_nube=2;
                    actualizarAviso();


                } else {

                    borrarDatos();
                    botonTablaGrafica.setEnabled(true);
                    empezarHilo();
                    botonConectar.setEnabled(false);

                }

            }
        });

        //eventos botones
        botonTablaGrafica.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (botonTablaGrafica.getText() == "TABLA") {
                    botonTablaGrafica.setText("GRAFICA");
                    linear_layout_segunda_fila.removeView(graficador);
                    //pegar tabla en segunda fila
                    linear_layout_segunda_fila.addView(tabla,parametros_tabla_grafica);


                } else {

                    botonTablaGrafica.setText("TABLA");
                    linear_layout_segunda_fila.removeView(tabla);
                    //pegar graficador en segunda fila
                    linear_layout_segunda_fila.addView(graficador,parametros_tabla_grafica);

                }

            }
        });


    }



    public void crearCliente() {

        cliente = new ClientePubSubMQTT(this);


    }

    public void empezarHilo() {

        hilo = new Thread(this);
        hilo.start();

    }


    private void actualizarTacometroTabla(){

        luxometro.setUnidades(AlmacenDatosRAM.unidades);
        luxometro.cambiarEscala(medida);
        tabla.setEtiquetaColumnas("Tiempo en s", "Medida en " + AlmacenDatosRAM.unidades);

    }


    //no estaba
    private void borrarDatos(){
        contador =0;
        AlmacenDatosRAM.datos.clear();
        n=-1;
        numero_datos=0;
        tabla.borrar();

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            //alerta();
            DialogoSalir dialogo_salir=new DialogoSalir(this);
            dialogo_salir.mostrarPopMenuCoeficientes();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void run() {

        while (true) {

            try {
                Thread.sleep(periodo_muestreo);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //leer IoT
            leer();


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
            hacerTrabajoDuro();
            AlmacenDatosRAM.estado_conexion_nube=4;



        } else {

            AlmacenDatosRAM.estado_conexion_nube=3;
            actualizarAviso();

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
            AlmacenDatosRAM.unidades= obj.getString("unidad");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            AlmacenDatosRAM.tiempo= ""+obj.getInt("tiempo");
            //muestrear a más del doble de frecuencia
            //con la que se envían los datos
            periodo_muestreo = (int)(0.4*obj.getInt("periodo"));
            medida =(float) obj.getDouble("valor");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


       /*
      Para el asunto de la table es mejor usar un manejador
      para que no se reviente la aplicación
     */

    private void hacerTrabajoDuro() {
        //.... realizar el trabajo duro

        //Actualiza la UI usando el handler y el runnable
        myHandler.post(updateRunnable);

    }



    final Runnable updateRunnable = new Runnable() {
        public void run() {

            actualizarAviso();

            n=n+1;

            //el instante donde envió el primer dato desde la ESP32
            //despúes de la conexión
            if(n==0){
                //convertir a entero (ms)
                tiempo_base=Integer.parseInt(AlmacenDatosRAM.tiempo);
            }


            //el tiempo es el real recibido en donde se envío el dato n
            tiempo_real = Integer.parseInt(AlmacenDatosRAM.tiempo)-tiempo_base;


            if(tiempo_real!=tiempo_anterior & numero_datos<AlmacenDatosRAM.nDatos) {


                //ya redeinido de nuevo el cero procedemos a procesar los datos
                numero_datos = numero_datos + 1;
                //convertir a segundos con dos decimales
                float t_r = (float) Math.round(tiempo_real * 0.001 * 100f) / 100f;

                //actualizar la tabla
                tabla.enviarDatos(t_r, (medida));

                //acualizar luxometro
                luxometro.cambiarEscala(medida);
                luxometro.setMedida(medida);

                //actualizar la gráfica
                graficador.setTituloEjeY(AlmacenDatosRAM.unidades);
                //graficar los últimos 20 puntos
                AlmacenDatosRAM.datos.add((new Entry(t_r, medida)));

                //graficar sólo un intervalo de datos
                graficarIntervaloDatos(AlmacenDatosRAM.nDatosGraficar);


                tiempo_anterior = tiempo_real;

            }


            if (numero_datos+1 > AlmacenDatosRAM.nDatos) {

                botonConectar.setEnabled(true);


            }

        }
    };


    private void graficarIntervaloDatos(int ancho_intervalo){

        contador = contador + 1;

        if(contador > ancho_intervalo) {
            AlmacenDatosRAM.datos.remove(0);
        }
        graficador.setDatos(AlmacenDatosRAM.datos);


    }


    private void actualizarAviso(){


        if(AlmacenDatosRAM.estado_conexion_nube==1){

            AlmacenDatosRAM.conectado_PubSub= "Hacer clic en CONECTAR para acceder al BROKER...";
            String aviso = AlmacenDatosRAM.conectado_PubSub;
            textviewAviso.setText(aviso);

        }

        if(AlmacenDatosRAM.estado_conexion_nube==2){

            AlmacenDatosRAM.conectado_PubSub= "Hacer clic en EMPEZAR para solictar datos del BROKER...";
            String aviso = AlmacenDatosRAM.conectado_PubSub;
            textviewAviso.setText(aviso);

        }


        if(AlmacenDatosRAM.estado_conexion_nube==3){

            AlmacenDatosRAM.conectado_PubSub= "  No se están recibiendo datos ...";
            String aviso = AlmacenDatosRAM.conectado_PubSub;
            textviewAviso.setText(aviso);

        }

        if(AlmacenDatosRAM.estado_conexion_nube==4){

            AlmacenDatosRAM.conectado_PubSub= "  Recibiendo datos ...";
            String aviso = AlmacenDatosRAM.conectado_PubSub;
            textviewAviso.setText(aviso);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        AlmacenDatosRAM.estado_conexion_nube=1;
        actualizarAviso();

    }

    protected void onPause() {
        super.onPause();

    }

}
