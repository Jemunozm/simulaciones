package com.curso_simulaciones.misegundoexamenapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.misegundoexamenapp.objetos_laboratorio.Bala;
import com.curso_simulaciones.misegundoexamenapp.vista.CR;
import com.curso_simulaciones.misegundoexamenapp.vista.Pizarra;

public class ActividadPrincipalMiSegundoExamen extends Activity implements Runnable {

        //dimensiones de pantalla
        private float ancho_pantalla, alto_pantalla;
        //Pizarra para dibujar
        private Pizarra pizarra;
        //objetos dibujables para Pizarra
        //arreglo que permite contener 10 Ruedas
        private Bala[] balas = new Bala[10];
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
                    tiempo = tiempo + 0.05f;//
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
            balas[0] = new Bala(x_c, y_c, 1.1f*radio);
            balas[0].setColorRueda(Color.MAGENTA,
                    Color.rgb(204,88,214),Color.GREEN);


            //rueda 2 con su centro en el centro del canvas
            x_c = CR.pcApxX(50);
            y_c = CR.pcApxY(50);
            balas[1] = new Bala(x_c, y_c, 1.5f*radio);
            balas[1].setColorRueda(Color.rgb(88,214,141),
                    Color.rgb(152,88,214),Color.WHITE);

            //rueda 3 con su centro en X = 0  e
            // Y = 0.25 de la altura del canvas
            x_c = CR.pcApxX(0);
            y_c = CR.pcApxY(25);
            balas[2] = new Bala(x_c, y_c, 0.5f*radio);
            balas[2].setColorRueda(Color.rgb(142, 68, 173),
                    Color.rgb(93, 109, 126),Color.rgb(215, 219, 221));

            //rueda 4 con su centro en X = 0 e
            // Y = 0.75 de la altura del canvas
            x_c = CR.pcApxX(0);
            y_c = CR.pcApxY(75);
            balas[3] = new Bala(x_c, y_c, 0.75f*radio);
            balas[3].setColorRueda(Color.BLACK,Color.WHITE,Color.GREEN);

            //rueda 5 con su centro en esquina inferior derecha
            x_c = CR.pcApxX(0);
            y_c = CR.pcApxY(0);
            balas[4] = new Bala(x_c, y_c, 1.3f*radio);
            balas[4].setColorRueda(Color.GRAY,
                    Color.rgb(204,88,214),Color.GREEN);

            //rueda 6 con su centro en esquina inferior derecha
            x_c = CR.pcApxX(0);
            y_c = CR.pcApxY(100);
            balas[5] = new Bala(x_c, y_c, 0.84f*radio);
            balas[5].setColorRueda(Color.YELLOW,
                    Color.rgb(250, 215, 160),Color.GREEN);

            //rueda 7 con su centro en esquina inferior derecha
            x_c = CR.pcApxX(100);
            y_c = CR.pcApxY(0);
            balas[6] = new Bala(x_c, y_c, 1.8f*radio);
            balas[6].setColorRueda(Color.rgb(250, 128, 114),
                    Color.rgb(250, 215, 160),Color.rgb(235, 152, 78));

            pizarra.setEstadoEscena(balas);
        }




        /*
        Cambia el esatado de los objetos Bala y lo comunica a pizarra
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

            //mover las balas
            //mover Bala 1
            balas[0].moverRueda(teta_1);
            //mover Bala 2
            balas[1].moverRueda(teta_2);
            //mover Bala 3
            balas[2].moverRueda(desplazamiento_3_x,desplazamiento_3_y);
            //mover Bala 4
            balas[3].moverRueda(desplazamiento_4_x,desplazamiento_4_y,teta_4);
            // mover Bala 5
            balas[4].moverRueda(-1.3f*teta_1);
            // mover Bala 6
            balas[5].moverRueda(2f*teta_1);
            // mover Bala 7
            balas[6].moverRueda(-teta_1);


        }
    }
