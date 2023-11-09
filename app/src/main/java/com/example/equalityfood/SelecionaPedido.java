package com.example.equalityfood;

import java.util.ArrayList;

public class SelecionaPedido {

        private static ArrayList<InformationPedidos> InfoPedidos = new ArrayList<>();

        public static void Adicionar(InformationPedidos i) {
            InfoPedidos.add(i);
        }

        public static ArrayList<InformationPedidos> Listar() {
            return InfoPedidos;
        }
}
