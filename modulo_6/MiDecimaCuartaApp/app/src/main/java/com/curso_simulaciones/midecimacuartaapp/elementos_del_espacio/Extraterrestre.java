package com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Extraterrestre extends ObjetoEspacial {
    protected float magnificacion = 1;
    protected float posicionInicialCentroideX, posicionInicialCentroideY;
    protected float posicionCentroideX, posicionCentroideY;
    protected float posicionEjeRotacionX, posicionEjeRotacionY;
    protected float posicionAngularRotacionEjeXY;
    protected int color = Color.RED;

    /**
     * Constructor por defecto del extraterrestre.
     * Su centro de masa está ubicado en la
     * posición (0,0). La posición angular es cero
     * y su color es rojo
     */
    public Extraterrestre() {
    }

    /**
     * Constructor de extraterrestre
     * cuyo centro de masa está
     * ubicado en (posicionInicialCentroideX,posicionInicialCentroideY)
     */
    public Extraterrestre(float posicionInicialCentroideX, float
            posicionInicialCentroideY) {
        this.posicionCentroideX = posicionInicialCentroideX;
        this.posicionCentroideY = posicionInicialCentroideY;
        this.posicionInicialCentroideX = posicionInicialCentroideX;
        this.posicionInicialCentroideY = posicionInicialCentroideY;
    }

    /**
     * Modifica el color del extraterrestre
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Devuelve el color del extraterrestre
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
        return posicionCentroideX;
    }

    /**
     * 71
     * <p>
     * Retorna posicion en Y
     * del centro de masa
     *
     * @return
     */
    public float getPosicionY() {
        return posicionCentroideY;
    }

    /**
     * Modifica la posición (posicionCentroideX,posicionCentroideY)
     * del centro de masa del extraterrestre
     *
     * @param desplazamientoCentroideX
     * @param desplazamientoCentroideY
     */
    public void mover(float desplazamientoCentroideX, float desplazamientoCentroideY) {
        this.posicionCentroideX = this.posicionInicialCentroideX +
                desplazamientoCentroideX;
        this.posicionCentroideY = this.posicionInicialCentroideY +
                desplazamientoCentroideY;
    }

    /**
     * Modifica la posición angular del extraterrestre
     * alrededor de une eje que pasa por su centro
     * de masa
     *
     * @param posicionAngular
     */
    public void mover(float posicionAngular) {
        this.posicionEjeRotacionX = this.posicionInicialCentroideX;
        this.posicionEjeRotacionY = this.posicionInicialCentroideY;
        this.posicionAngularRotacionEjeXY = posicionAngular;
    }

    /**
     * Modifica la posición (posicionCentroideX,posicionCentroideY)
     * del centro de masa del extraterrestre y genera una rotación
     * alrededor del eje que pasa por éste
     *
     * @param desplazamientoCentroideX
     * @param desplazamientoCentroideY
     * @param posicionAngular
     */

    public void mover(float desplazamientoCentroideX, float desplazamientoCentroideY,
                      float posicionAngular) {
        this.posicionCentroideX = this.posicionInicialCentroideX +
                desplazamientoCentroideX;
        this.posicionCentroideY = this.posicionInicialCentroideY +
                desplazamientoCentroideY;
        this.posicionEjeRotacionX = this.posicionCentroideX;
        this.posicionEjeRotacionY = this.posicionCentroideY;
        this.posicionAngularRotacionEjeXY = posicionAngular;
    }

    /**
     * Genera rotación del extraterrestre a la
     * posición angular posicionAngularRotacionEjeXY
     * alrededor de eje que pasa por
     * (posicionEjeRotacionX,posicionEjeRotacionY)
     *
     * @param posicionEjeRotacionX
     * @param posicionEjeRotacionY
     * @param posicionAngular
     */
    public void rotar(float posicionEjeRotacionX, float posicionEjeRotacionY, float
            posicionAngular) {
        this.posicionEjeRotacionX = posicionEjeRotacionX;
        this.posicionEjeRotacionY = posicionEjeRotacionY;
        this.posicionAngularRotacionEjeXY = posicionAngular;
    }

    /**
     * Genera magnificacion del extraterrestre
     * con base en el centroide
     *
     * @param magnificacion
     */
    public void setMagnificar(float magnificacion) {
        this.magnificacion = magnificacion;
    }

    public abstract void dibujese(Canvas canvas, Paint pincel);
}
