package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class telaSemInternet extends AppCompatActivity {

    private Button btVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sem_internet);
    }

    public void voltarLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}