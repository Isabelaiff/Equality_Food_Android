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
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context applicationContext;
    private LayoutInflater inflater;
    private String[] prod;
    private int[] img;
    private int[] qtdvetor;
    private double[] precoVet;
    private List<Carrinho> cardList;


    public Adapter(Context applicationContext, String[] prod, int[] img, double[] preco, List<Carrinho> cardList) {
        this.applicationContext = applicationContext;
        this.prod = prod;
        this.img = img;
        this.precoVet = preco;
        this.cardList = cardList;
        this.inflater = LayoutInflater.from(applicationContext);
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

        ImageView card = view.findViewById(R.id.imageView2);
        Button removeButton = view.findViewById(R.id.button);
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


//        removeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cardList.remove(card);
//                notifyDataSetChanged();
//            }
//        });

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

