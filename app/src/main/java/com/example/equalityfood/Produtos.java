package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Produtos  extends AppCompatActivity {
    TextView textView20;
    TextView textView16;
    ImageView imageView;
    TextView textView22;
    TextView textView25;
    ImageButton imageButton2;
    String img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        textView20 = findViewById(R.id.textView20);
        textView16 = findViewById(R.id.textView16);
        imageView = findViewById(R.id.imageView);
        textView22 = findViewById(R.id.textView22);
        textView25 = findViewById(R.id.textView25);
        imageButton2 = findViewById(R.id.imageButton2);

        Bundle bundle = getIntent().getExtras();

        double valor = bundle.getDouble("valor");
        String descricao = bundle.getString("descricao");
        String nome = bundle.getString("produto");
        String validade = bundle.getString("validade");
        img = bundle.getString("imagem");

        textView20.setText(String.valueOf(valor));
        textView16.setText(nome);
        textView22.setText(descricao);
        textView25.setText(validade);
        Glide.with(this)
                .load(img)
                .into(imageView);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Produtos.this, home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}