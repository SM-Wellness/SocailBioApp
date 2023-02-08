package com.shoppi.smwellness;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;



public class VisualizerView extends View {
    private static final int MAX_AMPLITUDE = 32767;

    private ArrayList<Float> amplitudes;
    private Paint linePaint;
    private int width;
    private int height;
    private int density;
    private float stroke;

    public VisualizerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        density = this.getResources().getDisplayMetrics().densityDpi;   //Get the display DPI

        linePaint = new Paint();
        linePaint.setColor(Color.GREEN);
        linePaint.setAntiAlias(true);  //Add AntiAlias for displaying strokes that are less than 1
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        width = w;
        height = h;
        amplitudes = new ArrayList<>(width * 2);
        stroke =(width * ((float)density / 500)) / 50; //Calculate actual pixel size for the view based on view width and dpi
        linePaint.setStrokeWidth(5f);
    }

    /**
     * Add a new value of int to the visualizer array
     * @param amplitude Int value
     */
    public void addAmplitude(int amplitude){
        invalidate();
        float scaledHeight = ((float) amplitude / MAX_AMPLITUDE) * (height * 7);
        amplitudes.add(scaledHeight);
    }

    /**
     * Clears Visualization
     */
    public void clear(){
        amplitudes.clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int middle = height; // get the middle of the View
        float curX = 150; // start curX at zero

        // for each item in the amplitudes ArrayList
        for (float power : amplitudes) {

            // draw a line representing this item in the amplitudes ArrayList
            canvas.drawLine(curX, middle + power / 2, curX, middle - power / 2, linePaint);
            curX += stroke; // increase X by line width
            String s= Float.toString(canvas.getHeight());
           // canvas.drawText(s, curX,, linePaint);
        }
    }
}