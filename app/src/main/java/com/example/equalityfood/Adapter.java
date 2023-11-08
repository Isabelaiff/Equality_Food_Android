package com.example.equalityfood;

import static android.system.Os.prctl;
import static android.system.Os.remove;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Adapter extends BaseAdapter {
    private Context applicationContext;
    private LayoutInflater inflater;
    private String[] prod;
    private Integer[] img;
    private Integer[] qtdvetlor;
    private Double[] precoVet;
    TextView valortotal;
    ArrayList<Produto> arrayList;

    private double soma = 0.0;

    public Adapter(Context context, ArrayList<Produto> arrayList, TextView valortotal) {
        this.arrayList = arrayList;
        this.valortotal = valortotal;
        this.inflater = LayoutInflater.from(context);
        for (Produto item : arrayList) {
            soma += item.getPrecoProd();
        }
        valortotal.setText(String.format("%.2f", soma));
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.activity_adapter, null);

        ImageView card = view.findViewById(R.id.imageView2);
        ImageView removeButton = view.findViewById(R.id.lixo);
        ImageView icon = view.findViewById(R.id.imgProd);
        TextView nome = view.findViewById(R.id.produto);
        TextView precoView = view.findViewById(R.id.preco);
        TextView textViewQtd = view.findViewById(R.id.qtd);
        Button buttonIncrement = view.findViewById(R.id.mais);
        Button button = view.findViewById(R.id.menos);
        TextView valor = view.findViewById(R.id.produto2);
        TextView moeda = view.findViewById(R.id.moeda);
        ConstraintLayout constraint = view.findViewById(R.id.constraint);

        Produto produto = arrayList.get(i);
        nome.setText(produto.getProdutos());
        precoView.setText(String.format("%.2f", produto.getPrecoProd()).replace(".", ","));
        icon.setImageResource(produto.getImgProd());
        textViewQtd.setText("1");

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtd = Integer.parseInt(textViewQtd.getText().toString());

                double valorRemovido =arrayList.get(i).getPrecoProd() * qtd * -1;
                somaTotal(valorRemovido);
                arrayList.remove(i);
                notifyDataSetChanged();
            }
        });

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtd = Integer.parseInt(textViewQtd.getText().toString());
                qtd++;

                textViewQtd.setText(String.valueOf(qtd));
                double novoPreco = produto.getPrecoProd() * qtd;
                precoView.setText(String.format("%.2f", novoPreco));
                somaTotal(produto.getPrecoProd());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtd = Integer.parseInt(textViewQtd.getText().toString());

                if (qtd > 1) {
                    qtd--;
                    textViewQtd.setText(String.valueOf(qtd));
                    double novoPreco = produto.getPrecoProd() * qtd;
                    precoView.setText(String.format("%.2f", novoPreco));
                    somaTotal(produto.getPrecoProd() * -1);
                }
            }
        });

        return view;
    }
    public void somaTotal(double preco) {
        soma += preco;
        valortotal.setText(String.format("%.2f", soma));
    }
}


