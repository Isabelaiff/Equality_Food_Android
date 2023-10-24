package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ImageButton btnHome2 = findViewById(R.id.btnHome);

        TextView nomeVis = findViewById(R.id.textView23);
        TextView emailVis = findViewById(R.id.textView24);
        TextView numeroVis = findViewById(R.id.textView26);



        btnHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, home.class);
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


        ImageButton imageButton13 = findViewById(R.id.imageButton13);

        imageButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, Carrinho.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, Pedidos.class);
                startActivity(intent);
            }
        });



        Button textView32 = findViewById(R.id.textView32);
        textView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, EsqueceuSenha.class);
                startActivity(intent);
            }
        });


        Button textView30 = findViewById(R.id.textView30);
        textView30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, PoliticaPrivacidade.class);
                startActivity(intent);
            }
        });

    }
}