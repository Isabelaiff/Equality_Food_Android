package com.example.equalityfood;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Carrinho extends AppCompatActivity {

    String[] produtos = {"Lasanha congelada Seara","Picanha Maturata", "Pão de Queijo"};
    Integer[] imgProd = {R.drawable.rectangleprod,R.drawable.rectangleprod, R.drawable.rectangleprod};
    Double[] precoProd = {80.00, 100.50, 30.0};
    TextView valortotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        ListView lista = findViewById(R.id.listaview);
        valortotal = findViewById(R.id.total);
        Adapter adapter = new Adapter(this, produtos, imgProd, precoProd, valortotal);
        lista.setAdapter(adapter);
        semInternet();
        somaTotal(precoProd);

        ImageView voltarHome = findViewById(R.id.voltar);

        voltarHome.setOnClickListener(new View.OnClickListener() {
            //não pode voltar, por questões de segurança
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrinho.this, home.class);
                startActivity(intent);
            }
        });


        Button btnCarrinho = findViewById(R.id.finalizar);

        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrinho.this, Pagamento.class);
                startActivity(intent);
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
    public void somaTotal(Double[] precoProd) {
        double soma = 0.0;

        for (double item : precoProd) {
            double valor = item;
            soma += valor;
            valortotal.setText(String.format("%.2f", soma));
        }

    }
}