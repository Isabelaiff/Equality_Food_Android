package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class EsqueceuSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        Button btnCodigo = findViewById(R.id.btnCodigo);
        EditText inputEmail = findViewById(R.id.inputEmail);

        btnCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(EsqueceuSenha.this, "Por favor, insira seu e-mail.", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                                @Override
                                public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                    if (task.isSuccessful()) {
                                        SignInMethodQueryResult result = task.getResult();

                                        if (result != null && result.getSignInMethods() != null && result.getSignInMethods().size() > 0) {
                                            auth.sendPasswordResetEmail(email)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(EsqueceuSenha.this, "E-mail de redefinição de senha enviado. Verifique seu e-mail.", Toast.LENGTH_SHORT).show();
                                                                Intent intent = new Intent(EsqueceuSenha.this, Login.class);
                                                                startActivity(intent);
                                                                finish();
                                                            } else {
                                                                Toast.makeText(EsqueceuSenha.this, "Falha ao enviar e-mail de redefinição de senha. Por favor, tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        } else {
                                            Toast.makeText(EsqueceuSenha.this, "Este e-mail não está cadastrado.", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(EsqueceuSenha.this, "Erro ao verificar o e-mail. Por favor, tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
