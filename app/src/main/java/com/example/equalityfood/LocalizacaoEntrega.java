package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LocalizacaoEntrega extends AppCompatActivity {

    ImageButton voltar = findViewById(R.id.imageButton5);
    Button concluido = findViewById(R.id.btnCodigo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao_entrega);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalizacaoEntrega.this, Perfil.class);
                startActivity(intent);
            }
        });

        concluido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalizacaoEntrega.this, Perfil.class);
                startActivity(intent);
            }
        });
    }
}