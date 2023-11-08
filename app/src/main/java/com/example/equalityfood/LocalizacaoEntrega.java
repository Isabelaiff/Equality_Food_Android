package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocalizacaoEntrega extends AppCompatActivity {

    ImageButton voltar;
    Button btnAlterarLoc;
    EditText novoCEP;
    EditText novoNum;
    EditText novoComplemento;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao_entrega);

        semInternet();

        mAuth = FirebaseAuth.getInstance();

        voltar = findViewById(R.id.voltar_button);
        btnAlterarLoc = findViewById(R.id.btnAlterarLoc);
        novoCEP = findViewById(R.id.novoCEP);
        novoNum = findViewById(R.id.novoNumero);
        novoComplemento = findViewById(R.id.novoComplemento);
        btnAlterarLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para verificar a conexão com a internet
                semInternet();

                // Obtenha o CEP inserido pelo usuário
                String novoCEPTexto = novoCEP.getText().toString();

                // API VIACEP
                String urlAPI = "https://viacep.com.br/ws/";

                // Configurar acesso à API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urlAPI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<CEP> call = apiService.recuperarCEP(novoCEPTexto);

                call.enqueue(new Callback<CEP>() {
                    @Override
                    public void onResponse(Call<CEP> call, Response<CEP> response) {
                        if (response.isSuccessful()) {
                            CEP cep = response.body();

                            // Obtenha os outros detalhes de endereço do usuário, como número e complemento
                            String novoNumTexto = novoNum.getText().toString();
                            String novoComplementoTexto = novoComplemento.getText().toString();

                            // Atualize o endereço no Firebase
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String uid = user.getUid();

                                DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference("Usuario").child(uid);
                                HashMap<String, Object> enderecoMap = new HashMap<>();
                                enderecoMap.put("CEP", novoCEPTexto);
                                enderecoMap.put("numEndereco", novoNumTexto);
                                enderecoMap.put("complemento", novoComplementoTexto);
                                enderecoMap.put("Rua", cep.getLogradouro());
                                enderecoMap.put("Bairro", cep.getBairro());
                                enderecoMap.put("Cidade", cep.getLocalidade());
                                enderecoMap.put("UF", cep.getUf());

                                usuarioRef.child("endereco").updateChildren(enderecoMap);

                                Toast.makeText(getApplicationContext(), "Informações de localização atualizadas com sucesso!", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(LocalizacaoEntrega.this, Perfil.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CEP> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha ao atualizar informações de endereço!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


//        btnAlterarLoc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseUser user = mAuth.getCurrentUser();
//                if (user != null) {
//                    String uid = user.getUid();
//
//                    String novoCEPTexto = novoCEP.getText().toString();
//                    String novoNumTexto = novoNum.getText().toString();
//                    String novoComplementoTexto = novoComplemento.getText().toString();
//
//                    DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference("Usuario").child(uid);
//                    HashMap<String, Object> enderecoMap = new HashMap<>();
//                    enderecoMap.put("CEP", novoCEPTexto);
//                    enderecoMap.put("numEndereco", novoNumTexto);
//                    enderecoMap.put("complemento", novoComplementoTexto);
//
//                    usuarioRef.child("endereco").updateChildren(enderecoMap);
//
//                    Toast.makeText(getApplicationContext(), "Informações de localização atualizadas com sucesso!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent(LocalizacaoEntrega.this, Perfil.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        });
//
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalizacaoEntrega.this, Perfil.class);
                startActivity(intent);
            }
        });

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