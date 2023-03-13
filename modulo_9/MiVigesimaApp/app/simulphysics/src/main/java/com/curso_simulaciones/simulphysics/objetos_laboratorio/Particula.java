package com.curso_simulaciones.simulphysics.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Particula extends ObjetoLaboratorio {


    //radio de la bola
    private float radio;
    private float[] x=new float[1000];
    private float[] y=new float[1000];
    private int i=0;
    private boolean TRAYECTORIA=false;
    private int colorTrayectoria= Color.RED;


    /**
     * Constructor de Partícula en (posicionX, posicionY) y de radio
     * @param posicionX
     * @param posicionY
     * @param radio
     */

    public Particula(float posicionX, float posicionY, float radio){

        this.posicionX=posicionX;
        this.posicionY=posicionY;
        this.radio=radio;

    }

    /**
     * Modifica la posicion de la partícula
     *
     * @param posicionX
     * @param posicionY
     */

    public void setPosicion(float posicionX, float posicionY) {

        this.posicionX = posicionX;
        this.posicionY = posicionY;

    }



    /**
     * Método para cambiar el radio de la bola.
     * @param radio
     */

    public void setRadio(float radio){

        this.radio=radio;


    }//fin setRadio


    /**
     * Método para obtener el radio de la bola.
     * @return
     */

    public float getRadio( ){

        return radio;

    }//fin getRadio


    /**
     * Asigna el color a la trayectoria
     * @param colorTrayectoria
     */
    public void setColorTrayectoria(int colorTrayectoria){
        this.colorTrayectoria=colorTrayectoria;

    }

    /**
     * Con true define que la trayectoria se desplegará
     * hasta 999 puntos
     * @param TRAYECTORIA
     * @param i
     */
    public void setTrayectoria(boolean TRAYECTORIA, int i){


        this.i=i;

        this.TRAYECTORIA=TRAYECTORIA;

        if(TRAYECTORIA==true&i<999) {
            x[i]=this.posicionX;
            y[i]=this.posicionY;
        }

    }

    public void borrarDatos(){

        for (int i = 0; i < 1000; i++) {
            x[i] = 0;
            y[i] = 0;
        }

    }



    public void dibujese(Canvas canvas, Paint pincel) {


        //estado del canvas para luego restaurarlo a como estaba
        canvas.save();


        pincel.setStrokeWidth(grosorLinea);

    /*
      Para dibujar óvalos no rellenos hay un BUG en ANDROID con
      el método drawCircle. Por esto se implementó el método
      aqui denominado dibujarOvalo() que arregla esto.
    */

        //el punto donde se centrarala polea
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(color);
        canvas.drawCircle(posicionX, posicionY, radio, pincel);

        //dibujar puntico central
        RectF rec_puntico= new RectF(-0.001f*radio,-0.001f*radio,0.001f*radio,0.001f*radio);
        pincel.setColor(Color.BLACK);
        canvas.drawCircle(posicionX, posicionY, 0.05f * radio, pincel);


        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(grosorLinea);


        //dibujar trayectoria
        pincel.setColor(colorTrayectoria);
        if(TRAYECTORIA==true) {

            for (int j = 1; j < i; j = j + 1) {

                if (i > 1 & i < 1000) {
                    canvas.drawLine(x[j - 1],y[j - 1],x[j], y[j], pincel);
                }  else{i=998;}
            }//fin dibujar la trayectoria
        }//fin if



        //restaurar el canvas
        canvas.restore();
        //restaurar el pincel
        pincel.reset();
    }

}

