package com.example.equalityfood;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import android.widget.ImageButton;

public class PopUpConfirmacaoPedido extends Dialog {

    public PopUpConfirmacaoPedido(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pop_up_confirmacao_pedido);

        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Pedidos.class);
                getContext().startActivity(intent);
            }
        });
    }
}
