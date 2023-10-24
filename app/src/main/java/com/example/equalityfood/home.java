package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

public class home extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        ImageButton btnPerfil = findViewById(R.id.btnPerfil2);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, Perfil.class);
                startActivity(intent);
            }
        });

        ImageButton btnCarrinho = findViewById(R.id.imageButton);

        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, Carrinho.class);
                startActivity(intent);
            }
        });

        ImageButton btnCongelados = findViewById(R.id.imageButton9);

        btnCongelados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaCongelados.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton4 = findViewById(R.id.imageButton4);

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, Pedidos.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton5 = findViewById(R.id.imageButton5);

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaNaoPereciveis.class);
                startActivity(intent);
            }
        });



        ImageButton imageButton10 = findViewById(R.id.imageButton10);

        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaLegumes.class);
                startActivity(intent);
            }
        });



        ImageButton imageButton11 = findViewById(R.id.imageButton11);

        imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaCarneSuina.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton14 = findViewById(R.id.imageButton14);

        imageButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaFrango.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton15 = findViewById(R.id.imageButton15);

        imageButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaCarneBovina.class);
                startActivity(intent);
            }
        });






    }
    private void performSearch(String query) {
        Toast.makeText(this, "Pesquisando por: " + query, Toast.LENGTH_SHORT).show();
    }

}
