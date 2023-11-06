package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Produtos  extends AppCompatActivity {

    private int count = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        textView = findViewById(R.id.textView26);
        Button buttonIncrement = findViewById(R.id.btnVoltaLogin);
        Button button = findViewById(R.id.button3);

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                textView.setText(String.valueOf(count));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 0) {
                    count--;
                    textView.setText(String.valueOf(count));
                }
            }
        });
    }
}