package com.curso_simulaciones.mivigesimaoctavaapp.utilidades;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class GaugeSimple extends View {

    private float largo;
    private float minimo = 0;
    private float maximo = 100f;
    private float medida = 0.0f;//tomar como medida inicial
    private String unidades = "UNIDADES";

    //color de los sectores
    private int colorPrimerTercio = Color.rgb(200, 200, 0);
    private int colorSegundoTercio = Color.rgb(0, 180, 0);
    private int colorTercerTercio = Color.RED;

    //color del marco
    private int colorFondoTacometro = Color.rgb(240,240,240);
    private int colorBordeTacometro= Color.BLACK;

    //color franja dinámica
    private int colorFranjaDinamica = Color.RED;


    private int angPrimertercio = 100;
    private int angSegundoTercio = 100;
    private int angTercerTercio = 40;

    private int colorLineas = Color.BLACK;
    private int colorNumeros =Color.BLACK;

    private int colorNumerosDesplieggue = Color.BLACK;

    private int numeroDivisiones= 25;
    private int separacionDivisionesGrandes = 5;


    /**
     * Constructor de GaugeSimple
     */
    public GaugeSimple(Context context) {

        super(context);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }
    }

    /**
     * Modifica el rango de medicion
     * desde minimo hasta maximo
     *
     * @param minimo
     * @param maximo
     */
    public void setRango(float minimo, float maximo) {

        this.minimo = minimo;
        this.maximo = maximo;

    }

    public void setSeparacionesDivisionesGrandes(int separacionDivisionesGrandes){

        this.separacionDivisionesGrandes = separacionDivisionesGrandes;


    }


    private void setNumeroDivisiones(int numeroDivisiones){

        this.numeroDivisiones =numeroDivisiones;


    }


    /**
     * Modifica el valor medido
     *
     * @param medida
     */
    public void setMedida(float medida) {

        this.medida = medida;

    }


    /**
     * Regresa el valor medido
     *
     * @return medida
     */
    public float getMedida() {

        return medida;
    }


    /**
     * Modifica las unidades del instrumento virtual
     *
     * @param unidades
     */
    public void setUnidades(String unidades) {

        this.unidades = unidades;

    }

    /**
     * Modifica el color del borde del marco
     * @param colorBordeMarco
     */

    public void setColorBordeTacometro(int colorBordeMarco){

        this.colorBordeTacometro = colorBordeMarco;

    }


    /**
     * Modifica los colores de los sectores circulares
     *
     * @param colorPrimerTercio
     * @param colorSegundoTercio
     * @param colorTercerTercio
     */
    public void setColorSectores(int colorPrimerTercio, int colorSegundoTercio, int colorTercerTercio) {

        this.colorPrimerTercio = colorPrimerTercio;
        this.colorSegundoTercio = colorSegundoTercio;
        this.colorTercerTercio = colorTercerTercio;

    }

    /**
     * Modifica los angulos de los sectores circulares
     * Deben sumar 250 grados
     *
     * @param angPrimerTercio
     * @param angSegundoTercio
     * @param angTercerTercio
     */
    public void setAngulosSectores(int angPrimerTercio, int angSegundoTercio, int angTercerTercio) {
        this.angPrimertercio = angPrimerTercio;
        this.angSegundoTercio = angSegundoTercio;
        this.angTercerTercio = angTercerTercio;

    }



    /**
     * Modifica el color de fondo del tacometro
     *
     * @param colorFondoTacometro
     */
    public void setColorFondoTacometro(int colorFondoTacometro) {

        this.colorFondoTacometro = colorFondoTacometro;


    }


    /**
     * Modifica el color de las lineas del tacometro
     *
     * @param color_lineas
     */
    public void setColorLineasTacometro(int color_lineas) {

        this.colorLineas = color_lineas;


    }


    public void setColorNumeros(int colorNumeros){

        this.colorNumeros = colorNumeros;

    }


    /**
     * Modifica el color del numero que se despliega
     *
     * @param colorNumerosDesplieggue
     */

    public void setColorNumeroDespliegue(int colorNumerosDesplieggue) {

        this.colorNumerosDesplieggue = colorNumerosDesplieggue;

    }


    /**
     * Modifica el color de la franja dinámica
     * @param colorFranjaDinamica
     */
    public void setColorFranjaDinámica(int colorFranjaDinamica) {

        this.colorFranjaDinamica = colorFranjaDinamica;


    }


    /**
     * @param canvas
     */

    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        se graba el estado actual del canvas
        para al final restaurarlo
        */
        canvas.save();


         /*
         La vista tendra las mismas dimensiones de su
         contenedor
         */
        float ancho = this.getWidth();//ancho de la vista
        float alto = this.getHeight();//alto de la vista

        /*
         Se define la variable largo como el 80%
         del menor valor entre alto y largo del
         contenedor
         */

        if (ancho > alto) {

            largo = 0.8f * alto;

        } else {

            largo = 0.8f * ancho;


        }

        /*
          se hace tralación del (0,0) al centro
          del contenedor
        */
        canvas.translate(0.5f * ancho, 0.5f * alto);

        //configurando el pincel
        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);
        //tamaño texto
        pincel.setTextSize(0.05f * largo);
        //para mejor manejo de la métrica de texto
        pincel.setLinearText(true);
        //para efectos de buen escalado de bitmaps
        pincel.setFilterBitmap(true);
        //para buen manejo de gradientes de color
        pincel.setDither(true);



        //dibujar fondo del tacómetro
        //marco borde: circulo no relleno
        pincel.setStyle(Paint.Style.STROKE);
        //grosor y color
        pincel.setStrokeWidth(0.02f*largo);
        pincel.setColor(colorBordeTacometro);
        canvas.drawCircle(0, 0, 0.5f * largo, pincel);
        //marco fondo: círculo relleno
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(colorFondoTacometro);
        canvas.drawCircle(0, 0, 0.48f * largo, pincel);



        //dibujar los tres segementos circulares
        float esquinaSuperiorIzquierdaX = -0.45f * largo;
        float esquinaSuperiorIzquierdaY = -0.45f * largo;
        float esquinaInferiorDerechaX = 0.45f * largo;
        float esquinaInferiorDerechaY = 0.45f * largo;

        RectF rect = new RectF(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY,
                esquinaInferiorDerechaX, esquinaInferiorDerechaY);

        //grosor líneas
        pincel.setStrokeWidth(0.02f*largo);
        //arcos
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(colorPrimerTercio);
        //con argumento false solo dibuja el arco y no el sector circular
        canvas.drawArc(rect, 150, angPrimertercio, false, pincel);
        pincel.setColor(colorSegundoTercio);
        canvas.drawArc(rect, 150 + angPrimertercio, angSegundoTercio, false, pincel);
        pincel.setColor(colorTercerTercio);
        canvas.drawArc(rect, 150 + angPrimertercio + angSegundoTercio, angTercerTercio, false, pincel);


        //dibujar la escala
        float indent = (float) (0.05 * largo);
        float posicionY = (float) (0.5 * largo);



         /*
         Divisiones grandes, pequeñas y números
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 50 grados comenzando
         en 235 grados.
          */
        pincel.setStyle(Paint.Style.FILL);


        for (int i = 0; i < numeroDivisiones +1; i = i + 1) {//6
            // float anguloRotacion = 235 + 50 * i;
            float salto = 240f/numeroDivisiones;
            //float anguloRotacion = 235 + 50 * i;
            float anguloRotacion = 240 + salto * i;
            canvas.save();
            canvas.rotate(anguloRotacion, 0, 0);
            pincel.setColor(colorLineas);

            if(i%separacionDivisionesGrandes ==0) {
                //dibujar líneas grandes
                pincel.setStrokeWidth(0.01f * largo);
                canvas.drawLine(0, -posicionY, 0, -posicionY + indent, pincel);

                //dibujar los números
                float valorIncrementoMarcas = (maximo - minimo) /numeroDivisiones;
                int valorMarca = (int) (minimo + valorIncrementoMarcas * i);
                String numero = "" + valorMarca;

                //ancho de la cadena del número
                float anchoCadenaNumero = pincel.measureText(numero);

                //dibuja números rotados
                //endereza los números a orientación horizontal
                canvas.rotate(-anguloRotacion, 0, -posicionY + 2.5f * indent);
                pincel.setColor(colorNumeros);
                canvas.drawText(numero, -0.5f * anchoCadenaNumero, -posicionY + 2.5f * indent, pincel);
            } else {
                //divisiones pequeñas
                pincel.setStrokeWidth(0.005f * largo);
                canvas.drawLine(0, -posicionY, 0, -posicionY + (float) (0.6 * indent), pincel);

            }

            canvas.restore();

        }

        /*
        dibujar la aguja
        */
        //aqui empieza dibujo de la aguja
        //calcular angulo para ubicar la aguja de acuerdo al valor medido
        float angulo_rotacion_medida = 240 + (240f / (maximo - minimo)) * (medida - minimo);
        //Dibujar aguja
        pincel.setStrokeWidth(0.005f * largo);
        pincel.setColor(Color.RED);
        canvas.rotate(angulo_rotacion_medida, 0, 0);
        float b = (float) (1.5f* indent);
        canvas.drawLine(0, -posicionY, 0, b, pincel);
        canvas.rotate(-angulo_rotacion_medida, 0, 0);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(colorFondoTacometro);
        canvas.drawCircle(0, 0, (float) (0.4 * indent), pincel);
        pincel.setColor(Color.RED);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, (float) (0.4 * indent), pincel);
        //aquí termina dibujo de la aguja




        //franja dinámica
        float a = (float) 0.03* largo;
        rect = new RectF(esquinaSuperiorIzquierdaX - a, esquinaSuperiorIzquierdaY - a,
                esquinaInferiorDerechaX + a, esquinaInferiorDerechaY + a);
        pincel.setColor(colorFranjaDinamica);
        pincel.setStrokeWidth(0.01f*largo);
        canvas.drawArc(rect, 150, angulo_rotacion_medida - 240, false, pincel);


        //Dibujar las unidades
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(colorLineas);
        pincel.setTextSize(0.08f * largo);
        float anchoCadenaUnidades = pincel.measureText(unidades);
        canvas.drawText(unidades, -0.5f * anchoCadenaUnidades,- 0.15f * largo, pincel);
        //aqui termina dibujo de las unidades

        //aqui despliegue de la medida
        pincel.setTextSize(0.1f * largo);
        float anchoCadenaNumero = pincel.measureText("" + medida);
        pincel.setColor(colorNumerosDesplieggue);
        canvas.drawText("" + medida, -0.5f * anchoCadenaNumero, 0.2f * largo, pincel);

        //marcar empresa
        String empresa = "IoT.PhysicsSensor";
        pincel.setTextSize(0.05f * largo);
        float anchoCadenaNombreEmpresa = pincel.measureText(empresa);
        canvas.drawText(empresa, -0.5f * anchoCadenaNombreEmpresa, 0.35f * largo, pincel);



        //se restaura el canvas al estado incial
        //el que se garbó al principio de este método
        canvas.restore();

        //para efectos de animación
        invalidate();

    }//fin onDraw


}
