package com.curso_simulaciones.simulphysics.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Cuerda extends ObjetoLaboratorio {

    private float posicionIniclaX, posicionInicialY, posicionFinalX, posicionFinalY;

    /**
     * Constructor de la cuerda con extremo inicial en
     * (posicionInicialX,posicionInicialY) y con extremo final en
     * (posicionFinalX,posicionFinalY)
     * @param posicionInicialX
     * @param posicionInicialY
     * @param posicionFinalX
     * @param posicionFinalY
     */
    public Cuerda(float posicionInicialX, float posicionInicialY, float posicionFinalX, float posicionFinalY) {

        this.posicionIniclaX = posicionInicialX;
        this.posicionInicialY = posicionInicialY;
        this.posicionFinalX=posicionFinalX;
        this.posicionFinalY=posicionFinalY;

    }


    /**
     * Cambia la psoción del extremo inicial de la cuerda
     * @param posicionIniclaX
     * @param posicionInicialY
     */

    public void setPosicionInicial(float posicionIniclaX, float posicionInicialY){

        this.posicionIniclaX=posicionIniclaX;
        this.posicionInicialY=posicionInicialY;

    }

    /**
     * Cambia la posición del extremo final de la cuerda
     * @param posicionFinalX
     * @param posicionFinalY
     */
    public void setPosicionFinal(float posicionFinalX, float posicionFinalY){

        this.posicionFinalX=posicionFinalX;
        this.posicionFinalY=posicionFinalY;

    }

    /**
     * Retorna la longitud de la cuerda
     * @return
     */

    public float getLongitud(){

        float a=posicionFinalX-posicionIniclaX;
        float b=posicionFinalY-posicionInicialY;

        return (float)(Math.sqrt(a*a+b*b));
    }



    //implementa el método de la Interface Dibujable
    public void dibujese(Canvas canvas, Paint pincel) {


        canvas.save();

        pincel.setStrokeWidth(grosorLinea);
        pincel.setColor(color);

        canvas.drawLine(posicionIniclaX,posicionInicialY,posicionFinalX,posicionFinalY,pincel);

        //restaurar el canvas
        canvas.restore();
        //restaurar el pincel
        pincel.reset();
    }

}
