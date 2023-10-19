package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ListaLegumes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_legumes);

        ImageButton esqueceuSenha = findViewById(R.id.imageButton8);

        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaLegumes.this, home.class);
                startActivity(intent);
            }
        });

        Button VerCarrinho = findViewById(R.id.button2);

        VerCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaLegumes.this, Carrinho.class);
                startActivity(intent);
            }
        });
    }


}