package com.example.equalityfood;

public class InformationPedidos {
    private double precoFinal;
    private String hora;

    public InformationPedidos(double precoProd, String horas) {
        this.precoFinal = precoProd;
        this.hora = horas;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    @Override
    public String toString() {
        return "InformationPedidos{" +
                "precoFinal=" + precoFinal +
                ", hora='" + hora + '\'' +
                '}';
    }
}
