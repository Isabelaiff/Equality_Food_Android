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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AlterarSenha extends AppCompatActivity {

    private EditText inputSenha;
    private Button btnAlterarSenha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        inputSenha = findViewById(R.id.inputSenha);
        btnAlterarSenha = findViewById(R.id.btnAlterarSenha);
        ImageButton btnVoltar = findViewById(R.id.voltar_button2);
        auth = FirebaseAuth.getInstance();

        semInternet();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            //usuario não consegue voltar por questões de segurança
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAlterarSenha.setOnClickListener(new View.OnClickListener() {
            // btnAlterarSenha salva a nova senha
            @Override
            public void onClick(View v) {
                FirebaseUser user = auth.getCurrentUser();
                String novaSenha = inputSenha.getText().toString().trim();

                if (user != null) {
                    user.updatePassword(novaSenha)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AlterarSenha.this, "Senha alterada com sucesso.", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(AlterarSenha.this, "Falha ao alterar a senha. Tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
    public void semInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            // Abre a tela de sem internet
            Intent intent = new Intent(AlterarSenha.this, telaSemInternet.class);
            startActivity(intent);
        }
    }
}
