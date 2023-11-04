//package com.example.equalityfood;
//
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//
//
//public class templateCarrinho extends BaseAdapter {
//
//    private int count = 0;
//    private TextView textView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_adapter);
//
//        textView = findViewById(R.id.qtd);
//        Button buttonIncrement = findViewById(R.id.mais);
//        Button button = findViewById(R.id.menos);
//        buttonIncrement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                count++;
//                textView.setText(String.valueOf(count));
//            }
//        });
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (count > 0) {
//                    count--;
//                    textView.setText(String.valueOf(count));
//                }
//            }
//        });
//
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        return null;
//    }
//}
