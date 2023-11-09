package com.example.equalityfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import android.Manifest;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class Perfil extends AppCompatActivity {

    ImageView fotoDePerfil;
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        TextView nomeVis = findViewById(R.id.textView23);
        TextView emailVis = findViewById(R.id.textView24);
        TextView numeroVis = findViewById(R.id.textView26);
        Button btnPolitica = findViewById(R.id.btnPolitica);
        Button alterarLoc = findViewById(R.id.textView31);
        ImageButton btnAlterarFoto = findViewById(R.id.imageButton12);
        fotoDePerfil = findViewById(R.id.imageView34);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null && user.getPhotoUrl() != null) {
            // Se o usuário tem uma foto de perfil configurada, carregue-a usando Picasso
            String photoUrl = user.getPhotoUrl().toString();
            Picasso.get().load(photoUrl).into(fotoDePerfil);
        }

        alterarLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, LocalizacaoEntrega.class);
                startActivity(intent);
            }
        });

        btnPolitica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, PoliticaPrivacidade.class);
                startActivity(intent);
            }
        });


        if (user != null) {
            String nome = user.getDisplayName();
            String email = user.getEmail();
            String numero = user.getPhoneNumber();

            nomeVis.setText(nome);
            emailVis.setText(email);
            numeroVis.setText(numero);
        }


        Button textView32 = findViewById(R.id.textView32);
        textView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, EsqueceuSenha.class);
                startActivity(intent);
            }
        });


        Button textView30 = findViewById(R.id.btnPolitica);
        textView30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, PoliticaPrivacidade.class);
                startActivity(intent);
            }
        });


        Button editarInfo = findViewById(R.id.button);
        editarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, AtualizarPerfil.class);
                startActivity(intent);
            }
        });

        Button logout = findViewById(R.id.logout_button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(Perfil.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.btnPerfil2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btnPerfil2:
                        return true;
                    case R.id.imageButton4:
                        startActivity(new Intent(getApplicationContext(), Pedidos.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.imageButton:
                        startActivity(new Intent(getApplicationContext(), Carrinho.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_page1:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        btnAlterarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Perfil.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Perfil.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    iniciarAtividadeCamera();
                }
            }
        });
    }

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                //Salva a imagem no Firebase Storage
                salvarImagemNoFirebaseStorage(imageBitmap);

                //Carrega a imagem do URL e altera no ImageView
                if (user != null && user.getPhotoUrl() != null) {
                    fotoDePerfil.setImageBitmap(imageBitmap);
//                    String photoUrl = user.getPhotoUrl().toString();
//                    Picasso.get().load(photoUrl).into(fotoDePerfil);
                }
            }
        }
    }

    private void iniciarAtividadeCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }
    private void salvarImagemNoFirebaseStorage(Bitmap imageBitmap) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            //Converte a imagem para um formato que pode ser armazenado
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();

            //Definir o caminho no Firebase Storage onde a imagem será salva
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference imageRef = storageRef.child("perfis")
                    .child(userId)
                    .child("foto_de_perfil.jpg");

            //Fazer o upload da imagem
            UploadTask uploadTask = imageRef.putBytes(imageData);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    System.out.println("Falha");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            Uri url = task.getResult();

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if (user != null) {
                                //Atualize o campo photoUrl do usuário com a URL da imagem
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setPhotoUri(url)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Perfil.this, "Foto de perfil alterada com sucesso! Ela pode levar alguns segundos para atualizar", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(Perfil.this, "Falha ao atualizar foto de perfil", Toast.LENGTH_LONG).show();

                                                }
                                            }
                                        });
                            }

                        }
                    });
                }
            });
        }
    }
}