package com.curso_simulaciones.midecimaterceraapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio.CuerpoRectangular;
import com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio.Flecha;
import com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio.Polea;
import com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio.Resorte;
import com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio.Rueda;
import com.curso_simulaciones.midecimaterceraapp.vista.CR;
import com.curso_simulaciones.midecimaterceraapp.vista.Pizarra;

public class AcividadPrincipalMiDecimaTerceraApp extends Activity implements Runnable {

    //Pizarra para dibujar
    Pizarra pizarra;
    //objetos dibujables para Pizarra
    private Polea polea_1, polea_2, polea_3;
    private Rueda rueda_1;
    private CuerpoRectangular rectangular_1, rectangular_2;
    private Flecha flecha_1, flecha_2;
    private Resorte resorte_1;
    public ObjetoLaboratorio[] objetosLab = new ObjetoLaboratorio[10];


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
        crearObjetosLaboratorio();

    }

    /*
    Crea los objetos cuerpo rígido con su estado inicia
    -X esta en porcentaje del annco del canvas
    -Y está en porcentaje del alto del canvas
    -Cualquier otra dimensión está en porcentaje del menor
     entre el alto y el ancho del canvas
    */
    private void crearObjetosLaboratorio() {

        //polea 1
        float x_1 = CR.pcApxX(10);
        float y_1 = CR.pcApxY(25);
        float radio_1 = CR.pcApxL(10);
        polea_1 = new Polea(x_1, y_1, radio_1);
        //agregar al arreglo de objetos de laboratorio
        objetosLab[0] = polea_1;

        //polea 2
        float x_2 = CR.pcApxX(15);
        float y_2 = CR.pcApxY(50);
        float radio_2 = CR.pcApxL(15);
        polea_2 = new Polea(x_2, y_2, radio_2);
        polea_2.setColor(Color.BLACK);
        //agregar al arreglo de objetos de laboratorio
        objetosLab[1] = polea_2;

        //polea 3
        float x_3 = CR.pcApxX(10);
        float y_3 = CR.pcApxY(75);
        float radio_3 = CR.pcApxL(10);
        polea_3 = new Polea(x_3, y_3, radio_3);
        polea_3.setColor(Color.MAGENTA);
        //agregar al arreglo de objetos de laboratorio
        objetosLab[2] = polea_3;


        //rueda
        float x_4 = CR.pcApxX(50);
        float y_4 = CR.pcApxY(50);
        float radio_4 = CR.pcApxL(15);
        rueda_1 = new Rueda(x_4, y_4, radio_4);
        rueda_1.setColor(Color.rgb(0, 128, 0));
        //agregar al arreglo de objetos de laboratorio
        objetosLab[3] = rueda_1;


        //cuerpo rectangular 1
        float x_5 = CR.pcApxX(50);
        float y_5 = CR.pcApxY(50);
        float ancho_1 = CR.pcApxL(40);
        float alto_1 = CR.pcApxL(20);
        rectangular_1 = new CuerpoRectangular(x_5, y_5, ancho_1, alto_1);
        rectangular_1.setColor(Color.argb(100, 250, 250, 100));
        //agregar al arreglo de objetos de laboratorio
        objetosLab[4] = rectangular_1;


        //cuerpo rectangular 2
        float x_6 = CR.pcApxX(75);
        float y_6 = CR.pcApxY(40);
        float ancho_2 = CR.pcApxL(5);
        float alto_2 = CR.pcApxL(60);
        rectangular_2 = new CuerpoRectangular(x_6, y_6, ancho_2, alto_2);
        //agregar al arreglo de objetos de laboratorio
        objetosLab[5] = rectangular_2;

        //flecha_1
        flecha_1 = new Flecha();
        float x_7 = CR.pcApxX(15);
        float y_7 = CR.pcApxY(50);
        float longitud_1 = CR.pcApxL(40);
        flecha_1.setLongitud(longitud_1);
        flecha_1.setPosicion(x_7, y_7);
        //agregar al arreglo de objetos de laboratorio
        objetosLab[6] = flecha_1;

        //flecha_2 de longitud 150 y ubicada en (400,200)
        flecha_2 = new Flecha();
        float x_8 = CR.pcApxX(50);
        float y_8 = CR.pcApxY(50);
        float longitud_2 = CR.pcApxL(40);
        flecha_2.setLongitud(longitud_2);
        flecha_2.setPosicion(x_8, y_8);
        flecha_2.setColor(Color.RED);
        flecha_2.rotar(90);
        //agregar al arreglo de objetos de laboratorio
        objetosLab[7] = flecha_2;

        //resorte_1
        resorte_1 = new Resorte();
        float x_9 = CR.pcApxX(50);
        float y_9 = CR.pcApxY(50);
        float longitud_natural = CR.pcApxL(50);
        resorte_1.setLongitudNatural(longitud_natural);
        resorte_1.setPosicion(x_9, y_9);
        resorte_1.setColor(Color.BLUE);
        resorte_1.rotar(90);
        //agregar al arreglo de objetos de laboratorio
        objetosLab[8] = resorte_1;

        //desplegar la escena inicial
        pizarra.setEstadoEscena(objetosLab);

    }


    /*
    Cambia el estado de movimiento de los cuerpos rígidos
    -X esta en porcentaje del annco del canvas
    -Y está en porcentaje del alto del canvas
    -Cualquier otra dimensión está en porcentaje del menor
     entre el alto y el ancho del canvas
    */
    private void cambiarEstadosEscenaPizarra(float tiempo) {


        //modelo de la polea 1
        float desplazamiento_x_1 = CR.pcApxX(2 * tiempo);
        //mover polea 1
        //MU
        polea_1.mover(desplazamiento_x_1, 0);

        //modelo de la polea 2
        //MCU
        float teta_2 = 100 * tiempo;
        //mover polea 2
        polea_2.mover(teta_2);


        //modelo de la polea 3
        //MU + MCU
        float desplazamiento_x_3 = CR.pcApxX(2 * tiempo);
        float teta_3 = 100 * tiempo;
        //mover polea 3
        polea_3.mover(desplazamiento_x_3, 0, teta_3);

        //modelo de la rueda
        //MCU
        float teta_4 = 100 * tiempo;
        //mover el cuerpo rectangular 1
        rueda_1.mover(teta_4);

        //modelo de la cuerpo rectangular 1
        //MCU
        float teta_5 = 50 * tiempo;
        //mover el cuerpo rectangular 1
        rectangular_1.mover(-teta_5);

        //modelo de la cuerpo rectangular 2
        //oscilación armónica
        float x_6 = CR.pcApxX(75);
        float y_6 = CR.pcApxY(20);
        float fase_en_grados = 100 * tiempo;
        float fase_en_radianes = (float) (Math.toRadians(fase_en_grados));
        float amplitud_en_grados = 10;
        float amplitud_en_radianes = (float) (Math.toRadians(amplitud_en_grados));
        float teta_6_en_radianes = (float) (amplitud_en_radianes * Math.sin(fase_en_radianes));
        float teta_6_en_grados = (float) (Math.toDegrees(teta_6_en_radianes));
        //mover el cuerpo rectangular 2
        rectangular_2.rotar(x_6, y_6, teta_6_en_grados);

        //mover flecha 1
        //rotando con MCU
        float teta_7 = 20 * tiempo;
        //mover el cuerpo rectangular 1
        flecha_1.rotar(-teta_7);


        //resorte oscilando
        //resorte_1 oscilando con amplitud 50 y frecuencia 2 Hz
        float frecuencia = 2;
        float amplitud = CR.pcApxY(5);
        fase_en_radianes = (float) (2 * Math.PI * frecuencia * tiempo);
        float y_resorte_1 = (float) (amplitud * (Math.sin(fase_en_radianes)));
        float longitudNatural = resorte_1.getLongitudNatural();
        resorte_1.setLongitud(longitudNatural + y_resorte_1);


    }

}
