package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LocalizacaoEntrega extends AppCompatActivity {

    ImageButton voltar;
    Button btnAlterarLoc;
    EditText novoCEP;
    EditText novoNum;
    EditText novoComplemento;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao_entrega);

        semInternet();

        mAuth = FirebaseAuth.getInstance();

        voltar = findViewById(R.id.voltar_button);
        btnAlterarLoc = findViewById(R.id.btnAlterarLoc);
        novoCEP = findViewById(R.id.novoCEP);
        novoNum = findViewById(R.id.novoNumero);
        novoComplemento = findViewById(R.id.novoComplemento);
        mAuth = FirebaseAuth.getInstance();

        btnAlterarLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    String uid = user.getUid();

                    String novoCEPTexto = novoCEP.getText().toString();
                    String novoNumTexto = novoNum.getText().toString();
                    String novoComplementoTexto = novoComplemento.getText().toString();

                    DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference("Usuario").child(uid);
                    HashMap<String, Object> enderecoMap = new HashMap<>();
                    enderecoMap.put("CEP", novoCEPTexto);
                    enderecoMap.put("numEndereco", novoNumTexto);
                    enderecoMap.put("complemento", novoComplementoTexto);

                    usuarioRef.child("endereco").updateChildren(enderecoMap);

                    Toast.makeText(getApplicationContext(), "Informações de localização atualizadas com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LocalizacaoEntrega.this, Perfil.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalizacaoEntrega.this, Perfil.class);
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