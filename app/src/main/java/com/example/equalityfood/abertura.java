package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class abertura extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abertura);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //abre a tela de login
                Intent intent = new Intent(abertura.this, bemVindo.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
