package com.example.equalityfood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PedidoFragmento extends Fragment {

    private ListView lista;
    private AdapterPedidos adapter;

    String[] status = {"Entregue", "Entregue"};
    String[] Data = {"dd/mes/ano", "dd/mes/ano"};
    double[] precoPedido = {80.00, 59.99};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pedido_fragmento, container, false);

        lista = view.findViewById(R.id.listaviewpedidos);
        adapter = new AdapterPedidos(requireContext(), status, Data, precoPedido);
        lista.setAdapter(adapter);

        return view;

    }
}