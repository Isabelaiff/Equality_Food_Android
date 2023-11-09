package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    List<String> produtos = new ArrayList<>();
    List<String> imgProd = new ArrayList<>();
    List<Double> precoProd = new ArrayList<>();
    List<String> descricao = new ArrayList<>();
    List<String> dataVal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView mostResult = findViewById(R.id.textView4);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        semInternet();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ArrayList<ProdutosAPI> produtosAPI = bundle.getParcelableArrayList("ResultadoPesquisa");
            for (int i=0; i < produtosAPI.size(); i++ ) {
                produtos.add(produtosAPI.get(i).getNome());
                precoProd.add(produtosAPI.get(i).getPreco());
                descricao.add(produtosAPI.get(i).getDescricao());
                imgProd.add(produtosAPI.get(i).getImagem());
            }
        }

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

                Intent intent = new Intent(ResultActivity.this, Produtos.class);
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