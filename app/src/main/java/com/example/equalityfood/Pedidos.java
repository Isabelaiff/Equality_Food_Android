package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

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


        ImageView close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pedidos.this, home.class);
                startActivity(intent);
            }
        });


    }
}