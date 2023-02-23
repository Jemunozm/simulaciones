package com.curso_simulaciones.miseptima_2_app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.miseptima_2_app.objetos_laboratorio.Polea;
import com.curso_simulaciones.miseptima_2_app.vista.Pizarra;

public class ActividadPrincipalMiSeptima_2_App extends Activity implements Runnable {

    //Pizarra para dibujar
    private Pizarra pizarra;
    //objetos dibujables para Pizarra
    private Polea polea_1, polea_2, polea_3;
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

        //crear objetos de laboratorio
        crearObjetosLaboratorio();


        /*
        para informar cómo se debe adaptar la GUI a la pantalla del dispositivo
        */
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        /*
        pegar al contenedor la GUI:
        en el argumento se está llamando al método crearGui()
        */
        this.setContentView(crearGui(), parametro_layout_principal);

        hilo = new Thread(this);

        hilo.start();


    }


    //crear los objetos de la interfaz gráfica de usuario (GUI)
    private void crearElementosGui() {
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.WHITE);
    }


    /*Crea los objetos cuerpo rígido con su estado inicial*/
    private void crearObjetosLaboratorio() {

        //trea poleas
        polea_1 = new Polea(100, 100, 50);
        poleas[0] = polea_1;


        polea_2 = new Polea(100, 250, 100);
        polea_2.setColorPolea(Color.BLACK);
        poleas[1] = polea_2;


        polea_3 = new Polea(100, 450, 50);
        polea_3.setColorPolea(Color.MAGENTA);
        poleas[2] = polea_3;

        //actualizar escena
        pizarra.setEstadoEscena(poleas);

    }

    //organizar la distribución de los objetos de de la GUI usando administradores de diseño
    private LinearLayout crearGui() {

        //administrador de diseño
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.rgb(250, 150, 50));


        /*pegar el objeto tipo PizarraDibujo)*/
        //parámetro de pegada
        LinearLayout.LayoutParams parametrosPegada = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegada.setMargins(50, 50, 50, 50);
        parametrosPegada.weight = 1.0f;
        //pegar
        linear_principal.addView(pizarra, parametrosPegada);


        return linear_principal;

    }


    @Override
    public void run() {

        //hilo sin fin
        while (true) {

            try {
                Thread.sleep(periodo_muestreo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tiempo = tiempo + 0.1f;
            //cambio de estado de la escena física en la pizarra
            cambiarEstadosEscenaPizarra(tiempo);

        }

    }

    /*
    Cambia el esatado de los objetos polea y lo comunica a pizarra
    */
    private void cambiarEstadosEscenaPizarra(float tiempo) {

        //polea_1
        /*
        se traslada con velocidad constante en x Vx=20; Vy=0;
        no rota alrededor de su eje, W=0
        */
        float desplazamiento_x_1 = 20 * tiempo;//ecuación cinemática MU
        //mover la polea 1
        poleas[0].moverPolea(desplazamiento_x_1, 0);

        //polea_2
        /*
        no se traslada  Vx=0; Vy=0;
        rota alrededor de su eje con velocidad
        angular W=50
        */
        float teta_2 = 50 * tiempo;//ecuación MCU
        //mover la polea 2
        poleas[1].moverPolea(teta_2);


        //polea3
        /*
        se traslada con velocidad  Vx=20; Vy=0;
        rota alrededor de su eje con velocidad
        angular W=50
        */
        float desplazamiento_x_3 = 20 * tiempo;//ecuación MU
        float teta_3 = 50 * tiempo;//ecuación MCU
        //mover la polea 3
        poleas[2].moverPolea(desplazamiento_x_3, 0, teta_3);

    }
}
