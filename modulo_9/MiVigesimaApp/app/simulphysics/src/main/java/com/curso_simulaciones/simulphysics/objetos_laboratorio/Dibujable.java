package com.curso_simulaciones.simulphysics.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Dibujable {

    /**
     * Método  que se debe implementar para que
     * los objetos se dibujen
     * @param canvas
     * @param pincel
     */
    public void dibujese(Canvas canvas, Paint pincel);

}

