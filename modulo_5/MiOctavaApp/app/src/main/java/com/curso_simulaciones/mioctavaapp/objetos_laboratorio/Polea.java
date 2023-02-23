package com.curso_simulaciones.mioctavaapp.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Polea {

    private float radio;
    private float posicionInicialX, posicionInicialY;
    private float posicionX, posicionY, posicionAngular;
    private int color = Color.rgb(255, 0, 0);//rojo


    /**
     * Constructor por defecto
     * Polea centrada en (0,0),
     * con posición angular 0,
     * de color rojo
     * y de radio 20f
     */
    public Polea() {
        this.radio = 20f;
    }

    /**
     * Constructor de Polea centrada
     * en (posicionX,posicionY),
     * con posición angular cero
     * de color rojo
     * y diámetro igual a 2*radio
     */

    public Polea(float posicionInicialX, float posicionInicialY, float radio) {
        this.posicionX = posicionInicialX;
        this.posicionY = posicionInicialY;

        this.posicionInicialX = posicionInicialX;
        this.posicionInicialY = posicionInicialY;

        this.radio = radio;
    }

    /**
     * Modifica el valor del radio de la polea
     *
     * @param radio
     */
    public void setRadioPolea(float radio) {
        this.radio = radio;
    }

    /**
     * Devuelve el valor del radio de la polea
     *
     * @return radio
     */
    public float getRadioPolea() {
        return radio;
    }


    /**
     * Modifica el color de la polea
     *
     * @param color
     */
    public void setColorPolea(int color) {
        this.color = color;
    }

    /**
     * Devuelve el color de la polea
     *
     * @return color
     */
    public int getColorPolea() {
        return color;
    }


    /**
     * Modifica la posición (x,y) de la polea
     *
     * @param desplazamientoX
     * @param desplazamientoY
     */
    public void moverPolea(float desplazamientoX, float desplazamientoY) {

        this.posicionX = this.posicionInicialX + desplazamientoX;
        this.posicionY = this.posicionInicialY + desplazamientoY;

    }


    /**
     * Modifica la posición angular en grados de la polea
     *
     * @param posicionAngular
     */
    public void moverPolea(float posicionAngular) {
        this.posicionX = this.posicionInicialX;
        this.posicionY = this.posicionInicialY;

        this.posicionAngular = posicionAngular;
    }

    /**
     * Modifica la posición (x,y) y genera
     * una rotación alrededor del eje que pasa por (x,y)
     *
     * @param desplazamientoX
     * @param desplazamientoY
     * @param posicionAngular
     */

    public void moverPolea(float desplazamientoX, float desplazamientoY, float posicionAngular) {
        this.posicionX = this.posicionInicialX + desplazamientoX;
        this.posicionY = this.posicionInicialY + desplazamientoY;

        this.posicionAngular = posicionAngular;
    }


    public void dibujese(Canvas canvas, Paint pincel) {

        //estilo del pindel
        pincel.setStyle(Paint.Style.STROKE);
        //grosor del pincel
        pincel.setStrokeWidth(2f);
        //color del pincel
        pincel.setColor(color);

        canvas.save();
        //rotar
        canvas.rotate(posicionAngular, posicionX, posicionY);


        //dibujar circunferencia de la polea
        canvas.drawCircle(posicionX, posicionY, radio, pincel);

        //hace una rotación igual a posicionAngular

        //dibujar las líenas radiales de la polea
        for (int i = 0; i < 12; i = i + 1) {

            //rotar de a 36 grados
            canvas.rotate(i * 36f, posicionX, posicionY);
            canvas.drawLine(posicionX, posicionY, posicionX, posicionY - radio, pincel);
            //retroceder la rotación
            canvas.rotate(-i * 36f, posicionX, posicionY);
        }

        //regresar la rotación
        canvas.restore();
    }
}

