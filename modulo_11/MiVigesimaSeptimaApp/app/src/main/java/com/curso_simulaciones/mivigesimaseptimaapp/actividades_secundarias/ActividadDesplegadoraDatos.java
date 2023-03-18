package com.curso_simulaciones.mivigesimaseptimaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mivigesimaseptimaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimaseptimaapp.utilidades.Graficador;
import com.curso_simulaciones.mivigesimaseptimaapp.utilidades.Luxometro;

public class ActividadDesplegadoraDatos extends Activity {


    private Luxometro luxometro;
    public Graficador graficador;

    /*Hilo responsable de la animación
    El trabajo de animación es mejor manejarlo en hilo
    aparte para evitar bloqueos de la aplicación
    debido al manejo simultáneo de la GUI con la Acivity
   */
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

        hilo = new HiloAnimacion(this);
        hilo.start();

    }//fin del método onCreate


    private void crearElementosGUI() {


        //gauge
        luxometro = new Luxometro(this);
        luxometro.setUnidades("lx");
        luxometro.setAngulosSectores(100,100,50);
        luxometro.setColorSectores(Color.YELLOW,Color.YELLOW,Color.RED);

        //graficador
        graficador = new Graficador(this);
        //se está muestreando cada segundo (1000 ms)
        graficador.setTituloEjeX("Tiempo (s)");
        graficador.setTituloEjeY("Iluminancia (lx)");
        graficador.setGrosorLinea(2f);
        graficador.setColorLinea(Color.RED);
        graficador.setColorValores(Color.YELLOW);
        graficador.setColorMarcadores(Color.GREEN);
        graficador.setColorFondo(Color.BLACK);
        graficador.setColorTextoEjes(Color.WHITE);



    }


    /*método responsable de administrar el diseño de la GUI*/
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
        LinearLayout.LayoutParams parametros_segunda_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_segunda_columna.weight = 5.0f;
        parametros_segunda_columna.setMargins(5, 10, 10, 10);
        linear_layout_segunda_columna.setLayoutParams(parametros_segunda_columna);


        linear_layout_primera_columna.addView(luxometro);

        //pegar grafico en segunda columna
        LinearLayout.LayoutParams parametros_grafica = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_grafica.weight = 1.0f;
        linear_layout_segunda_columna.addView(graficador, parametros_grafica);

        linear_layout_principal.addView(linear_layout_primera_columna);
        linear_layout_principal.addView(linear_layout_segunda_columna);

        return linear_layout_principal;
    }




    protected void onPause() {

        resetear();
        super.onPause();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        hilo.corriendo = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        resetear();
    }


    private void resetear() {

        hilo.corriendo = false;
        AlmacenDatosRAM.datos.clear();
        hilo.tiempo = 0;
        hilo.contador=0;

    }

}
