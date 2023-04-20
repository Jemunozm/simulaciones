package com.curso_simulaciones.micuadragesimaprimeraapp.actividades_secundarias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.micuadragesimaprimeraapp.R;
import com.curso_simulaciones.micuadragesimaprimeraapp.utilidades.Boton;

public class ActividadComunicacion extends Activity {

    private Boton pub, sub;


    private int COLOR_1 = Color.rgb(220, 156, 80);


    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        gestionarResolucion();

        creacionElementosGUI();

        setContentView(crearGUI());

        eventos();


    }


    private void gestionarResolucion() {


    }//fin método gestionarResolucion()


    private void creacionElementosGUI() {

        pub = new Boton(this);
        pub.setImagen(R.drawable.wifi_subida);

        sub = new Boton(this);
        sub.setImagen(R.drawable.wifi_bajada);

    }


    private LinearLayout crearGUI() {

        //LinearLayoutPrincipal
        LinearLayout linearLayoutPrincipal = new LinearLayout(this);
        linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearLayoutPrincipal.setBackgroundColor(Color.WHITE);
        linearLayoutPrincipal.setWeightSum(10f);


        //LinearLayout primera fila
        LinearLayout linearLayoutPrimeraFila = new LinearLayout(this);
        linearLayoutPrimeraFila.setOrientation(LinearLayout.VERTICAL);

        //fondo primera fila
        Drawable fondo = getResources().getDrawable(R.drawable.modelo);
        linearLayoutPrimeraFila.setBackgroundDrawable(fondo);


        //LinearLayout segunda fila
        LinearLayout linearLayoutSegundaFila = new LinearLayout(this);
        linearLayoutSegundaFila.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutSegundaFila.setBackgroundColor(Color.WHITE);
        linearLayoutSegundaFila.setWeightSum(2f);


        //pegar las filas en el princpal
        LinearLayout.LayoutParams parametrosPrimeraFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPrimeraFila.weight = 9.0f;
        linearLayoutPrimeraFila.setLayoutParams(parametrosPrimeraFila);


        LinearLayout.LayoutParams parametrosSegundaFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosSegundaFila.weight = 1.0f;
        linearLayoutSegundaFila.setLayoutParams(parametrosSegundaFila);


        linearLayoutPrincipal.addView(linearLayoutPrimeraFila);
        linearLayoutPrincipal.addView(linearLayoutSegundaFila);


        //pegado botones tercera fila
        LinearLayout.LayoutParams parametrosPegadoBotones = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadoBotones.weight = 1.0f;

        //Adicionar a la tercera fila los botones
        linearLayoutSegundaFila.addView(pub, parametrosPegadoBotones);
        linearLayoutSegundaFila.addView(sub, parametrosPegadoBotones);

        return linearLayoutPrincipal;
    }

    private void eventos() {

        //evento cliente
        pub.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadComoClientePubMQTT();

            }
        });

        //evento cliente
        sub.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadComoClienteSubMQTT();

            }
        });


    }//fin eventos()


    private void lanzarActividadComoClientePubMQTT() {

        Intent intent = new Intent(this, ActividadComoClientePubMQTT.class);
        startActivity(intent);
    }

    private void lanzarActividadComoClienteSubMQTT() {

        Intent intent = new Intent(this, ActividadComoClienteSubMQTT.class);
        startActivity(intent);
    }

}
