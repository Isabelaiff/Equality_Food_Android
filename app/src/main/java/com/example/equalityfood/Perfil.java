package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView nomeVis = findViewById(R.id.textView23);
        TextView emailVis = findViewById(R.id.textView24);
        TextView numeroVis = findViewById(R.id.textView26);
        Button btnPolitica = findViewById(R.id.btnPolitica);
        TextView textViewEmail = findViewById(R.id.textView34);
        Button localizacao = findViewById(R.id.textView31);




        localizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, LocalizacaoEntrega.class);
                startActivity(intent);
            }
        });

        btnPolitica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, PoliticaPrivacidade.class);
                startActivity(intent);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String nome = user.getDisplayName();
            String email = user.getEmail();
            String numero = user.getPhoneNumber();

            nomeVis.setText(nome);
            emailVis.setText(email);
                numeroVis.setText(numero);
        }


        Button textView32 = findViewById(R.id.textView32);
        textView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, EsqueceuSenha.class);
                startActivity(intent);
            }
        });


        Button textView30 = findViewById(R.id.btnPolitica);
        textView30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, PoliticaPrivacidade.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton12 = findViewById(R.id.imageButton12);
        imageButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, Foto.class);
                startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.imageButton4:
                        Intent intent = new Intent(Perfil.this, Pedidos.class);
                        startActivity(intent);
                        return true;
                    case R.id.imageButton:
                        Intent carrinho = new Intent(Perfil.this, Carrinho.class);
                        startActivity(carrinho);
                        return true;
                    case R.id.menu_page1:
                        Intent perfil = new Intent(Perfil.this, home.class);
                        startActivity(perfil);
                        return true;
                }
                return false;
            }
        });
    }
}

