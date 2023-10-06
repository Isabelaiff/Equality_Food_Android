package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaProdutos extends BaseAdapter {
    private Context applicationContext;

    private LayoutInflater inflater;

    List<Integer> fotoP = new ArrayList<>();
    List<String> produtos = new ArrayList<>();
    List<String> descri = new ArrayList<>();
    List<Double> valor = new ArrayList<>();

    public AdapterListaProdutos(Context applicationContext,List<Integer> foto, List<String> produtos, List<String> descricoes, List<Double> valores) {
        this.applicationContext = applicationContext;
        this.fotoP = foto;
        this.produtos = produtos;
        this.descri = descricoes;
        this.valor = valores;
        this.inflater =(LayoutInflater.from(applicationContext));
    }



    @Override
    public int getCount() {
        return 0;
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

        TextView produto = view.findViewById(R.id.textView);
        TextView descr = view.findViewById(R.id.textView2);
        TextView valores = view.findViewById(R.id.textView3);
        TextView foto = view.findViewById(R.id.imageView);

        produto.setText(produtos.get(i));
        descr.setText(descri.get(i));
        valores.setText(Double.toString(valor.get(i)));
        foto.setText(fotoP.get(i));

        return view;
    }
}
