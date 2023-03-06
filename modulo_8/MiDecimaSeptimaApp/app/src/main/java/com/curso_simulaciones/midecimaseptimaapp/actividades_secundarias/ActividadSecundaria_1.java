package com.curso_simulaciones.midecimaseptimaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.midecimaseptimaapp.R;

public class ActividadSecundaria_1 extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //para crear elementos de la GUI
        crearElementosGUI();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        //para administrar los eventos
        eventos();



    } //fin del método onCreate



    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI(){



    }//fin método crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI(){

        LinearLayout linearPrincipal = new LinearLayout(this);

        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setGravity(Gravity.CENTER_HORIZONTAL);
        linearPrincipal.setGravity(Gravity.FILL);
        linearPrincipal.setBackgroundColor(Color.rgb(255, 252, 131));

//fondo
        Drawable fondo = getResources().getDrawable(R.drawable.gatico);
        linearPrincipal.setBackgroundDrawable(fondo);


        return linearPrincipal;

    }//fin método crearGUI


    /*Administra los eventos de la GUI*/
    private void eventos(){


    }//fin método eventos

}
