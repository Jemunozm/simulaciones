package com.curso_simulaciones.minovenaapp;


import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.minovenaapp.objetos_laboratorio.Rueda;
import com.curso_simulaciones.minovenaapp.vista.CR;
import com.curso_simulaciones.minovenaapp.vista.Pizarra;

public class ActividadPrincipalMiNovenaApp extends Activity implements Runnable {

    //dimensiones de pantalla
    private float ancho_pantalla, alto_pantalla;
    //Pizarra para dibujar
    private Pizarra pizarra;
    //objetos dibujables para Pizarra
    //arreglo que permite contener 10 Ruedas
    private Rueda[] ruedas = new Rueda[10];
    //período de muestreo en mislisegucnos
    private long periodo_muestreo = 50;
    private float tiempo;
    //hilo responsable de controlar la animación
    private Thread hilo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        llamada al método para crear los elementos de la
        interfaz gráfica de usuario (GUI)
        */
        crearElementosGui();

        /*
        para informar cómo se debe adaptar la GUI a la pantalla del dispositivo
        */
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        /*
        pegar al contenedor la GUI:
        en el argumento se está llamando al método crearGui()
        */
        this.setContentView(crearGui(), parametro_layout_principal);

        /*
         las Ruedas con responsividad se crearan dentro
         del hilo con el fin de garantizar que las dimensiones
         de la pizarra donde se desplegarán con responsividad
         tenga ya dimensiones no nulas
         */
        //hilo que administra la animación
        hilo = new Thread(this);
        hilo.start();

    }


    //crear los objetos de la interfaz gráfica de usuario (GUI)
    private void crearElementosGui() {

        //crear pizarra sabiendo de antemano sus dimensiones
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.WHITE);
    }



    //organizar la distribución de los objetos de de la GUI usando administradores de diseño
    private LinearLayout crearGui() {

        //el inear principal
        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setBackgroundColor(Color.BLACK);
        linearPrincipal.setWeightSum(10.0f);


        //linear secundario arriba
        LinearLayout linearArriba = new LinearLayout(this);



        //linear secundario abajo
        LinearLayout linearAbajo = new LinearLayout(this);
        linearAbajo.setBackgroundColor(Color.rgb(87,55,8));


        //pegar linearArriba al princial
        LinearLayout.LayoutParams parametrosPegadoArriba = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadoArriba.weight = 9f;
        parametrosPegadoArriba.setMargins(50,50,50,50);
        linearPrincipal.addView(linearArriba, parametrosPegadoArriba);


        //pegar linearAbajo al princial
        LinearLayout.LayoutParams parametrosPegadoAbajo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadoAbajo.weight = 1f;
        linearPrincipal.addView(linearAbajo, parametrosPegadoAbajo);


        //pegar pizarra a linearArriba
        linearArriba.addView(pizarra);


        return linearPrincipal;

    }


    @Override
    public void run() {

        boolean ON =true;

        //hilo sin fin
        while (true) {

            try {
                Thread.sleep(periodo_muestreo);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

            /*
            hacer la creación de las Ruedas con
            responsividad SÓLO cuando se garantice que la
            GUI se conformó completamente con el fin de que
            las dimensiones de la pizarra NO SEAN NULAS.
            */
            if(pizarra.getWidth()!=0&& ON==true) {
                crearRuedasConResponsividad();
                ON=false;
            }

            //ya creadas las Ruedas con responsividad hacer efectiva la animación
            if (ON==false) {
                tiempo = tiempo + 0.05f;
                //cambio de estado de la escena física en la pizarra
                cambiarEstadosEscenaPizarra(tiempo);
            }


        }

    }

    private void crearRuedasConResponsividad(){

        CR.anchoPizarra = pizarra.getWidth();
        CR.altoPizarra = pizarra.getHeight();
        crearObjetosLaboratorio();

    }

    /*Crea los objetos cuerpo rígido con su estado inicial*/
    private void crearObjetosLaboratorio() {

        /*
        seis Ruedas de radio 10% de
        la menor dimensión del canvas
        */
        float radio = CR.pcApxL(10);

        //centro de las Ruedas
        float x_c = 0;
        float y_c = 0;

        //rueda 1 con su centro en esquina inferior derecha
        x_c = CR.pcApxX(100);
        y_c = CR.pcApxY(100);
        ruedas[0] = new Rueda(x_c, y_c, 3f*radio);
        ruedas[0].setColorRueda(Color.MAGENTA,
                Color.rgb(204,88,214),Color.GREEN);


        //rueda 2 con su centro en el centro del canvas
        x_c = CR.pcApxX(50);
        y_c = CR.pcApxY(50);
        ruedas[1] = new Rueda(x_c, y_c, 2f*radio);
        ruedas[1].setColorRueda(Color.rgb(88,214,141),
                Color.rgb(152,88,214),Color.WHITE);

        //rueda 3 con su centro en X = 0  e
        // Y = 0.25 de la altura del canvas
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(25);
        ruedas[2] = new Rueda(x_c, y_c, radio);
        ruedas[2].setColorRueda(Color.BLACK,
                Color.rgb(88,212,214),Color.GRAY);

        //rueda 4 con su centro en X = 0 e
        // Y = 0.75 de la altura del canvas
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(75);
        ruedas[3] = new Rueda(x_c, y_c, 1.5f*radio);
        ruedas[3].setColorRueda(Color.BLACK,Color.WHITE,Color.GREEN);

        pizarra.setEstadoEscena(ruedas);

    }




    /*
    Cambia el esatado de los objetos Rueda y lo comunica a pizarra
    */
    private void cambiarEstadosEscenaPizarra(float tiempo) {

        //rueda 1
        /* Modelo de la rueda 1
        la rueda 1 rota con velocidad angular W=50
        alrededor del eje que pasa por su centro
        */
        float teta_1 = 50f * tiempo;//ecuación MCU

        //rueda 2
        /* Modelo de la rueda 2
        la rueda 2 rota con velocidad angular W=100
        alrededor del eje que pasa por su centro
        */
        float teta_2 = 100f * tiempo;//ecuación MCU

        //rueda 3
        /* Modelo de la rueda 3
        la rueda 3 se desplaza sin rotar
        con velocidad Vx = 5, Vy=0;
        alrededor del eje que pasa por su centro
        */
        float desplazamiento_3_x = CR.pcApxX(10*tiempo);
        float desplazamiento_3_y = 0;

        //rueda 4
        /* Modelo de la rueda 4
        la rueda 4 se desplaza con velocidad Vx = 5, Vy=0
        y a la vez rota con W=200 alrededor del eje que
         pasa por su centro
        */
        float teta_4 = 200f * tiempo;//ecuación MCU
        float desplazamiento_4_x = CR.pcApxX(5*tiempo);
        float desplazamiento_4_y = 0;

        //mover las ruedas
        //mover Rueda 1
        ruedas[0].moverRueda(teta_1);
    //mover Rueda 2
        ruedas[1].moverRueda(teta_2);
        //mover Rueda 3
        ruedas[2].moverRueda(desplazamiento_3_x,desplazamiento_3_y);
        //mover Rueda 4
        ruedas[3].moverRueda(desplazamiento_4_x,desplazamiento_4_y,teta_4);

    }
}

