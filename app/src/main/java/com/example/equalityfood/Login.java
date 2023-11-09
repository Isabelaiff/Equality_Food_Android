package com.example.equalityfood;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1001;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    SignInButton EntrarGoogle;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser usuarioLogin = auth.getCurrentUser();

        if (usuarioLogin != null){
            Intent intent = new Intent(Login.this, home.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button entrarButton = findViewById(R.id.button3);
        TextView campoEmail = findViewById(R.id.campoEmail);
        EditText campoSenha = findViewById(R.id.campoSenha);
        Button esqueceuSenha = findViewById(R.id.esqueceuSenha);

        semInternet();

        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTexto = campoEmail.getText().toString().trim();
                String senhaTexto = campoSenha.getText().toString().trim();

                semInternet();

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


        Button criarConta = findViewById(R.id.criarConta);

        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, criarConta.class);
                startActivity(intent);
            }
        });

        //Login com google

        EntrarGoogle = findViewById(R.id.sign_in_button2);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        EntrarGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });

    }

    void signInWithGoogle() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                Toast.makeText(this, "Não é possível logar com o Google por enquanto...", Toast.LENGTH_SHORT).show();
//                fazerLogin();
            } catch (ApiException e) {
                Toast.makeText(this, "Algo deu errado, tente novamente!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void fazerLogin() {
        finish();
        Intent intent = new Intent(Login.this, home.class);
        startActivity(intent);
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
