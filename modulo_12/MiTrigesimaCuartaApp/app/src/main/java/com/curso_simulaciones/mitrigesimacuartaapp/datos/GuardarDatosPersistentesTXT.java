package com.curso_simulaciones.mitrigesimacuartaapp.datos;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class GuardarDatosPersistentesTXT {

     /*
      El vector es similar a un arreglo []
      con la diferencia que el mismo se redimensiona
      al doble de su tamaño cuando se llena (se duplica).
      Además, proporciona métodos adicionales
      para añadir, eliminar elementos, e insertar
      elementos entre otros dos existentes.
      Se pueden guardar objetos de diferentes tipo.
     */

    private Vector almacen_tiempo = new Vector();
    private Vector almacen_datos = new Vector();
    private Vector almacen_datos1 = new Vector();
    private Vector almacen_datos2 = new Vector();
    private Vector almacen_datos3 = new Vector();


    /**
     * Constructor del Manejador de  Archivos
     */
    public GuardarDatosPersistentesTXT(){


    }

    /**
     * Para recibir los datos que se grabarán
     * en archivo .txt en una carpeta definida
     * por el usuario en un documento.
     * Serán dos columnas una para X y otra para Y.
     * @param tiempo
     * @param dato
     */

    public void llenarDatos(double tiempo, double dato1, double dato2, double dato3, double dato) {

        almacen_tiempo.addElement(tiempo);
        almacen_datos.addElement(dato);
        almacen_datos1.addElement(dato1);
        almacen_datos2.addElement(dato2);
        almacen_datos3.addElement(dato3);

    }


    /**
     * Para borrar los datos. Estos no serán
     * guardados en el arcivo .txt.
     */
    public void borrarDatos() {

        almacen_tiempo.removeAllElements();
        almacen_datos.removeAllElements();
        almacen_datos1.removeAllElements();
        almacen_datos2.removeAllElements();
        almacen_datos3.removeAllElements();

    }


    /**
     * Para guradar los datos en formato .txt
     * @param actividad
     * @param carpeta
     */

    public void guardar(Activity actividad, String carpeta) {

        Date date = new Date();
        DateFormat hora_fecha = new SimpleDateFormat("yy-MM-dd hh:mm:ss");


        try {

            /*
              Paso 1 y Paso 2: Crear y abrir flujo de salida
              Esto es, crear y abrir el canal de salida
            */

            String marca= hora_fecha.format(date).toString();
            String nombre_archivo ="datos_"+marca+ ".txt";

            File file=null;
            File path = null;

            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q){
                //versiones inferiores a Android 10
                path = new File(Environment.getExternalStorageDirectory(), carpeta);

            } else {

                //versiones desde Android 10 en adelante
                path = new File(actividad.getExternalFilesDir(null), carpeta);

            }

            file = new File(path, nombre_archivo);
            FileOutputStream flujoSalida= new FileOutputStream(file);

            /*
             Agregar filtro. En este caso se le pasa a
             OutputStreamWriter para que escriba
           */
            OutputStreamWriter escritor = new OutputStreamWriter(flujoSalida);//fos);


            /*
              Paso 3: Escribir información mientras haya
            */

            // Escribimos el String en el archivo
            for (int i = 0; i < almacen_tiempo.size(); i = i + 1) {
                //importar los datos
                double tiempo = ((Double)(almacen_tiempo.get(i))).doubleValue();
                double dato = ((Double)(almacen_datos.get(i))).doubleValue();
                double dato1 = ((Double)(almacen_datos1.get(i))).doubleValue();
                double dato2 = ((Double)(almacen_datos2.get(i))).doubleValue();
                double dato3 = ((Double)(almacen_datos3.get(i))).doubleValue();

                //desplegar con dos decimales
                float tiempo_dos_decimales = (float)(Math.round(tiempo * 100) / 100f);
                escritor.write(""+tiempo_dos_decimales);

                escritor.write("\t");
                escritor.write("\t");

                //desplegar con dos decimales
                float dato_dos_decimales= (float)(Math.round(dato1 * 100) / 100f);
                escritor.write(""+dato_dos_decimales);

                escritor.write("\t");

                float dato_dos1_decimales= (float)(Math.round(dato2 * 100) / 100f);
                escritor.write(""+dato_dos1_decimales);

                escritor.write("\t");

                float dato_dos2_decimales= (float)(Math.round(dato3 * 100) / 100f);
                escritor.write(""+dato_dos2_decimales);

                escritor.write("\t");

                float dato_dos3_decimales= (float)(Math.round(dato * 100) / 100f);
                escritor.write(""+dato_dos3_decimales + "\r\n");



            }

           /*
            Paso 4: cerrar canal
           */
            escritor.flush();
            escritor.close();

            // Mostramos que se ha guardado
            String aviso= "Los datos fueron guardados en la carpeta:" + "\n" +
                    "Mis archivos/"+carpeta;
            Toast.makeText(actividad, aviso, 2000).show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}
