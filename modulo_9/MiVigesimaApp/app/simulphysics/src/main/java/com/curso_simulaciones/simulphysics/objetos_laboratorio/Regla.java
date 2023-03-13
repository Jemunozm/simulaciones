package com.curso_simulaciones.simulphysics.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Regla extends ObjetoLaboratorio {


    private float esquinaIzquierdaX, esquinaIzquierdaY,largo, alto;
    private int colorLetras= Color.BLACK;
    private int numeroDivisiones=50;


    /**
     * Cosntrutor de regla cuya esquina superior izquierda
     * se encuentra en (esquinaIzquierdaX,esquinaIzquierdaY)
     * y con largo y ancho
     * @param esquinaIzquierdaX
     * @param esquinaIzquierdaY
     * @param largo
     * @param alto
     */
    public Regla(float esquinaIzquierdaX,float esquinaIzquierdaY, float largo, float alto){

        this.esquinaIzquierdaX=esquinaIzquierdaX;
        this.esquinaIzquierdaY=esquinaIzquierdaY;
        this.largo=largo;
        this.alto=alto;

    }


    public void setNumeroDivisiones(int numeroDivisiones){

        this.numeroDivisiones=numeroDivisiones;

    }
    public void setColorLetras(int colorLetras){

        this.colorLetras=colorLetras;

    }

    /**
     * Rota la regla alrededor de eje que pasa por
     * la esquina superior izquierda un ángulo igual
     * a posicionAngular
     * @param posicionAngular
     *
     */
    public void rotar(float posicionAngular){

        this.posicionAngular=posicionAngular;


    }


    /**
     * Cambia la posición de la regla
     * @param esquinaIzquierdaX
     * @param esquinaIzquierdaY
     */

    public void setPosicionRegla(float esquinaIzquierdaX, float esquinaIzquierdaY){

        this.esquinaIzquierdaX=esquinaIzquierdaX;
        this.esquinaIzquierdaY=esquinaIzquierdaY;

    }

    //implementa el método de la Interface Dibujable
    public void dibujese(Canvas canvas, Paint pincel) {


        //estado del canvas para luego restaurarlo a como estaba
        canvas.save();

        //traslada la cadena a la posición (posicionX, posicionY)
        canvas.translate(esquinaIzquierdaX, esquinaIzquierdaY);
        //rota la cadena alrededor de eje que pasa por (pX, pY)
        canvas.rotate(posicionAngular, 0, 0);

        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(color);
        canvas.drawRect(0, 0, largo, alto, pincel);
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, largo, alto, pincel);

        pincel.setStrokeWidth(2);

        //int numeroDivisiones=30;
        float paso=(float)((largo-0.1*largo)/numeroDivisiones);
        float inicio= (float)(0.05*largo);

        for(int i=0;i<numeroDivisiones+1;i++){
            pincel.setColor(Color.BLACK);
            canvas.drawLine(inicio+paso*i,0,inicio+paso*i,(float)(0.25*alto),pincel);
            //cada 5 divisiones una divisón larga

            if(i%5==0){
                pincel.setColor(Color.RED);
                canvas.drawLine(inicio+paso*i,0,inicio+paso*i,(float)(0.5*alto),pincel);
                //marcar
                pincel.setTextSize(0.2f*alto);
                pincel.setStyle(Paint.Style.FILL);
                pincel.setColor(colorLetras);
                canvas.drawText(""+i,inicio+paso*i-0.01f*largo,0.75f*alto,pincel);
            }



        }

        //restaurar el canvas
        canvas.restore();
        //restaurar el pincel
        pincel.reset();


    }

}
