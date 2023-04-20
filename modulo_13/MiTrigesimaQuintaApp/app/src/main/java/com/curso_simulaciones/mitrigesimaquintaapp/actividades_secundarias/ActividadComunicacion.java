package com.curso_simulaciones.mitrigesimaquintaapp.actividades_secundarias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.curso_simulaciones.mitrigesimaquintaapp.R;
import com.curso_simulaciones.mitrigesimaquintaapp.datos.AlmacenDatosRAM;

public class ActividadComunicacion extends Activity {

    private Button botonCliente, botonServidor;
    private int tamanoLetraResolucionIncluida;
    private int COLOR_1 = Color.rgb(220, 156, 80);


    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        gestionarResolucion();

        creacionElementosGUI();

        setContentView(crearGUI());

        eventos();


    }


    private void gestionarResolucion() {

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (0.8 * AlmacenDatosRAM.tamanoLetraResolucionIncluida);


    }//fin método gestionarResolucion()solucion()


    private void creacionElementosGUI() {

        botonCliente = new Button(this);
        botonCliente.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonCliente.setText("CLIENTE");
        botonCliente.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonCliente.setEnabled(true);

        botonServidor = new Button(this);
        botonServidor.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonServidor.setText("SERVIDOR");
        botonServidor.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonServidor.setEnabled(true);

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
        linearLayoutSegundaFila.addView(botonCliente, parametrosPegadoBotones);
        linearLayoutSegundaFila.addView(botonServidor, parametrosPegadoBotones);

        return linearLayoutPrincipal;
    }

    private void eventos() {

        //evento cliente
        botonCliente.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                botonServidor.setEnabled(false);
                lanzarActividadComoCliente();
                botonCliente.setEnabled(false);
                AlmacenDatosRAM.rol = "SOY EL CLIENTE";

            }
        });

        //evento cliente
        botonServidor.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                botonCliente.setEnabled(false);
                lanzarActividadComoServidor();
                botonServidor.setEnabled(false);
                AlmacenDatosRAM.rol = "SOY EL SERVIDOR";

            }
        });


    }//fin eventos()


    private void lanzarActividadComoCliente() {

        Intent intent = new Intent(this, ActividadComoClienteBluetooth.class);
        startActivity(intent);
    }

    private void lanzarActividadComoServidor() {

        Intent intent = new Intent(this, ActividadComoServidorBluetooth.class);
        startActivity(intent);
    }

}

