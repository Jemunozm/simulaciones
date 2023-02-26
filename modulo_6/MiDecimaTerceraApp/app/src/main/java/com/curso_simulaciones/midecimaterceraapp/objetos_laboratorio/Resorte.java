package com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Resorte extends ObjetoLaboratorio{
    private float posicionX, posicionY;
    private float posicionAngular;
    private float longitudNatural, longitud;
    private float ancho;
    private float grosorLinea = 5f;
    private int color = Color.BLACK;

    /**
     * Resorte  de longitud 10f
     * ancho 20f y horizontal y
     * origen en (0,0)
     */

    public Resorte() {

        this.longitudNatural = 10f;
        this.longitud = longitudNatural;
        this.ancho = 20f;
    }


    /**
     * Modifica la longitud del objeto de laboratorio Resorte
     *
     * @param longitud
     */
    public void setLongitud(float longitud) {

        this.longitud = longitud;

    }

    /**
     * Devuelve la longitud del objeto de laboratorio Resorte
     *
     * @return longitud
     */
    public float getLongitud() {

        return longitud;
    }


    /**
     * Modifica la longitud natural del objeto de laboratorio Resorte
     *
     * @param longitudNatural
     */

    public void setLongitudNatural(float longitudNatural) {

        this.longitudNatural = longitudNatural;
        this.longitud = longitudNatural;

    }


    /**
     * Devuelve la longitud natural del objeto de laboratorio Resorte
     *
     * @return longitudNatural
     */

    public float getLongitudNatural() {

        return longitudNatural;
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
     * Modifica el ancho del objeto de laboratorio Resorte2D
     *
     * @param ancho
     */
    public void setAncho(float ancho) {

        this.ancho = ancho;

    }




    /**
     * Devuelve el ancho del objeto de laboratorio Resorte2D
     *
     * @return
     */

    public float getAncho() {

        return ancho;
    }



    /**
     * Genera rotación del resorte a la
     * posición angular posicionAngular
     * alrededor de eje que pasa por
     * (posicionX,posicionY)
     * @param posicionAngular
     */

    public void rotar(float posicionAngular){

        this.posicionAngular=posicionAngular;

    }

    public void dibujese(Canvas canvas, Paint pincel) {

        //valores del pincel para luego restaurarlo  como estaba
        float grosorInicialLinea = pincel.getStrokeWidth();

        //estado del canvas para luego restaurarlo a como estaba
        canvas.save();

        pincel.setStrokeWidth(grosorLinea);
        pincel.setColor(color);


        //traslada la resorte a la posición (posicionX, posicionY)
        canvas.translate(posicionX, posicionY);
        //rota la resorte alrededor de eje que pasa por (posicionX, posicionY)
        canvas.rotate(posicionAngular);


        float paso = longitud / 24f;

        pincel.setStyle(Paint.Style.STROKE);

        Path path = new Path();


        path.moveTo(0, 0);
        path.lineTo(0, 0);


        for (int i = 1; i < 24; i = i + 1) {

            path.lineTo(i * paso, (float) Math.pow(-1, i) * ancho);
        }

        path.lineTo(24 * paso, 0);
        canvas.drawPath(path, pincel);


        //restaurar pincel
        pincel.setStrokeWidth(grosorInicialLinea);
        pincel.setStyle(Paint.Style.FILL);

        //restaurar el canvas
        canvas.restore();

    }

}
