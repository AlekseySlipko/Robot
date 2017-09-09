package com.example.aleksey.robot;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Aleksey on 05.01.2017.
 */
public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "Log";
    public DrawThread thread;
    private Droid droid;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);

        droid=new Droid(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),50,50);

        thread = new DrawThread(getHolder(),this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry){
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN){
// вызываем метод handleActionDown, куда передаем координаты касания
            droid.handleActionDown((int)event.getX(),(int)event.getY());

// если щелчок по нижней области экрана, то выходим
            if(event.getY()> getHeight()-50){
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            }else{
                Log.d(TAG,"Coords: x="+ event.getX()+",y="+ event.getY());
            }
        }if(event.getAction()== MotionEvent.ACTION_MOVE){
// перемещение
            if(droid.isTouched()){
// робот находится в состоянии перетаскивания,
// поэтому изменяем его координаты
                droid.setX((int)event.getX());
                droid.setY((int)event.getY());
            }
        }if(event.getAction()== MotionEvent.ACTION_UP){
// Обрабатываем отпускание
            if(droid.isTouched()){
                droid.setTouched(false);
            }
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {

        //DrawThread.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
// Вызываем метод, который выводит рисунок робота
        droid.draw(canvas);
    }

}
