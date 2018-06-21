package com.joseph.chattu.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.joseph.chattu.Entidades.MensajeEnviar;
import com.joseph.chattu.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PhotoActivity extends AppCompatActivity {

    private ImageView imgView;
    private Button enviarFoto,hacerFoto;
    private static final int CAMERA_REQUEST=123;
    private static final int PHOTO_SEND = 1;
    private StorageReference storageReference;
    private FirebaseStorage storage;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private String fotoPerfilCadena;
    private String NOMBRE_USUARIO;
    private static final int PHOTO_PERFIL = 2;
    private CircleImageView fotoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imgView = (ImageView) findViewById(R.id.imageView);
        enviarFoto = (Button) findViewById(R.id.btnEnviarFoto);
        hacerFoto = (Button) findViewById(R.id.btnHacerFoto);
        hacerFoto = (Button) findViewById(R.id.btnHacerFoto);
        fotoPerfil = (CircleImageView) findViewById(R.id.fotoPerfil);

        storage = FirebaseStorage.getInstance();
        databaseReference = database.getReference("Sala de Chat");//Sala de chat (nombre)
        database = FirebaseDatabase.getInstance();
        fotoPerfilCadena = "";

        hacerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });

        enviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAMERA_REQUEST && resultCode== Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(photo);
        }


    }

}
