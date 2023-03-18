package com.curso_simulaciones.mivigesimacuartaapp.controlador;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.curso_simulaciones.mivigesimacuartaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimacuartaapp.vista.CR;
import com.curso_simulaciones.mivigesimacuartaapp.vista.Pizarra;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Flecha;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Marca;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Particula;

public class ActividadControladora extends Activity {


    //variable tamaño de las letras basado en resolución de pantalla
    private int tamanoLetraResolucionIncluida;

    //Objetos GUI necesarios
    private TextView textAceletacion, textVelocidad, textAngulo;
    private SeekBar seekBarAceleracion, seekarVelocidadInicial, seekBarAngulo;
    private Button botonEmpezar, botonPausar;
    private Pizarra pizarra;

    //valores de las variables
    private float velocidadInicial;
    private float angulo;
    private float aceleracion;
    private float origenX_en_pixeles, origenY_en_pixeles;


    //objetos
    private Flecha ejeY, ejeX, vectAceleracion, vectVelocidad, vectVelocidadX, vectVelocidadY;
    private Marca marcaEjeX, marcaEjeY;
    private Particula bolita;

    //colección de los objetos
    private ObjetoLaboratorio[] objetos = new ObjetoLaboratorio[10];


    /*Hilo responsable de la animación
     El trabajo de animación es mejor manejarlo en hilo
     aparte para evitar bloqueos de la aplicación
     debido al manejo simultáneo de la GUI con la Acivity
    */
    private HiloAnimacion hilo;

    private int i=-1;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gestionarResolucion();


        //para crear elementos de la GUI
        crearElementosGUI();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        //hilo que administrador los cálculos
        hilo = new HiloAnimacion(this);
        hilo.start();


        //para administrar los eventos
        eventos();


    }//fin onCreate


    /*Método auxiliar para asuntos de resolución*/
    private void gestionarResolucion() {

        tamanoLetraResolucionIncluida = (int) (0.6f * AlmacenDatosRAM.tamanoLetraResolucionIncluida);
        /*
        Según el diseño de la GUI se puede anticipar cuál es la
        dimensión de la pizarra. En este caso es el 85% del ancho
        de la pantalla y el 100% del alto de la misma
        El cálculo del alto y el ancho de la pantalla
        se hizo en la clase principal con el móvil
        en portrait y aquí estamos en posición lanscape.
        Por tanto troquemos alto por ancho y viceversa
        */
        CR.anchoPizarra = 0.85f * AlmacenDatosRAM.alto_pantalla;
        CR.altoPizarra = AlmacenDatosRAM.ancho_pantalla;

    }

    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {

        textAceletacion = new TextView(this);
        textAceletacion.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textAceletacion.setGravity(Gravity.CENTER);
        String marca_aceleracion = "ACELERACION \n -20 a +20";//con salto de línea
        textAceletacion.setTextColor(Color.BLACK);
        textAceletacion.setText(marca_aceleracion);

        textVelocidad = new TextView(this);
        textVelocidad.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textVelocidad.setGravity(Gravity.CENTER);
        textVelocidad.setTextColor(Color.BLACK);
        String marca_velocidad = "VELOCIDAD \n 0 a +30";//con salto de línea
        textVelocidad.setText(marca_velocidad);

        textAngulo = new TextView(this);
        textAngulo.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textAngulo.setGravity(Gravity.CENTER);
        textAngulo.setTextColor(Color.BLACK);
        String marca_angulo = "ANGULO gra. \n -90 a +90 ";//con salto de línea
        textAngulo.setText(marca_angulo);

        seekBarAceleracion = new SeekBar(this);
        seekBarAceleracion.setMax(40);
        seekBarAceleracion.setProgress(10);//aceleración inicial -10
        seekBarAceleracion.setScaleY(0.2f);
        aceleracion = seekBarAceleracion.getProgress() - 20;
        AlmacenDatosRAM.aceleracion=aceleracion;

        seekarVelocidadInicial = new SeekBar(this);
        seekarVelocidadInicial.setMax(30);
        seekarVelocidadInicial.setProgress(20);//velocidad inicial 20
        seekarVelocidadInicial.setScaleY(0.2f);
        velocidadInicial = seekarVelocidadInicial.getProgress();
        AlmacenDatosRAM.vInicial=velocidadInicial;
        AlmacenDatosRAM.velocidad=velocidadInicial;

        seekBarAngulo = new SeekBar(this);
        seekBarAngulo.setMax(180);
        seekBarAngulo.setProgress(150);//ángulo inicial -30
        seekBarAngulo.setScaleY(0.2f);
        angulo = seekBarAngulo.getProgress()-90;
        AlmacenDatosRAM.angulo = angulo;


        botonEmpezar = new Button(this);
        botonEmpezar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonEmpezar.setText("EMPEZAR");
        botonEmpezar.getBackground().setColorFilter(Color.argb(180, 250, 170, 50), PorterDuff.Mode.MULTIPLY);

        botonPausar = new Button(this);
        botonPausar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonPausar.setText("PAUSAR");
        botonPausar.getBackground().setColorFilter(Color.argb(180, 250, 170, 50), PorterDuff.Mode.MULTIPLY);
        botonPausar.setEnabled(false);


        //creación del objeto Pizarra
        pizarra = new Pizarra(this);
        //color del tablero
        pizarra.setBackgroundColor(Color.WHITE);
        pizarra.setSistemaCoordenadas(CR.pcApxX(40f),CR.pcApxY(60f),1, -1);

        crearObjetosLaboratorio();

        estadoInicial();

    }

    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.HORIZONTAL);
        linearPrincipal.setBackgroundColor(Color.BLACK);
        linearPrincipal.setWeightSum(10.0f);


        //linear secudario izquierda
        LinearLayout linearIzquierda = new LinearLayout(this);
        //linearIzquierda.setWeightSum(1.0f);


        //linear secudario derecha
        LinearLayout linearDerecha = new LinearLayout(this);
        linearDerecha.setOrientation(LinearLayout.VERTICAL);
        linearDerecha.setBackgroundColor(Color.YELLOW);
        linearDerecha.setWeightSum(8.0f);


        //pegar estos secundarios al principal
        LinearLayout.LayoutParams parametrosPegadoIzquierda = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        //parametrosPegadoIzquierda.setMargins(0, 5, 0, 5);
        //ocupará el 80% de linear_principal
        parametrosPegadoIzquierda.weight = 8.5f;
        linearPrincipal.addView(linearIzquierda, parametrosPegadoIzquierda);

        //pegar la pizarra al linearIzquierdo
        //linearIzquierda.setOrientation(LinearLayout.VERTICAL);
        linearIzquierda.addView(pizarra);


        //pegar estos secundarios al princial
        LinearLayout.LayoutParams parametrosPegadoDerecha = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        //ocupará el 20% de linear_principal
        parametrosPegadoDerecha.setMargins(0, 5, 0, 5);
        parametrosPegadoDerecha.weight = 1.5f;
        linearPrincipal.addView(linearDerecha, parametrosPegadoDerecha);


        //pegar componentes a lineardDerecha.
        linearDerecha.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams parametrosPegadoComponentes = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        //ocupará el 10% de linear_principal
        parametrosPegadoComponentes.setMargins(5, 5, 5, 5);
        parametrosPegadoComponentes.weight = 1.0f;


        linearDerecha.addView(textAngulo, parametrosPegadoComponentes);
        linearDerecha.addView(seekBarAngulo, parametrosPegadoComponentes);
        linearDerecha.addView(textVelocidad, parametrosPegadoComponentes);
        linearDerecha.addView(seekarVelocidadInicial, parametrosPegadoComponentes);
        linearDerecha.addView(textAceletacion, parametrosPegadoComponentes);
        linearDerecha.addView(seekBarAceleracion, parametrosPegadoComponentes);
        linearDerecha.addView(botonEmpezar, parametrosPegadoComponentes);
        linearDerecha.addView(botonPausar, parametrosPegadoComponentes);

        return linearPrincipal;


    }//fin gui


    private void eventos() {

        //evento botones
        botonEmpezar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if (botonEmpezar.getText() == "EMPEZAR") {

                    AlmacenDatosRAM.NUEVO=false;
                    //para que la bolita deje el trazo de su trayectoria
                    //desde su posición inicial
                    i=-1;
                    hilo.pausa = false;
                    botonEmpezar.setText("NUEVO");
                    seekBarAceleracion.setEnabled(false);
                    seekBarAngulo.setEnabled(false);
                    seekarVelocidadInicial.setEnabled(false);
                    botonPausar.setEnabled(true);
                    hilo.tiempo = 0;
                    botonPausar.setText("PAUSAR");

                } else {

                    AlmacenDatosRAM.NUEVO=true;
                    botonEmpezar.setText("EMPEZAR");
                    seekBarAceleracion.setEnabled(true);
                    seekBarAngulo.setEnabled(true);
                    seekarVelocidadInicial.setEnabled(true);
                    botonPausar.setEnabled(false);
                    estadoInicial();
                    hilo.pausa = true;

                }

            }

        });


        //evento del boton activar
        botonPausar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (botonPausar.getText() == "PAUSAR") {

                    botonPausar.setText("CONTINUAR");
                    hilo.pausa = true;

                } else {


                    botonPausar.setText("PAUSAR");
                    hilo.pausa = false;

                }


            }

        });



        //eventos seekbar

        // seek_bar_velocidad_inicial
        seekarVelocidadInicial.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int progressChanged = progress;
                velocidadInicial = progressChanged;//0 el valor mínimo
                //nuevas condiciones iniciales de la escena
                estadoInicial();


            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        // seek_bar_angulo
        seekBarAngulo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int progressChanged = progress;

                //-90 será el valor mínimo y +90 el valor máximo
                angulo = progressChanged -90;

                //condiciones iniciales de la animación
                estadoInicial();



            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });


        // seek_bar_aceleración
        seekBarAceleracion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;

                //-20 será el valor mínimo y +20 el máximo
                aceleracion = (progressChanged) - 20;
                estadoInicial();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }

    private void crearObjetosLaboratorio() {

        //origen en pixeles
        float origenX_en_pixeles = CR.pcApxX(10);
        float origenY_en_pixeles = CR.pcApxY(50);
        //almacenar origen en pixeles
        AlmacenDatosRAM.origenX_en_pixeles = origenX_en_pixeles;
        AlmacenDatosRAM.origenY_en_pixeles = origenY_en_pixeles;

        //posición inicial en pixeles
        //posición en pixeles en coordenadas pantalla
        float xi_en_pixeles = CR.pcApxX(0);
        float yi_en_pixeles = CR.pcApxY(0);
        //almacenar en pixeles
        AlmacenDatosRAM.xi_en_pixeles = xi_en_pixeles;
        AlmacenDatosRAM.yi_en_pixeles = yi_en_pixeles;

        //crear eje x
        ejeX = new Flecha(0, 0, CR.pcApxL(30f));
        ejeX.setColor(Color.BLACK);
        ejeX.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[0] = ejeX;

        //crear eje y
        ejeY = new Flecha(0, 0, CR.pcApxL(30f));
        ejeY.setColor(Color.BLACK);
        ejeY.setGrosorLinea(CR.pcApxL(0.5f));
        ejeY.rotar(90);
        objetos[1] = ejeY;

        //los vectores inicialmente longitud cero
        vectAceleracion = new Flecha(xi_en_pixeles, yi_en_pixeles, CR.pcApxL(1.0f));
        vectAceleracion.setGrosorLinea(CR.pcApxL(0.5f));
         /*
         factor de amplificación de la longitud del vector
         para ver de buen tamaño el vector
         */
        float factor_aceleracion = CR.pcApxL(1.5f);
        vectAceleracion.setLongitud(factor_aceleracion * AlmacenDatosRAM.aceleracion);
        vectAceleracion.setColor(Color.BLUE);
        vectAceleracion.rotar(90);
        objetos[2] = vectAceleracion;

        //crear velocidad
        vectVelocidad = new Flecha(xi_en_pixeles, yi_en_pixeles, CR.pcApxL(1.0f));
        vectVelocidad.setGrosorLinea(CR.pcApxL(0.5f));
        //float anguloVelocidadGrados = (float) Math.toDegrees(angulo);//AlmacenDatosRAM.anguloVelocidad);
        vectVelocidad.rotar(angulo);//anguloVelocidadGrados);
         /*
         factor de amplificación de la longitud del vector
         para ver de buen tamaño el vector
         */
        float factor_velocidad = CR.pcApxL(0.8f);
        vectVelocidad.setLongitud(factor_velocidad * AlmacenDatosRAM.velocidad);
        vectVelocidad.setColor(Color.RED);
        objetos[3] = vectVelocidad;

        //crear velocidad x
        vectVelocidadX = new Flecha(xi_en_pixeles, yi_en_pixeles, CR.pcApxL(1.0f));
        vectVelocidadX.setGrosorLinea(CR.pcApxL(0.5f));
        vectVelocidadX.setLongitud(factor_velocidad * AlmacenDatosRAM.velocidadX);
        vectVelocidadX.setColor(Color.MAGENTA);
        objetos[4] = vectVelocidadX;

        //crear velocidad y
        vectVelocidadY = new Flecha(xi_en_pixeles, yi_en_pixeles, CR.pcApxL(1.0f));
        vectVelocidadY.setGrosorLinea(CR.pcApxL(0.5f));
        vectVelocidadY.setLongitud(factor_velocidad * AlmacenDatosRAM.velocidadY);
        vectVelocidadY.setColor(Color.rgb(0, 128, 0));
        vectVelocidadY.rotar(-90);
        objetos[5] = vectVelocidadY;

        //crear bola
        bolita = new Particula(xi_en_pixeles, yi_en_pixeles, CR.pcApxL(10.0f));
        bolita.setRadio(CR.pcApxL(2f));
        bolita.setColor(Color.rgb(200, 0, 0));
        objetos[6] = bolita;

        //marcar ejes
        marcaEjeX = new Marca("Eje X",  CR.pcApxX(10f),  CR.pcApxY(4f));
        marcaEjeX.setTamano(CR.pcApxL(3f));
        objetos[7] = marcaEjeX;

        //marcar ejes
        marcaEjeY = new Marca("Eje Y",  - CR.pcApxX(2f),  CR.pcApxY(25f));
        marcaEjeY.setTamano(CR.pcApxL(3f));
        marcaEjeY.rotar(-90f);
        objetos[8] = marcaEjeY;

        //estado inicial de la escena
        pizarra.setEstadoEscena(objetos);

    }

    public void cambiarEstadosEscenaPizarra() {

        //se obtienen los valores de las variables
        float x_en_pixeles = AlmacenDatosRAM.x_en_pixeles;
        float y_en_pixeles = AlmacenDatosRAM.y_en_pixeles;
        float v = AlmacenDatosRAM.velocidad;
        float vX = AlmacenDatosRAM.velocidadX;
        float vY = AlmacenDatosRAM.velocidadY;
        float anguloVelocidad = AlmacenDatosRAM.anguloVelocidad;//en radianes
        float a = AlmacenDatosRAM.aceleracion;

        //actualizar la escena
        vectVelocidad.setPosicion(x_en_pixeles, y_en_pixeles);
        float factor_velocidad = CR.pcApxL(0.8f);
        vectVelocidad.setLongitud(factor_velocidad * v);
        //el ángulo está en radianes y se debe convertir a grados
        vectVelocidad.rotar((float) Math.toDegrees(anguloVelocidad));

        vectVelocidadX.setLongitud(factor_velocidad * vX);
        vectVelocidadX.setPosicion(x_en_pixeles, y_en_pixeles);

        vectVelocidadY.setLongitud(factor_velocidad * vY);
        vectVelocidadY.setPosicion(x_en_pixeles, y_en_pixeles);
        vectVelocidadY.rotar(90);

        float factor_aceleracion = CR.pcApxL(1.5f);
        vectAceleracion.setLongitud(factor_aceleracion * a);
        vectAceleracion.setPosicion(x_en_pixeles, y_en_pixeles);

        bolita.setPosicion(x_en_pixeles, y_en_pixeles);
        bolita.setColorTrayectoria(Color.rgb(250, 180, 0));
        i = i + 1;//necesario para ir dibujando la trayectoria
        bolita.setTrayectoria(true, i);
        bolita.setGrosorLinea(CR.pcApxL(0.6f));

    }

    private void estadoInicial(){

        AlmacenDatosRAM.aceleracion = aceleracion;
        AlmacenDatosRAM.angulo = angulo;
        AlmacenDatosRAM.vInicial = velocidadInicial;
        AlmacenDatosRAM.velocidad= velocidadInicial;
        AlmacenDatosRAM.velocidadX = (float)(velocidadInicial*Math.cos(Math.toRadians(angulo)));
        AlmacenDatosRAM.velocidadY = (float)(velocidadInicial*Math.sin(Math.toRadians(angulo)));

        float x_i= AlmacenDatosRAM.xi_en_pixeles;
        float y_i= AlmacenDatosRAM.yi_en_pixeles;
        i=-1;
        bolita.borrarDatos();
        bolita.setPosicion(x_i, y_i);
        vectAceleracion.setPosicion(x_i,y_i);
        /*
         factor de amplificación de la longitud del vector
         para ver de buen tamaño el vector
         */
        float factor_aceleracion = CR.pcApxL(1.5f);
        vectAceleracion.setLongitud(factor_aceleracion*aceleracion);
        /*
         factor de amplificación de la longitud del vector
         para ver de buen tamaño el vector
         */
        float factor_velocidad = CR.pcApxL(0.8f);
        vectVelocidad.setLongitud(factor_velocidad * velocidadInicial);
        vectVelocidad.setPosicion(x_i, y_i);
        vectVelocidad.rotar(angulo);
        //borrar las componentes
        vectVelocidadX.setLongitud(0f);
        vectVelocidadY.setLongitud(0f);

    }

}
