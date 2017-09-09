package com.example.aleksey.robot;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Aleksey on 05.01.2017.
 */
public class DrawThread extends Thread{
    public static final String TAG= "Log";

    private SurfaceHolder surfaceHolder;
    private DrawView drawView;
    private boolean running;
    public void setRunning(boolean running){
        this.running= running;

    }

    public DrawThread(SurfaceHolder surfaceHolder, DrawView drawView){
        super();
        this.surfaceHolder= surfaceHolder;
        this.drawView= drawView;
    }

    public void run(){
        Canvas canvas;
        Log.d(TAG,"Starting game loop");
        while(running){
            canvas=null;
// пытаемся заблокировать canvas
// для изменение картинки на поверхности
            try{
                canvas= this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder){
// здесь будет обновляться состояние игры
// и формироваться кадр для вывода на экран
                    this.drawView.draw(canvas);//Вызываем метод для рисования
                }
            } finally {
// в случае ошибки, плоскость не перешла в
//требуемое состояние
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }}}


}
