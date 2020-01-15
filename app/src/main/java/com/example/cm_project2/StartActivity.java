package com.example.cm_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        b = (Button)findViewById(R.id.button);

        Animation animation = AnimationUtils.loadAnimation(StartActivity.this, R.anim.lefttoright);
        
        animation.setDuration(2000);
        b.startAnimation(animation);


    }
}
