package com.example.equalityfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Pedidos extends AppCompatActivity {
    TabsAdapter tabsAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    ImageButton close;
    private final String[] titles = new String[]{"Em andamento", "Histórico de pedidos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        viewPager =  findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabLayout);
        tabsAdapter = new TabsAdapter(this);
        close = findViewById(R.id.close);

        viewPager.setAdapter(tabsAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (((tab, position) -> tab.setText(titles[position])))).attach();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pedidos.this, home.class);
                startActivity(intent);
            }
        });


    }
}