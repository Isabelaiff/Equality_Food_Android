package com.example.equalityfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class bemVindo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        Button btnVamosLa = findViewById(R.id.btnVamosLa);

        btnVamosLa.setOnClickListener(new View.OnClickListener() {
            //pelo botão "btnVamosLa" direciona para tela de login
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bemVindo.this, Login.class);
                startActivity(intent);
            }
        });
    }
}