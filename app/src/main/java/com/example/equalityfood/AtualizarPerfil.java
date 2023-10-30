package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AtualizarPerfil extends AppCompatActivity {

    private EditText alterarNome;
    private EditText alterarNumero;
    private EditText alterarEmail;

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

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            alterarNome.setText(user.getDisplayName());
            alterarEmail.setText(user.getEmail());
            alterarNumero.setText(user.getPhoneNumber());
        }
    }

    private void atualizarDados() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String novoNome = alterarNome.getText().toString();
            String novoNumero = alterarNumero.getText().toString();
            String novoEmail = alterarEmail.getText().toString();

            user.updateProfile(new UserProfileChangeRequest.Builder()
                            .setDisplayName(novoNome)
                            .build())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DatabaseReference usuarioRef = mDatabase.child("usuarios").child(user.getUid());
                            usuarioRef.child("nome").setValue(novoNome);
                            usuarioRef.child("numero").setValue(novoNumero);
                            usuarioRef.child("email").setValue(novoEmail);
                        } else {
                            //Falha
                        }
                    });
        }
    }
}
