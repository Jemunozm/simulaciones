package com.curso_simulaciones.mivigesimacuartaapp.vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import com.curso_simulaciones.mivigesimacuartaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Flecha;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Particula;

public class Pizarra extends View {

    private ObjetoLaboratorio objetosLab[];

    public boolean evento_touch=true;

    private float evento_x_touch_en_pixeles= AlmacenDatosRAM.x_en_pixeles;
    private float evento_y_touch_en_pixeles=AlmacenDatosRAM.y_en_pixeles;
    private float factorConversion_metroApixel,factorConversion_pixelAmetro;


    private float origen_x, origen_y;
    private float m_x = 1;
    private float m_y = 1;


    /**
     * Constructor
     *
     * @param context
     */
    public Pizarra(Context context) {
        super(context);

        eventos();

    }


    public void setSistemaCoordenadas(float origen_x, float origen_y, float m_x, float m_y) {

        this.origen_x = origen_x;
        this.origen_y = origen_y;
        this.m_x = m_x;
        this.m_y = m_y;

    }

    public void eventos(){

        this.setOnTouchListener(new View.OnTouchListener() {


            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();

                if(evento_touch==true) {
                    //touch en pixeles
                    evento_x_touch_en_pixeles = event.getX()-origen_x;
                    evento_y_touch_en_pixeles = -(event.getY()-origen_y) + CR.pcApxY(50);


                }



                switch (action) {

                    case MotionEvent.ACTION_DOWN:


                        break;

                    case MotionEvent.ACTION_MOVE:

                        if(evento_touch==true) {

                            dibujarEstadoInicial();


                        }


                        break;

                    case MotionEvent.ACTION_UP:


                        break;


                }

                return true;
            }

        });//fin onTouch


    }//fin eventos


    public void setEstadoEscena(ObjetoLaboratorio[] cuerpos) {

        this.objetosLab = cuerpos;

    }


    //Método para dibujar la escena
    private void dibujarEscena(Canvas canvas, Paint pincel) {

        //dibujar las objetos de laboratorio
        for (int i = 0; i < objetosLab.length; i++) {
            if (objetosLab[i] != null) {
                objetosLab[i].dibujese(canvas, pincel);
            }
        }


    }


    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);

        canvas.save();
        //cambio a sistema de coordenadas tradicional
        canvas.translate(origen_x, origen_y);
        canvas.scale(m_x, m_y);

        //dibujar objetos
        if (objetosLab != null)
            dibujarEscena(canvas, pincel);

        canvas.restore();

        //dibujar letreros
        dibujarLetreros(canvas, pincel);


        //necesario para actualizar los dibujos en animaciones
        invalidate();

    }

    private void dibujarEstadoInicial(){

        //recordar que el (0,0) está en
        float posición_inicial_x_en_pixeles = evento_x_touch_en_pixeles-AlmacenDatosRAM.origenX_en_pixeles;
        float posición_inicial_y_en_pixeles = evento_y_touch_en_pixeles-AlmacenDatosRAM.origenY_en_pixeles;

        Particula bolita= (Particula)objetosLab[6];//se hace casting
        bolita.setPosicion(posición_inicial_x_en_pixeles,posición_inicial_y_en_pixeles);

        Flecha velocidad= (Flecha)objetosLab[3];//se hace casting
        velocidad.setPosicion(posición_inicial_x_en_pixeles,posición_inicial_y_en_pixeles);

        Flecha velocidadX= (Flecha)objetosLab[4];//se hace casting
        velocidadX.setPosicion(posición_inicial_x_en_pixeles,posición_inicial_y_en_pixeles);

        Flecha velocidadY= (Flecha)objetosLab[5];//se hace casting
        velocidadY.setPosicion(posición_inicial_x_en_pixeles,posición_inicial_y_en_pixeles);

        Flecha aceleracion= (Flecha)objetosLab[2];//se hace casting
        aceleracion.setPosicion(posición_inicial_x_en_pixeles,posición_inicial_y_en_pixeles);

        //reportar esta posicion inicial en pixeles AlmacenDatosRAM
        AlmacenDatosRAM.xi_en_pixeles = posición_inicial_x_en_pixeles;
        AlmacenDatosRAM.yi_en_pixeles = posición_inicial_y_en_pixeles;

        //reportar esta posicion inicial en m AlmacenDatosRAM
        factorConversion();
        AlmacenDatosRAM.xi_en_metros= factorConversion_pixelAmetro*posición_inicial_x_en_pixeles;;
        AlmacenDatosRAM.yi_en_metros=factorConversion_pixelAmetro*posición_inicial_y_en_pixeles;
        AlmacenDatosRAM.x_en_metros= AlmacenDatosRAM.xi_en_metros;
        AlmacenDatosRAM.y_en_metros= AlmacenDatosRAM.yi_en_metros;
    }


    private void dibujarLetreros(Canvas canvas, Paint pincel) {


        pincel.setTextSize(CR.pcApxL(3f));
        pincel.setTypeface(Typeface.MONOSPACE);

        String tiempo= String.format("%.2f", AlmacenDatosRAM.tiempo);
        String desplazamiento_en_metros_X= String.format("%.2f", AlmacenDatosRAM.desplazamiento_en_metros_X);
        String desplazamiento_en_metros_Y= String.format("%.2f", AlmacenDatosRAM.desplazamiento_en_metros_Y);

        //cuando empieza una nueva simulación la posición es
        // la corrspondiente a la posción inicial
        if(AlmacenDatosRAM.NUEVO==true){
            AlmacenDatosRAM.x_en_metros= AlmacenDatosRAM.xi_en_metros;
            AlmacenDatosRAM.y_en_metros= AlmacenDatosRAM.yi_en_metros;
        }

        String x_metros = String.format("%.2f", AlmacenDatosRAM.x_en_metros);
        String y_metros = String.format("%.2f", AlmacenDatosRAM.y_en_metros);
        String angulo= String.format("%.2f", AlmacenDatosRAM.angulo);
        String vX= String.format("%.2f", AlmacenDatosRAM.velocidadX);
        String vY= String.format("%.2f", AlmacenDatosRAM.velocidadY);
        String v= String.format("%.2f", AlmacenDatosRAM.velocidad);
        String a= String.format("%.2f", AlmacenDatosRAM.aceleracion);

        canvas.drawText("Desplaz. x  (m) = "+desplazamiento_en_metros_X,CR.pcApxX(2f),CR.pcApxY(4f),pincel);
        canvas.drawText("Desplaz. y (m) = "+desplazamiento_en_metros_Y,CR.pcApxX(2f),CR.pcApxY(8f),pincel);
        canvas.drawText("t  (s) = "+tiempo,CR.pcApxX(2f),CR.pcApxY(12f),pincel);
        canvas.drawText("x  (m) = "+x_metros,CR.pcApxX(2f),CR.pcApxY(16f),pincel);
        canvas.drawText("y  (m) = " +y_metros,CR.pcApxX(2f),CR.pcApxY(20f),pincel);
        canvas.drawText("Ang.  (0) = " +angulo,CR.pcApxX(2f),CR.pcApxY(24f),pincel);
        pincel.setColor(Color.MAGENTA);
        canvas.drawText("Vx (m/s) = " +vX,CR.pcApxX(2f),CR.pcApxY(28f),pincel);
        pincel.setColor(Color.rgb(0,128,0));
        canvas.drawText("Vy (m/s) = " +vY,CR.pcApxX(2f),CR.pcApxY(32f),pincel);
        pincel.setColor(Color.RED);
        canvas.drawText("V  (m/s) = " +v,CR.pcApxX(2f),CR.pcApxY(36f),pincel);
        pincel.setColor(Color.BLUE);
        canvas.drawText("a  (m/s2) = " +a,CR.pcApxX(2f),CR.pcApxY(40f),pincel);

        pincel.setColor(Color.RED);
        canvas.drawText("Arrastrar con el dedo para cambiar la posición inicial",CR.pcApxX(2f),CR.pcApxY(85f),pincel);

        pincel.setColor(Color.BLACK);
        //autor
        String autor = "Copyright 2022 para Diego Luis Aristizábal Ramírez" ;
        canvas.drawText(autor, CR.pcApxX(2),CR.pcApxY(90),pincel);
        String universidad = "Universidad Nacional de Colombia - Sede Medellín";
        canvas.drawText(universidad, CR.pcApxX(2),CR.pcApxY(95),pincel);

    }

    private void factorConversion(){

       /*
        Para dar una equivalencia de pixeles
        en metros se aumirá que 50 m equivale
        al ALTO de la pantalla (en posición
        LANSCAPE) en pixeles. Con base en esto
        el factor de conversion de metos  apixeles
        es:

        factorConversion_metroApixel= (ALTO en pixeles/ 50 metros)
        factorConversion_pixelAmetro= (ALTO metros / ALTO en pixeles)

        */

        factorConversion_metroApixel =  CR.pcApxY(100f)/50f;

        factorConversion_pixelAmetro = 50f/CR.pcApxY(100f);

    }

}
