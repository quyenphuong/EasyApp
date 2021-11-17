package com.example.easyapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.easyapp.ui.LoginActivity;
import com.example.easyapp.R;

public class MainActivity extends AppCompatActivity {

    Animation zoom;
    ImageView img;
    private static int SPLASH_SCREEN = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        img = findViewById(R.id.splashImg);
        img.startAnimation(zoom);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}