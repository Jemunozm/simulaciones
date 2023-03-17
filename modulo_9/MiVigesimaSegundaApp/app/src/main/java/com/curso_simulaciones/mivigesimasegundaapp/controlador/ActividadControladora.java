package com.curso_simulaciones.mivigesimasegundaapp.controlador;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mivigesimasegundaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimasegundaapp.vista.CR;
import com.curso_simulaciones.mivigesimasegundaapp.vista.Pizarra;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Cuerda;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.CuerpoRectangular;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Flecha;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Marca;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Masa;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Polea;

public class ActividadControladora extends Activity {


    private Pizarra pizarra;

    private Masa masa_1, masa_2, masa_3;
    private Polea polea_1, polea_2, polea_3;
    private Flecha flecha_1, flecha_2, flecha_3;
    private CuerpoRectangular barra_1, barra_2;
    private Cuerda cuerda_1, cuerda_2, cuerda_3, cuerda_4, cuerda_5;
    private Marca marca;

    private ObjetoLaboratorio[] objetos = new ObjetoLaboratorio[20];


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


    }//fin onCreate


    /*Método auxiliar para asuntos de resolución*/
    private void gestionarResolucion() {

        /*
        Según el diseño de la GUI se puede anticipar cuál es la
        dimensión de la pizarra. En este caso es el 100% del ancho
        de la pantalla y el 100% del alto de la misma
        */
        CR.anchoPizarra = AlmacenDatosRAM.ancho_pantalla;
        CR.altoPizarra = AlmacenDatosRAM.alto_pantalla;

    }

    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {


        //crear pizarra sabiendo de antemano sus dimensiones
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.WHITE);

        crearObjetosLaboratorio();


    }//fin crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        //el linear principal
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);


        //pegar pizarra a linearArriba
        linear_principal.addView(pizarra);

        return linear_principal;

    }//fin crearGUI


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
        barra_1 = new CuerpoRectangular(CR.pcApxX(50), CR.pcApxY(50), CR.pcApxL(55), CR.pcApxL(70));
        barra_1.setColor(Color.YELLOW);
        objetos[3] = barra_1;
        //barra inferior que sostine el sistema
        barra_2 = new CuerpoRectangular(CR.pcApxX(50), CR.pcApxY(90), CR.pcApxL(70), CR.pcApxL(10));
        barra_2.setColor(Color.GREEN);
        objetos[4] = barra_2;

         /*
          Coordenadas de puntos básicos y
          dimensiones de los elementos
         */
        //radio de las poleas
        float radio = CR.pcApxL(4);
        //dimensiones de cada masa: m1 y m2
        float ancho_bloque = CR.pcApxL(5);
        float alto_bloque = CR.pcApxL(10);


        //coordenadas de las poleas
        //abscisa y ordenada del centro de la polea 1
        float xp1 = CR.pcApxX(50) - CR.pcApxL(35) + radio;
        float yp1 = CR.pcApxY(50) - CR.pcApxL(35) - radio;
        //abscisa y ordenada del centro de la polea 2
        float xp2 = CR.pcApxX(50) + CR.pcApxL(35) - radio;
        float yp2 = CR.pcApxY(50) - CR.pcApxL(35) - radio;
        //abscisa y ordenada del centro de la polea 3
        float xp3 = xp2+radio;
        float yp3 = yp2+(6*radio);//CR.pcApxY(50)-CR.pcApxL(17.5f);

        //coordenadas de las masas
        //abscisa y ordenada del centro de la masa 1
        float x1 = xp1-radio;
        float y1 = yp1+(12.5f*radio);//CR.pcApxY(50)+CR.pcApxL(13.125f);
        //abscisa y ordenada del centro de la masa 2
        float x2 = xp3-radio;
        float y2 = yp3+(8*radio);
        //abscisa y ordenada del centro de la masa 3
        float x3 = xp3+radio;
        float y3 = yp3+(5*radio);



        /*
        Creación de objetos físicos y dibujo del
        estado inicial de la escena física
       */


        //dos poleas
        //polea 1 (polea azul izquierda)
        polea_1 = new Polea(xp1, yp1, radio);
        polea_1.setColor(Color.BLUE);
        polea_1.setGrosorLinea(CR.pcApxL(0.4f));
        polea_1.setSoportePolea(true);
        polea_1.rotarEje(325);
        objetos[0] = polea_1;

        //polea 2 (polea azul derecha)
        polea_2 = new Polea(xp2, yp2, radio);
        polea_2.setColor(Color.BLUE);
        polea_2.setGrosorLinea(CR.pcApxL(0.4f));
        polea_2.setSoportePolea(true);
        polea_2.rotarEje(35);
        objetos[1] = polea_2;

        //polea 3 (polea verde)
        polea_3 = new Polea(xp3, yp3, radio);
        polea_3.setColor(Color.GREEN);
        polea_3.setGrosorLinea(CR.pcApxL(0.4f));
        polea_3.setSoportePolea(true);
        polea_3.rotarEje(180);
        objetos[2] = polea_3;


        //4  cuerdas
        cuerda_1=new Cuerda(xp1-radio,yp1, xp1-radio,CR.pcApxY(60));
        cuerda_1.setColor(Color.RED);
        cuerda_1.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[6]=cuerda_1;

        cuerda_2=new Cuerda(xp1,yp1-radio,xp2,yp2-radio);
        cuerda_2.setColor(Color.RED);
        cuerda_2.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[7]=cuerda_2;

        cuerda_3=new Cuerda(xp2+radio,yp2,xp2+radio,yp3-(1.2f*radio));
        cuerda_3.setColor(Color.RED);
        cuerda_3.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[8]=cuerda_3;


        cuerda_4=new Cuerda(xp3+radio,yp3,xp3+radio,yp3+(5*radio));
        cuerda_4.setColor(Color.BLACK);
        cuerda_4.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[9]=cuerda_4;

        cuerda_5=new Cuerda(xp3-radio,yp3,xp3-radio,yp3+(8*radio));
        cuerda_5.setColor(Color.BLACK);
        cuerda_5.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[10]=cuerda_5;



        //2 masas
        //masa 1
        masa_1=new Masa(x1,y1,ancho_bloque,alto_bloque);
        masa_1.setColor(Color.YELLOW);
        masa_1.setColorMarca(Color.BLACK);
        masa_1.setMarca("M1");
        objetos[11]=masa_1;

        //masa 2
        masa_2=new Masa(x2,y2,ancho_bloque,alto_bloque);
        masa_2.setColor(Color.YELLOW);
        masa_2.setColorMarca(Color.BLACK);
        masa_2.setMarca("M2");
        objetos[12]=masa_2;
        //masa 3
        masa_3=new Masa(x3,y3,ancho_bloque,alto_bloque);
        masa_3.setColor(Color.YELLOW);
        masa_3.setColorMarca(Color.BLACK);
        masa_3.setMarca("M3");
        objetos[13]=masa_3;

        //desplegar la escena inicial
        pizarra.setEstadoEscena(objetos);

    }


}

