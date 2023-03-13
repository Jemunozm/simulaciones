package com.curso_simulaciones.simulphysics.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Resorte extends ObjetoLaboratorio {

    private float longitudNatural, longitud;
    private float ancho;
    private int numeroEspiras=12;


    /**
     * Constructor de resorte que comienza en
     * (posicionX,posicionY) de longitudNatural
     * y ancho
     * @param posicionX
     * @param posicionY
     * @param longitudNatural
     * @param ancho
     */

    public Resorte(float posicionX, float posicionY,float longitudNatural,float ancho) {

        this.posicionX=posicionX;
        this.posicionY=posicionY;
        this.longitud = longitudNatural;
        this.ancho = ancho;

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


    public void setNumeroEspiras(int numeroEspiras){

        this.numeroEspiras=numeroEspiras+1;
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


        //estado del canvas para luego restaurarlo a como estaba
        canvas.save();

        pincel.setStrokeWidth(grosorLinea);
        pincel.setColor(color);


        //traslada la resorte a la posición (posicionX, posicionY)
        canvas.translate(posicionX, posicionY);
        //rota la resorte alrededor de eje que pasa por (posicionX, posicionY)
        canvas.rotate(posicionAngular);//,posicionX,posicionY);


        float paso = longitud / numeroEspiras;

        pincel.setStyle(Paint.Style.STROKE);

        Path path = new Path();


        path.moveTo(0, 0);
        path.lineTo(0, 0);


        for (int i = 1; i < numeroEspiras; i = i + 1) {

            path.lineTo(i * paso, (float) Math.pow(-1, i) * ancho);
        }

        path.lineTo(numeroEspiras * paso, 0);
        canvas.drawPath(path, pincel);


        //restaurar el canvas
        canvas.restore();
        //restaurar el pincel
        pincel.reset();

    }


}
