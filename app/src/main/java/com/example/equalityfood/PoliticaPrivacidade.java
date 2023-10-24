package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PoliticaPrivacidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politica_privacidade);

        Button finalizar2 = findViewById(R.id.finalizar2);
        finalizar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PoliticaPrivacidade.this, bemVindo.class);
                startActivity(intent);
            }
        });
    }


}