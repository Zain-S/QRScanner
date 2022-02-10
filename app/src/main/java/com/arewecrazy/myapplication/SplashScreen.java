package com.arewecrazy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread SP = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    Intent It = new Intent(SplashScreen.this, QRScanner.class);
                    startActivity(It);
                }
            }
        };SP.start();
    }
}
