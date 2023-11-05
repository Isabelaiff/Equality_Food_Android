package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

//        ImageButton imageButton12 = findViewById(R.id.imageButton12);
//        imageButton12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Perfil.this, Foto.class);
//                startActivity(intent);
//            }
//        });

        Button editarInfo = findViewById(R.id.button);
        editarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, AtualizarPerfil.class);
                startActivity(intent);
            }
        });

        Button logout = findViewById(R.id.logout_button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(Perfil.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.btnPerfil2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btnPerfil2:
                        return true;
                    case R.id.imageButton4:
                        startActivity(new Intent(getApplicationContext(), Pedidos.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.imageButton:
                        startActivity(new Intent(getApplicationContext(), Carrinho.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_page1:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

}