package com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CuerpoRectangular extends CuerpoRigido {
    private float largo, alto;

    /**
     * Constructor por defecto
     * Bloque centrado en (0,0)
     * de largo 150f y de alto 100f
     */
    public CuerpoRectangular() {
        this.largo = 150f;
        this.alto = 100f;
    }


    /**
     * Constructor de cuerpo rectangular
     * centrado en (posicionCentroMasaX,posicionCentroMasaY)
     * de dimensiones largoxalto
     */

    public CuerpoRectangular(float posicionInicialCentroMasaX, float posicionInicialCentroMasaY, float largo, float alto) {
        super(posicionInicialCentroMasaX, posicionInicialCentroMasaY);
        this.largo = largo;
        this.alto = alto;
    }


    /**
     * Modifica el valor del largo del cuerpo
     * rectangular     *
     *
     * @param largo
     */
    public void setLargo(float largo) {
        this.largo = largo;
    }


    /**
     * Devuelve el valor del largo del cuerpo
     * rectangular
     *
     * @return
     */
    public float getLargo() {
        return largo;
    }

    /**
     * Modifica el valor del ancho del cuerpo
     * rectangular
     *
     * @param alto
     */
    public void setAlto(float alto) {
        this.alto = alto;
    }


    /**
     * Devuelve el valor del alto del cuerpo
     * rectangular
     *
     * @return
     */
    public float getAlto() {
        return alto;
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
        //dibujar el relleno del bloque
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawRect(posicionCentroMasaX - 0.5f * largo, posicionCentroMasaY - 0.5f * alto, posicionCentroMasaX + 0.5f * largo, posicionCentroMasaY + 0.5f * alto, pincel);
        //dibujar el perímetro del bloque
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawRect(posicionCentroMasaX - 0.5f * largo, posicionCentroMasaY - 0.5f * alto, posicionCentroMasaX + 0.5f * largo, posicionCentroMasaY + 0.5f * alto, pincel);
        //regresar la rotación
        canvas.restore();

    }

}
