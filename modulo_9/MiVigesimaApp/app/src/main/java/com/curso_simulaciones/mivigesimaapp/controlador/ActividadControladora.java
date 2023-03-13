package com.curso_simulaciones.mivigesimaapp.controlador;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mivigesimaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimaapp.vista.CR;
import com.curso_simulaciones.mivigesimaapp.vista.Pizarra;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Cuerda;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.CuerpoRectangular;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Flecha;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Marca;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Masa;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Particula;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Polea;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Regla;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Resorte;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Rueda;

public class ActividadControladora extends Activity {


    private Pizarra pizarra;
    private Masa masa;
    private Cuerda cuerda;
    private Regla regla;
    private Polea polea_1, polea_2,polea_3,polea_4;
    private CuerpoRectangular cuerpoRectangular;
    private Marca marca;
    private Flecha flecha;
    private Resorte resorte;
    private Rueda rueda;
    private Particula particula;
    private ObjetoLaboratorio[] obj=new ObjetoLaboratorio[15];



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
        //linear_principal.setBackgroundColor(Color.YELLOW);

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

        //objetos
        //una masa
        masa =new Masa(CR.pcApxX(10),CR.pcApxY(20),CR.pcApxL(20),CR.pcApxL(10));
        masa.setColorMarca(Color.BLACK);
        masa.setColor(Color.rgb(255,152,60));
        masa.setMarca("M");
        masa.mover(30);
        obj[0]=masa;

        //una cuerda
        cuerda=new Cuerda(CR.pcApxX(20),CR.pcApxY(10),CR.pcApxX(25),CR.pcApxY(50));
        cuerda.setColor(Color.RED);
        cuerda.setGrosorLinea(CR.pcApxL(0.5f));
        obj[1]=cuerda;

        //una regla
        regla=new Regla(CR.pcApxX(35),CR.pcApxY(10), CR.pcApxL(60),CR.pcApxL(10));
        regla.setColor(Color.YELLOW);
        regla.setColorLetras(Color.BLACK);
        regla.setNumeroDivisiones(20);
        regla.rotar(90);
        obj[2]=regla;

        //una polea
        polea_1=new Polea(CR.pcApxX(70),CR.pcApxY(40),CR.pcApxL(10));
        polea_1.setColor(Color.BLUE);
        polea_1.setGrosorLinea(CR.pcApxL(1f));
        obj[3]=polea_1;


        //una polea
        polea_2=new Polea(CR.pcApxX(85),CR.pcApxY(40),CR.pcApxL(10));
        polea_2.setColor(Color.RED);
        polea_2.setGrosorLinea(CR.pcApxL(1f));
        polea_2.rotarEje(36);
        obj[4]=polea_2;


        //una polea
        polea_3=new Polea(CR.pcApxX(70),CR.pcApxY(65),CR.pcApxL(10));
        polea_3.setColor(Color.BLUE);
        polea_3.setGrosorLinea(CR.pcApxL(1f));
        polea_3.rotarEje(36);
        polea_3.setSoportePolea(true);
        obj[5]=polea_3;

        //una polea
        polea_4=new Polea(CR.pcApxX(85),CR.pcApxY(65),CR.pcApxL(10));
        polea_4.setColor(Color.RED);
        polea_4.setGrosorLinea(CR.pcApxL(1f));
        polea_4.setSoportePolea(true);
        polea_4.rotarEje(36);
        polea_4.setSoportePolea(true);
        obj[6]=polea_4;


        //un cuerpo rectangular
        cuerpoRectangular=new CuerpoRectangular(CR.pcApxX(55),CR.pcApxY(30),CR.pcApxL(5),CR.pcApxL(60));
        cuerpoRectangular.setGrosorLinea(CR.pcApxL(0.5f));
        cuerpoRectangular.setColor(Color.argb(100,150,250,30));
        cuerpoRectangular.mover(75);
        obj[7]=cuerpoRectangular;

        //una marca
        marca=new Marca("Eje y",CR.pcApxX(92),CR.pcApxY(40));
        marca.setColor(Color.BLACK);
        marca.setTamano(CR.pcApxL(3f));
        marca.rotar(90);
        obj[8]=marca;

        //una flecha
        flecha=new Flecha(CR.pcApxX(95),CR.pcApxY(20),CR.pcApxL(30));
        flecha.rotar(90);
        obj[9]=flecha;

        //un resorte
        resorte=new Resorte(CR.pcApxX(20),CR.pcApxY(60),CR.pcApxL(30),CR.pcApxL(5) );
        resorte.setColor(Color.BLACK);
        resorte.setNumeroEspiras(15);
        resorte.setGrosorLinea(CR.pcApxL(0.5f));
        resorte.rotar(90);
        obj[10]=resorte;

        //una rueda
        rueda= new Rueda(CR.pcApxX(50),CR.pcApxY(50),CR.pcApxL(10));
        obj[11]=rueda;

        //una particula
        particula=new Particula(CR.pcApxX(50),CR.pcApxY(70),CR.pcApxL(1));
        obj[12]=particula;


        //desplegar la escena inicial
        pizarra.setEstadoEscena(obj);

    }


}
