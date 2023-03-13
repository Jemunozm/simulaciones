package com.curso_simulaciones.mivigesimaprimeraapp.controlador;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mivigesimaprimeraapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimaprimeraapp.vista.CR;
import com.curso_simulaciones.mivigesimaprimeraapp.vista.Pizarra;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Cuerda;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.CuerpoRectangular;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Flecha;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Marca;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Masa;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Polea;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Regla;

public class ActividadControladora extends Activity {


    private Pizarra pizarra;

    private Masa masa_1, masa_2;
    private Polea polea_1, polea_2;
    private Flecha flecha_1, flecha_2, flecha_3;
    private Regla regla_1, regla_2;
    private CuerpoRectangular barra;
    private Cuerda cuerda_1, cuerda_2, cuerda_3, cuerda_4;
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
          Coordenadas de puntos básicos y
          dimensiones de los elementos
         */
        //radio de las poleas
        float radio= CR.pcApxL(5);

        //dimensiones de cada masa: m1 y m2
        float ancho_bloque= 2*radio;
        float alto_bloque=radio;

        //coordenadas de las masas
        //abscisa y ordenada del centro de la masa 1
        float x1= CR.pcApxX(51)+radio;
        float y1= CR.pcApxY(34.2f);
        //abscisa y ordenada del centro de la masa 2
        float x2= x1-3*radio;
        float y2=CR.pcApxY(81.5f);

        //coordenadas de las poleas
        //abscisa y ordenada del centro de la polea 1
        float xp1=x1-radio;
        float yp1=CR.pcApxY(10f);
        //abscisa y ordenada del centro de la polea 2
        float xp2=x2;
        float yp2= y2 - 3*radio;


        /*
        Creación de objetos físicos y dibujo del
        estado inicial de la escena física
       */
        //dos poleas
        //polea 1 (polea azul)
        polea_1=new Polea(xp1,yp1,radio);
        polea_1.setColor(Color.BLUE);
        polea_1.setGrosorLinea(CR.pcApxL(0.5f));
        polea_1.setSoportePolea(true);
        polea_1.rotarEje(180);
        objetos[0]=polea_1;

        //polea 2 (polea roja)
        polea_2=new Polea(xp2,yp2,radio);
        polea_2.setColor(Color.RED);
        polea_2.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[1]=polea_2;


        //barra superior que sostiene el sistem
        barra=new CuerpoRectangular(CR.pcApxX(50),CR.pcApxY(2),CR.pcApxL(40),CR.pcApxL(2));
        objetos[2]=barra;


        //4  cuerdas
        cuerda_1=new Cuerda(xp2+radio,yp1, xp2+radio,yp2);
        cuerda_1.setColor(Color.BLACK);
        cuerda_1.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[3]=cuerda_1;

        cuerda_2=new Cuerda(xp2-radio,CR.pcApxY(2.8f),xp2-radio,yp2);
        cuerda_2.setColor(Color.BLACK);
        cuerda_2.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[4]=cuerda_2;

        cuerda_3=new Cuerda(xp1+radio,yp1,xp1+radio,y1-0.5f*alto_bloque);
        cuerda_3.setColor(Color.BLACK);
        cuerda_3.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[5]=cuerda_3;

        //cuerda_4=new Cuerda(xp2,yp2+0.5f*alto_barrita,xp2,y2-0.5f*alto_bloque);
        cuerda_4=new Cuerda(xp2,yp2+radio,xp2,y2-0.5f*alto_bloque);
        cuerda_4.setColor(Color.BLACK);
        cuerda_4.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[6]=cuerda_4;

        //2 masas
        //masa 2
        masa_2=new Masa(x2,y2,ancho_bloque,alto_bloque);
        masa_2.setColor(Color.YELLOW);
        masa_2.setColorMarca(Color.BLACK);
        masa_2.setMarca("M2");
        objetos[7]=masa_2;

        //masa 1
        masa_1=new Masa(x1,y1,ancho_bloque,alto_bloque);
        masa_1.setColor(Color.YELLOW);
        masa_1.setColorMarca(Color.BLACK);
        masa_1.setMarca("M1");
        objetos[8]=masa_1;

        //dos reglas
        //regla 1
        regla_1=new Regla(xp2-2.5f*radio,CR.pcApxY(15),CR.pcApxL(70),1.5f*radio);
        regla_1.setColor(Color.GREEN);
        regla_1.setColorLetras(Color.BLACK);
        regla_1.setNumeroDivisiones(20);
        regla_1.rotar(90);
        objetos[9]=regla_1;

        //regla 2
        regla_2=new Regla(xp1+5f*radio,CR.pcApxY(15),CR.pcApxL(70),1.5f*radio);
        regla_2.setColor(Color.GREEN);
        regla_2.setColorLetras(Color.BLACK);
        regla_2.setNumeroDivisiones(20);
        regla_2.rotar(90);
        objetos[10]=regla_2;


        //tres flechas
        //eje y
        flecha_1=new Flecha(xp1+7f*radio,yp1,8*radio);
        flecha_1.rotar(90);
        objetos[11]=flecha_1;

        //flecha señaladora de regla derecha
        flecha_2=new Flecha(x1+0.5f*ancho_bloque,y1,2f*radio);
        flecha_2.setColor(Color.BLACK);
        objetos[12]=flecha_2;

        //flecha señaladora de regla izquierda
        flecha_3=new Flecha(x2-0.5f*ancho_bloque,y2,2f*radio);
        flecha_3.setColor(Color.BLACK);
        flecha_3.rotar(180);
        objetos[13]=flecha_3;

        //una marca
        //despliega el nombre del Eje y
        marca=new Marca("Eje y",xp1+7.3f*radio,yp1+5.6f*radio);
        marca.setColor(Color.BLACK);
        marca.setTamano(CR.pcApxL(3f));
        marca.rotar(90);
        objetos[14]=marca;

        //desplegar la escena inicial
        pizarra.setEstadoEscena(objetos);

    }


}

