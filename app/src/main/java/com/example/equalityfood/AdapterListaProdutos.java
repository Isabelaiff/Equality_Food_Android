package com.example.equalityfood;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaProdutos extends BaseAdapter {

    private Context applicationContext;
    private LayoutInflater inflater;
    List<String> fotoP;
    List<String> produtos;
    List<String> descri;
    List<Double> valor;
    List<String> dataVal;

    public AdapterListaProdutos(Context applicationContext, List<String> foto, List<String> produtos, List<String> descricoes, List<Double> valores, List<String> validade) {
        this.applicationContext = applicationContext;
        this.fotoP = foto;
        this.produtos = produtos;
        this.descri = descricoes;
        this.valor = valores;
        this.dataVal = validade;
        this.inflater = LayoutInflater.from(applicationContext);

        // Create an intent to start the Produtos activity
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_adapter_lista_produtos, null);

        TextView prod = view.findViewById(R.id.textView);
        TextView desc = view.findViewById(R.id.textView2);
        TextView val = view.findViewById(R.id.textView3);
        ImageView icon = view.findViewById(R.id.imageView);

        prod.setText(produtos.get(i));
        desc.setText(descri.get(i));
        val.setText(String.valueOf(valor.get(i)));

        Glide.with(view.getContext())
                .load(fotoP.get(i))
                .into(icon);
        return view;
    }
}

