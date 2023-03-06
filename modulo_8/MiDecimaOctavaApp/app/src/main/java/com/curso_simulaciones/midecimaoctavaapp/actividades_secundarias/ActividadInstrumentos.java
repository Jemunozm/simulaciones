package com.curso_simulaciones.midecimaoctavaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.midecimaoctavaapp.utilidades.GaugeSimple;

public class ActividadInstrumentos extends Activity {

    private GaugeSimple temperatura;
    private GaugeSimple humedad;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        crearElementosGUI();



        ViewGroup.LayoutParams linear_layou_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        this.setContentView(crearGUI(), linear_layou_principal);

    }


    private void crearElementosGUI(){

        /*
          Gauge de temperatura
         */
        temperatura= new GaugeSimple(this);

        //rngos de la escala
        temperatura.setRango(-10,40);
        //colores de la escala
        temperatura.setColorSectores(Color.CYAN,Color.GREEN,Color.RED);
        //unidades
        temperatura.setUnidades("Temperatura en C");
        //valor medido
        temperatura.setMedida(25);

        /*
         Gauge de humedad relativa
         */

        humedad= new GaugeSimple(this);

        //rngos de la escala
        humedad.setRango(0,100);
        //colores de la escala
        humedad.setColorSectores(Color.rgb(100, 250,80),Color.rgb(250, 150,80),Color.rgb(200, 250,80));
        //unidades
        humedad.setUnidades("Humedad Relativa (%)");
        //valor medido
        humedad.setMedida(70);


    }


    private LinearLayout crearGUI() {

        LinearLayout linearPrincipal = new LinearLayout(this);
        //los componentes se agregarán verticalmente
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        //para definir los pesos de las filas que se agregaran
        linearPrincipal.setWeightSum(2.0f);

        //las dos filas
        LinearLayout linearArriba = new LinearLayout(this);
        LinearLayout linearAbajo = new LinearLayout(this);



        LinearLayout.LayoutParams parametros_pegado_a_principal = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_a_principal.weight = 1.0f;
        linearPrincipal.addView(linearArriba, parametros_pegado_a_principal);
        linearPrincipal.addView(linearAbajo, parametros_pegado_a_principal);

        //pegar los gauges
        linearArriba.addView(temperatura);
        linearAbajo.addView(humedad);




        return linearPrincipal;


    }
}


