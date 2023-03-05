package com.curso_simulaciones.midecimacuartaapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.EstrellaFija;
import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.Marciano;
import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.ObjetoEspacial;
import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.Selenita;
import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.Venusiano;
import com.curso_simulaciones.midecimacuartaapp.vista.CR;
import com.curso_simulaciones.midecimacuartaapp.vista.Pizarra;

public class ActividadPrincipalMiDecimaCuartaApp extends Activity implements Runnable {
    //Pizarra para dibujar
    Pizarra pizarra;
    //objetos dibujables para Pizarra
    private Marciano marciano_1;
    private Selenita selenita_1;
    private Venusiano venusiano_1;
    private EstrellaFija estrella_1, estrella_2, estrella_3, estrella_4, estrella_5;
    private ObjetoEspacial[] objetos_espaciales = new ObjetoEspacial[10];

    private float aumento_1=1;


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
        pizarra.setBackgroundColor(Color.rgb(27, 23, 66 ));
    }


    //organizar la distribución de los objetos de de la GUI usando administradores de diseño
    private LinearLayout crearGui() {

        //el inear principal
        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setBackgroundColor(Color.rgb(4, 3, 20 ));
        linearPrincipal.setWeightSum(10.0f);


        //linear secundario arriba
        LinearLayout linearArriba = new LinearLayout(this);


        //linear secundario abajo
        LinearLayout linearAbajo = new LinearLayout(this);
        linearAbajo.setBackgroundColor(Color.rgb(62, 10, 86 ));


        //pegar linearArriba al princial
        LinearLayout.LayoutParams parametrosPegadoArriba = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadoArriba.weight = 8.5f;
        parametrosPegadoArriba.setMargins(50, 50, 50, 50);
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

        boolean ON = true;

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
            if (pizarra.getWidth() != 0 && ON == true) {
                crearObjetosConResponsividad();
                ON = false;
            }

            //ya creadas las poleas con responsividad hacer efectiva la animación
            if (ON == false) {
                tiempo = tiempo + 0.05f;
                //cambio de estado de la escena física en la pizarra
                cambiarEstadosEscenaPizarra(tiempo);
            }


        }

    }

    private void crearObjetosConResponsividad() {

        CR.anchoPizarra = pizarra.getWidth();
        CR.altoPizarra = pizarra.getHeight();
        crearObjetosEspacial();

    }

    /*
    Crea los objetos espaciales con su estado inicial
    -X esta en porcentaje del annco del canvas
    -Y está en porcentaje del alto del canvas
    -Cualquier otra dimensión está en porcentaje del menor
     entre el alto y el ancho del canvas
    */
    private void crearObjetosEspacial() {

        //marciano 1
        float x_1 = CR.pcApxX(10);
        float y_1 = CR.pcApxY(75);
        marciano_1 =new Marciano(x_1,y_1);

        float longitud_1 = CR.pcApxL(5);
        float x_2 = CR.pcApxX(20);
        float y_2 = CR.pcApxY(20);
        estrella_1 = new EstrellaFija(x_2,y_2,longitud_1);
        estrella_1.setColor(Color.WHITE);

        float longitud_2 = CR.pcApxL(1);
        float x_3 = CR.pcApxX(30);
        float y_3 = CR.pcApxY(30);
        estrella_2 = new EstrellaFija(x_3,y_3,longitud_2);
        estrella_2.setColor(Color.rgb(255, 0, 0));

        float longitud_3 = CR.pcApxL(3);
        float x_4 = CR.pcApxX(40);
        float y_4 = CR.pcApxY(70);
        estrella_3 = new EstrellaFija(x_4,y_4,longitud_3);
        estrella_3.setColor(Color.rgb(245, 237, 157));

        float longitud_4 = CR.pcApxL(2);
        float x_5 = CR.pcApxX(70);
        float y_5 = CR.pcApxY(80);
        estrella_4 = new EstrellaFija(x_5,y_5,longitud_4);
        estrella_4.setColor(Color.rgb(255, 232, 0));


        float longitud_5 = CR.pcApxL(4);
        float x_6 = CR.pcApxX(80);
        float y_6 = CR.pcApxY(20);
        estrella_5 = new EstrellaFija(x_6,y_6,longitud_5);
        estrella_5.setColor(Color.rgb(255, 234, 196));

        float x_7 = CR.pcApxX(0);
        float y_7 = CR.pcApxY(76);
        selenita_1 = new Selenita(x_7,y_7);
        selenita_1.setColor(Color.rgb(157, 210, 210 ));

        float x_8 = CR.pcApxX(0);
        float y_8 = CR.pcApxY(50);
        venusiano_1 = new Venusiano(x_8,y_8);
        venusiano_1.setColor(Color.rgb(255, 159, 2 ));

        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[0] = estrella_1;
        objetos_espaciales[1] = estrella_2;
        objetos_espaciales[2] = estrella_3;
        objetos_espaciales[3] = estrella_4;
        objetos_espaciales[4] = estrella_5;
        objetos_espaciales[5] = marciano_1;
        objetos_espaciales[6] = selenita_1;
        objetos_espaciales[7] = venusiano_1;

        //desplegar la escena inicial
        pizarra.setEstadoEscena(objetos_espaciales);

    }


    /*
    Cambia el estado de movimiento de los objetos espaciales
    -X esta en porcentaje del annco del canvas
    -Y está en porcentaje del alto del canvas
    -Cualquier otra dimensión está en porcentaje del menor
     entre el alto y el ancho del canvas
    */
    private void cambiarEstadosEscenaPizarra(float tiempo) {


        //modelo de la marciano_1
        //movimiento parabólico
        float desplazamiento_x_1 = CR.pcApxX(10* tiempo);//MU
        float desplazamiento_y_1 = CR.pcApxY(-35*tiempo + 4.9f* tiempo*tiempo);//caída libre
        //mover al marciano
        marciano_1.mover(desplazamiento_x_1, desplazamiento_y_1);
        //aumentar tamañp de marciano_1
        aumento_1 = aumento_1+0.01f;
        marciano_1.setMagnificar(aumento_1);
        //cambiar color de marciano_1 en el instante que su tamaño
        //esté en 40% mayor que el inicial
        if (aumento_1>1.4f){
            marciano_1.setColor(Color.GREEN);
        }

        //modelo de la selenita_1
        float desplazamiento_S_x = CR.pcApxX(2*tiempo);
        float desplazamiento_S_y = 0;
        float teta_S = -7f*tiempo;
        selenita_1.mover(desplazamiento_S_x,desplazamiento_S_y,teta_S);

        float desplazamiento_V_x = CR.pcApxX(2.5f*tiempo);
        float frecuencia = 2;
        float amplitud = CR.pcApxY(8);
        float fase_en_radianes = (float) (2 * Math.PI * frecuencia * tiempo);
        float deplazamiento_V_y = (float) (amplitud * (Math.sin(fase_en_radianes)));
        venusiano_1.mover(desplazamiento_V_x , deplazamiento_V_y);



    }



}
