package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pagamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        ImageButton btnCarrinho = findViewById(R.id.imageButton7);
        Button btnPagar = findViewById(R.id.finalizar);
        TextView enderecoFinal = findViewById(R.id.textView31);

        semInternet();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Usuario").child(userId).child("endereco");

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String enderecoRua = String.valueOf(dataSnapshot.child("Rua").getValue());
                        String enderecoNumero = String.valueOf(dataSnapshot.child("numEndereco").getValue());
                        String enderecoComp = String.valueOf(dataSnapshot.child("complemento").getValue());
                        String enderecoCidade = String.valueOf(dataSnapshot.child("Cidade").getValue());
                        String enderecoBairro = String.valueOf(dataSnapshot.child("Bairro").getValue());
                        enderecoFinal.setText(enderecoRua + " n°" + enderecoNumero + " " + enderecoComp + " bairro " + enderecoBairro + " cidade " +enderecoCidade);
                    } else {
                        System.out.println("endereco cadastrado errado, entre em contato com suporte.");
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("Erro ao acessar o banco de dados: " + databaseError.getMessage());
                }
            });
        }

        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semInternet();
                PopUpQrCode pop = new PopUpQrCode(Pagamento.this);
                pop.show();
                onDestroy();
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