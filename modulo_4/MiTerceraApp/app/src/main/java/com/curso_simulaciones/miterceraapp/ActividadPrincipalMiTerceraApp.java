package com.curso_simulaciones.miterceraapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.miterceraapp.componentes.Pizarra;

public class ActividadPrincipalMiTerceraApp extends Activity {
    private Pizarra lienzo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    /*llamada al método para crear los elementos de la interfaz
    gráfica de usuario (GUI)*/
        crearElementosGui();


    /*para informar cómo se debe adaptar la GUI a la pantalla del
    dispositivo*/
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


    /*pegar al contenedor la GUI: en el argumento se está llamando
    al método crearGui()*/
        this.setContentView(crearGui(), parametro_layout_principal);


    }


    /*crear los objetos de la interfaz gráfica de usuario (GUI)*/
    private void crearElementosGui() {
        //crear objeto Pizarra
        lienzo = new Pizarra(this);
        //darle color blanco al lienzo antes de pegar
        lienzo.setBackgroundColor(Color.WHITE);


    }


    /*organizar la distribución de los objetos de de la GUI usando
    administradores de diseño*/
    private LinearLayout crearGui() {

        //administrador de diseño
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.rgb(250, 150, 50));

        /*parametro para pegar el lienzo*/
        LinearLayout.LayoutParams parametrosPegadaLienzo = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadaLienzo.setMargins(20, 20, 20, 20);
        parametrosPegadaLienzo.weight = 1.0f;

        //pegar lienzo
        linear_principal.addView(lienzo, parametrosPegadaLienzo);


        return linear_principal;

    }

}
