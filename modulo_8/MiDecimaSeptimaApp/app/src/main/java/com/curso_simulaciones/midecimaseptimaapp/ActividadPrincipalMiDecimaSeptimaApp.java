package com.curso_simulaciones.midecimaseptimaapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.curso_simulaciones.midecimaseptimaapp.actividades_secundarias.ActividadSecundaria_1;
import com.curso_simulaciones.midecimaseptimaapp.actividades_secundarias.ActividadSecundaria_2;
import com.curso_simulaciones.midecimaseptimaapp.actividades_secundarias.ActividadSecundaria_3;
import com.curso_simulaciones.midecimaseptimaapp.datos.AlmacenDatosRAM;

public class ActividadPrincipalMiDecimaSeptimaApp extends Activity {

    private Button botonUno, botonDos, botonTres, botonCuatro,
            botonCinco,botonSeis,botonSiete,botonOcho,
            botonNueve,botonDiez,botonOnce,botonDoce;
    private int tamanoLetraResolucionIncluida;


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



    } //fin del método onCreate

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
        int tamanoLetra = dimensionReferencia / 20;


        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);

        //guardar en el almacen de datos para que otras clases la accedan fácilmente
        AlmacenDatosRAM.tamanoLetraResolucionIncluida = tamanoLetraResolucionIncluida;


    }//fin método gestionarResolucion()



    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI(){


   /*
     1. El tamaño a usar de la letra tiene corrección de
        resolución y tamaño de pantalla.

     2. Se usa un diseño de color de botón especial
        PorterDuff.Mode.MULTIPLY. Podría usarse uno más sencillo
        Como por ejemplo boton.setBackgroundColor(Color.RED)
    */

        botonUno = new Button(this);
        botonUno.setTextSize(TypedValue.COMPLEX_UNIT_SP,tamanoLetraResolucionIncluida);
        botonUno.setText("UNO");
        botonUno.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);

        botonDos = new Button(this);
        botonDos.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonDos.setText("DOS");
        botonDos.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);

        botonTres = new Button(this);
        botonTres.setTextSize(TypedValue.COMPLEX_UNIT_SP,tamanoLetraResolucionIncluida);
        botonTres.setText("TRES");
        botonTres.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);
        botonTres.setEnabled(false);

        botonCuatro = new Button(this);
        botonCuatro.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonCuatro.setText("CUATRO");
        botonCuatro.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);
        botonCuatro.setEnabled(false);

        botonCinco = new Button(this);
        botonCinco.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonCinco.setText("CINCO");
        botonCinco.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);
        botonCinco.setEnabled(false);

        botonSeis = new Button(this);
        botonSeis.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonSeis.setText("SEIS");
        botonSeis.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);
        botonSeis.setEnabled(false);

        botonSiete = new Button(this);
        botonSiete.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonSiete.setText("SIETE");
        botonSiete.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);
        botonSiete.setEnabled(false);

        botonOcho = new Button(this);
        botonOcho.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonOcho.setText("OCHO");
        botonOcho.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);
        botonOcho.setEnabled(false);

        botonNueve = new Button(this);
        botonNueve.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonNueve.setText("NUEVE");
        botonNueve.getBackground().setColorFilter(Color.rgb(220, 156,
                80), PorterDuff.Mode.MULTIPLY);
        botonNueve.setEnabled(false);

        botonDiez = new Button(this);
        botonDiez.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonDiez.setText("DIEZ");
        botonDiez.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);
        botonDiez.setEnabled(false);

        botonOnce = new Button(this);
        botonOnce.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonOnce.setText("ONCE");
        botonOnce.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);
        botonOnce.setEnabled(false);

        botonDoce = new Button(this);
        botonDoce.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonDoce.setText("DOCE");
        botonDoce.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);
        botonDoce.setEnabled(false);




    }//fin método crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI(){

        LinearLayout linearPrincipal = new LinearLayout(this);

        //los componentes se agregarán verticalmente
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        //para definir los pesos de las filas que se agregaran
        linearPrincipal.setWeightSum(6.0f);

        //las seis filas
        LinearLayout linearUno = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearUno.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los botones que se agregaran esta fila
        linearUno.setWeightSum(2.0f);

        LinearLayout linearDos = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearDos.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los botones que se agregaran esta fila
        linearDos.setWeightSum(2.0f);

        LinearLayout linearTres = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearTres.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los botones que se agregaran esta fila
        linearTres.setWeightSum(2.0f);

        LinearLayout linearCuatro = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearCuatro.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los botones que se agregaran esta fila
        linearCuatro.setWeightSum(2.0f);

        LinearLayout linearCinco = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearCinco.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los botones que se agregaran esta fila
        linearCinco.setWeightSum(2.0f);

        LinearLayout linearSeis = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearSeis.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los botones que se agregaran esta fila
        linearSeis.setWeightSum(2.0f);


        //pegado de filas a linear principal
    /*
      Pegado vetical (el ancho se acomoda en todo el contenedor
      cada fila se pega con un peso de 1.0. Son seis filas,
      la suma da 6.0.
      Como el pegado es vertical el 0 debe ir como segundo argumento
    */
        LinearLayout.LayoutParams parametros_pegado_filas = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_pegado_filas.weight = 1.0f;
        linearPrincipal.addView(linearUno, parametros_pegado_filas);
        linearPrincipal.addView(linearDos, parametros_pegado_filas);
        linearPrincipal.addView(linearTres, parametros_pegado_filas);
        linearPrincipal.addView(linearCuatro, parametros_pegado_filas);
        linearPrincipal.addView(linearCinco, parametros_pegado_filas);
        linearPrincipal.addView(linearSeis, parametros_pegado_filas);

        //pegado de botones a cada fila
    /*
      Pegado horizontal (el alto se acomoda en todo el contenedor
      se pegan dos botones por fila con un peso de 1.0,
      la suma por fila da 2.0.
      Como el pegado es vertical el 0 debe ir como primer argumento
    */
        LinearLayout.LayoutParams parametros_pegado_botones = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_botones.weight = 1.0f;
        linearUno.addView(botonUno, parametros_pegado_botones);
        linearUno.addView(botonDos, parametros_pegado_botones);
        linearDos.addView(botonTres, parametros_pegado_botones);
        linearDos.addView(botonCuatro, parametros_pegado_botones);
        linearTres.addView(botonCinco, parametros_pegado_botones);
        linearTres.addView(botonSeis, parametros_pegado_botones);
        linearCuatro.addView(botonSiete, parametros_pegado_botones);
        linearCuatro.addView(botonOcho, parametros_pegado_botones);
        linearCinco.addView(botonNueve, parametros_pegado_botones);
        linearCinco.addView(botonDiez, parametros_pegado_botones);
        linearSeis.addView(botonOnce, parametros_pegado_botones);
        linearSeis.addView(botonDoce, parametros_pegado_botones);





        return linearPrincipal;

    }//fin método crearGUI


    /*Administra los eventos de la GUI*/
    private void eventos(){

        //evento del boton con etiqueta UNO
        botonUno.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadSecundaria_1();
            }
        });


        //evento del boton con etiqueta UNO
        botonDos.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadSecundaria_2();
            }
        });

        //evento del boton con etiqueta UNO
        botonTres.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadSecundaria_3();
            }
        });


    }//fin método eventos

    //métodos que lanzar las actividades secundarias
    private void lanzarActividadSecundaria_1(){


        Intent intent = new Intent(this, ActividadSecundaria_1.class);
        startActivity(intent);

    }


    private void lanzarActividadSecundaria_2(){


        Intent intent = new Intent(this, ActividadSecundaria_2.class);
        startActivity(intent);

    }

    private void lanzarActividadSecundaria_3(){


        Intent intent = new Intent(this, ActividadSecundaria_3.class);
        startActivity(intent);
    }

    protected void onResume(){
        super.onResume();

        if(AlmacenDatosRAM.habilitar_boton_tres==true){
            botonTres.setEnabled(true);
        } else {

            botonTres.setEnabled(false);

        }
    }


    protected void onDestroy() {

        //Volver los valores de los daos a su estado por defecto
        AlmacenDatosRAM.nombres = "Xxxx  Xxxx";
        AlmacenDatosRAM.apellidoUno = "Yyyy";
        AlmacenDatosRAM.apellidoDos = "Zzzz";
        AlmacenDatosRAM.edad = 0;

        this.finish();
        super.onDestroy();
    }


}//fin método eventos

