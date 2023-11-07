package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class home extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchView = findViewById(R.id.searchView);

        semInternet();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
            }
        });
//        ImageButton btnPerfil = findViewById(R.id.btnPerfil2);
//
//        btnPerfil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(home.this, Perfil.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton btnCarrinho = findViewById(R.id.imageButton);
//
//        btnCarrinho.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(home.this, Carrinho.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton btnInicio = findViewById(R.id.menu_page1);
//
//        btnInicio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(this, "Você está nessa página", Toast.LENGTH_SHORT).show();;
//            }
//        });
//
//        ImageButton imageButton4 = findViewById(R.id.imageButton4);

//        imageButton4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(home.this, Pedidos.class);
//                startActivity(intent);
//            }
//        });

        ImageView naoPerecivel = findViewById(R.id.imageView23);

        naoPerecivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAPI = "https://api-equality.onrender.com/";
                //configurar acesso a API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urlAPI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<List<ProdutosAPI>> call = apiService.GetProdutosByCategoria("naoPereciveis");

                call.enqueue(new Callback<List<ProdutosAPI>>() {
                    @Override
                    public void onResponse(Call<List<ProdutosAPI>> call, Response<List<ProdutosAPI>> response) {
                        if( response.isSuccessful() ){
                            List<ProdutosAPI> produtosAPI = response.body();

                            // Crie um Bundle e coloque a lista nele
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("listaDeNaoPereciveis", new ArrayList<>(produtosAPI));

                            Intent intent = new Intent(home.this, ListaNaoPereciveis.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProdutosAPI>> call, Throwable t) {
                        Log.e("API", "Falha na chamada à API", t);
                        System.out.println(t);
                        Toast.makeText(getApplicationContext(), "Falha ao tentar vizualizar os produtos, Tente mais tarde!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        ImageView imageButton10 = findViewById(R.id.imageView31);
        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAPI = "https://api-equality.onrender.com/";
                //configurar acesso a API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urlAPI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<List<ProdutosAPI>> call = apiService.GetProdutosByCategoria("congelados");

                call.enqueue(new Callback<List<ProdutosAPI>>() {
                    @Override
                    public void onResponse(Call<List<ProdutosAPI>> call, Response<List<ProdutosAPI>> response) {
                        if( response.isSuccessful() ){
                            List<ProdutosAPI> produtosAPI = response.body();

                            // Crie um Bundle e coloque a lista nele
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("listaDeProdutos", new ArrayList<>(produtosAPI));

                            Intent intent = new Intent(home.this, ListaCongelados.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProdutosAPI>> call, Throwable t) {
                        Log.e("API", "Falha na chamada à API", t);
                        System.out.println(t);
                        Toast.makeText(getApplicationContext(), "Falha ao tentar vizualizar os produtos, Tente mais tarde!", Toast.LENGTH_LONG).show();
                    }
                });
            }
    });

        ImageView imageButton11 = findViewById(R.id.imageView35);

        imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAPI = "https://api-equality.onrender.com/";
                //configurar acesso a API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urlAPI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<List<ProdutosAPI>> call = apiService.GetProdutosByCategoria("carneSuina");

                call.enqueue(new Callback<List<ProdutosAPI>>() {
                    @Override
                    public void onResponse(Call<List<ProdutosAPI>> call, Response<List<ProdutosAPI>> response) {
                        if (response.isSuccessful()) {
                            List<ProdutosAPI> produtosAPI = response.body();

                            // Crie um Bundle e coloque a lista nele
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("listaDeCarneSuina", new ArrayList<>(produtosAPI));
                            Intent intent = new Intent(home.this, ListaCarneSuina.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProdutosAPI>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha ao tentar vizualizar os produtos, Tente mais tarde!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        ImageView imageButton14 = findViewById(R.id.imageView36);

        imageButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAPI = "https://api-equality.onrender.com/";
                //configurar acesso a API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urlAPI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<List<ProdutosAPI>> call = apiService.GetProdutosByCategoria("frango");

                call.enqueue(new Callback<List<ProdutosAPI>>() {
                    @Override
                    public void onResponse(Call<List<ProdutosAPI>> call, Response<List<ProdutosAPI>> response) {
                        if (response.isSuccessful()) {
                            List<ProdutosAPI> produtosAPI = response.body();

                            // Crie um Bundle e coloque a lista nele
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("listaDeCarneDeFrango", new ArrayList<>(produtosAPI));

                            Intent intent = new Intent(home.this, ListaFrango.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProdutosAPI>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha ao tentar vizualizar os produtos, Tente mais tarde!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        ImageView imageButton15 = findViewById(R.id.imageView37);

        imageButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAPI = "https://api-equality.onrender.com/";
                //configurar acesso a API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urlAPI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<List<ProdutosAPI>> call = apiService.GetProdutosByCategoria("carneBovina");

                call.enqueue(new Callback<List<ProdutosAPI>>() {
                    @Override
                    public void onResponse(Call<List<ProdutosAPI>> call, Response<List<ProdutosAPI>> response) {
                        if (response.isSuccessful()) {
                            List<ProdutosAPI> produtosAPI = response.body();

                            // Crie um Bundle e coloque a lista nele
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("listaDeCarneBovina", new ArrayList<>(produtosAPI));

                            Intent intent = new Intent(home.this, ListaCarneBovina.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProdutosAPI>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha ao tentar vizualizar os produtos, Tente mais tarde!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        ImageView imageView32 = findViewById(R.id.imageView32);

        imageView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAPI = "https://api-equality.onrender.com/";
                //configurar acesso a API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urlAPI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<List<ProdutosAPI>> call = apiService.GetProdutosByCategoria("legumes");

                call.enqueue(new Callback<List<ProdutosAPI>>() {
                    @Override
                    public void onResponse(Call<List<ProdutosAPI>> call, Response<List<ProdutosAPI>> response) {
                        if (response.isSuccessful()) {
                            List<ProdutosAPI> produtosAPI = response.body();

                            // Crie um Bundle e coloque a lista nele
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("listaDeLegumes", new ArrayList<>(produtosAPI));

                            Intent intent = new Intent(home.this, ListaLegumes.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProdutosAPI>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha ao tentar vizualizar os produtos, Tente mais tarde!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.imageButton4:
                        Intent intent = new Intent(home.this, Pedidos.class);
                        startActivity(intent);
                        return true;
                    case R.id.imageButton:
                        Intent carrinho = new Intent(home.this, Carrinho.class);
                        startActivity(carrinho);
                        return true;
                    case R.id.btnPerfil2:
                        Intent perfil = new Intent(home.this, Perfil.class);
                        startActivity(perfil);
                        return true;
                }
                return false;
            }
        });
    }

    private void performSearch(String query) {
        //Barra de Pesquisa
        Toast.makeText(this, "Pesquisando por: " + query, Toast.LENGTH_SHORT).show();

            String urlAPI = "https://api-equality.onrender.com/";
            //configurar acesso a API
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(urlAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiService = retrofit.create(ApiService.class);
            Call<List<ProdutosAPI>> call = apiService.BuscarPorNome(query);

            call.enqueue(new Callback<List<ProdutosAPI>>() {
                @Override
                public void onResponse(Call<List<ProdutosAPI>> call, Response<List<ProdutosAPI>> response) {
                    if (response.isSuccessful()) {
                        List<ProdutosAPI> produtosAPI = response.body();

                        // Crie um Bundle e coloque a lista nele
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("ResultadoPesquisa", new ArrayList<>(produtosAPI));

                        Intent intent = new Intent(home.this, ResultActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<List<ProdutosAPI>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha ao tentar vizualizar os produtos, Tente mais tarde!", Toast.LENGTH_LONG).show();
                }
            });
        }

    public void semInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            // Abre a tela de sem internet
            Intent intent = new Intent(this, telaSemInternet.class);
            startActivity(intent);
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.imageButton4:
                        startActivity(new Intent(getApplicationContext(), Pedidos.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.imageButton:
                        startActivity(new Intent(getApplicationContext(), Carrinho.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.btnPerfil2:
                        startActivity(new Intent(getApplicationContext(), Perfil.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_page1:
                        return true;
                }
                return false;
            }
        });
    }
}