package com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class ObjetoEspacial implements Dibujable{
    /**
     * Método para que se dibujen los objetos Dibujables,
     * es decir, los objetos que implementanla interface Dibujable
     * @param canvas
     * @param pincel
     */
    public abstract void dibujese(Canvas canvas, Paint pincel);
}
