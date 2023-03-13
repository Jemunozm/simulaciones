package com.curso_simulaciones.simulphysics.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class ObjetoLaboratorio implements Dibujable{


    protected int color = Color.RED;
    protected float posicionX,posicionY;
    protected float posicionAngular;
    protected float grosorLinea=2f;


    /**
     * Modifica el grosor de la línea con la que se dibujará
     * @param grosorLinea
     */

    public void setGrosorLinea(float grosorLinea){

        this.grosorLinea=grosorLinea;

    }



    /**
     * Devuelve el grosor de la línea con la que se dibuja
     * @return
     */
    public float getGrosorLinea(){

        return grosorLinea;
    }


    /**
     * Modifica el color del cuerpo del objeto de laboratorio
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Devuelve el color del objeto de laboratorio
     *
     * @return
     */
    public int getColor() {
        return color;
    }


    /**
     * Método para que se dibujen los objetos Dibujables,
     * es decir, los objetos que implementanla interface Dibujable
     * @param canvas
     * @param pincel
     */
    public abstract void dibujese(Canvas canvas, Paint pincel);
}
