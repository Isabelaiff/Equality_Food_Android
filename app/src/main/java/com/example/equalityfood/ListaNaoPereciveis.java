package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ListaNaoPereciveis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nao_pereciveis);

        ImageButton esqueceuSenha = findViewById(R.id.imageButton8);

        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaNaoPereciveis.this, home.class);
                startActivity(intent);
            }
        });

        Button VerCarrinho = findViewById(R.id.button2);

        VerCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaNaoPereciveis.this, Carrinho.class);
                startActivity(intent);
            }
        });
    }


}