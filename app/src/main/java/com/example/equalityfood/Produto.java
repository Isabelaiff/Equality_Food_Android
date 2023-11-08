package com.example.equalityfood;

public class Produto {
    private String produtos;
    private int imgProd;
    private double precoProd;
    private int qtd;

    public Produto(String produtos, int imgProd, double precoProd) {
        this.produtos = produtos;
        this.imgProd = imgProd;
        this.precoProd = precoProd;
        this.qtd = 1;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public int getImgProd() {
        return imgProd;
    }

    public void setImgProd(int imgProd) {
        this.imgProd = imgProd;
    }

    public double getPrecoProd() {
        return precoProd;
    }

    public void setPrecoProd(double precoProd) {
        this.precoProd = precoProd;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "produtos='" + produtos + '\'' +
                ", imgProd=" + imgProd +
                ", precoProd=" + precoProd +
                ", qtd=" + qtd +
                '}';
    }
}
