package com.curso_simulaciones.miseptima_1_app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.miseptima_1_app.objetos_laboratorio.Polea;
import com.curso_simulaciones.miseptima_1_app.vista.Pizarra;

public class ActividadPrincipalMiSeptima_1_App extends Activity {

    //pizarra para dibujar
    private Pizarra pizarra;

    //objetos dibujables para Pizarra
    private Polea polea_1, polea_2, polea_3;
    //arreglo que contine las tres poleas
    private Polea poleas[] = new Polea[3];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*llamada al método para crear los elementos de la interfaz gráfica de usuario (GUI)*/
        crearElementosGui();


        /*para informar cómo se debe adaptar la GUI a la pantalla del dispositivo*/
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        /*pegar al contenedor la GUI: en el argumento se está llamando al método crearGui()*/
        this.setContentView(crearGui(), parametro_layout_principal);

        //crear objetos de laboratorio
        crearObjetosLaboratorio();


    }


    //crear los objetos de la interfaz gráfica de usuario (GUI)
    private void crearElementosGui() {
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.BLACK);
    }


    //organizar la distribución de los objetos de de la GUI usando administradores de diseño
    private LinearLayout crearGui() {

        //administrador de diseño
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.rgb(250, 150, 50));


        /*pegar el objeto cadena (es tipo TextView)*/
        //parámetro de pegada
        LinearLayout.LayoutParams parametrosPegada = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegada.setMargins(50, 50, 50, 50);
        parametrosPegada.weight = 1.0f;
        //pegar
        linear_principal.addView(pizarra, parametrosPegada);


        return linear_principal;

    }


    /*Crea los objetos polea con su estado inicial*/
    private void crearObjetosLaboratorio() {

        /*El constructor por defecto hace una polea
          polea ubicada en (0,0) de radio 100 y color rojo
        */

        polea_1 = new Polea();
        //agregar al arreglo poleas[]
        poleas[0] = polea_1;


        //polea ubicada en (600,200) de radio 150 y color rojo
        polea_2 = new Polea(600f, 200f, 150);
        //agregar al arreglo poleas[]
        poleas[1] = polea_2;

        //polea ubicada en (600,500) de radio 150 y color verde
        polea_3 = new Polea(600, 600, 150);
        polea_3.setColorPolea(Color.GREEN);
        //agregar al arreglo poleas[]
        poleas[2] = polea_3;

        //estado inicial de la escen:envía el arreglo de poleas a la pizarra.
        pizarra.setEstadoEscena(poleas);

    }

}

