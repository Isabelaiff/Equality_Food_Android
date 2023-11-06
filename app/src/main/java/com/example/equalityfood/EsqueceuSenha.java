package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.ImageButton;

import java.util.concurrent.TimeUnit;

public class EsqueceuSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        Button btnCodigo = findViewById(R.id.btnCodigo);
        EditText inputEmail = findViewById(R.id.inputEmail);
        ImageButton btnVoltar = findViewById(R.id.voltar_button2);

        semInternet();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            //aplicativo será fechado caso o usuario tente voltar
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCodigo.setOnClickListener(new View.OnClickListener() {
            //processo para enviar o codigo para redefinição de senha
            @Override
            public void onClick(View v) {
                String phoneNumber = inputEmail.getText().toString();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(EsqueceuSenha.this, "Por favor, insira seu número de telefone.", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,        // Número de telefone a ser verificado
                            60,                 // Tempo de timeout
                            TimeUnit.SECONDS,   // Unidade de tempo para o timeout
                            EsqueceuSenha.this, // Activity para callback
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    // Caso a verificação seja bem-sucedida automaticamente
                                    // Você pode usar o phoneAuthCredential para criar o usuário se necessário
                                    // Exemplo: auth.createUserWithEmailAndPassword(email, password);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    // Caso a verificação falhe
                                    Toast.makeText(EsqueceuSenha.this, "Falha ao verificar o número de telefone.", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId,
                                                       @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    // Callback para quando o código de verificação é enviado com sucesso
                                    // Guarde o verificationId para usar posteriormente
                                    // e chame uma nova activity para inserir o código de verificação
                                    Intent intent = new Intent(EsqueceuSenha.this, VerificarCodigo.class);
                                    intent.putExtra("verificationId", verificationId);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });

//        btnCodigo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = inputEmail.getText().toString();
//
//                if (email.isEmpty()) {
//                    Toast.makeText(EsqueceuSenha.this, "Por favor, insira seu e-mail.", Toast.LENGTH_SHORT).show();
//                } else {
//                    auth.sendPasswordResetEmail(email)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(EsqueceuSenha.this, "E-mail de redefinição de senha enviado. Verifique seu e-mail.", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(EsqueceuSenha.this, Login.class);
//                                        startActivity(intent);
//                                        finish();
//                                    } else {
//                                        Toast.makeText(EsqueceuSenha.this, "Falha ao enviar e-mail de redefinição de senha. Por favor, tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                }
//            }
//        });
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

