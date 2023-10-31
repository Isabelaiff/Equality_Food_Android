package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class telaSemInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sem_internet);
    }

    public void voltarinicio(View view) {
        Intent intent = new Intent(telaSemInternet.this, Login.class);
        startActivity(intent);
    }
}