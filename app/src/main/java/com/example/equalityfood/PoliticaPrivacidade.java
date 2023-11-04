package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class PoliticaPrivacidade extends AppCompatActivity {

    private CheckBox checkBox;
    private Button finalizar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politica_privacidade);

        finalizar2 = findViewById(R.id.finalizar2);
        checkBox = findViewById(R.id.checkBox);

        finalizar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    Intent intent = new Intent(PoliticaPrivacidade.this, home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, marque a CheckBox para continuar.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}