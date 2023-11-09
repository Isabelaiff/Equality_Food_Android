package com.example.equalityfood;

import java.util.ArrayList;

public class Selecionados {

    private static ArrayList<Produto> produtos = new ArrayList<>();

    public static void Adicionar(Produto p) {
        produtos.add(p);
    }

    public static ArrayList<Produto> Listar() {
        return produtos;
    }
}
