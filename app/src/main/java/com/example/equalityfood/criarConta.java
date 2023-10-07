package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class criarConta extends AppCompatActivity {

    TextView nome;
    TextView dataNasc;
    TextView CPF;
    TextView numTelefone;
    TextView email;
    TextView CEP;
    TextView numEndereco;
    TextView complemento;
    TextView senha;
    Button cadastrarButton;
    FirebaseDatabase database;
    DatabaseReference EqualityFoodRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);


        ImageButton voltar = findViewById(R.id.voltar_button);
        nome = findViewById(R.id.nomeCompleto);
        dataNasc = findViewById(R.id.dataNasc);
        CPF = findViewById(R.id.cpf);
        numTelefone = findViewById(R.id.numTelefone);
        email = findViewById(R.id.email);
        CEP = findViewById(R.id.cep);
        numEndereco = findViewById(R.id.numEndereco);
        complemento = findViewById(R.id.complemento);
        senha = findViewById(R.id.senha);
        cadastrarButton = findViewById(R.id.cadastrar_button);
        database = FirebaseDatabase.getInstance();
        EqualityFoodRef = database.getReference();

        cadastrarButton.setOnClickListener(v -> {
            salvarUsuario();
        });


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(criarConta.this, Login.class);
                startActivity(intent);
            }
        });

        //        Button cadastrar = findViewById(R.id.cadastrar_button);
//
//        cadastrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(criarConta.this, Login.class);
//                startActivity(intent);
//            }
//        });
    }

    private void salvarUsuario() {
        String nomeTexto = nome.getText().toString();
        String dataNascTexto = dataNasc.getText().toString();
        String cpfTexto = CPF.getText().toString();
        String numTelefoneTexto = numTelefone.getText().toString();
        String emailTexto = email.getText().toString();
        String cepTexto = CEP.getText().toString();
        String numEnderecoTexto = numEndereco.getText().toString();
        String complementoTexto = complemento.getText().toString();
        String senhaTexto = senha.getText().toString();

        // Verifique se os campos obrigatórios estão preenchidos
        if (nomeTexto.isEmpty() || cpfTexto.isEmpty() || numTelefoneTexto.isEmpty() || emailTexto.isEmpty() || cepTexto.isEmpty() || numEnderecoTexto.isEmpty() || senhaTexto.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference usuariosRef = EqualityFoodRef.child("Usuario").push();

            HashMap<String, String> enderecoMap = new HashMap<>();
            enderecoMap.put("CEP", cepTexto);
            enderecoMap.put("numEndereco", numEnderecoTexto);

            if (!complementoTexto.isEmpty()) {
                enderecoMap.put("complemento", complementoTexto);
            }

            usuariosRef.child("endereco").setValue(enderecoMap);
            usuariosRef.child("nome").setValue(nomeTexto);
            usuariosRef.child("dataNasc").setValue(dataNascTexto);
            usuariosRef.child("CPF").setValue(cpfTexto);
            usuariosRef.child("numTelefone").setValue(numTelefoneTexto);
            usuariosRef.child("email").setValue(emailTexto);
            usuariosRef.child("senha").setValue(senhaTexto);

            Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(criarConta.this, bemVindo.class);
            startActivity(intent);
        }
    }



}


