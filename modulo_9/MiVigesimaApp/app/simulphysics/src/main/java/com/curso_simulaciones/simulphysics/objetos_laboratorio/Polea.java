package com.curso_simulaciones.simulphysics.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Polea extends CuerpoRigido {


    protected float radio;
    private float anguloEje;
    private boolean soporte =false;


    /**
     * Constructor de Polea centrada
     * en (posicionX,posicionY)
     * y diámetro igual a 2*radio
     */

    public Polea(float posicionCentroMasaX, float posicionCentroMasaY, float radio) {
        super(posicionCentroMasaX, posicionCentroMasaY);
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


    public void setSoportePolea(boolean soporte){

        this.soporte=soporte;

    }


    /**
     * Modifica el ángulo del eje
     * @param anguloEje
     * @return
     */


    public void rotarEje(float anguloEje) {

        this.anguloEje = anguloEje;

    }


    //se implementó este método dque en su clase madre es abstracto
    public void dibujese(Canvas canvas, Paint pincel) {


        //valores del pincel para luego restaurarlo  como estaba
        //  float grosorInicialLinea=pincel.getStrokeWidth();
        //estado del canvas para luego restaurarlo a como estaba
        canvas.save();


        //estilo del pincel
        pincel.setStyle(Paint.Style.STROKE);
        //grosor del pincel
        pincel.setStrokeWidth(grosorLinea);

        //color del pincel
        pincel.setColor(color);
        //rotar
        canvas.rotate(posicionAngularRotacionEjeXY, posicionEjeRotacionX, posicionEjeRotacionY);
        //dibujar circunferencia de la polea
        canvas.drawCircle(posicionCentroMasaX, posicionCentroMasaY, radio, pincel);
        //dibujar las líenas radiales de la polea
        for (int i = 0; i < 12; i = i + 1) {
            //rotar de a 36 grados
            canvas.rotate(i * 36f, posicionCentroMasaX, posicionCentroMasaY);
            canvas.drawLine(posicionCentroMasaX, posicionCentroMasaY, posicionCentroMasaX, posicionCentroMasaY - radio, pincel);
            //retroceder la rotación
            canvas.rotate(-i * 36f, posicionCentroMasaX, posicionCentroMasaY);
        }//fin for





        //restaurar el canvas
        canvas.restore();


        //dibujar eje polea
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(0.5f*grosorLinea);
        if (soporte == false) {
            canvas.rotate(anguloEje, posicionCentroMasaX, posicionCentroMasaY);
            canvas.drawRect(posicionCentroMasaX - 0.1f * radio, posicionCentroMasaY - 1.2f * radio, posicionCentroMasaX + 0.1f * radio, posicionCentroMasaY + 1.2f * radio, pincel);
            canvas.rotate(-anguloEje, posicionCentroMasaX, posicionCentroMasaY);

        }

        if (soporte ==true) {
            canvas.rotate(anguloEje, posicionCentroMasaX, posicionCentroMasaY);
            canvas.drawRect(posicionCentroMasaX - 0.1f * radio, posicionCentroMasaY-1.2f*radio, posicionCentroMasaX+0.1f*radio, posicionCentroMasaY + 1.8f * radio, pincel);
            canvas.rotate(-anguloEje, posicionCentroMasaX, posicionCentroMasaY);

        }

        //restaurar el pincel
        pincel.reset();
    }


}
