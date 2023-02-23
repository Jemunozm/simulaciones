package com.curso_simulaciones.micuartaapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.micuartaapp.componentes.GaugeSimple;

public class ActividadPrincipalMiCuartaApp extends Activity {

    private GaugeSimple tacometro_1, tacometro_2, tacometro_3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // llamada al metodo para crear los elementos de la GUI
        crearElemtentosGui();


        //para informar como se debe adaptar la GUI a la pantalla

        ViewGroup.LayoutParams parametro_layout_principal = new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);


        /* pegar al contenedor al GUI: en el argumento se está llamando
        al metodo crearGui()*/

        this.setContentView(crearGui(), parametro_layout_principal);

    }

    //crear los objetos de la GUI
    private void crearElemtentosGui() {

        // crear el objeto GaugeSimple
        tacometro_1 = new GaugeSimple(this);
        //cambiar atributos (propiedades)

        //darle color blanco al lienzo antes de pegae
        tacometro_1.setBackgroundColor(Color.WHITE);
        //asignar las unidades
        tacometro_1.setUnidades("HR %");
        //asignar rangos
        tacometro_1.setRango(0, 100);
        //asignar la medida
        tacometro_1.setMedida(78f);

        //crear objeto GaugeSimple
        tacometro_2 = new GaugeSimple(this);
        //cambiar atributos (propiedades)
        //darle color blanco al lienzo antes de pegar
        tacometro_2.setBackgroundColor(Color.WHITE);
        //darle color a los sectores
        tacometro_2.setColorSectores(Color.RED, Color.BLUE, Color.rgb(200, 200, 20));
        //asignar las unidades
        tacometro_2.setUnidades("lx");
        //asignar rangos
        tacometro_2.setRango(0, 1000);
        //asignar la medida
        tacometro_2.setMedida(430f);

        //crear objeto GaugeSimple
        tacometro_3 = new GaugeSimple(this);
        //cambiar atributos (propiedades)
        //darle color blanco al lienzo antes de pegar
        tacometro_3.setBackgroundColor(Color.WHITE);
        //darle color a los sectores
        tacometro_3.setColorSectores(Color.GREEN, Color.GREEN, Color.rgb(255, 100, 0));
        //asignar las unidades
        tacometro_3.setUnidades("ax (m/s^2)");
        //asignar rangos
        tacometro_3.setRango(0, 10);
        //asignar la medida
        tacometro_3.setMedida(6.5f);
    }

        /*organizar la distribución de los objetos de de la GUI usando
        administradores de diseño*/

    private LinearLayout crearGui() {
        //administrador de diseño
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.HORIZONTAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.rgb(250, 150, 50));
        linear_principal.setWeightSum(3);

        LinearLayout linear_izquierdo = new LinearLayout(this);
        linear_izquierdo.setOrientation(LinearLayout.VERTICAL);
        linear_izquierdo.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_izquierdo.setGravity(Gravity.FILL);
        linear_izquierdo.setBackgroundColor(Color.RED);
        linear_izquierdo.setWeightSum(1);

        LinearLayout linear_centro = new LinearLayout(this);
        linear_centro.setOrientation(LinearLayout.VERTICAL);
        linear_centro.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_centro.setGravity(Gravity.FILL);
        linear_centro.setBackgroundColor(Color.BLUE);
        linear_centro.setWeightSum(1);

        LinearLayout linear_derecho = new LinearLayout(this);
        linear_derecho.setOrientation(LinearLayout.VERTICAL);
        linear_derecho.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_derecho.setGravity(Gravity.FILL);
        linear_derecho.setBackgroundColor(Color.GREEN);
        linear_derecho.setWeightSum(1);


        //parametro para pegar los gauges
        LinearLayout.LayoutParams parametrosPegadaGauges= new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametrosPegadaGauges.setMargins(20, 20, 20, 20);
        parametrosPegadaGauges.weight = 1.0f;

        //pegar gauges
        linear_izquierdo.addView(tacometro_1,parametrosPegadaGauges);
        linear_centro.addView(tacometro_2,parametrosPegadaGauges);
        linear_derecho.addView(tacometro_3,parametrosPegadaGauges);


        //parametro para pegar los linear al pricipal
        LinearLayout.LayoutParams parametrosPegadaLinear= new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadaLinear.setMargins(20, 20, 20, 20);
        parametrosPegadaLinear.weight = 1.0f;

        linear_principal.addView(linear_izquierdo,parametrosPegadaLinear);
        linear_principal.addView(linear_centro,parametrosPegadaLinear);
        linear_principal.addView(linear_derecho,parametrosPegadaLinear);

        return linear_principal;

    }

}
