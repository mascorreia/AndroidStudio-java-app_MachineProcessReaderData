package com.example.cm_project2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CustomView extends View {

    private Rect square;
    private Paint paint;
    private Paint green;
    private Paint orange;
    private Paint red;
    private Paint start;
    private Paint stop;
    private Paint select;
    private Paint emer;


    public CustomView(Context context) {
        super(context);

        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        square = new Rect();

        green = new Paint(Paint.ANTI_ALIAS_FLAG);
        green.setColor(Color.rgb(204,255,204));

        orange = new Paint(Paint.ANTI_ALIAS_FLAG);
        orange.setColor(Color.rgb(255, 194, 153));

        red = new Paint(Paint.ANTI_ALIAS_FLAG);
        red.setColor(Color.rgb(255, 153, 153));

        start = new Paint(Paint.ANTI_ALIAS_FLAG);
        start.setColor(Color.rgb(204,255,204));

        stop = new Paint(Paint.ANTI_ALIAS_FLAG);
        stop.setColor(Color.rgb(255, 153, 153));

        select = new Paint(Paint.ANTI_ALIAS_FLAG);
        select.setColor(Color.rgb(204, 204, 204));

        emer = new Paint(Paint.ANTI_ALIAS_FLAG);
        emer.setColor(Color.rgb(255, 153, 153));



        paint = new Paint();

    }

    public void swapColorGreen() {
        green.setColor(green.getColor() == Color.GREEN ? Color.rgb(204,255,204) : Color.GREEN);

        postInvalidate();
    }

    public void swapColorOrange() {
        orange.setColor(orange.getColor() == Color.rgb(255, 102, 0) ? Color.rgb(255, 194, 153) : Color.rgb(255, 102, 0));

        postInvalidate();
    }

    public void swapColorRed() {
        red.setColor(red.getColor() == Color.RED ? Color.rgb(255, 153, 153) : Color.RED);

        postInvalidate();
    }

    public void swapColorStart() {
        start.setColor(start.getColor() == Color.GREEN ? Color.rgb(204,255,204) : Color.GREEN);

        postInvalidate();
    }

    public void swapColorStop() {
        stop.setColor(stop.getColor() == Color.RED ? Color.rgb(255, 153, 153) : Color.RED);

        postInvalidate();
    }

    public void swapColorSelect() {
        select.setColor(select.getColor() == Color.BLACK ? Color.rgb(204, 204, 204) : Color.BLACK);

        postInvalidate();
    }

    public void swapColorEmer() {
        emer.setColor(emer.getColor() == Color.RED ? Color.rgb(255, 153, 153) : Color.RED);

        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);

        // -------------------MESA-------------------
        // Mesa baixo
        canvas.drawRect(20,20,30,1800,paint);

        // Mesa cima
        canvas.drawRect(1410,20,1420,1800,paint);

        // Mesa esquerda
        canvas.drawRect(20,20,1420,30,paint);

        // Mesa direita
        canvas.drawRect(20,1790,1420,1800,paint);


        // -------------------TAPETE-------------------
        // Tapete baixo
        canvas.drawRect(650,180,660,1620,paint);

        // Tapete cima
        canvas.drawRect(1000,180,1010,1620,paint);

        // Tapete esquerda
        canvas.drawRect(650,180,1010,190,paint);

        // Tapete direita
        canvas.drawRect(650,1610,1010,1620,paint);


        // -------------------MOTOR-------------------
        // Motor baixo
        canvas.drawRect(320,1430,330,1620,paint);

        // Motor cima
        canvas.drawRect(550,1430,560,1620,paint);

        // Motor esquerda
        canvas.drawRect(320,1430,560,1440,paint);

        // Motor direita
        canvas.drawRect(320,1610,560,1620,paint);


        // -------------------S1-------------------
        // S1 esquerda
        canvas.drawRect(560,1490,650,1500,paint);

        // S1 direita
        canvas.drawRect(560,1540,650,1550,paint);


        // -------------------Fila Metal-------------------
        // Fila Metal baixo
        canvas.drawRect(200,1060,210,1200,paint);

        // Fila Metal esquerda
        canvas.drawRect(200,1060,650,1070,paint);

        // Fila Metal direita
        canvas.drawRect(200,1190,650,1200,paint);

        // -------------------Fila Branca-------------------
        // Fila Metal baixo
        canvas.drawRect(200,710,210,850,paint);

        // Fila Metal esquerda
        canvas.drawRect(200,710,650,720,paint);

        // Fila Metal direita
        canvas.drawRect(200,840,650,850,paint);


        // -------------------Fila Preta-------------------
        // Fila Metal baixo
        canvas.drawRect(200,340,210,500,paint);

        // Fila Metal esquerda
        canvas.drawRect(200,340,650,350,paint);

        // Fila Metal direita
        canvas.drawRect(200,490,650,500,paint);


        // -------------------S4-------------------
        // S4 cima
        canvas.drawRect(1310,1100,1320,1160,paint);

        // S4 esquerda
        canvas.drawRect(1010,1100,1310,1110,paint);

        // S4 direita
        canvas.drawRect(1010,1150,1310,1160,paint);

        // -------------------S5-------------------
        // S5 cima
        canvas.drawRect(1310,750,1320,810,paint);

        // S5 esquerda
        canvas.drawRect(1010,750,1310,760,paint);

        // S5 direita
        canvas.drawRect(1010,800,1310,810,paint);


        // -------------------S6-------------------
        // S6 cima
        canvas.drawRect(1310,380,1320,460,paint);

        // S6 esquerda
        canvas.drawRect(1010,380,1310,390,paint);

        // S6 direita
        canvas.drawRect(1010,450,1310,460,paint);


        // -------------------S2-------------------
        // S2 esquerda
        canvas.drawRect(650,1240,1010,1250,paint);


        // -------------------S3-------------------
        // S3 esquerda
        canvas.drawRect(650,1340,1010,1350,paint);

        // S3 direita
        canvas.drawRect(650,1420,1010,1430,paint);


        // Botão Verde
        square.left = 300;
        square.top = 1670;
        square.right = 400;
        square.bottom = 1770;
        canvas.drawRect(square,green);


        // Botão Laranja
        canvas.drawRect(180, 1670,280,1770,orange);


        // Botão Vermelho
        canvas.drawRect(420,1670,520,1770,red);


        // Botão Start
        canvas.drawRect(620,1670,720,1770,start);


        // Botão Stop
        canvas.drawRect(740,1670,840,1770,stop);


        // Botão Select
        canvas.drawRect(940, 1670,1040,1770,select);


        // Botão Emer
        canvas.drawRect(1060,1660,1180,1780,emer);

    }
}