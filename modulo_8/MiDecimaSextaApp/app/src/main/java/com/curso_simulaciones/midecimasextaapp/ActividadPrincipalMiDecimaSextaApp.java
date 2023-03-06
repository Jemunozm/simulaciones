package com.curso_simulaciones.midecimasextaapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class ActividadPrincipalMiDecimaSextaApp extends Activity {

    private int tamanoLetraResolucionIncluida;
    private Button botonUno, botonDos, botonTres, botonCuatro, botonCinco;

    private LinearLayout linearIzquierdo;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gestionarResolucion();
        //para crear elementos de la GUI
        crearElementosGUI();

        /*
        Para informar cómo se debe pegar el administrador de
        diseño LinearLayout obtenido con el método crearGui()
        */
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

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
        int tamanoLetra = dimensionReferencia / 20;


        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);
    }

    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {


 /*
 1. El tamaño a usar de la letra tiene corrección de
    resolución y tamaño de pantalla.
*/

        botonUno = new Button(this);
        botonUno.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonUno.setText("UNO");
        botonUno.setBackgroundColor(Color.rgb(220, 156, 80));

        botonDos = new Button(this);
        botonDos.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonDos.setText("DOS");
        botonDos.setBackgroundColor(Color.rgb(220, 156, 80));

        botonTres = new Button(this);
        botonTres.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonTres.setText("TRES");
        botonTres.setBackgroundColor(Color.rgb(220, 156, 80));

        botonCuatro = new Button(this);
        botonCuatro.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonCuatro.setText("CUATRO");
        botonCuatro.setBackgroundColor(Color.rgb(220, 156, 80));

        botonCinco = new Button(this);
        botonCinco.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonCinco.setText("CINCO");
        botonCinco.setBackgroundColor(Color.rgb(220, 156, 80));
    }//fin método crearElementosGUI



    /*método responsable de administrar el diseño de la GUI*/

    private LinearLayout crearGUI() {

         /*
          Creación del LinearLayout principal. Su color
          es amarillo
         */
        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setBackgroundColor(Color.YELLOW);

        //los componentes se agregarán horizontalamente
        linearPrincipal.setOrientation(LinearLayout.HORIZONTAL);
    /*
     Definir la suma los pesos  de los componentes que se pegarán a
     LinearLayout principal
     */
        linearPrincipal.setWeightSum(10.0f);


    /*
      Creación del LinearLayout izquierdo. Su color
      es verde
     */
        linearIzquierdo = new LinearLayout(this);
        linearIzquierdo.setBackgroundColor(Color.GREEN);

    /*
      Creación del LinearLayout derecho. Su color
      es gris
    */
        LinearLayout linearDerecho = new LinearLayout(this);
        linearDerecho.setBackgroundColor(Color.GRAY);

     /*
     Atributos con que se pega el LinearLayout
     izquierdo a LinearLayout principal.
     Como el pegado es horizontal el
     CERO debe ir de primero
     */

        LinearLayout.LayoutParams parametros_pegado_linear_izquierdo = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        //peso con que se pega
        parametros_pegado_linear_izquierdo.weight = 7.0f;
        //se dejará un espacio de 10 pixeles en los bordes
        parametros_pegado_linear_izquierdo.setMargins(10, 10, 10, 10);


    /*
     Atributos con que se pega el LinearLayout
     derecho a LinearLayout principal.
     Como el pegado es horizontal el
     CERO debe ir de primero
     */
        LinearLayout.LayoutParams parametros_pegado_linear_derecho = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_linear_derecho.weight = 3.0f;
        //se dejará un espacio de 10 pixeles en los bordes
        parametros_pegado_linear_derecho.setMargins(10, 10, 10, 10);



    /*
      Pegar ahora los dos LinearLayout (izquerdo
      y el derecho al LinearLayout principal.
    */
        linearPrincipal.addView(linearIzquierdo, parametros_pegado_linear_izquierdo);
        linearPrincipal.addView(linearDerecho, parametros_pegado_linear_derecho);

        /*
      Creación del LinearLayout uno. Su color
      es rojo
     */
        LinearLayout linearUno = new LinearLayout(this);
        linearUno.setBackgroundColor(Color.RED);

    /*
     Creación del LinearLayout dos. Su color
     es azul
     */
        LinearLayout linearDos = new LinearLayout(this);
        linearDos.setBackgroundColor(Color.BLUE);

     /*
      Creación del LinearLayout tres. Su color
      es cyan
     */
        LinearLayout linearTres = new LinearLayout(this);
        linearTres.setBackgroundColor(Color.CYAN);

    /*
      Creación del LinearLayout cuatro. Su color
      es magenta
    */
        LinearLayout linearCuatro = new LinearLayout(this);
        linearCuatro.setBackgroundColor(Color.MAGENTA);


    /*
     Para definir la suma de los pesos
     de los componentes que se pegarán a
     LinearLayout derecho
     */
        linearDerecho.setWeightSum(4.0f);
        //los componentes se agregarán verticalmente
        linearDerecho.setOrientation(LinearLayout.VERTICAL);

    /*
     Atributos con que se pegan los LinearLayout
     uno, dos, tres y cuatro a LinearLayout dereccho.
     En este caso Todos se pegan con los
     mismos atributos. El CERO debe ir de segundo
     */
        LinearLayout.LayoutParams parametros_pegado_linear_a_derecho = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_pegado_linear_a_derecho.weight = 1.0f;
        //se dejará un espacio de 10 pixeles en los costados
        //y 5 pixeles arriba y abajo
        parametros_pegado_linear_a_derecho.setMargins(10, 5, 10, 5);


     /*
      Pegar ahora los cuatro LinearLayout (1,2, 3 y 4)
      al LinearLayout derecho.
    */
        linearDerecho.addView(linearUno, parametros_pegado_linear_a_derecho);
        linearDerecho.addView(linearDos, parametros_pegado_linear_a_derecho);
        linearDerecho.addView(linearTres, parametros_pegado_linear_a_derecho);
        linearDerecho.addView(linearCuatro, parametros_pegado_linear_a_derecho);

        //pegar los botones
        linearUno.setWeightSum(2f);
        linearUno.setOrientation(LinearLayout.HORIZONTAL);
        linearDos.setWeightSum(1f);
        linearDos.setOrientation(LinearLayout.HORIZONTAL);
        linearTres.setWeightSum(1f);
        linearTres.setOrientation(LinearLayout.HORIZONTAL);
        linearCuatro.setWeightSum(1f);
        linearCuatro.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams parametros_pegado_botones = new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_botones.weight = 1.0f;
        parametros_pegado_botones.setMargins(10,5,10,5);
        linearUno.addView(botonUno, parametros_pegado_botones);
        linearUno.addView(botonDos, parametros_pegado_botones);
        linearDos.addView(botonTres, parametros_pegado_botones);
        linearTres.addView(botonCuatro, parametros_pegado_botones);
        linearCuatro.addView(botonCinco, parametros_pegado_botones);


        return linearPrincipal;
    }//fin método crearGUI

    /*Administra los eventos de la GUI*/
    private void eventos() {

        //evento del boton con etiqueta UNO
        botonUno.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                linearIzquierdo.setBackgroundColor(Color.BLACK);
            }
        });

        //evento del boton con etiqueta DOS
        botonDos.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                linearIzquierdo.setBackgroundColor(Color.WHITE);
            }
        });

        //evento del boton con etiqueta TRES
        botonTres.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                linearIzquierdo.setBackgroundColor(Color.RED);
            }
        });

        //evento del boton con etiqueta CUATRO
        botonCuatro.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                linearIzquierdo.setBackgroundColor(Color.rgb(200,200,50));
            }
        });

        //evento del boton con etiqueta CINCO
        botonCinco.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                linearIzquierdo.setBackgroundColor(Color.GREEN);
            }
        });
    }



    protected void onDestroy() {

        this.finish();

        super.onDestroy();

    }//fin del método onDestroy
}
