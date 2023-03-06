package com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Flecha extends ObjetoLaboratorio {

    private float longitud;
    private float posicionX, posicionY;
    private float posicionAngular;
    private float grosorLinea = 5f;
    private int color = Color.BLACK;

    /**
     * Constructor por defecto
     * Fecha con origen en (0,0),
     * longitud 20f, horizontal y
     * sentido + X
     */

    public Flecha() {

        this.posicionX = 0;
        this.posicionY = 0;
        this.longitud = 20f;

    }


    /**
     * Modifica la longitud del objeto de laboratorio Flecha
     *
     * @param longitud
     */

    public void setLongitud(float longitud) {

        this.longitud = longitud;
    }


    public float getLongitud() {

        return longitud;

    }


    /**
     * Modifica la posicion del objeto de laboratorio
     *
     * @param posicionX
     * @param posicionY
     */

    public void setPosicion(float posicionX, float posicionY) {

        this.posicionX = posicionX;
        this.posicionY = posicionY;

    }


    /**
     * Regresa la posición del laboratorio
     *
     * @return
     */


    public float[] getPosicion() {

        float[] coordenadas = new float[2];

        coordenadas[0] = posicionX;
        coordenadas[1] = posicionY;

        return coordenadas;


    }


    /**
     * Modifica el grosor de la línea con la que se dibujará
     *
     * @param grosorLinea
     */

    public void setGrosorLinea(float grosorLinea) {

        this.grosorLinea = grosorLinea;

    }


    /**
     * Devuelve el grosor de la línea con la que se dibuja
     *
     * @return
     */
    public float getGrosorLinea() {

        return grosorLinea;
    }

    /**
     * Modifica el color del objeto de laboratorio
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
     * Genera rotación de la flecha a la
     * posición angular posicionAngular
     * alrededor de eje que pasa por
     * (posicionX,posicionY)
     *
     * @param posicionAngular
     */

    public void rotar(float posicionAngular) {

        this.posicionAngular = posicionAngular;

    }


    //implementa el método de la Interface Dibujable
    public void dibujese(Canvas canvas, Paint pincel) {


        //valores del pincel para luego restaurarlo  como estaba
        float grosorInicialLinea = pincel.getStrokeWidth();

        //estado del canvas para luego restaurarlo a como estaba
        canvas.save();


        pincel.setStrokeWidth(grosorLinea);
        pincel.setColor(color);


        //traslada la flecha a la posición (posicionX, posicionY)
        canvas.translate(posicionX, posicionY);
        //rota la flecha alrededor de eje que pasa por (posicionX, posicionY)
        canvas.rotate(posicionAngular);

        float cabeza = 0.1f * longitud;
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(longitud - cabeza, 0);
        path.lineTo(longitud - cabeza, -0.5f * cabeza);
        path.lineTo(longitud, 0);
        path.lineTo(longitud - cabeza, 0.5f * cabeza);
        path.lineTo(longitud - cabeza, 0);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, pincel);


        //restaurar pincel
        pincel.setStrokeWidth(grosorInicialLinea);
        pincel.setStyle(Paint.Style.FILL);

        //restaurar el canvas
        canvas.restore();

    }
}
