package com.curso_simulaciones.mivigesimaquintaapp.controlador;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.curso_simulaciones.mivigesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimaquintaapp.vista.CR;
import com.curso_simulaciones.mivigesimaquintaapp.vista.Pizarra;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Cuerda;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.CuerpoRectangular;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Flecha;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Marca;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Masa;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Polea;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Regla;

public class ActividadControladora extends Activity {

    //variable tamaño de las letras basado en resolución de pantalla
    private int tamanoLetraResolucionIncluida;

    private Pizarra pizarra;
    //Objetos GUI necesarios
    private TextView text_m1, text_m2, text_m3;
    private SeekBar seek_bar_m1, seek_bar_m2, seek_bar_m3;
    private Button boton_empezar, boton_pausar;


    //valores de las variables
    private float m1 = 15;
    private float m2 = 10;
    private float m3 = 15;

    //valores iniciales puntos escena
    //centros de masa de las masas
    private float x1_pixeles, x2_pixeles, x3_pixeles, y1_pixeles, y2_pixeles, y3_pixeles;
    //centros de masa de las poleas
    private float xp1_pixeles, xp2_pixeles, xP3_pixeles, yp1_pixeles, yp2_pixeles, yP3_pixeles;
    //radio de las poleas
    private float radio;

    //dimensiones de los bloques
    private float ancho_bloque, alto_bloque;


    private Masa masa_1, masa_2, masa_3;
    private Polea polea_1, polea_2, polea_3;
    private Flecha flecha_y;
    private CuerpoRectangular barra_1, barra_2, flecha_x, flecha_Y;
    private Cuerda cuerda_1, cuerda_2, cuerda_3, cuerda_4, cuerda_5;
    private Marca marca_y, marca_x;

    private ObjetoLaboratorio[] objetos = new ObjetoLaboratorio[25];

    //hilo responsable de controlar la animación
    private HiloAnimacion hilo;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gestionarResolucion();


        //para crear elementos de la GUI
        crearElementosGUI();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        //hilo que administrador los cálculos
        hilo = new HiloAnimacion(this);
        hilo.start();


        //para administrar los eventos
        eventos();


    }//fin onCreate

    /*Método auxiliar para asuntos de resolución*/
    private void gestionarResolucion() {

        tamanoLetraResolucionIncluida = (int) (0.8f * AlmacenDatosRAM.tamanoLetraResolucionIncluida);
        /*
        Según el diseño de la GUI se puede anticipar cuál es la
        dimensión de la pizarra. En este caso es el 80% del ancho
        de la pantalla y el 100% del alto de la misma
        El cálculo del alto y el ancho de la pantalla
        se hizo en la clase principal con el móvil
        en portrait y aquí estamos en posición lanscape.
        Por tanto troquemos alto por ancho y viceversa
        */
        CR.anchoPizarra = 0.80f * AlmacenDatosRAM.alto_pantalla;
        CR.altoPizarra = AlmacenDatosRAM.ancho_pantalla;


    }

    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {

        text_m1 = new TextView(this);
        text_m1.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        text_m1.setGravity(Gravity.CENTER);
        String marca_m1 = "MASA M1 \n 5 a 25 kg";//con salto de línea
        text_m1.setTextColor(Color.WHITE);
        text_m1.setText(marca_m1);

        text_m2 = new TextView(this);
        text_m2.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        text_m2.setGravity(Gravity.CENTER);
        text_m2.setTextColor(Color.WHITE);
        String marca_m2 = "MASA M2 \n 5 a 25 kg ";//con salto de línea
        text_m2.setText(marca_m2);

        text_m3 = new TextView(this);
        text_m3.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        text_m3.setGravity(Gravity.CENTER);
        text_m3.setTextColor(Color.WHITE);
        String marca_m3 = "MASA M3 \n 5 a 25 kg ";//con salto de línea
        text_m3.setText(marca_m3);


        seek_bar_m1 = new SeekBar(this);
        seek_bar_m1.setMax(20);
        seek_bar_m1.setProgress(15);//masa m1 inicial 15
        AlmacenDatosRAM.m1 = 15;
        seek_bar_m1.setScaleY(0.5f);


        seek_bar_m2 = new SeekBar(this);
        seek_bar_m2.setMax(20);
        seek_bar_m2.setProgress(10);//masa m2 inicial 10
        AlmacenDatosRAM.m2 = 10;
        seek_bar_m2.setScaleY(0.5f);

        seek_bar_m3 = new SeekBar(this);
        seek_bar_m3.setMax(20);
        seek_bar_m3.setProgress(15);//masa m3 inicial 15
        seek_bar_m3.getThumb().setColorFilter(Color.rgb(72, 201, 176), PorterDuff.Mode.MULTIPLY);

        AlmacenDatosRAM.m3 = 15;
        seek_bar_m3.setScaleY(0.5f);


        boton_empezar = new Button(this);
        boton_empezar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        boton_empezar.setText("EMPEZAR");
        boton_empezar.setTextColor(Color.WHITE);


        boton_empezar.getBackground().setColorFilter(Color.rgb(72, 201, 176), PorterDuff.Mode.MULTIPLY);

        boton_pausar = new Button(this);
        boton_pausar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        boton_pausar.setText("PAUSAR");
        boton_pausar.setTextColor(Color.WHITE);

        boton_pausar.getBackground().setColorFilter(Color.rgb(72, 201, 176), PorterDuff.Mode.MULTIPLY);
        boton_pausar.setEnabled(false);


        //crear pizarra sabiendo de antemano sus dimensiones
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.rgb(143, 132, 155));
        pizarra.setSistemaCoordenadas(CR.pcApxX(50f), CR.pcApxY(1f), 1, 1);

        crearObjetosLaboratorio();

    }//fin crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        //el linear principal
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.HORIZONTAL);
        linear_principal.setWeightSum(10.0f);

        //linear secudario izquierda
        LinearLayout linear_izquierda = new LinearLayout(this);
        linear_izquierda.setWeightSum(1.0f);

        //linear secudario derecha
        LinearLayout linear_derecha = new LinearLayout(this);
        linear_derecha.setBackgroundColor(Color.rgb(35, 3, 36));
        linear_derecha.setWeightSum(8.0f);


        //pegar estos secundarios al princial
        LinearLayout.LayoutParams parametros_pegado_izquierdo = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        //ocupará el 80% de linear_principal
        parametros_pegado_izquierdo.weight = 8.0f;
        linear_principal.addView(linear_izquierda, parametros_pegado_izquierdo);

        //pegar estos secundarios al princial
        LinearLayout.LayoutParams parametros_pegado_derecho = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        //ocupará el 20% de linear_principal
        parametros_pegado_derecho.weight = 2.0f;
        linear_principal.addView(linear_derecha, parametros_pegado_derecho);


        //pegar comonentes a linear_derecha.
        //ojo: el cero va a la derecha ya que se agregaran verticalmente
        linear_derecha.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams parametros_pegado_componentes = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        //ocupará el 80% de linear_principal
        parametros_pegado_componentes.setMargins(3, 3, 3, 3);
        parametros_pegado_componentes.weight = 1.0f;

        linear_derecha.addView(text_m1, parametros_pegado_componentes);
        linear_derecha.addView(seek_bar_m1, parametros_pegado_componentes);

        linear_derecha.addView(text_m2, parametros_pegado_componentes);
        linear_derecha.addView(seek_bar_m2, parametros_pegado_componentes);

        linear_derecha.addView(text_m3, parametros_pegado_componentes);
        linear_derecha.addView(seek_bar_m3, parametros_pegado_componentes);

        linear_derecha.addView(boton_empezar, parametros_pegado_componentes);
        linear_derecha.addView(boton_pausar, parametros_pegado_componentes);

        //pegar la pizarra al linear izquierdo
        linear_izquierda.setOrientation(LinearLayout.HORIZONTAL);
        linear_izquierda.addView(pizarra);


        return linear_principal;
    }//fin gui

    private void eventos() {

        //evento botones
        boton_empezar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if (boton_empezar.getText() == "EMPEZAR") {

                    // hilo.pausa = false;
                    hilo.pausa = false;
                    seek_bar_m2.setEnabled(false);
                    seek_bar_m1.setEnabled(false);
                    seek_bar_m3.setEnabled(false);
                    boton_empezar.setText("NUEVO");
                    boton_pausar.setText("PAUSAR");
                    boton_pausar.setEnabled(true);

                } else {


                    //hilo.pausa=true;
                    hilo.pausa = true;
                    seek_bar_m2.setEnabled(true);
                    seek_bar_m1.setEnabled(true);
                    seek_bar_m3.setEnabled(true);
                    boton_empezar.setText("EMPEZAR");
                    boton_pausar.setEnabled(false);
                    // detener();
                    // hilo = null;


                }

            }


        });


        //evento del boton activar
        boton_pausar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if (boton_pausar.getText() == "PAUSAR") {

                    boton_pausar.setText("CONTINUAR");
                    hilo.pausa = true;

                } else {


                    boton_pausar.setText("PAUSAR");
                    hilo.pausa = false;

                }

            }

        });


        //eventos seekbar

        // seek_bar_m1
        seek_bar_m1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int progressChanged = progress;
                m1 = 5 + progressChanged;//5 será el valor mínimo

                actualizarValoresIniciales();


            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        // seek_bar_m2
        seek_bar_m2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int progressChanged = progress;

                m2 = 5 + progressChanged;//5 será el valor mínimo

                actualizarValoresIniciales();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        // seek_bar_m3
        seek_bar_m3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int progressChanged = progress;

                m3 = 5 + progressChanged;//5 será el valor mínimo

                actualizarValoresIniciales();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }


    /*
    Crea los objetos cuerpo rígido con su estado inicia
    -X esta en porcentaje del annco del canvas
    -Y está en porcentaje del alto del canvas
    -Cualquier otra dimensión está en porcentaje del menor
     entre el alto y el ancho del canvas
    */
    private void crearObjetosLaboratorio() {


        /*
        Creación de la base del dibujo en
        estado inicial de la escena física
       */

        //barra superior que sostiene el sistema
        barra_1 = new CuerpoRectangular(CR.pcApxX(0), CR.pcApxY(50), CR.pcApxL(55), CR.pcApxL(70));
        barra_1.setColor(Color.rgb(96, 86, 167));
        objetos[23] = barra_1;
        //barra inferior que sostine el sistema
        barra_2 = new CuerpoRectangular(CR.pcApxX(0), CR.pcApxY(90), CR.pcApxL(70), CR.pcApxL(10));
        barra_2.setColor(Color.rgb(11, 24, 59));
        objetos[24] = barra_2;

        /*
          coordenadas iniciales
          de puntos básicos y
          dimensiones de los elementos
        */

        //radio de las poleas
        radio = CR.pcApxL(4);
        //almacenar valor del radio
        AlmacenDatosRAM.radio = radio;

        //dimensiones de cada masa: m1, m2 y m3
        ancho_bloque = 1.25f * radio;
        alto_bloque = 2.5f * radio;

        //coordenadas de las poleas
        //abscisa y ordenada del centro de la polea 1
        xp1_pixeles = CR.pcApxX(0) - CR.pcApxL(35) + radio;
        yp1_pixeles = CR.pcApxY(50) - CR.pcApxL(35) - radio;
        //abscisa y ordenada del centro de la polea 2
        xp2_pixeles = CR.pcApxX(0) + CR.pcApxL(35) - radio;
        yp2_pixeles = CR.pcApxY(50) - CR.pcApxL(35) - radio;
        //abscisa y ordenada del centro de la polea 3
        xP3_pixeles = xp2_pixeles + radio;
        yP3_pixeles = yp2_pixeles + (6 * radio);
        AlmacenDatosRAM.xP3_en_pixeles = xP3_pixeles;
        AlmacenDatosRAM.yiP3_en_pixeles = yP3_pixeles - yp1_pixeles;

        //coordenadas de las masas

        //abscisa y ordenada del centro de la masa 1
        x1_pixeles = xp1_pixeles - radio;
        y1_pixeles = yp1_pixeles + (12.5f * radio);
        AlmacenDatosRAM.x1_en_pixeles = x1_pixeles;
        AlmacenDatosRAM.yi1_en_pixeles = y1_pixeles - yp1_pixeles;

        //abscisa y ordenada del centro de la masa 2
        x2_pixeles = xP3_pixeles - radio;
        y2_pixeles = yP3_pixeles + (8 * radio);
        AlmacenDatosRAM.x2_en_pixeles = x2_pixeles;
        AlmacenDatosRAM.yi2_en_pixeles = y2_pixeles - yp1_pixeles;

        //abscisa y ordenada del centro de la masa 3
        x3_pixeles = xP3_pixeles + radio;
        y3_pixeles = yP3_pixeles + (5 * radio);
        AlmacenDatosRAM.x3_en_pixeles = x3_pixeles;
        AlmacenDatosRAM.yi3_en_pixeles = y3_pixeles - (CR.pcApxY(50) - CR.pcApxL(35) - radio);


        /*
        Creación de objetos físicos y dibujo del
        estado inicial de la escena física
       */
        //dos poleas
        //polea 1 (polea azul izquierda)
        polea_1 = new Polea(xp1_pixeles, yp1_pixeles, radio);
        polea_1.setColor(Color.BLUE);
        polea_1.setGrosorLinea(CR.pcApxL(0.4f));
        polea_1.setSoportePolea(true);
        polea_1.rotarEje(325);
        objetos[6] = polea_1;


        //polea 2 (polea roja)
        polea_2 = new Polea(xp2_pixeles, yp2_pixeles, radio);
        polea_2.setColor(Color.BLUE);
        polea_2.setGrosorLinea(CR.pcApxL(0.4f));
        polea_2.setSoportePolea(true);
        polea_2.rotarEje(35);
        objetos[7] = polea_2;

        //polea 3 (polea verde)
        polea_3 = new Polea(xP3_pixeles, yP3_pixeles, radio);
        polea_3.setColor(Color.rgb(2, 110, 0));
        polea_3.setGrosorLinea(CR.pcApxL(0.4f));
        polea_3.setSoportePolea(true);
        polea_3.rotarEje(180);
        objetos[8] = polea_3;


        //5  cuerdas
        cuerda_1 = new Cuerda(xp1_pixeles - radio, yp1_pixeles, xp1_pixeles - radio, yp1_pixeles + (12.5f * radio) - (alto_bloque / 2));
        cuerda_1.setColor(Color.BLACK);
        cuerda_1.setGrosorLinea(CR.pcApxL(0.3f));
        objetos[0] = cuerda_1;

        cuerda_2 = new Cuerda(xp1_pixeles, yp1_pixeles - radio, xp2_pixeles, yp2_pixeles - radio);
        cuerda_2.setColor(Color.BLACK);
        cuerda_2.setGrosorLinea(CR.pcApxL(0.3f));
        objetos[1] = cuerda_2;

        cuerda_3 = new Cuerda(xp2_pixeles + radio, yp2_pixeles, xp2_pixeles + radio, yP3_pixeles - (1.2f * radio));
        cuerda_3.setColor(Color.BLACK);
        cuerda_3.setGrosorLinea(CR.pcApxL(0.3f));
        objetos[2] = cuerda_3;

        cuerda_4 = new Cuerda(xP3_pixeles + radio, yP3_pixeles, xP3_pixeles + radio, yP3_pixeles + (5 * radio) - (alto_bloque / 2));
        cuerda_4.setColor(Color.BLACK);
        cuerda_4.setGrosorLinea(CR.pcApxL(0.3f));
        objetos[3] = cuerda_4;

        cuerda_5 = new Cuerda(xP3_pixeles - radio, yP3_pixeles, xP3_pixeles - radio, yP3_pixeles + (8 * radio) - (alto_bloque / 2));
        cuerda_5.setColor(Color.BLACK);
        cuerda_5.setGrosorLinea(CR.pcApxL(0.3f));
        objetos[4] = cuerda_5;

        //masa 1
        masa_1 = new Masa(x1_pixeles, y1_pixeles, ancho_bloque, alto_bloque);
        masa_1.setColor(Color.rgb(199, 0, 145));
        masa_1.setColorMarca(Color.BLACK);
        masa_1.setMarca("M1");
        objetos[11] = masa_1;


        //2 masas
        //masa 2
        masa_2 = new Masa(x2_pixeles, y2_pixeles, ancho_bloque, alto_bloque);
        masa_2.setColor(Color.rgb(199, 0, 145));
        masa_2.setColorMarca(Color.BLACK);
        masa_2.setMarca("M2");
        objetos[13] = masa_2;


        //masa 3
        masa_3=new Masa(x3_pixeles,y3_pixeles,ancho_bloque,alto_bloque);
        masa_3.setColor(Color.rgb(199, 0, 145));
        masa_3.setColorMarca(Color.BLACK);
        masa_3.setMarca("M3");
        objetos[15]=masa_3;



        //eje y
        flecha_Y = new CuerpoRectangular(CR.pcApxX(0) + CR.pcApxL(50), CR.pcApxY(40),CR.pcApxL(0.1f),CR.pcApxL(70));
        objetos[16]=flecha_Y;

        flecha_y = new Flecha(CR.pcApxX(0) + CR.pcApxL(50), CR.pcApxY(40) + CR.pcApxL(20f) , CR.pcApxL(23));
        flecha_y.setGrosorLinea(3f);
        flecha_y.rotar(90);
        flecha_y.setColor(Color.BLACK);
        objetos[18] = flecha_y;

        //eje x
        flecha_x = new CuerpoRectangular(CR.pcApxX(0) + CR.pcApxL(50), CR.pcApxY(50) - CR.pcApxL(35) - radio, CR.pcApxL(12),CR.pcApxL(0.1f));
        objetos[19] = flecha_x;


        //marcas de lo ejes

        //despliega el nombre del Eje y
        marca_y = new Marca("Eje y", CR.pcApxX(0) + CR.pcApxL(47), CR.pcApxY(50) + CR.pcApxL(37));
        marca_y.setColor(Color.BLACK);
        marca_y.setTamano(CR.pcApxL(3f));
        objetos[20] = marca_y;


        //despliega el nombre del Eje x
        marca_x = new Marca("0", CR.pcApxX(0) + CR.pcApxL(51), CR.pcApxY(50) - CR.pcApxL(37) - radio);
        marca_x.setColor(Color.BLACK);
        marca_x.setTamano(CR.pcApxL(3f));
        objetos[21] = marca_x;


        //desplegar la escena inicial
        pizarra.setEstadoEscena(objetos);


    }


    /*
    Cambia el estado de movimiento de los cuerpos rígidos
   -X esta en porcentaje del ancho del canvas
   -Y está en porcentaje del alto del canvas
   -Cualquier otra dimensión está en porcentaje del menor
    entre el alto y el ancho del canvas
   */
    public void cambiarEstadosEscenaPizarra() {

         /*
          variables necesarias para actualizar la
          escena física.
         */


        /*
         Coordenadas de los centros de las masas:

         Recoge los valores y1, y2 y y3 calculados
         en con la clase ModeloFisico.

         Los valores x1, x2 y x3 se mantienen constantes
         y fueron asignados en la clase ActividadPrincipal
         */

        float x1_en_pixeles = AlmacenDatosRAM.x1_en_pixeles;
        float y1_en_pixeles = AlmacenDatosRAM.y1_en_pixeles;

        float x2_en_pixeles = AlmacenDatosRAM.x2_en_pixeles;
        float y2_en_pixeles = AlmacenDatosRAM.y2_en_pixeles;

        float x3_en_pixeles = AlmacenDatosRAM.x3_en_pixeles;
        float y3_en_pixeles = AlmacenDatosRAM.y3_en_pixeles;

        float xP3_en_pixeles = AlmacenDatosRAM.xP3_en_pixeles;
        float yP3_en_pixeles = AlmacenDatosRAM.yP3_en_pixeles;

        /*
         Desplazamiento angular de las poleas:

         Recoge los valores de teta_1 y teaa_2
         calculados con la clase ModeloFisico
         */
        float teta_1 = AlmacenDatosRAM.teta_1;
        float teta_2 = AlmacenDatosRAM.teta_2;
        float teta_3 = AlmacenDatosRAM.teta_3;

        /*
          Coordenadas de la polea 3.
          La polea 1 y 2 sólo rotan
         */
        float xp3_en_pixeles = xP3_en_pixeles;
        float yp3_en_pixeles = yP3_en_pixeles - 3 * radio;

        /*
          Ahora sí, actualizar la posición de los
          objetos que se mueven
        */
        //mover las poleas
        //mover polea_1
        polea_1.mover(teta_1);
        //mover polea_2
        polea_2.mover(teta_2);
        //mover polea_2
        polea_3.mover(0, AlmacenDatosRAM.desplazamiento_mP3_en_pixeles, teta_3);


        //mover masa 1
        masa_1.mover(0, AlmacenDatosRAM.desplazamiento_m1_en_pixeles);
        //mover masa2
        masa_2.mover(0, AlmacenDatosRAM.desplazamiento_m2_en_pixeles);
        //mover masa3
        masa_3.mover(0,AlmacenDatosRAM.desplazamiento_m3_en_pixeles);


        // mover las cuerdas
        y1_en_pixeles = y1_pixeles + AlmacenDatosRAM.desplazamiento_m1_en_pixeles;
        y2_en_pixeles = y2_pixeles + AlmacenDatosRAM.desplazamiento_m2_en_pixeles;
        y3_en_pixeles = y3_pixeles + AlmacenDatosRAM.desplazamiento_m3_en_pixeles;
        yP3_en_pixeles = yP3_pixeles + AlmacenDatosRAM.desplazamiento_mP3_en_pixeles;
//        flecha_2.setPosicion(x1_en_pixeles + 0.5f * ancho_bloque, y1_en_pixeles);

        //mover la cuerda 1
        cuerda_1.setPosicionFinal(xp1_pixeles-radio, y1_en_pixeles - 0.5f * alto_bloque);

        //mover la cuerda 3
        cuerda_3.setPosicionFinal(xp2_pixeles+radio,yP3_en_pixeles - (1.2f*radio));

        //mover la cuerda 4
        cuerda_4.setPosicionInicial(xP3_pixeles+radio,yP3_en_pixeles );
        cuerda_4.setPosicionFinal(xP3_pixeles+radio,y3_en_pixeles - 0.5f * alto_bloque);

        //mover la cuerda 5
        cuerda_5.setPosicionInicial(xP3_pixeles-radio,yP3_en_pixeles );
        cuerda_5.setPosicionFinal(xP3_pixeles-radio,y2_en_pixeles - 0.5f * alto_bloque);

    }


    private void actualizarValoresIniciales() {


        AlmacenDatosRAM.m1 = m1;
        AlmacenDatosRAM.m2 = m2;
        AlmacenDatosRAM.m3 = m3;


        float origenY_en_metros = yp1_pixeles*(2 / CR.pcApxY(100f));
        //almacenar
        AlmacenDatosRAM.origenY_en_metros = origenY_en_metros;


        //condiciones iniciales de posición
        AlmacenDatosRAM.yi1_en_metros = y1_pixeles*(2 / CR.pcApxY(100f));
        AlmacenDatosRAM.yi2_en_metros = y2_pixeles*(2 / CR.pcApxY(100f));
        AlmacenDatosRAM.yi3_en_metros = y3_pixeles*(2 / CR.pcApxY(100f));


    }


}
