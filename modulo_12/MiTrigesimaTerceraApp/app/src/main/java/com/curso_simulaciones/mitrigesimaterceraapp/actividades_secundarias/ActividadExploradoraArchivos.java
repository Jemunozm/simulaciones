package com.curso_simulaciones.mitrigesimaterceraapp.actividades_secundarias;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;

import com.curso_simulaciones.mitrigesimaterceraapp.R;
import com.curso_simulaciones.mitrigesimaterceraapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mitrigesimaterceraapp.gui_auxiliares.FolderLayout;
import com.curso_simulaciones.mitrigesimaterceraapp.gui_auxiliares.IFolderItemListener;

import java.io.File;

public class ActividadExploradoraArchivos extends Activity implements IFolderItemListener {


    private int tamanoLetraResolucionIncluida;

    //  @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        gestionarResolucion();

        /*
          vamos a leer datos de archivos txt ubicados en
          el directorio almacen_mis_datos/luxometro/
         */

        FolderLayout folder = new FolderLayout(this);

        folder.setGravity(Gravity.CENTER);
        folder.setGravity(Gravity.FILL);
        folder.setIFolderItemListener(this);
        setContentView(folder);

    }


    private void gestionarResolucion() {

        tamanoLetraResolucionIncluida = (int) (0.8* AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }//fin método gestionarResolucion()


    /*
      Los dos métodos siguientes
      son implementados debido a que
      se está usando la interface
      IFolderItemListener que pertenece
      a la librería explorador

     */

    /*
      Desplegar aviso en caso de no poder
      leer carpeta
     */
    public void OnCannotFileRead(File file) {
        // TODO Auto-generated method stub

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher_background)
                .setTitle(
                        "[" + file.getName()
                                + "] folder can't be read!")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        }).show();

    }

    /*
    Cuando se escoge el archivo se guarda
    su path en AlmacenDatosRAM
    */
    public void OnFileClicked(File file) {

        AlmacenDatosRAM.path = file.getPath();

        finish();
    }


}
