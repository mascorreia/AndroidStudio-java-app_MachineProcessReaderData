package com.example.cm_project2;

import androidx.appcompat.app.AppCompatActivity;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends AppCompatActivity {

    RingProgressBar ringProgressBar1;

    int progress = 0;

    Handler myHandler  = new Handler(){

        @Override
        public void handleMessage(Message msg){

            if(msg.what == 0)
            {
                if(progress < 100)
                {
                    progress++;
                    ringProgressBar1.setProgress(progress);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ringProgressBar1 = (RingProgressBar) findViewById(R.id.progress_bar_1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){

                    try {
                        Thread.sleep(100);
                        myHandler.sendEmptyMessage(0);
                    }catch (InterruptedException e){
                        e.printStackTrace();

                    }
                }
            }
        }).start();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, StartActivity.class);
                startActivity(i);

                finish();
            }
        }, 10000);
    }
}
