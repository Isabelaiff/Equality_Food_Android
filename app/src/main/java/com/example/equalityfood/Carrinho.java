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
    private ListView lista;

    TextView valortotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        semInternet();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            double valor = bundle.getDouble("valor");
            String descricao = bundle.getString("descricao");
            String nome = bundle.getString("produto");
            String validade = bundle.getString("validade");
            String img = bundle.getString("imagem");

            Produto prod = new Produto(nome, img, valor);
//        produtos.add(prod);
            Selecionados.Adicionar(prod);

            lista = findViewById(R.id.listaview);
            valortotal = findViewById(R.id.total);
            ArrayList a = Selecionados.Listar();

            Adapter adapter = new Adapter(this, a, valortotal);
            lista.setAdapter(adapter);
        }


        ImageView voltarHome = findViewById(R.id.voltar);

        voltarHome.setOnClickListener(new View.OnClickListener() {
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