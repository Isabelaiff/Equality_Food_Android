package com.example.equalityfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterPedidos extends BaseAdapter {
    private Context applicationContext;
    private LayoutInflater inflater;
    private String[] staus;
    private String[] data;
    private double[] precoPedido;


    public AdapterPedidos(Context applicationContext, String[] stts, String[] date, double[] valor) {
        this.applicationContext = applicationContext;
        this.staus = stts;
        this.data = date;
        this.precoPedido = valor;
        this.inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return data.length;
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
        view = inflater.inflate(R.layout.activity_adapter_pedidos,null);

        ImageView icon = view.findViewById(R.id.imgCesta);
        TextView valoPago = view.findViewById(R.id.valor);
        TextView dateEnt = view.findViewById(R.id.data);
        TextView statusEnt = view.findViewById(R.id.status);

        icon.setImageResource(R.drawable.groupcesta);
        valoPago.setText(String.valueOf(precoPedido[i]));
        dateEnt.setText(data[i]);
        statusEnt.setText(staus[i]);
        return view;
    }

}
