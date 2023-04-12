package com.curso_simulaciones.mitrigesimaterceraapp.gui_auxiliares;


import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.curso_simulaciones.mitrigesimaterceraapp.R;
import com.curso_simulaciones.mitrigesimaterceraapp.datos.AlmacenDatosRAM;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderLayout extends LinearLayout implements AdapterView.OnItemClickListener {

    Context context;
    private IFolderItemListener folderListener;
    private List<String> item = null;
    private List<String> path = null;
    private String root = "/";
    private TextView miRuta;
    private ListView listaView;
    private LinearLayout linear_layout_principal;
    private int tamano_letras;
    private TextView text;

    private File[] files;



    public FolderLayout(Context context) {
        super(context);
        this.context = context;


        tamanoLetras();


        this.setGravity(Gravity.LEFT);
        this.setGravity(Gravity.FILL);

        crearElementosGUI();

        this.addView(crearGUI(context));

        getDir(root, listaView);

    }






    private void crearElementosGUI(){

        miRuta = new TextView(context);
        listaView = new ListView(context);
        miRuta.setTextColor(Color.BLACK);
        miRuta.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamano_letras);


    }

    private LinearLayout crearGUI(Context context){

        //LinearLayoutPrincipal
        linear_layout_principal = new LinearLayout(context);
        linear_layout_principal.setOrientation(LinearLayout.VERTICAL);
        linear_layout_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.FILL);
        linear_layout_principal.setBackgroundColor(Color.CYAN);
        linear_layout_principal.setWeightSum(10);

        //LinearLayout primera fila
        LinearLayout linear_layout_primera_fila = new LinearLayout(context);
        LayoutParams parametros_primera_fila = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parametros_primera_fila.weight = 1.0f;
        linear_layout_primera_fila.setBackgroundColor(Color.RED);
        linear_layout_primera_fila.setLayoutParams(parametros_primera_fila);

        //LinearLayout segunda fila
        LinearLayout linear_layout_segunda_fila = new LinearLayout(context);
        LayoutParams parametros_segunda_fila = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parametros_segunda_fila.weight = 9f;
        linear_layout_segunda_fila.setBackgroundColor(Color.BLACK);
        linear_layout_segunda_fila.setLayoutParams(parametros_segunda_fila);

        linear_layout_primera_fila.addView(miRuta);
        linear_layout_segunda_fila.addView(listaView);



        linear_layout_principal.addView(linear_layout_primera_fila);
        linear_layout_principal.addView(linear_layout_segunda_fila);

        return linear_layout_principal;

    }

    public void setIFolderItemListener(IFolderItemListener folderItemListener) {
        this.folderListener = folderItemListener;
    }

    //Set Directory for view at anytime
    public void setDir(String dirPath) {

    }

    private void getDir(String dirPath, ListView v) {
        String localizacion="";

        localizacion="Ejemplo ap_33";


        miRuta.setText(localizacion +dirPath);
        item = new ArrayList<String>();
        path = new ArrayList<String>();

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q){
            //versiones inferiores a Android 10
            files =  new File(Environment.getExternalStorageDirectory(),  "almacen_mis_datos/luxometro/").listFiles();

        } else {

            //versiones desde Android 10 en adelante
            files = context.getExternalFilesDir( "almacen_mis_datos/luxometro/").listFiles();
        }
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            path.add(file.getPath());
            if (file.isDirectory()) {
                item.add(file.getName() + "/");
            } else {
                item.add(file.getName());
            }

        }

        setItemList(item);

    }


    public void setItemList(List<String> item) {


        ArrayAdapter<String> fileList = new ArrayAdapter<String>(context, R.layout.row,R.id.rowtext, item){


            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Obtener el item del ListView
                View view = super.getView(position, convertView, parent);

                //Inicializar un TextView para ListView cada item
                TextView tv = (TextView) view.findViewById(R.id.rowtext);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.YELLOW);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamano_letras);

                // Generar el ListView item usando TextView
                return view;
            }
        };




        listaView.setAdapter(fileList);

        listaView.setOnItemClickListener(this);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        File file = new File(path.get(position));
        if (file.isDirectory()) {
            if (file.canRead()) {
                getDir(path.get(position), l);
            } else {

                if (folderListener != null) {
                    folderListener.OnCannotFileRead(file);

                }

            }
        } else {


            if (folderListener != null) {
                folderListener.OnFileClicked(file);
            }

        }
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        onListItemClick((ListView) arg0, arg0, arg2, arg3);
    }


    private void tamanoLetras( ){

        tamano_letras=(int)(0.8f* AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }

}
