package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerificarCodigo extends AppCompatActivity {

    private EditText inputCodigo;
    private Button btnEnviarCodigo;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_codigo);

        inputCodigo = findViewById(R.id.inputCodigo);
        btnEnviarCodigo = findViewById(R.id.btnCodigo);
        auth = FirebaseAuth.getInstance();
        ImageButton btnVoltar = findViewById(R.id.voltar_button2);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final String verificationId = getIntent().getStringExtra("verificationId");

        btnEnviarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigoInserido = inputCodigo.getText().toString().trim();
                if (!codigoInserido.isEmpty()) {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, codigoInserido);
                    signInWithPhoneAuthCredential(credential);
                } else {
                    Toast.makeText(VerificarCodigo.this, "Por favor, insira o código de verificação.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sucesso na verificação do número de telefone
                        // Você pode iniciar a próxima atividade ou executar qualquer ação necessária
                        Intent intent = new Intent(VerificarCodigo.this, AlterarSenha.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Falha na verificação do número de telefone
                        Toast.makeText(VerificarCodigo.this, "Código de verificação incorreto.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
