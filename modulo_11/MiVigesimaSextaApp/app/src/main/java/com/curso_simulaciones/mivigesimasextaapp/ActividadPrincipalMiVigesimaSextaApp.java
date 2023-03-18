package com.curso_simulaciones.mivigesimasextaapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActividadPrincipalMiVigesimaSextaApp extends Activity {

    private TextView textView;

    //variable tamaño de las letras basado en resolución de pantalla
    private int tamanoLetraResolucionIncluida;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //gestionar la resolución
        gestionarResolucion();

        //para crear elementos de la GUI
        crearElementosGUI();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametroLayoutPrincipal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametroLayoutPrincipal);




        detectarSensores();


    }//fin onCreate



    /*Método auxiliar para asuntos de resolución*/
    private void gestionarResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;

        /*
         Tamaño de las letras con independencia de
         tamaño y resolución de pantalla
        */
        int dimensionReferencia;

        //tomar el menor entre alto y ancho de pantalla
        if(alto>ancho){

            dimensionReferencia=ancho;
        }

        else {

            dimensionReferencia=alto;
        }


        //una estimación de un buen tamaño
        int tamanoLetra= dimensionReferencia / 25;

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);

    }


    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {

        textView= new TextView(this);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        //darle la propiedad de tener el scrolling
        textView.setMovementMethod(new ScrollingMovementMethod());


    }//fin crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        //el linear principal
        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setBackgroundColor(Color.BLACK);


        linearPrincipal.addView(textView);


        return linearPrincipal;


    }//fin gui

    /*
      Los sensores más importantes de dispositivos android son:
      Aceleración (TYPE_ACCELEROMETER)
      Temperatura ambiente (TYPE_AMBIENT_TEMPERATURE)
      Gravedad (TYPE_GRAVITY)
      Giróscopo (TYPE_GYROSCOPE)
      Iluminacia (TYPE_LIGHT)
      Aceleración Lineal (TYPE_LINEAR_ACCELERATION)
      Campo magnético (TYPE_MAGNETIC_FIELD)
      Orientación (TYPE_ORIENTATION)
      prsión atmosférica (TYPE_PRESSURE)
      Proximidad (TYPE_PROXIMITY)
      Humedad Relativa (TYPE_RELATIVE_HUMIDITY)
      Temperaura del dispositivo (TYPE_TEMPERATURE)
     */

    /*
      Se detectará la presencia de los sensores anteriores
      e información básica acerca de ellos
     */

    public void detectarSensores() {

        //Instancia de SensorManger
        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        Sensor sensor=null;

        //Lista con la información de los sensores
        // List<Sensor> listSensor  = sensorManager.getSensorList(Sensor.TYPE_ALL);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            setInformacioSensor(sensor);
        }


        if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            setInformacioSensor(sensor);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE) !=null) {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
            setInformacioSensor(sensor);
        }


    }


    private void setInformacioSensor(Sensor sensor){

        String unidades=" "+getUnidades(sensor.getType());
        textView.append("\n" + " Nombre:" +(sensor.getName()));
        textView.append("\n" + " Corriente: "+ sensor.getPower()+ " mA") ;
        textView.append("\n" + " Máximo delay: "+sensor.getMaxDelay()+ " μs");
        textView.append("\n" + " Mínimo delay: "+ sensor.getMinDelay()+ " μs");
        textView.append("\n" + " Máximo rango: " +sensor.getMaximumRange()+ unidades);
        textView.append("\n" + " Resolución: "+sensor.getResolution()+ unidades);
        textView.append("\n" + " Vendedor: "+ sensor.getVendor());
        textView.append("\n" + " Versión: "+sensor.getVersion());
        textView.append("\n" + " ");


    }

    private String getUnidades(int tipoSensor ){

        String unidades="";

        if(tipoSensor==Sensor.TYPE_ACCELEROMETER) {

            unidades="m/s^2";

        }

        if(tipoSensor==Sensor.TYPE_GRAVITY) {

            unidades="m/s^2";

        }

        if(tipoSensor==Sensor.TYPE_AMBIENT_TEMPERATURE) {

            unidades="°C";

        }

        if(tipoSensor==Sensor.TYPE_GYROSCOPE) {

            unidades="rad/s";

        }

        if(tipoSensor==Sensor.TYPE_MAGNETIC_FIELD) {

            unidades="μT";

        }


        if(tipoSensor==Sensor.TYPE_LIGHT) {

            unidades="lx";

        }

        if(tipoSensor==Sensor.TYPE_RELATIVE_HUMIDITY) {

            unidades="%";

        }

        if(tipoSensor==Sensor.TYPE_LINEAR_ACCELERATION) {

            unidades="m/s^2";

        }

        if(tipoSensor==Sensor.TYPE_PROXIMITY) {

            unidades="cm";

        }

        if(tipoSensor==Sensor.TYPE_PRESSURE) {

            unidades="hPa";

        }

        if(tipoSensor==Sensor.TYPE_TEMPERATURE) {

            unidades="°C";

        }

        if(tipoSensor==Sensor.TYPE_ORIENTATION) {

            unidades="°";

        }




        return unidades;

    }

    public void onDestroy() {

        super.onDestroy();
        finish();

    }



}
