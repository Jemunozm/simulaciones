package com.curso_simulaciones.mivigesimaseptimaapp.utilidades;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.widget.ImageView;

public class Boton extends ImageView {

    private Bitmap imagen;
    Bitmap imagen_escalada = null;
    private float tamamano_letra;
    private String cadena = "";


    public Boton(Context context) {

        super(context);


    }


    public void setText(String cadena) {

        this.cadena = cadena;


    }


    public String getText() {

        this.cadena = cadena;

        return cadena;


    }


    public void setImagen(int imagen_importada) {


        imagen = BitmapFactory.decodeResource(getResources(), imagen_importada);


    }


    protected void onDraw(Canvas canvas) {


        Paint pincel = new Paint();
        pincel.setAntiAlias(true);
        pincel.setLinearText(true);

        float ancho=getWidth();
        float alto=getHeight();
        float escala=1;
        if(ancho>alto){
            escala=alto;
        } else {escala=ancho;}

        if (imagen != null) {

            imagen_escalada = escalarImagen(imagen, (int) (0.9f * escala), (int) (0.9f * escala));

        }

        int ex = (getWidth() - imagen_escalada.getWidth()) / 2;
        int ey = (getHeight() - imagen_escalada.getHeight()) / 2;

        tamamano_letra = 0.08f * getWidth();

        if (imagen != null) {
            canvas.drawBitmap(imagen_escalada, ex, ey, null);//dimension,dimension, null);
        }


        pincel.setTextSize(tamamano_letra);
        float anchoCadenaUnidades = pincel.measureText(cadena);
        float posicion_x_letra = (getWidth() - anchoCadenaUnidades) / 2;
        float posicion_y_letra = ey + imagen_escalada.getHeight() + tamamano_letra;

        pincel.setColor(Color.BLACK);
        canvas.drawText(cadena, posicion_x_letra, posicion_y_letra, pincel);


        invalidate();

    }

    /*
       Se escala con Matrix para no dañar la resolución
     */


    public Bitmap escalarImagen(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, bitmap.getConfig());

        float scaleX = newWidth / (float) bitmap.getWidth();
        float scaleY = newHeight / (float) bitmap.getHeight();

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, 0, 0);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return scaledBitmap;

    }


}
