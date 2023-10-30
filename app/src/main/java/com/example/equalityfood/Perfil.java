package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ImageButton btnHome2 = findViewById(R.id.btnHome);
        TextView nomeVis = findViewById(R.id.textView23);
        TextView emailVis = findViewById(R.id.textView24);
        TextView numeroVis = findViewById(R.id.textView26);
        Button btnPolitica = findViewById(R.id.btnPolitica);
        TextView textViewEmail = findViewById(R.id.textView34);
        Button localizacao = findViewById(R.id.textView31);


        textViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailContent = textViewEmail.getText().toString();
                sendEmail(emailContent);
            }
        });


        localizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, LocalizacaoEntrega.class);
                startActivity(intent);
            }
        });

        btnHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, home.class);
                startActivity(intent);
            }
        });

        btnPolitica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, PoliticaPrivacidade.class);
                startActivity(intent);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String nome = user.getDisplayName();
            String email = user.getEmail();
            String numero = user.getPhoneNumber();

            nomeVis.setText(nome);
            emailVis.setText(email);
                numeroVis.setText(numero);
        }


        ImageButton imageButton13 = findViewById(R.id.imageButton13);

        imageButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, Carrinho.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, Pedidos.class);
                startActivity(intent);
            }
        });



        Button textView32 = findViewById(R.id.textView32);
        textView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, EsqueceuSenha.class);
                startActivity(intent);
            }
        });


        Button textView30 = findViewById(R.id.btnPolitica);
        textView30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, PoliticaPrivacidade.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton12 = findViewById(R.id.imageButton12);
        imageButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, Foto.class);
                startActivity(intent);
            }
        });

    }
    private void sendEmail(String content) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Assunto do e-mail");
        emailIntent.putExtra(Intent.EXTRA_TEXT, content);

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}