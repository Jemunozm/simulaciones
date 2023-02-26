package com.curso_simulaciones.midecimaterceraapp.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Polea extends CuerpoRigido {
    protected float radio;

    /**
     * Constructor por defecto
     * Polea centrada en (0,0)
     * y de radio 50f
     */
    public Polea() {
        super();
        this.radio = 50f;
    }


    /**
     * Constructor de Polea centrada
     * en (posicionX,posicionY)
     * y diámetro igual a 2*radio
     */

    public Polea(float posicionInicialCentroMasaX, float posicionInicialCentroMasaY, float radio) {
        super(posicionInicialCentroMasaX, posicionInicialCentroMasaY);
        this.radio = radio;
    }

    /**
     * Modifica el valor del radio de la polea
     *
     * @param radio
     */
    public void setRadio(float radio) {
        this.radio = radio;
    }


    /**
     * Devuelve el valor del radio de la polea
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
        //dibujar circunferencia de la polea
        canvas.drawCircle(posicionCentroMasaX, posicionCentroMasaY, radio, pincel);
        //dibujar las líneas radiales de la polea
        for (int i = 0; i < 12; i = i + 1) {
            //rotar de a 36 grados
            canvas.rotate(i * 36f, posicionCentroMasaX, posicionCentroMasaY);
            canvas.drawLine(posicionCentroMasaX, posicionCentroMasaY, posicionCentroMasaX, posicionCentroMasaY - radio, pincel);
            //retroceder la rotación
            canvas.rotate(-i * 36f, posicionCentroMasaX, posicionCentroMasaY);
        }//fin for


        //regresar la rotación
        canvas.restore();
    }

}
