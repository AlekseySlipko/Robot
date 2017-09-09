package com.example.aleksey.robot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
// перевод приложения в полноэкранный режим
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
// устанавливаем MainGamePanel как View
        setContentView(new DrawView(this));
        Log.d(TAG,"View added");
    }

    @Override
    protected void onDestroy(){
        Log.d(TAG,"Destroying...");
        super.onDestroy();
    }

    @Override
    protected void onStop(){
        Log.d(TAG,"Stopping...");
        super.onStop();
    }
}


