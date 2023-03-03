package com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Dibujable {

    /**
     * método que deben implementar las clases cuyos objetos
     * serán dibujables en Pizarra que herede de View.
     * @param canvas
     * @param pincel
     */

    void dibujese(Canvas canvas, Paint pincel);

}
