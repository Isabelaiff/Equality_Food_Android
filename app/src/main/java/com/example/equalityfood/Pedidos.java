package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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


        ImageButton close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}