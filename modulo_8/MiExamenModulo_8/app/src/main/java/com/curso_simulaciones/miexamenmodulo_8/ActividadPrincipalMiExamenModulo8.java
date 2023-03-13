package com.curso_simulaciones.miexamenmodulo_8;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.curso_simulaciones.miexamenmodulo_8.vista.Pizarra;


public class ActividadPrincipalMiExamenModulo8 extends Activity {
    Pizarra pizarra;
    private int tamanoLetraResolucionIncluida;
    private Button botonUno, botonDos, botonTres, botonCuatro, botonCinco;
    private ImageView imagen;
    private int margenesResolucionIncluida;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        gestionarResolucion();

        //para crear elementos de la GUI
        crearElementosGUI();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        //para administrar los eventos
        eventos();
    }//fin del método onCreate
    private void gestionarResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;
        int dimensionReferencia;

        //tomar el menor valor entre alto y ancho de pantalla
        if (alto > ancho) {

            dimensionReferencia = ancho;
        } else {

            dimensionReferencia = alto;
        }


        //una estimación de un buen tamaño
        int tamanoLetra = dimensionReferencia / 26;


        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);
        margenesResolucionIncluida = tamanoLetraResolucionIncluida;

    }//fin método gestionarResolucion()
    private void crearElementosGUI() {
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.YELLOW);

        botonUno = new Button(this);
        botonUno.setTextSize(TypedValue.COMPLEX_UNIT_SP, 2*tamanoLetraResolucionIncluida);
        botonUno.setText("UNO");
        botonUno.setTextColor(Color.WHITE);
        botonUno.getBackground().setColorFilter(Color.rgb(153, 163, 164), PorterDuff.Mode.MULTIPLY);

        botonDos = new Button(this);
        botonDos.setTextSize(TypedValue.COMPLEX_UNIT_SP, 2*tamanoLetraResolucionIncluida);
        botonDos.setText("DOS");
        botonDos.setTextColor(Color.WHITE);
        botonDos.getBackground().setColorFilter(Color.rgb(153, 163, 164), PorterDuff.Mode.MULTIPLY);
        botonDos.setEnabled(false);

        botonTres = new Button(this);
        botonTres.setTextSize(TypedValue.COMPLEX_UNIT_SP, 2*tamanoLetraResolucionIncluida);
        botonTres.setText("TRES");
        botonTres.setTextColor(Color.WHITE);
        botonTres.getBackground().setColorFilter(Color.rgb(153, 163, 164), PorterDuff.Mode.MULTIPLY);
        botonTres.setEnabled(false);

        botonCuatro = new Button(this);
        botonCuatro.setTextSize(TypedValue.COMPLEX_UNIT_SP, 2*tamanoLetraResolucionIncluida);
        botonCuatro.setText("CUATRO");
        botonCuatro.setTextColor(Color.WHITE);
        botonCuatro.getBackground().setColorFilter(Color.rgb(153, 163, 164), PorterDuff.Mode.MULTIPLY);
        botonCuatro.setEnabled(false);

        botonCinco = new Button(this);
        botonCinco.setTextSize(TypedValue.COMPLEX_UNIT_SP, 2*tamanoLetraResolucionIncluida);
        botonCinco.setText("CINCO");
        botonCinco.setTextColor(Color.WHITE);
        botonCinco.getBackground().setColorFilter(Color.rgb(153, 163, 164), PorterDuff.Mode.MULTIPLY);
        botonCinco.setEnabled(false);

        imagen = new ImageView(this);
        imagen.setImageResource(R.drawable.logo);
    }
    private LinearLayout crearGUI(){
        int a=margenesResolucionIncluida;
        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        //para definir los pesos de las filas que se agregaran
        linearPrincipal.setWeightSum(10.0f);
        linearPrincipal.setBackgroundColor(Color.rgb(46, 64, 83));

        LinearLayout linearArriba = new LinearLayout(this);
        //los componentes se agregarán verticalmente
        linearArriba.setOrientation(LinearLayout.HORIZONTAL);
        //para definir los pesos de las filas que se agregaran
        linearArriba.setWeightSum(10.0f);

        LinearLayout.LayoutParams parametro_pegado_linear_arriba = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametro_pegado_linear_arriba.weight = 8.0f;
        parametro_pegado_linear_arriba.setMargins(a,a,a,0);
        linearPrincipal.addView(linearArriba,parametro_pegado_linear_arriba);


        LinearLayout linearAbajo = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearAbajo.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los botones que se agregaran esta fila
        linearAbajo.setWeightSum(1.0f);
        linearAbajo.setBackgroundColor(Color.rgb(153, 163, 164));

        LinearLayout.LayoutParams parametro_pegado_linear_abajo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametro_pegado_linear_abajo.weight = 2.0f;
        parametro_pegado_linear_abajo.setMargins(a,a,a,a);
        linearPrincipal.addView(linearAbajo,parametro_pegado_linear_abajo);

        LinearLayout linearUno = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearUno.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los componentes que se agregaran en esta fila
        linearUno.setWeightSum(1.0f);
        linearUno.setBackgroundColor(Color.rgb(131, 145, 146));

        LinearLayout.LayoutParams parametro_pegado_linear_uno = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametro_pegado_linear_uno.weight = 2.5f;
        parametro_pegado_linear_uno.setMargins(a,a,a,a);
        linearArriba.addView(linearUno,parametro_pegado_linear_uno);

        LinearLayout linearDos = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearDos.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los componentes que se agregaran en  esta fila
        linearDos.setWeightSum(1.0f);
        linearDos.setBackgroundColor(Color.rgb(100, 163, 164));

        LinearLayout.LayoutParams parametro_pegado_linear_dos = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametro_pegado_linear_dos.weight = 7.5f;
        parametro_pegado_linear_dos.setMargins(a,a,a,a);
        linearArriba.addView(linearDos,parametro_pegado_linear_dos);

        linearDos.addView(pizarra);

        LinearLayout linearTres = new LinearLayout(this);
        linearTres.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los componentes que se agregaran en  esta fila
        linearTres.setWeightSum(5.0f);
        linearTres.setBackgroundColor(Color.rgb(23, 32, 42));

        LinearLayout.LayoutParams parametro_pegado_linear_tres = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametro_pegado_linear_tres.weight = 1.0f;
        parametro_pegado_linear_tres.setMargins(a,a,a,a);
        linearAbajo.addView(linearTres,parametro_pegado_linear_tres);

        LinearLayout.LayoutParams parametros_pegado_botones = new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_botones.weight = 1.0f;
        parametros_pegado_botones.setMargins(0,a,a,a);
        linearTres.addView(botonUno, parametros_pegado_botones);
        linearTres.addView(botonDos, parametros_pegado_botones);
        linearTres.addView(botonTres, parametros_pegado_botones);
        linearTres.addView(botonCuatro, parametros_pegado_botones);
        linearTres.addView(botonCinco, parametros_pegado_botones);

        LinearLayout.LayoutParams parametros_pegado_imagen_abajo = new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_imagen_abajo.weight = 1.0f;
        linearUno.addView(imagen,parametros_pegado_imagen_abajo);


        return linearPrincipal;
    }

    private void eventos(){

        //evento del boton con etiqueta UNO
        botonUno.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                pizarra.setBackgroundColor(Color.WHITE);
                botonUno.setEnabled(false);
                botonCinco.setEnabled(true);
            }
        });
        botonCinco.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                botonUno.setBackgroundColor(Color.BLUE);
                botonDos.setBackgroundColor(Color.BLUE);
                botonTres.setBackgroundColor(Color.BLUE);
                botonCuatro.setBackgroundColor(Color.BLUE);
                botonCinco.setBackgroundColor(Color.BLUE);
            }
        });
        botonDos.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {}});
        botonTres.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {}});
        botonCuatro.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {}});
    }
}
