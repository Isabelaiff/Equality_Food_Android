package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Calendar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;

public class criarConta extends AppCompatActivity {

    EditText nome;
    EditText dataNasc;
    EditText CPF;
    EditText numTelefone;
    EditText email;
    EditText CEP;
    EditText numEndereco;
    EditText complemento;
    EditText senha;
    EditText confirmarSenha;
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
        confirmarSenha = findViewById(R.id.confirmarSenha);
        cadastrarButton = findViewById(R.id.cadastrar_button);


        database = FirebaseDatabase.getInstance();
        EqualityFoodRef = database.getReference();

//        EditText phoneNumberEditText = findViewById(R.id.numTelefone);
//        MaskWatcher phoneNumberMaskWatcher = new MaskWatcher();
//        phoneNumberEditText.addTextChangedListener(phoneNumberMaskWatcher);


        cadastrarButton.setOnClickListener(v -> {
            salvarUsuario();
        });

        dataNasc.setOnClickListener(v -> mostrarCalendario());

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(criarConta.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void mostrarCalendario() {
        dataNasc.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year1);
                        if (dataValida(selectedDate)) {
                            dataNasc.setText(selectedDate);
                        } else {
                            Toast.makeText(getApplicationContext(), "Data inválida ou formato incorreto. Por favor, insira uma data válida no formato DD/MM/AAAA.", Toast.LENGTH_SHORT).show();
                        }
                    }, year, month, day);

            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        });
    }

    private boolean dataValida(String date) {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            Date parsedDate = sdf.parse(date);

            if (sdf.format(parsedDate).equals(date)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
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

        String confirmarSenhaTexto = confirmarSenha.getText().toString();

        if (ValidarCPF.isCPF(cpfTexto) == false){
            Toast.makeText(getApplicationContext(), "Por favor, coloque um cpf válido.", Toast.LENGTH_SHORT).show();
        }
        if (!senhaTexto.equals(confirmarSenhaTexto)) {
            Toast.makeText(getApplicationContext(), "As senhas não correspondem. Por favor, verifique e tente novamente.", Toast.LENGTH_SHORT).show();
            return; // Não prossiga com o cadastro se as senhas não coincidirem
        }
        if (nomeTexto.isEmpty() || dataNascTexto.isEmpty() || cpfTexto.isEmpty() || numTelefoneTexto.isEmpty() ||
                emailTexto.isEmpty() || cepTexto.isEmpty() || numEnderecoTexto.isEmpty() || senhaTexto.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
        } else {
            // Verifique se a data de nascimento é válida
            if (dataValida(dataNascTexto)) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailTexto, senhaTexto)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Usuário criado com sucesso
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user != null) {
                                    // Armazene o número de telefone no Firebase Realtime Database
                                    DatabaseReference usuariosRef = FirebaseDatabase.getInstance().getReference().child("Usuario").push();

                                    // Outros dados do usuário
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

                                    // Usuário autenticado com sucesso e informações armazenadas no banco de dados
                                    Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(criarConta.this, PoliticaPrivacidade.class);
                                    startActivity(intent);
                                }
                            } else {
                                // Falha ao criar usuário
                                Toast.makeText(getApplicationContext(), "Falha ao criar usuário: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            } else {
                // A data de nascimento não é válida, exiba uma mensagem de erro
                Toast.makeText(getApplicationContext(), "Data de nascimento inválida ou formato incorreto. Por favor, insira uma data válida no formato DD/MM/AAAA.", Toast.LENGTH_SHORT).show();
            }
        }

        }
    }
