package com.example.equalityfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button entrarButton = findViewById(R.id.button3);
        TextView campoEmail = findViewById(R.id.campoEmail);
        EditText campoSenha = findViewById(R.id.campoSenha);
        Button esqueceuSenha = findViewById(R.id.esqueceuSenha);

        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTexto = campoEmail.getText().toString().trim();
                String senhaTexto = campoSenha.getText().toString().trim();

                if (emailTexto.isEmpty() || senhaTexto.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailTexto, senhaTexto)
                            .addOnCompleteListener(Login.this, task -> {
                                if (task.isSuccessful()) {
                                    // Login bem-sucedido
                                    Toast.makeText(getApplicationContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Falha no login do usuário
                                    Toast.makeText(getApplicationContext(), "Falha no login: e-mail ou senha incorretos ", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });


        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, EsqueceuSenha.class);
                startActivity(intent);
            }
        });

        SignInButton EntrarGoogle = findViewById(R.id.sign_in_button2);

        EntrarGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Login.class);
                startActivity(intent);
            }
        });

        Button criarConta = findViewById(R.id.criarConta);

        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, criarConta.class);
                startActivity(intent);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        com.google.android.gms.common.SignInButton signInButton = findViewById(R.id.sign_in_button2);
        signInButton.setOnClickListener(view -> {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                String displayName = account != null ? account.getDisplayName() : "";
                Toast.makeText(this, "Bem-vindo, " + displayName + "!", Toast.LENGTH_SHORT).show();
            } else {
                Status status = result.getStatus();
                Toast.makeText(this, "Falha na autenticação: " + status, Toast.LENGTH_SHORT).show();
                System.out.println(Toast.LENGTH_SHORT);
            }
        }
    }
}
