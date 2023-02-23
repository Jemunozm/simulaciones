package com.curso_simulaciones.minovenaapp.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Rueda {

    private float radio;
    private float posicionInicialX, posicionInicialY;
    private float posicionX, posicionY, posicionAngular;
    private int color, colorRuedaInterna, colorSimbolos = Color.rgb(255, 0, 0);//rojo


    /**
     * Constructor por defecto
     * Rueda centrada en (0,0),
     * con posición angular 0,
     * de color rojo
     * y de radio 20f
     */
    public Rueda() {
        this.radio = 20f;
    }

    /**
     * Constructor de Rueda centrada
     * en (posicionX,posicionY),
     * con posición angular cero
     * de color rojo
     * y diámetro igual a 2*radio
     */

    public Rueda(float posicionInicialX, float posicionInicialY, float radio) {
        this.posicionX = posicionInicialX;
        this.posicionY = posicionInicialY;

        this.posicionInicialX = posicionInicialX;
        this.posicionInicialY = posicionInicialY;

        this.radio = radio;
    }

    /**
     * Modifica el valor del radio de la Rueda
     *
     * @param radio
     */
    public void setRadioRueda(float radio) {
        this.radio = radio;
    }

    /**
     * Devuelve el valor del radio de la Rueda
     *
     * @return radio
     */
    public float getRadioRueda() {
        return radio;
    }


    /**
     * Modifica el color de la Rueda
     *
     * @param color
     */
    public void setColorRueda(int color, int colorRuedaInterna, int colorSimbolos) {
        this.color = color;
        this.colorRuedaInterna = colorRuedaInterna;
        this.colorSimbolos = colorSimbolos;

    }

    /**
     * Devuelve el color de la Rueda
     *
     * @return color
     */
    public int getColorRueda() {
        return color;
    }


    /**
     * Modifica la posición (x,y) de la Rueda
     *
     * @param desplazamientoX
     * @param desplazamientoY
     */
    public void moverRueda(float desplazamientoX, float desplazamientoY) {

        this.posicionX = this.posicionInicialX + desplazamientoX;
        this.posicionY = this.posicionInicialY + desplazamientoY;

    }


    /**
     * Modifica la posición angular en grados de la Rueda
     *
     * @param posicionAngular
     */
    public void moverRueda(float posicionAngular) {
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

    public void moverRueda(float desplazamientoX, float desplazamientoY, float posicionAngular) {
        this.posicionX = this.posicionInicialX + desplazamientoX;
        this.posicionY = this.posicionInicialY + desplazamientoY;

        this.posicionAngular = posicionAngular;
    }


    public void dibujese(Canvas canvas, Paint pincel) {

        //grosor del pincel
        pincel.setStrokeWidth(2f);
        //color del pincel
        pincel.setColor(color);

        canvas.save();
        //rotar
        canvas.rotate(posicionAngular, posicionX, posicionY);

        //dibujar circunferencia de la Rueda
        canvas.drawCircle(posicionX, posicionY, radio, pincel);

        //dibujar circuferencia de la rueda interna
        pincel.setColor(colorRuedaInterna);
        canvas.drawCircle(posicionX,posicionY,.84f*radio,pincel);

        //dibujar el patron interno
        pincel.setColor(colorSimbolos);
        canvas.drawCircle(posicionX,posicionY,.192f*radio,pincel);

        //hace una rotación igual a posicionAngular
        //dibujar las líenas radiales de la Rueda
        for (int i = 0; i < 4; i = i + 1) {
            //rotar de a 90 grados
            canvas.rotate(i * 90f, posicionX, posicionY);
            pincel.setStrokeWidth(6f);
            canvas.drawLine(posicionX, posicionY, posicionX, posicionY - (.4f*radio), pincel);
            canvas.drawCircle(posicionX,posicionY - (.4f*radio),.087f*radio,pincel);
            //retroceder la rotación
            canvas.rotate(-i * 90f, posicionX, posicionY);
        }

        //regresar la rotación
        canvas.restore();
    }
}

