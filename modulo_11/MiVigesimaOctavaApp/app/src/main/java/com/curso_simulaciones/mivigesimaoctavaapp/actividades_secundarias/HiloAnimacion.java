package com.curso_simulaciones.mivigesimaoctavaapp.actividades_secundarias;

import com.curso_simulaciones.mivigesimaoctavaapp.datos.AlmacenDatosRAM;
import com.github.mikephil.charting.data.Entry;

public class HiloAnimacion extends Thread {


    public boolean corriendo;
    private long periodo_muestreo = 500;
    public float tiempo = 0;
    public int contador = 0;


    private ActividadDesplegadoraDatos actividad;


    public HiloAnimacion(ActividadDesplegadoraDatos actividad) {

        this.actividad =actividad;

    }




    @Override
    public void run() {
        corriendo = true;
        while (corriendo) {


            try {
                Thread.sleep(periodo_muestreo);

                //graficador.setDatos(AlmacenDatosRAM.datos);
                AlmacenDatosRAM.datos.add((new Entry(tiempo, AlmacenDatosRAM.datoActual)));
                actividad.graficador.setDatos(AlmacenDatosRAM.datos);

                tiempo = tiempo + 0.001f * periodo_muestreo;//en segundos

                //se desplegarán sólo 21 datos (podrían ser muchos más)
                if (contador > 20) {
                    corriendo = false;
                }

                //numero datos
                contador = contador + 1;

            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }


    }


}
