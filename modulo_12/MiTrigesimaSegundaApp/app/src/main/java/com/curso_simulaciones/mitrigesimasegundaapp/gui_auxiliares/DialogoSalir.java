package com.curso_simulaciones.mitrigesimasegundaapp.gui_auxiliares;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.curso_simulaciones.mitrigesimasegundaapp.datos.AlmacenDatosRAM;

public class DialogoSalir {

    private Activity actividad;
    private PopupWindow popup_salir;
    private Button si,no;
    private TextView salir;
    private int tamano_letra_25;
    private int tamano_letra_20;
    private LinearLayout linear_layout_principal;


    public DialogoSalir(Activity actividad){

        this.actividad=actividad;

        gestionandoResolucion();

        crearGUI();

    }

    private void gestionandoResolucion(){

        float tamanoLetraResolucionIncluida = AlmacenDatosRAM.tamanoLetraResolucionIncluida;

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamano_letra_25 = (int)tamanoLetraResolucionIncluida;
        tamano_letra_20 = (int)(0.8f*tamanoLetraResolucionIncluida);

    }//fin método gestionandoResolucion()


    private void crearGUI(){

        linear_layout_principal = new LinearLayout(actividad);
        linear_layout_principal.setBackgroundColor(Color.YELLOW);
        linear_layout_principal.setOrientation(LinearLayout.VERTICAL);
        linear_layout_principal.setWeightSum(3);

        //LinearLayoutArriba
        LinearLayout linear_layout_arriba = new LinearLayout(actividad);
        linear_layout_arriba.setOrientation(LinearLayout.HORIZONTAL);

        //LinearLayoutMedio
        LinearLayout linear_layout_medio = new LinearLayout(actividad);
        linear_layout_medio.setOrientation(LinearLayout.HORIZONTAL);

        //LinearLayoutAbajo
        LinearLayout linear_layout_abajo = new LinearLayout(actividad);
        linear_layout_abajo.setOrientation(LinearLayout.HORIZONTAL);


        //parametro para pegar elementos a cada linearyout
        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros.weight = 1.0f;

        //elementos
        salir=new TextView(actividad);
        salir.setTextSize(tamano_letra_20);
        salir.setText("__________ ¿SALIR?__________");
        salir.setTextColor(Color.BLACK);

        si=new Button(actividad);
        si.setTextSize(tamano_letra_25);
        si.setText("SI");
        si.getBackground().setColorFilter(Color.rgb(255, 255, 100), PorterDuff.Mode.MULTIPLY);

        no=new Button(actividad);
        no.setTextSize(tamano_letra_25);
        no.setText("NO");
        no.getBackground().setColorFilter(Color.rgb(255, 255, 100), PorterDuff.Mode.MULTIPLY);


        linear_layout_arriba.addView(salir, parametros);
        linear_layout_medio.addView(si,parametros);
        linear_layout_abajo.addView(no,parametros);


        linear_layout_principal.addView(linear_layout_arriba);
        linear_layout_principal.addView(linear_layout_medio);
        linear_layout_principal.addView(linear_layout_abajo);


        eventosPopMenu();


    }


    public void mostrarPopMenu() {

        popup_salir = new PopupWindow(actividad);

        popup_salir.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popup_salir.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popup_salir.setContentView(linear_layout_principal);
        popup_salir.setFocusable(true);
        popup_salir.showAtLocation(linear_layout_principal, Gravity.CENTER, 0, 0);


    }

    private void eventosPopMenu() {

        si.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                actividad.finish();
                popup_salir.dismiss();


            }
        });

        no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                popup_salir.dismiss();

            }
        });

    }


}
