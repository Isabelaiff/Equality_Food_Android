package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class Pedidos extends AppCompatActivity {


    String[] status = {"Entregue"};
    String[] Data = {"dd/mes/ano"};
    double[] precoPedido = {80.00};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        ListView lista = findViewById(R.id.listaviewpedidos);
        AdapterPedidos adapter = new AdapterPedidos(this, status, Data, precoPedido);
        lista.setAdapter(adapter);

        ImageButton btnHome2 = findViewById(R.id.btnHome2);
        btnHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pedidos.this, home.class);
                startActivity(intent);
            }
        });

        ImageButton btnPerfil2 = findViewById(R.id.btnPerfil2);
        btnPerfil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pedidos.this, Perfil.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pedidos.this, Carrinho.class);
                startActivity(intent);

//                ListView list = (ListView) findViewById(android.R.id.list);
//                TextView emptyView = (TextView) findViewById(android.R.id.empty);
//                list.setEmptyView(emptyView);
            }
        });


        ImageButton close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}