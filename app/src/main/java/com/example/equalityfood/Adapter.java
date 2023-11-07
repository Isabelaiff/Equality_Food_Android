package com.example.equalityfood;

import static android.system.Os.remove;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Arrays;
import androidx.annotation.NonNull;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context applicationContext;
    private LayoutInflater inflater;
    private String[] prod;
    private Integer[] img;
    private Integer[] qtdvetlor;
    private Double[] precoVet;
    TextView valortotal;

    private double soma = 0.0;


    public Adapter(Context applicationContext, String[] prod, Integer[] img, Double[] precoVet, TextView valortotal) {
        this.applicationContext = applicationContext;
        this.prod = prod;
        this.img = img;
        this.precoVet = precoVet;
        this.inflater = LayoutInflater.from(applicationContext);
        this.valortotal = valortotal;
        for (double item : precoVet) {
            soma += item;
            valortotal.setText(String.valueOf(soma));
        }
    }

    @Override
    public int getCount() {
        return prod.length;
    }

    @Override
    public Object getItem(int position) {
        return prod.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.activity_adapter, null);

        TextView valorTotal = view.findViewById(R.id.total);
        ImageView card = view.findViewById(R.id.imageView2);
        Button removeButton = view.findViewById(R.id.button);
        ImageView icon = view.findViewById(R.id.imgProd);
        TextView nome = view.findViewById(R.id.produto);
        TextView precoView = view.findViewById(R.id.preco);
        TextView textViewQtd = view.findViewById(R.id.qtd);
        Button buttonIncrement = view.findViewById(R.id.mais);
        Button button = view.findViewById(R.id.menos);
        TextView valor = view.findViewById(R.id.produto2);
        TextView moeda = view.findViewById(R.id.moeda);
        ConstraintLayout constraint = view.findViewById(R.id.constraint);


        nome.setText(prod[i]);
        precoView.setText(String.valueOf(precoVet[i]));
        icon.setImageResource(img[i]);
        textViewQtd.setText("1");

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int[] novoArray = new int[prod.length - 1];
//
//                // Copie os elementos do array original para o novo array, excluindo o elemento na posição 'posicaoRemover'
//                for (int k = 0, j = 0; k < prod.length; k++) {
//                    if (k != i) {
//                        novoArray[j] = Integer.parseInt(prod[k]);
//                        j++;
//                    }
//                }

                removeButton.setVisibility(View.GONE);
                icon.setVisibility(View.GONE);
                nome.setVisibility(View.GONE);
                precoView.setVisibility(View.GONE);
                textViewQtd.setVisibility(View.GONE);
                buttonIncrement.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                valor.setVisibility(View.GONE);
                card.setVisibility(View.GONE);
                moeda.setVisibility(View.GONE);



            }
        });


        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtd = Integer.parseInt(textViewQtd.getText().toString());
                qtd++;

                textViewQtd.setText(String.valueOf(qtd));
                double novoPreco = precoVet[i] * qtd;
                precoView.setText(String.format("%.2f", novoPreco));
                somaTotal(precoVet[i]);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtd = Integer.parseInt(textViewQtd.getText().toString());

                if (qtd > 1) {
                    qtd--;
                    textViewQtd.setText(String.valueOf(qtd));
                    double novoPreco = precoVet[i] * qtd;
                    precoView.setText(String.format("%.2f", novoPreco));
                    somaTotal(precoVet[i] * -1);
                }
            }
        });

        return view;
    }

    public void somaTotal(double precoVet) {
        soma += precoVet;
        valortotal.setText(String.format("%.2f", soma));
    }
}


