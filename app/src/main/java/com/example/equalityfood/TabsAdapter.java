package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabsAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"Em andamento", "Histórico de pedidos"};

    public TabsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new TabFragment();
            case 1:
                return new PedidoFragmento();
        }
        return new TabFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}