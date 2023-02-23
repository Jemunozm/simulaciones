package com.curso_simulaciones.mioctavaapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mioctavaapp.objetos_laboratorio.Polea;
import com.curso_simulaciones.mioctavaapp.vista.CR;
import com.curso_simulaciones.mioctavaapp.vista.Pizarra;

public class ActividadPrincipalMiOctavaApp extends Activity implements Runnable {

    //dimensiones de pantalla
    private float ancho_pantalla, alto_pantalla;
    //Pizarra para dibujar
    private Pizarra pizarra;
    //objetos dibujables para Pizarra
    //arreglo que permite contener 10 poleas
    private Polea[] poleas = new Polea[10];
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
         las poleas con responsividad se crearan dentro
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
        linearAbajo.setBackgroundColor(Color.YELLOW);


        //pegar linearArriba al princial
        LinearLayout.LayoutParams parametrosPegadoArriba = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadoArriba.weight = 8.5f;
        parametrosPegadoArriba.setMargins(50,50,50,50);
        linearPrincipal.addView(linearArriba, parametrosPegadoArriba);


        //pegar linearAbajo al princial
        LinearLayout.LayoutParams parametrosPegadoAbajo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadoAbajo.weight = 1.5f;
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
            hacer la creación de las poleas con
            responsividad SÓLO cuando se garantice que la
            GUI se conformó completamente con el fin de que
            las dimensiones de la pizarra NO SEAN NULAS.
            */
            if(pizarra.getWidth()!=0&& ON==true) {
                crearPoleasConResponsividad();
                ON=false;
            }

            //ya creadas las poleas con responsividad hacer efectiva la animación
            if (ON==false) {
                tiempo = tiempo + 0.05f;
                //cambio de estado de la escena física en la pizarra
                cambiarEstadosEscenaPizarra(tiempo);
            }


        }

    }

    private void crearPoleasConResponsividad(){

        CR.anchoPizarra = pizarra.getWidth();
        CR.altoPizarra = pizarra.getHeight();
        crearObjetosLaboratorio();

    }

    /*Crea los objetos cuerpo rígido con su estado inicial*/
    private void crearObjetosLaboratorio() {

        /*
        seis poleas de radio 10% de
        la menor dimensión del canvas
        */
        float radio = CR.pcApxL(10);

        //centro de las poleas
        float x_c = 0;
        float y_c = 0;

        //polea 1 con su centro en esquina superior izquierda
        poleas[0] = new Polea(x_c, y_c, radio);


        //polea 2 con su centro en esquina superior derecha
        x_c = CR.pcApxX(100);
        poleas[1] = new Polea(x_c, y_c, radio);
        poleas[1].setColorPolea(Color.BLACK);


        //polea 3 con su centro en esquina inferior izquierda
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(100);
        poleas[2] = new Polea(x_c, y_c, radio);
        poleas[2].setColorPolea(Color.BLUE);


        //polea 4 con su centro en esquina inferior derecha
        x_c = CR.pcApxX(100);
        y_c = CR.pcApxY(100);
        poleas[3] = new Polea(x_c, y_c, radio);
        poleas[3].setColorPolea(Color.MAGENTA);


        //polea 5 con su centro en el centro del canvas
        x_c = CR.pcApxX(50);
        y_c = CR.pcApxY(50);
        poleas[4] = new Polea(x_c, y_c, radio);
        poleas[4].setColorPolea(Color.rgb(200, 200, 0));

        //polea 6 con su centro en X = 0  e
        // Y = 0.25 de la altura del canvas
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(25);
        poleas[5] = new Polea(x_c, y_c, radio);
        poleas[5].setColorPolea(Color.RED);

        //polea 7 con su centro en X = 0 e
        // Y = 0.75 de la altura del canvas
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(75);
        poleas[6] = new Polea(x_c, y_c, radio);
        poleas[6].setColorPolea(Color.BLACK);

        pizarra.setEstadoEscena(poleas);

    }




    /*
    Cambia el esatado de los objetos polea y lo comunica a pizarra
    */
    private void cambiarEstadosEscenaPizarra(float tiempo) {

        //polea 1
        /* Modelo de la polea 1
        la polea 1 rota con velocidad angular W=50
        alrededor del eje que pasa por su centro
        */
        float teta_1 = 50f * tiempo;//ecuación MCU

        //polea 2
        /* Modelo de la polea 2
        la polea 2 rota con velocidad angular W=100
        alrededor del eje que pasa por su centro
        */
        float teta_2 = 100f * tiempo;//ecuación MCU


        //polea 3
        /* Modelo de la polea 3
        la polea 3 rota con velocidad angular W=-50
        alrededor del eje que pasa por su centro
        */
        float teta_3 = -50f * tiempo;//ecuación MCU


        //polea 4
        /* Modelo de la polea 4
        la polea 4 rota con velocidad angular W=-100
        alrededor del eje que pasa por su centro
        */
        float teta_4 = -100f * tiempo;//ecuación MCU

        //polea 5
        /* Modelo de la polea 5
        la polea 5 rota con velocidad angular W=200
        alrededor del eje que pasa por su centro
        */
        float teta_5 = 200f * tiempo;//ecuación MCU


        //polea 6
        /* Modelo de la polea 6
        la polea 6 se desplaza sin rotar
        con velocidad Vx = 5, Vy=0;
        alrededor del eje que pasa por su centro
        */
        float desplazamiento_6_x = CR.pcApxX(5*tiempo);
        float desplazamiento_6_y = 0;

        //polea 7
        /* Modelo de la polea 7
        la polea 7 se desplaza con velocidad Vx = 5, Vy=0
        y a la vez rota con W=200 alrededor del eje que
         pasa por su centro
        */
        float teta_7 = 200f * tiempo;//ecuación MCU
        float desplazamiento_7_x = CR.pcApxX(5*tiempo);
        float desplazamiento_7_y = 0;

        //mover las poleas
        //mover polea 1
        poleas[0].moverPolea(teta_1);
        //mover polea 2
        poleas[1].moverPolea(teta_2);
        //mover polea 3
        poleas[2].moverPolea(teta_3);
        //mover polea 4
        poleas[3].moverPolea(teta_4);
        //mover polea 5
        poleas[4].moverPolea(teta_5);
        //mover polea 6
        poleas[5].moverPolea(desplazamiento_6_x,desplazamiento_6_y);
        //mover polea 7
        poleas[6].moverPolea(desplazamiento_7_x,desplazamiento_7_y,teta_7);

    }
}

