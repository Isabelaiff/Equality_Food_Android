package com.example.equalityfood;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/api/produtos/selecionarPorCategoria/{categoria}")
    Call<List<ProdutosAPI>> GetProdutosByCategoria(@Path("categoria") String categoria);
    @GET("/api/produtos/listar")
    Call<List<ProdutosAPI>> GetAll();

    @GET("{cep}/json")
    Call<CEP> recuperarCEP(@Path("cep") String cep);
}
