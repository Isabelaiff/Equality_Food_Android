package com.example.equalityfood;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
    private Context applicationContext;
    private LayoutInflater inflater;
    private String[] prod;
    private int[] img;
    private int[] qtdvetor;
    private double[] precoVet;


    public Adapter(Context applicationContext, String[] prod, int[] img, double[] preco) {
        this.applicationContext = applicationContext;
        this.prod = prod;
        this.img = img;
        this.precoVet = preco;
        this.inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return prod.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.activity_adapter, null);

        int count = 0;

        ImageView icon = view.findViewById(R.id.imgProd);
        TextView nome = view.findViewById(R.id.produto);
        TextView precoView = view.findViewById(R.id.preco);
        TextView textViewQtd = view.findViewById(R.id.qtd);
        Button buttonIncrement = view.findViewById(R.id.mais);
        Button button = view.findViewById(R.id.menos);

        nome.setText(prod[i]);
        precoView.setText(String.valueOf(precoVet[i]));
        icon.setImageResource(img[i]);
        textViewQtd.setText("1");


        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtd = Integer.parseInt(textViewQtd.getText().toString());
                qtd++;

                textViewQtd.setText(String.valueOf(qtd));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtd = Integer.parseInt(textViewQtd.getText().toString());

                if (qtd > 1) {
                    qtd--;
                    textViewQtd.setText(String.valueOf(qtd));
                }
            }
        });

        return view;
        }

    }

