package com.example.equalityfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        view = inflater.inflate(R.layout.activity_adapter,null);

        ImageView icon = view.findViewById(R.id.imgProd);
        TextView nome = view.findViewById(R.id.produto);
        TextView precoView = view.findViewById(R.id.preco);


        nome.setText(prod[i]);
        precoView.setText(String.valueOf(precoVet[i]));
        icon.setImageResource(img[i]);
        return view;
    }
}
