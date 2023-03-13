package com.curso_simulaciones.simulphysics.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Marca extends ObjetoLaboratorio {

    private String marca="";
    private float tamano=12;


    /**
     * Constructor de una cadena en posición
     * (posicionX,posicionY)
     * @param marca
     * @param posicionX
     * @param posicionY
     */

    public Marca(String marca, float posicionX,float posicionY){
        this.marca=marca;
        this.posicionX=posicionX;
        this.posicionY=posicionY;


    }

    /**
     * Cambia la cadena marca
     * @param marca
     */
    public void setMarca(String marca){

        this.marca=marca;

    }

    public void setTamano(float tamano){

        this.tamano=tamano;

    }
    /**
     * Genera rotación de la cadena
     * @param posicionAngular
     */
    public void rotar(float posicionAngular){

        this.posicionAngular=posicionAngular;


    }


    //implementa el método de la Interface Dibujable
    public void dibujese(Canvas canvas, Paint pincel) {


        //estado del canvas para luego restaurarlo a como estaba
        canvas.save();


        pincel.setColor(color);

        pincel.setStyle(Paint.Style.FILL);
        pincel.setTextSize(tamano);

        //traslada la cadena a la posición (posicionX, posicionY)
        canvas.translate(posicionX, posicionY);
        //rota la cadena
        canvas.rotate(posicionAngular);
        canvas.drawText(marca,0,0,pincel);

        //restaurar el canvas
        canvas.restore();
        //restaurar el pincel
        pincel.reset();


    }

}
