package com.curso_simulaciones.midecimasegundaapp.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Rueda extends CuerpoRigido {
    protected float radio;


    /**
     * Constructor por defecto
     * Rueda centrada en (0,0)
     * y de radio 50f
     */
    public Rueda() {
        super();
        this.radio = 50f;
    }


    /**
     * Constructor de Rueda centrada
     * en (posicionCentroMasaX,posicionCentroMasaY)
     * y diámetro igual a 2*radio
     */

    public Rueda(float posicionInicialCentroMasaX, float posicionInicialCentroMasaY, float radio) {
        super(posicionInicialCentroMasaX, posicionInicialCentroMasaY);
        this.radio = radio;

    }

    /**
     * Modifica el valor del radio de la rueda
     *
     * @param radio
     */
    public void setRadio(float radio) {
        this.radio = radio;
    }


    /**
     * Devuelve el valor del radio de la rueda
     *
     * @return
     */
    public float getRadio() {
        return radio;
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
        canvas.rotate(posicionAngularRotacionEjeXY, posicionEjeRotacionX, posicionEjeRotacionY);
        //dibujar círculo de la rueda
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(posicionCentroMasaX, posicionCentroMasaY, radio, pincel);
        pincel.setColor(Color.BLACK);
        //dibujar punticos negro para poder bservar la rotación de la rueda

        for (int i = 0; i < 6; i = i + 1) {
            //rotar de a 36 grados
            canvas.rotate(i * 60f, posicionCentroMasaX, posicionCentroMasaY);
            canvas.drawCircle(posicionCentroMasaX - 0.2f * radio, posicionCentroMasaY - 0.3f * radio, 0.1f * radio, pincel);
            canvas.rotate(-i * 60f, posicionCentroMasaX, posicionCentroMasaY);
        }

        //dibujar circunferencia de la rueda
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(posicionCentroMasaX, posicionCentroMasaY, radio, pincel);

        //regresa la rotación
        canvas.restore();

    }
}
