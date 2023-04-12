package com.curso_simulaciones.mivigesimanovenaapp.utilidades;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.curso_simulaciones.mivigesimanovenaapp.datos.AlmacenDatosRAM;

public class Gaussimetro extends GaugeSimple implements SensorEventListener {

    private SensorManager sensorManager;
    private int componenteMagnetica = 1;
    private float maximoRango;

    public Gaussimetro(Context context) {
        super(context);
        this.setRango(-20, 20);

        captarSensor(context);
    }

    public void setComponenteGaussimetro(int componenteMagnetica) {

        this.componenteMagnetica = componenteMagnetica;

    }

    private void captarSensor(Context context) {

        Sensor sensor = null;
        //captamos el servicio del sensor
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_FASTEST);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        maximoRango = sensor.getMaximumRange();

    }


    public void onSensorChanged(SensorEvent event) {

        //en x
        float a = 0;
        float medida_x = 0;
        float medida_y = 0;
        float medida_z = 0;
        float medida = 0;

        medida_x = event.values[SensorManager.DATA_X];
        medida_y = event.values[SensorManager.DATA_Y];
        medida_z = event.values[SensorManager.DATA_Z];
        float resultado = medida_x * medida_x + medida_y * medida_y + medida_z * medida_z;
        a = (float) (Math.sqrt(resultado));


        if (componenteMagnetica == 1) {
            medida = medida_x;
            this.setUnidades(" CampoX (μT)");
        }
        if (componenteMagnetica == 2) {
            medida = medida_y;
            this.setUnidades(" CampoY (μT)");
        }
        if (componenteMagnetica == 3) {
            medida = medida_z;
            this.setUnidades(" CampoZ (μT)");
        }
        if (componenteMagnetica == 4) {
            medida = a;
            this.setUnidades(" Campo (μT)");
        }

        //un decimal
        medida = (float) (Math.round(medida * 100) / 100.0f);
        this.setMedida(medida);
        cambiarEscala(medida);
        //almacenar dato actual
        AlmacenDatosRAM.datoActual = medida;

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void cambiarEscala(float medida) {

        float maximo = 100f;
        float minimo = 0f;

        if (medida >= -maximoRango && (medida < -500f)) {
            maximo = -500f;
            minimo = -maximoRango;
        }

        if (medida >= -500f && (medida < -50f)) {

            maximo = -50f;
            minimo = -600f;

        }

        if (medida >= -50f && (medida <= 0)) {

            maximo = 0f;
            minimo = -50f;

        }


        if (medida > 0 && (medida <= 30)) {

            maximo = 30f;
            minimo = 0f;

        }

        if (medida > 30 && (medida <= 60)) {

            maximo = 60f;
            minimo = 30f;

        }

        if (medida > 60 && (medida <= 100)) {

            maximo = 100f;
            minimo = 60f;

        }


        if (medida > 100 && (medida <= 500)) {

            maximo = 500f;
            minimo = 100f;

        }

        if (medida > 500 && (medida <= 1000)) {

            maximo = 1000f;
            minimo = 500f;

        }

        if (medida > 10000 && (medida <= maximoRango)) {

            maximo = maximoRango;
            minimo = 1000f;
        }

        this.setRango(minimo, maximo);
    }
}
