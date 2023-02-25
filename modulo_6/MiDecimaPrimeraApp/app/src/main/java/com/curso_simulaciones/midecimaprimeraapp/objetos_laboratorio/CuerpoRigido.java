package com.curso_simulaciones.midecimaprimeraapp.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CuerpoRigido {

    protected float posicionInicialCentroMasaX, posicionInicialCentroMasaY;
    protected float posicionCentroMasaX, posicionCentroMasaY;
    protected float posicionEjeRotacionX, posicionEjeRotacionY;
    protected float posicionAngularRotacionEjeXY;
    protected int color = Color.RED;

    /**
     * Constructor por defecto del cuerpo rígido.
     * Su centro de masa está ubicado en la
     * posición (0,0). La posición angular es cero
     * y su color es rojo
     */
    public CuerpoRigido() {

    }

    /**
     * Constructor de CuerpoRigido
     * cuyo centro de masa está
     * ubicado en (posicionInicialX,posicionInicialX)
     */

    public CuerpoRigido(float posicionInicialCentroMasaX, float posicionInicialCentroMasaY) {
        this.posicionCentroMasaX = posicionInicialCentroMasaX;
        this.posicionCentroMasaY = posicionInicialCentroMasaY;

        this.posicionInicialCentroMasaX = posicionInicialCentroMasaX;
        this.posicionInicialCentroMasaY = posicionInicialCentroMasaY;
    }


    /**
     * Modifica el color del cuerpo rígido
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Devuelve el color del cuerpo rígido
     *
     * @return
     */
    public int getColor() {
        return color;
    }


   /*
     Se podría hacer un método setPosicion(x,y)
     para cambiar la posición del centro de masa, pero más adelante
     esta el método mover(x,y) que hace esto
    */

    /**
     * Retorna la posicion en X
     * del centro de masa
     *
     * @return
     */

    public float getPosicionX() {

        return posicionCentroMasaX;
    }


    /**
     * Retorna posicion en Y
     * del centro de masa
     *
     * @return
     */
    public float getPosicionY() {

        return posicionCentroMasaY;
    }


    /**
     * Modifica la posición (posicionCentroMasaX,posicionCentroMasaY)
     * del centro de masa del cuerpo rígido
     *
     * @param desplazamientoCentroMasaX
     * @param desplazamientoCentroMasaY
     */
    public void mover(float desplazamientoCentroMasaX, float desplazamientoCentroMasaY) {

        this.posicionCentroMasaX = this.posicionInicialCentroMasaX + desplazamientoCentroMasaX;
        this.posicionCentroMasaY = this.posicionInicialCentroMasaY + desplazamientoCentroMasaY;

    }


    /**
     * Modifica la posición angular del cuerpo rígido
     * alrededor de une eje que pasa por su centro
     * de masa
     *
     * @param posicionAngular
     */
    public void mover(float posicionAngular) {
        this.posicionEjeRotacionX = this.posicionInicialCentroMasaX;
        this.posicionEjeRotacionY = this.posicionInicialCentroMasaY;

        this.posicionAngularRotacionEjeXY = posicionAngular;
    }

    /**
     * Modifica la posición (posicionCentroMasaX,posicionCentroMasaY)
     * del centro de masa del cuerpo rígido y genera una rotación
     * alrededor del eje que pasa por éste
     *
     * @param desplazamientoCentroMasaX
     * @param desplazamientoCentroMasaY
     * @param posicionAngular
     */

    public void mover(float desplazamientoCentroMasaX, float desplazamientoCentroMasaY, float posicionAngular) {
        this.posicionCentroMasaX = this.posicionInicialCentroMasaX + desplazamientoCentroMasaX;
        this.posicionCentroMasaY = this.posicionInicialCentroMasaY + desplazamientoCentroMasaY;

        this.posicionEjeRotacionX = this.posicionCentroMasaX;
        this.posicionEjeRotacionY = this.posicionCentroMasaY;

        this.posicionAngularRotacionEjeXY = posicionAngular;
    }


    /**
     * Genera rotación del cuerpo rígido a la
     * posición angular posicionAngularRotacionEjeXY
     * alrededor de eje que pasa por
     * (posicionEjeRotacionX,posicionEjeRotacionY)
     *
     * @param posicionEjeRotacionX
     * @param posicionEjeRotacionY
     * @param posicionAngular
     */

    public void rotar(float posicionEjeRotacionX, float posicionEjeRotacionY, float posicionAngular) {

        this.posicionEjeRotacionX = posicionEjeRotacionX;
        this.posicionEjeRotacionY = posicionEjeRotacionY;

        this.posicionAngularRotacionEjeXY = posicionAngular;

    }
    public void dibujese(Canvas canvas, Paint pincel) {

        canvas.save();
        //rotar
        canvas.rotate(posicionAngularRotacionEjeXY, posicionEjeRotacionX, posicionEjeRotacionY);

        //dibujar un circulo de radio 40
        pincel.setColor(color);
        canvas.drawCircle(posicionCentroMasaX, posicionCentroMasaY, 40, pincel);
        pincel.setColor(Color.BLACK);
        pincel.setTextSize(40);
        canvas.drawText("R", posicionCentroMasaX - 10, posicionCentroMasaY + 15, pincel);

        //regresar la rotación
        canvas.restore();

    }


}
