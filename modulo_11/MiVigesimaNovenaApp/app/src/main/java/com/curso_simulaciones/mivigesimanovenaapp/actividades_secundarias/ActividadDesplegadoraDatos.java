package com.curso_simulaciones.mivigesimanovenaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mivigesimanovenaapp.R;
import com.curso_simulaciones.mivigesimanovenaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimanovenaapp.utilidades.Boton;
import com.curso_simulaciones.mivigesimanovenaapp.utilidades.Gaussimetro;
import com.curso_simulaciones.mivigesimanovenaapp.utilidades.Graficador;

public class ActividadDesplegadoraDatos extends Activity {

    private Boton ax, ay, az, a;

    private Gaussimetro gaussimetro;
    public Graficador graficador;

    private HiloAnimacion hilo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        hilo = new HiloAnimacion(this);
        hilo.start();

    }//fin del método onCreate

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

        //gauge
        gaussimetro = new Gaussimetro(this);

        //graficador
        graficador = new Graficador(this);
        //se está muestreando cada segundo (1000 ms)
        graficador.setTituloEjeX("Tiempo (s)");
        graficador.setTituloEjeY("Gauss (μT)");
        graficador.setGrosorLinea(2f);
        graficador.setColorLinea(Color.RED);
        graficador.setColorValores(Color.YELLOW);
        graficador.setColorMarcadores(Color.GREEN);
        graficador.setColorFondo(Color.BLACK);
        graficador.setColorTextoEjes(Color.WHITE);


    }


    private LinearLayout crearGUI() {


        LinearLayout linear_layout_principal = new LinearLayout(this);
        linear_layout_principal.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.FILL);
        linear_layout_principal.setBackgroundColor(Color.WHITE);
        linear_layout_principal.setPadding(20, 20, 20, 20);
        linear_layout_principal.setWeightSum(10);


        //LinearLayout primera columna
        LinearLayout linear_layout_primera_columna = new LinearLayout(this);
        linear_layout_primera_columna.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_primera_columna.setGravity(Gravity.FILL);
        linear_layout_primera_columna.setBackgroundColor(Color.rgb(245, 245, 245));
        //parámetro para pegar la primera columna al principal
        LinearLayout.LayoutParams parametros_primera_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_primera_columna.weight = 5.0f;
        parametros_primera_columna.setMargins(10, 10, 5, 10);
        linear_layout_primera_columna.setLayoutParams(parametros_primera_columna);


        //LinearLayout segunda columna
        LinearLayout linear_layout_segunda_columna = new LinearLayout(this);
        linear_layout_segunda_columna.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_columna.setGravity(Gravity.FILL);
        linear_layout_segunda_columna.setBackgroundColor(Color.rgb(245, 245, 245));
        linear_layout_segunda_columna.setWeightSum(1.0f);
        //parámetro para pegar la segunda columna al principal
        LinearLayout.LayoutParams parametros_segunda_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_segunda_columna.weight = 4.0f;
        parametros_segunda_columna.setMargins(5, 10, 10, 10);
        linear_layout_segunda_columna.setLayoutParams(parametros_segunda_columna);

        //LinearLayout tercera columna
        LinearLayout linear_layout_tercera_columna = new LinearLayout(this);
        linear_layout_tercera_columna.setOrientation(LinearLayout.VERTICAL);
        linear_layout_tercera_columna.setGravity(Gravity.FILL);
        linear_layout_tercera_columna.setBackgroundColor(Color.YELLOW);
        linear_layout_tercera_columna.setWeightSum(4.0f);
        //parámetro para pegar la tercera columna al principal
        LinearLayout.LayoutParams parametros_tercera_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_tercera_columna.weight = 1.0f;
        parametros_tercera_columna.setMargins(5, 10, 10, 10);
        linear_layout_tercera_columna.setLayoutParams(parametros_tercera_columna);


        //peado de las tres columnas al principal
        linear_layout_principal.addView(linear_layout_primera_columna);
        linear_layout_principal.addView(linear_layout_segunda_columna);
        linear_layout_principal.addView(linear_layout_tercera_columna);


        //pegar gauge primera columna
        linear_layout_primera_columna.addView(gaussimetro);

        //pegar grafico en segunda columna
        LinearLayout.LayoutParams parametros_grafica = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_grafica.weight = 1.0f;
        linear_layout_segunda_columna.addView(graficador, parametros_grafica);

        //pegar botones tercera columna
        LinearLayout.LayoutParams parametros_pegado_boton = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_pegado_boton.weight = 1.0f;
        linear_layout_tercera_columna.addView(ax, parametros_pegado_boton);
        linear_layout_tercera_columna.addView(ay, parametros_pegado_boton);
        linear_layout_tercera_columna.addView(az, parametros_pegado_boton);
        linear_layout_tercera_columna.addView(a, parametros_pegado_boton);


        return linear_layout_principal;


    }

    private void eventos() {

        ax.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                lanzarDatosAx();


            }
        });

        ay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarDatosAy();
            }
        });

        az.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarDatosAz();
            }
        });

        a.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarDatosA();
            }
        });


    }
    private void lanzarDatosAx() {

        resetear();
        gaussimetro.setComponenteGaussimetro(1);
        gaussimetro.setRango(-20,20);

        graficador.setTituloEjeY("Campo en x (μT)");
        hilo.corriendo = true;

    }

    private void lanzarDatosAy() {

        resetear();
        gaussimetro.setComponenteGaussimetro(2);
        gaussimetro.setRango(-20,20);
        graficador.setTituloEjeY("Campo en y (μT)");
        hilo.corriendo = true;

    }

    private void lanzarDatosAz() {

        resetear();
        gaussimetro.setComponenteGaussimetro(3);
        gaussimetro.setRango(-20,20);
        graficador.setTituloEjeY("Campo en z (μT)");
        hilo.corriendo = true;

    }

    private void lanzarDatosA() {

        resetear();
        gaussimetro.setComponenteGaussimetro(4);
        gaussimetro.setRango(0,20);
        graficador.setTituloEjeY("Campo (μT)");
        hilo.corriendo = true;

    }
    protected void onPause() {

        hilo.corriendo = false;
        AlmacenDatosRAM.datos.clear();
        hilo.contador = 0;
        super.onPause();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        hilo.corriendo = true;
    }


    private void resetear() {

        hilo.corriendo = false;
        AlmacenDatosRAM.datos.clear();
        hilo.tiempo = 0;
        hilo.contador = 0;

    }

}