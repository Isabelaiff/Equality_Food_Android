package com.example.equalityfood;

import android.text.Editable;
import android.text.TextWatcher;

public class MaskWatcher implements TextWatcher {
    private boolean isRunning = false;
    private boolean isDeleting = false;

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        isDeleting = count > after;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (isRunning || isDeleting) {
            return;
        }
        isRunning = true;

        // Obtém o texto sem a máscara atual
        String originalText = editable.toString().replaceAll("[^\\d]", "");

        if (originalText.length() > 0) {
            StringBuilder formattedText = new StringBuilder();

            // Adiciona o código de área
            if (originalText.length() >= 2) {
                formattedText.append("(")
                        .append(originalText.substring(0, 2))
                        .append(") ");
            }

            // Adiciona os próximos 5 dígitos
            if (originalText.length() >= 7) {
                formattedText.append(originalText.substring(2, 7))
                        .append("-");
            }

            // Adiciona os últimos 4 dígitos
            if (originalText.length() >= 11) {
                formattedText.append(originalText.substring(7, 11));
            }

            // Define o texto formatado no EditText
            editable.replace(0, editable.length(), formattedText.toString());
        }

        isRunning = false;
    }
}
