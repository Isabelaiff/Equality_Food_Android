package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;

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

        semInternet();

        cadastrarButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Endereco endereco = new Endereco();
           //API VIACEP//
           String urlAPI = "https://viacep.com.br/ws/";
           //configurar acesso a API
           Retrofit retrofit = new Retrofit.Builder()
                   .baseUrl(urlAPI)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();

           ApiService apiService = retrofit.create(ApiService.class);
           Call<CEP> call = apiService.recuperarCEP(CEP.getText().toString());

           call.enqueue(new Callback<CEP>() {
               @Override
               public void onResponse(Call<CEP> call, Response<CEP> response) {
                   if (response.isSuccessful()) {
                       CEP cep = response.body();
                       endereco.setRua(cep.getLogradouro());
                       endereco.setBairro(cep.getBairro());
                       endereco.setCidade(cep.getLocalidade());
                       endereco.setUf(cep.getUf());
                       salvarUsuario(endereco);
                   }
               }

               @Override
               public void onFailure(Call<CEP> call, Throwable t) {
                   Toast.makeText(getApplicationContext(), "Falha ao cadastrar informações de endereço!", Toast.LENGTH_SHORT).show();
               }
           });
       }
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

    private void salvarUsuario(Endereco endereco) {
        final String[] nomeTexto = {nome.getText().toString()};
        String dataNascTexto = dataNasc.getText().toString();
        String cpfTexto = CPF.getText().toString();
        String numTelefoneTexto = numTelefone.getText().toString();
        String emailTexto = email.getText().toString();
        String cepTexto = CEP.getText().toString();
        String numEnderecoTexto = numEndereco.getText().toString();
        String complementoTexto = complemento.getText().toString();
        String senhaTexto = senha.getText().toString();
        String confirmarSenhaTexto = confirmarSenha.getText().toString();

        if (ValidaCPF.isCPF(cpfTexto) == false){
            Toast.makeText(getApplicationContext(), "Por favor, coloque um cpf válido.", Toast.LENGTH_SHORT).show();
        }
        if (!senhaTexto.equals(confirmarSenhaTexto)) {
            Toast.makeText(getApplicationContext(), "As senhas não correspondem. Por favor, verifique e tente novamente.", Toast.LENGTH_SHORT).show();
            return; // Não prossiga com o cadastro se as senhas não coincidirem
        }
        if (nomeTexto[0].isEmpty() || dataNascTexto.isEmpty() || cpfTexto.isEmpty() || numTelefoneTexto.isEmpty() ||
                emailTexto.isEmpty() || cepTexto.isEmpty() || numEnderecoTexto.isEmpty() || senhaTexto.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
        }
        if (!ValidaCPF.isCPF(cpfTexto)) {
            Toast.makeText(getApplicationContext(), "CPF inválido. Por favor, verifique e tente novamente.", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            // Verifique se a data de nascimento é válida
            if (dataValida(dataNascTexto)) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailTexto, senhaTexto)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Usuário criado com sucesso
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                // Atualizando nome
                                UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(nomeTexto[0]).build();
                                user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (!task.isSuccessful()){
                                            Log.i("ERRO profile", "Erro ao atualizar o nome");
                                        }
                                    }
                                });

                                if (user != null) {
                                    // Armazene o número de telefone no Firebase
                                    String uid = user.getUid();
                                    DatabaseReference usuariosRef = FirebaseDatabase.getInstance().getReference().child("Usuario").child(uid);



                                    // Outros dados do usuário
                                    HashMap<String, String> enderecoMap = new HashMap<>();
                                    enderecoMap.put("CEP", cepTexto);
                                    enderecoMap.put("numEndereco", numEnderecoTexto);
                                    enderecoMap.put("Rua", endereco.getRua());
                                    enderecoMap.put("Bairro", endereco.getBairro());
                                    enderecoMap.put("Cidade", endereco.getCidade());
                                    enderecoMap.put("UF", endereco.getUf());

//
                                    if (!complementoTexto.isEmpty()) {
                                        enderecoMap.put("complemento", complementoTexto);
                                    }

                                    usuariosRef.child("endereco").setValue(enderecoMap);
                                    usuariosRef.child("nome").setValue(nomeTexto[0]);
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
