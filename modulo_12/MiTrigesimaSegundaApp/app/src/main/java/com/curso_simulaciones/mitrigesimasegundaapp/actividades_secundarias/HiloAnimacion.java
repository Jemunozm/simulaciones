package com.curso_simulaciones.mitrigesimasegundaapp.actividades_secundarias;

import com.curso_simulaciones.mitrigesimasegundaapp.datos.AlmacenDatosRAM;

public class HiloAnimacion extends Thread {


    public boolean corriendo;
    private long periodo_muestreo = 500;
    public float tiempo = 0;
    private int contador = -1;


    private ActividadDesplegadoraDatos actividad;


    public HiloAnimacion(ActividadDesplegadoraDatos actividad) {

        this.actividad = actividad;

        periodo_muestreo =AlmacenDatosRAM.periodoMuestreo;

    }


    @Override
    public void run() {
        corriendo = true;
        while (corriendo) {


            try {
                Thread.sleep(periodo_muestreo);

            } catch (InterruptedException e) {
                e.printStackTrace();

            }

            tiempo = tiempo + periodo_muestreo;//en segundos
            AlmacenDatosRAM.tiempo =tiempo;


            contador = contador + 1;

            if (contador == AlmacenDatosRAM.nDatos) {

                actividad.activarBotones = true;
                detener();

            }

            actividad.hacerTrabajoDuro();

        }

    }


    //parar la animación
    private void detener() {

        corriendo = false;

    }
}
