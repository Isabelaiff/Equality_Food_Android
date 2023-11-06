package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.equalityfood.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    ActivityHomeBinding binding;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
            }
        });

        ImageView naoPerecivel = findViewById(R.id.imageView23);

        naoPerecivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaNaoPereciveis.class);
                startActivity(intent);
            }
        });


        ImageView imageButton10 = findViewById(R.id.imageView31);

        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaCongelados.class);
                startActivity(intent);
            }
        });


        ImageView imageButton11 = findViewById(R.id.imageView35);

        imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaCarneSuina.class);
                startActivity(intent);
            }
        });

        ImageView imageButton14 = findViewById(R.id.imageView36);

        imageButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaFrango.class);
                startActivity(intent);
            }
        });

        ImageView imageButton15 = findViewById(R.id.imageView37);

        imageButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaCarneBovina.class);
                startActivity(intent);
            }
        });

        ImageView imageView32 = findViewById(R.id.imageView32);

        imageView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, ListaLegumes.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.imageButton4:
                        startActivity(new Intent(getApplicationContext(), Pedidos.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.imageButton:
                        startActivity(new Intent(getApplicationContext(), Carrinho.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.btnPerfil2:
                        startActivity(new Intent(getApplicationContext(), Perfil.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_page1:
                        return true;
                }
                return false;
            }
        });
    }

    private void performSearch (String query){
        Toast.makeText(this, "Pesquisando por: " + query, Toast.LENGTH_SHORT).show();
    }
}