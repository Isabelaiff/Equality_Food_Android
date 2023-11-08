package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

        public class ListaLegumes extends AppCompatActivity {
            List<String> produtos = new ArrayList<>();
            List<String> imgProd = new ArrayList<>();
            List<Double> precoProd = new ArrayList<>();
            List<String> descricao = new ArrayList<>();
            List<String> dataVal = new ArrayList<>();

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_lista_legumes);
                Bundle bundle = getIntent().getExtras();
                ImageButton voltar = findViewById(R.id.voltarHome);
                ListView lista = findViewById(R.id.lista);
                AdapterListaProdutos adapter = new AdapterListaProdutos(this, imgProd, produtos, descricao, precoProd, dataVal);
                lista.setAdapter(adapter);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        // Pass the product information to the Produtos activity
                        Bundle bundle = new Bundle();
                        bundle.putString("produto", produtos.get(i));
                        bundle.putString("descricao", descricao.get(i));
                        bundle.putDouble("valor", precoProd.get(i));
                        bundle.putString("imagem", imgProd.get(i));
                        bundle.putString("validade", dataVal.get(i));

                        Intent intent = new Intent(ListaLegumes.this, Produtos.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                semInternet();

                if (bundle != null) {
                    ArrayList<ProdutosAPI> produtosAPI = bundle.getParcelableArrayList("listaDeLegumes");
                    for (int i = 0; i < produtosAPI.size(); i++) {
                        produtos.add(produtosAPI.get(i).getNome());
                        precoProd.add(produtosAPI.get(i).getPreco());
                        descricao.add(produtosAPI.get(i).getDescricao());
                        imgProd.add(produtosAPI.get(i).getImagem());
                        dataVal.add(produtosAPI.get(i).getData_validade());
                    }
                }

                voltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListaLegumes.this, home.class);
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