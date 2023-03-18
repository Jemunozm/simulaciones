package com.curso_simulaciones.mivigesimacuartaapp.controlador;

import com.curso_simulaciones.mivigesimacuartaapp.modelo.ModeloFisico;

public class HiloAnimacion extends Thread {


    public boolean pausa = true;
    private boolean corriendo;
    private long periodo_muestreo = 50;
    public float tiempo = 0;

    private ModeloFisico modelo = new ModeloFisico();

    private ActividadControladora actividad;


    public HiloAnimacion(ActividadControladora actividad) {

        this.actividad =actividad;

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

            if(pausa==false) {
                tiempo = tiempo + 0.05f;
                setCalculos(tiempo);
                actualizarFisica();
            }

        }


    }

    private void setCalculos(float tiempo){

        modelo.setEstadoSistema(tiempo);

    }


    private void actualizarFisica(){

        actividad.cambiarEstadosEscenaPizarra();

    }

}
