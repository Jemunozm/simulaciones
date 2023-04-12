package com.curso_simulaciones.mitrigesimaterceraapp.datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StreamTokenizer;

public class LeerDatosTXT {

    public LeerDatosTXT() {

    }


    public void leer(String path) {

        int i = 0;

        try {

            //Paso 1 y 2: Crear y abrir el canal apar leer texto
            FileReader fr = new FileReader(path);

            //Buffer mejora la eficiencia
            BufferedReader text = new BufferedReader(fr);
            String ultima_linea = "";

            //Paso 3: leer
            //aqui debe ir código de leer y cargar datos en el almacen
            StreamTokenizer streamtokenizer = new StreamTokenizer(text);
            //leemos la primera línea
            //ultima_linea = text.readLine();

            while (ultima_linea != null) {

                streamtokenizer.nextToken();
                //Guardar los datos en la clase
                double x = streamtokenizer.nval;

                AlmacenDatosRAM.x[i] = x;
                streamtokenizer.nextToken();
                double y = streamtokenizer.nval;
                AlmacenDatosRAM.y[i] = y;


                //hasta aqui
                ultima_linea = text.readLine();
                i = i + 1;
            }

            AlmacenDatosRAM.n = i - 1;

            //Paso 4: cerrar
            text.close();

        } catch (Exception ex) {

        }

    }

}
