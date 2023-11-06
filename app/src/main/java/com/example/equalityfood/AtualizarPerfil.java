package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AtualizarPerfil extends AppCompatActivity {

    private EditText alterarNome;
    private EditText alterarNumero;
    private EditText alterarEmail;
    private Button btnAlterar;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_perfil);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        alterarNome = findViewById(R.id.nomeCompleto);
        alterarNumero = findViewById(R.id.numTelefone);
        alterarEmail = findViewById(R.id.email);
        btnAlterar = findViewById(R.id.cadastrar_button);

        semInternet();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            alterarNome.setText(user.getDisplayName());
            alterarEmail.setText(user.getEmail());
            alterarNumero.setText(user.getPhoneNumber());
        }
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenha os novos dados dos campos de texto
                String novoNome = alterarNome.getText().toString().trim();
                String novoNumero = alterarNumero.getText().toString().trim();
                String novoEmail = alterarEmail.getText().toString().trim();

                // Valide os campos (adicione validações conforme necessário)
                if (TextUtils.isEmpty(novoNome) || TextUtils.isEmpty(novoNumero) || TextUtils.isEmpty(novoEmail)) {
                    Toast.makeText(AtualizarPerfil.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {
                    // Atualize os dados do usuário
                    atualizarDados(novoNome, novoNumero, novoEmail);
                }
            }
        });
    }

    private void atualizarDados(String novoNome, String novoNumero, String novoEmail) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userID = user.getUid();
            DatabaseReference usuarioRef = mDatabase.child("usuarios").child(userID);

            user.updateProfile(new UserProfileChangeRequest.Builder()
                            .setDisplayName(novoNome)
                            .build())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            usuarioRef.child("nome").setValue(novoNome);
                            usuarioRef.child("numero").setValue(novoNumero);
                            usuarioRef.child("email").setValue(novoEmail);

                            Toast.makeText(AtualizarPerfil.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Falha ao atualizar dados
                            Toast.makeText(AtualizarPerfil.this, "Falha ao atualizar dados. Por favor, tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
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
