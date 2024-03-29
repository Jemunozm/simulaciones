package com.curso_simulaciones.mitrigesimacuartaapp.utilidades;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.curso_simulaciones.mitrigesimacuartaapp.datos.AlmacenDatosRAM;

public class Acelerometro extends GaugeSimple implements SensorEventListener {

    private SensorManager sensorManager;
    private int componenteAceleracion = 1;

    public Acelerometro(Context context) {
        super(context);

        captarSensor(context);

        this.setRango(-20, 20);


    }


    public void setComponenteAcelerometro(int componenteAceleracion) {

        this.componenteAceleracion = componenteAceleracion;

    }

    private void captarSensor(Context context) {

        //captamos el servicio del sensor
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);

    }

    //se activa sólo cuando hay cambios
    public void onSensorChanged(SensorEvent event) {

        //en x
        float a = 0;
        float medida_x = 0;
        float medida_y = 0;
        float medida_z = 0;
//        float medida = 0;

        medida_x = event.values[SensorManager.DATA_X];
        medida_y = event.values[SensorManager.DATA_Y];
        medida_z = event.values[SensorManager.DATA_Z];
        float resultado = medida_x * medida_x + medida_y * medida_y + medida_z * medida_z;
        a = (float) (Math.sqrt(resultado));

/*
        if (componenteAceleracion == 1) {
            medida = medida_x;
            this.setUnidades(" ax (m/S2)");
        }
        if (componenteAceleracion == 2){
            medida = medida_y;
            this.setUnidades(" ay (m/S2)");
        }
        if (componenteAceleracion == 3){
            medida = medida_z;
            this.setUnidades(" az (m/S2)");
        }
        if (componenteAceleracion == 4){
            medida = a;
            this.setUnidades(" a (m/S2)");
        }*/

        //un decimal
        medida_x = (float) (Math.round(medida_x * 10) / 10.0f);
        AlmacenDatosRAM.datoActual1 = medida_x;

        medida_y = (float) (Math.round(medida_y * 10) / 10.0f);
        AlmacenDatosRAM.datoActual2 = medida_y;


        medida_z = (float) (Math.round(medida_z * 10) / 10.0f);
        AlmacenDatosRAM.datoActual3 = medida_z;


        a = (float) (Math.round(a * 10) / 10.0f);
        //almacenar dato actual
        this.setMedida(a);
        this.setUnidades("a (m/s^2)");
        AlmacenDatosRAM.datoActual = a;




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
