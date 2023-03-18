package com.curso_simulaciones.mivigesimaseptimaapp.utilidades;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.curso_simulaciones.mivigesimaseptimaapp.datos.AlmacenDatosRAM;

public class Luxometro extends GaugeSimple implements SensorEventListener {

    private SensorManager sensorManager;

    public Luxometro(Context context){
        super(context);

        captarSensor(context);


    }


    private void captarSensor(Context context) {

        //captamos el servicio del sensor
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_FASTEST);

    }

    //se activa sólo cuando hay cambios
    public void onSensorChanged(SensorEvent event) {

        float medida = event.values[SensorManager.DATA_X];
        //para reportar con un decimal
        medida = (float)Math.floor(medida*100)/100f;
        this.setMedida(medida);
        cambiarEscala(medida);
        //almacenar dato actual
        AlmacenDatosRAM.datoActual=medida;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private void cambiarEscala(float medida){

        float maximo=100f;
        float minimo=0f;

        if(medida>0 && ((medida < 100)^(medida==100f ))){

            maximo=100f;
            minimo=0f;

        }

        if(medida>100 && ((medida < 200)^(medida==200f ))){

            maximo=200f;
            minimo=100f;

        }

        if(medida>200 && ((medida < 500)^(medida==500f ))){

            maximo=500f;
            minimo=200f;

        }

        if(medida>500 && ((medida < 1000)^(medida==1000f ))){

            maximo=1000f;
            minimo=500f;

        }


        if(medida>1000 && ((medida < 5000)^(medida==5000f ))){

            maximo=5000f;
            minimo=1000f;

        }

        if(medida>5000 && ((medida < 10000)^(medida==10000f ))){

            maximo=10000f;
            minimo=5000f;

        }

        if(medida>10000 && ((medida < 50000)^(medida==50000f ))){

            maximo=50000f;
            minimo=10000f;

        }


        this.setRango(minimo,maximo);

    }

}
