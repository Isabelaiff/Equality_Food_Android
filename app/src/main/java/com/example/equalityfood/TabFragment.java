package com.example.equalityfood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
//        View view = getView();
//        TextView data = view.findViewById(R.id.data);
//        ArrayList<InformationPedidos> a = SelecionaPedido.Listar();
//
//        data.setText(String.valueOf(a));

    return inflater.inflate(R.layout.fragment_tab, container, false);
    }
}
