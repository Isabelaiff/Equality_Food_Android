package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AtualizarPerfil extends AppCompatActivity {

    private EditText novoNome;
    private EditText novoNum;
    private EditText novoEmail;
    private Button btnAlterar;
    private ImageButton voltar_button;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_perfil);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Usuario");

        novoNome = findViewById(R.id.nomeCompleto);
        novoNum = findViewById(R.id.numTelefone);
        novoEmail = findViewById(R.id.email);
        btnAlterar = findViewById(R.id.cadastrar_button);
        voltar_button = findViewById(R.id.voltar_button);

        semInternet();



        voltar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    String uid = user.getUid();
                    String novoNomeString = novoNome.getText().toString().trim();
                    String novoNumString = novoNum.getText().toString().trim();
                    String novoEmailString = novoEmail.getText().toString().trim();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(novoNomeString)
                            .build();

                    user.updateProfile(profileUpdates);
                    user.updateEmail(novoEmailString);

                    // Atualizar os dados no banco de dados
                    Map<String, Object> updates = new HashMap<>();
                    if (!TextUtils.isEmpty(novoNomeString)) {
                        updates.put("nome", novoNomeString);
                    }
                    if (!TextUtils.isEmpty(novoNumString)) {
                        updates.put("numTelefone", novoNumString);
                    }
                    if (!TextUtils.isEmpty(novoEmailString)) {
                        updates.put("email", novoEmailString);
                    }

                    mDatabase.child(uid).updateChildren(updates)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AtualizarPerfil.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AtualizarPerfil.this, "Falha ao atualizar dados. Por favor, tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(AtualizarPerfil.this, "Usuário não encontrado. Faça login novamente.", Toast.LENGTH_SHORT).show();
                    // Redirecione para a tela de login se o usuário não estiver autenticado
                    Intent intent = new Intent(AtualizarPerfil.this, Login.class);
                    startActivity(intent);
                    finish();
                }
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
