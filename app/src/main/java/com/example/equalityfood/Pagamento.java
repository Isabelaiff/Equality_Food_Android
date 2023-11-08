package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Pagamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        ImageButton btnCarrinho = findViewById(R.id.imageButton7);
        Button btnPagar = findViewById(R.id.finalizar);
        TextView subtotal = findViewById(R.id.subtotal);
        TextView TotalFinal = findViewById(R.id.sub);
        semInternet();

        Bundle bundle = getIntent().getExtras();
        String total = bundle.getString("TOTAL");
        Double valorTotal = Double.parseDouble(total.replace(",", "."));
        valorTotal += 5;
        String totalComAcrescimo = String.format("R$%.2f", valorTotal);
        subtotal.setText("R$" + total);
        TotalFinal.setText(totalComAcrescimo);

        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpQrCode pop = new PopUpQrCode(Pagamento.this);
                pop.show();
                onDestroy();
            }
        });
    }

    public void semInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            // Abre a tela de sem internet
            Intent intent = new Intent(this, telaSemInternet.class);
            startActivity(intent);
        }
    }
}