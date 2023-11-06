package com.example.equalityfood;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ProdutosAPI implements Parcelable  {
    private int id;
    private long fk_Administrador_cpf;
    private String nome;
    private double preco;
    private String data_validade;
    private int quant;
    private String descricao;
    private String categoria;
    private String imagem;
    private boolean ativado;

    public ProdutosAPI(int id, long fk_Administrador_cpf, String nome, double preco, String data_validade, int quant, String descricao, String categoria, String imagem, boolean ativado) {
        this.id = id;
        this.fk_Administrador_cpf = fk_Administrador_cpf;
        this.nome = nome;
        this.preco = preco;
        this.data_validade = data_validade;
        this.quant = quant;
        this.descricao = descricao;
        this.categoria = categoria;
        this.imagem = imagem;
        this.ativado = ativado;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(fk_Administrador_cpf);
        dest.writeString(nome);
        dest.writeDouble(preco);
        dest.writeString(data_validade);
        dest.writeInt(quant);
        dest.writeString(descricao);
        dest.writeString(categoria);
        dest.writeString(imagem);
        dest.writeInt(ativado ? 1 : 0); // Converte o booleano em int (1 para true, 0 para false)
    }

    public static final Parcelable.Creator<ProdutosAPI> CREATOR = new Parcelable.Creator<ProdutosAPI>() {
        @Override
        public ProdutosAPI createFromParcel(Parcel source) {
            return new ProdutosAPI(source);
        }
        @Override
        public ProdutosAPI[] newArray(int size) {
            return new ProdutosAPI[size];
        }
    };

    public ProdutosAPI(Parcel source) {
        id = source.readInt();
        fk_Administrador_cpf = source.readLong();
        nome = source.readString();
        preco = source.readDouble();
        data_validade = source.readString();
        quant = source.readInt();
        descricao = source.readString();
        categoria = source.readString();
        imagem = source.readString();
        ativado = source.readInt() == 1;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getFk_Administrador_cpf() {
        return fk_Administrador_cpf;
    }

    public void setFk_Administrador_cpf(long fk_Administrador_cpf) {
        this.fk_Administrador_cpf = fk_Administrador_cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getData_validade() {
        return data_validade;
    }

    public void setData_validade(String data_validade) {
        this.data_validade = data_validade;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }

    public String toString() {
        return "ProdutosAPI{" +
                "id='" + id + '\'' +
                ", fk_Administrador_cpf='" + fk_Administrador_cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", preco='" + preco + '\'' +
                ", data_validade='" + data_validade + '\'' +
                ", quant='" + quant + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", imagem='" + imagem + '\'' +
                ", ativado='" + ativado + '\'' +
                '}';
    }
}
