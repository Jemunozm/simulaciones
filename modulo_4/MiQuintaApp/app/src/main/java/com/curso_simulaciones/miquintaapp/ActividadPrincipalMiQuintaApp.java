package com.curso_simulaciones.miquintaapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.miquintaapp.componentes.GaugeSimple;

public class ActividadPrincipalMiQuintaApp extends Activity {

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
        tacometro_1.setUnidades("Vatios");
        //asignar rangos
        tacometro_1.setRango(0, 720);
        //asingar medida
        tacometro_1.setMedida(270);
        //asingar color a la escala
        tacometro_1.setColorEscala(Color.YELLOW, Color.GREEN, Color.RED);
        //asignar angulos a la escala
        tacometro_1.setAnguloSectores(150,75,45);
        //asignar color al tacometro
        tacometro_1.setColorFondoTacometro(Color.BLACK);
        //asignar color a la aguja
        tacometro_1.setColorAguja(Color.BLUE);
        //asignar color a los numeros
        tacometro_1.setColorUnidades(Color.WHITE);
        //asignar color a la unidad
        tacometro_1.setColorNumeroDespliegue(Color.YELLOW);

        //crear objeto GaugeSimple
        tacometro_2 = new GaugeSimple(this);
        //cambiar atributos (propiedades)
        //darle color blanco al lienzo antes de pegar
        tacometro_2.setBackgroundColor(Color.WHITE);
        //darle color a los sectores
        tacometro_2.setColorEscala(Color.BLUE, Color.YELLOW, Color.GREEN);
        //asignar angulos a la escala
        tacometro_2.setAnguloSectores(45,75,150);
        //asignar las unidades
        tacometro_2.setUnidades("Gauss");
        //asignar rangos
        tacometro_2.setRango(-20, 40);
        //asignar la medida
        tacometro_2.setMedida(32);
        //asingar color al tacometro
        tacometro_2.setColorFondoTacometro(Color.BLACK);

        //crear objeto GaugeSimple
        tacometro_3 = new GaugeSimple(this);
        //cambiar atributos (propiedades)
        //darle color blanco al lienzo antes de pegar
        tacometro_3.setBackgroundColor(Color.WHITE);
        //darle color a los sectores
        tacometro_3.setColorEscala(Color.YELLOW, Color.rgb(255, 128, 0), Color.RED);
        //asignar las unidades
        tacometro_3.setUnidades("Joule");
        //asignar rangos
        tacometro_3.setRango(0, 100);
        //asignar la medida
        tacometro_3.setMedida(95);
        //asingar color al fondo del tacometro
        tacometro_3.setColorFondoTacometro(Color.BLACK);
        //asingar colores a las secciones
        tacometro_3.setAnguloSectores(80,35,155);
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

