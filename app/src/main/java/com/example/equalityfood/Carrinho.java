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

import java.util.ArrayList;


public class Carrinho extends AppCompatActivity {

    private ArrayList<Produto> produtos;
    private ListView lista;

//    String[] produtos = {"Lasanha congelada Seara", "Picanha Maturata", "Pão de Queijo"};
//    Integer[] imgProd = {R.drawable.rectangleprod, R.drawable.rectangleprod, R.drawable.rectangleprod};
//    Double[] precoProd = {80.00, 100.50, 30.0};
    TextView valortotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        lista = findViewById(R.id.listaview);
        valortotal = findViewById(R.id.total);
//        Adapter adapter = new Adapter(this, produtos, imgProd, precoProd, valortotal);
//        lista.setAdapter(adapter);
        semInternet();
//        somaTotal(precoProd);

        ImageView voltarHome = findViewById(R.id.voltar);

        voltarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrinho.this, home.class);
                startActivity(intent);
            }
        });

        produtos = new ArrayList<>();
        produtos.add(new Produto("Pão de Queijo", R.drawable.rectangleprod,  10));
        produtos.add(new Produto("Lasanha ", R.drawable.rectangleprod,  25));
        produtos.add(new Produto("Picanha", R.drawable.rectangleprod,  40));

        Adapter adapter = new Adapter(this, produtos, valortotal);
        lista.setAdapter(adapter);


        Button btnCarrinho = findViewById(R.id.finalizar);

        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String total = valortotal.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("TOTAL", total);
                Intent intent = new Intent(Carrinho.this, Pagamento.class);
                intent.putExtras(bundle);
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

}