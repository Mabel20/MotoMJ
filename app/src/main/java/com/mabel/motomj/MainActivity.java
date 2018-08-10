package com.mabel.motomj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mabel.motomj.models.Producto;
import com.mabel.motomj.repository.MotoRepository;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements MotoRepository {

    private FirebaseDatabase MotosMJBD;
    private DatabaseReference MotosMJReference;
    private StorageReference MotosMJStorage;
    private EditText etmarca, etmodelo, etarranque, etchasis, etcolor, ettipcombustile, etembrague, etprecio, etconfmotor;
    private ImageView imagen;
    private Button btnsgte, iBcamera;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MotosMJBD = FirebaseDatabase.getInstance();
        MotosMJReference = MotosMJBD.getReference();
        MotosMJStorage = FirebaseStorage.getInstance().getReference();

        progress = new ProgressDialog(this);

        etmarca = findViewById(R.id.editTextMarca);
        etmodelo = findViewById(R.id.editTextModelo);
        etarranque = findViewById(R.id.editTextArranque);
        etchasis = findViewById(R.id.editTextChasis);
        etcolor = findViewById(R.id.editTextColor);
        ettipcombustile = findViewById(R.id.editTexttip_combustible);
        etembrague = findViewById(R.id.editTextEmbrague);
        etprecio = findViewById(R.id.editTextPrecio);
        etconfmotor = findViewById(R.id.editTextConf_motor);
        imagen = findViewById(R.id.ivimagen);
        btnsgte = findViewById(R.id.buttonSiguiente);
        iBcamera = findViewById(R.id.btnfoto);

        btnsgte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MotosCatalogoActivity.class);
                startActivity(intent);
            }
        });

        iBcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            progress.setMessage("Subiendo Imagen");
            progress.show();

            Bundle extras = data.getExtras();
            Bitmap imagebitmap = (Bitmap) extras.get("data");
            StorageReference filepath = MotosMJStorage.child("Motos").child(autogeneratekey(etmarca.getText().toString(),etmodelo.getText().toString()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imagebitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] datas = baos.toByteArray();

            UploadTask uploadTask = filepath.putBytes(datas);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri uri = taskSnapshot.getDownloadUrl();
                    Glide.with(MainActivity.this).load(uri).into(imagen);
                    Toast.makeText(MainActivity.this, "Subido con exito", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

        @Override
        public void create (View v){
            MotosMJStorage.child("Motos/"+autogeneratekey(etmarca.getText().toString(), etmodelo.getText().toString())).getDownloadUrl()
            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Producto producto = new Producto(etmarca.getText().toString(), etmodelo.getText().toString(),
                            etarranque.getText().toString(), etchasis.getText().toString(), etcolor.getText().toString(),
                            ettipcombustile.getText().toString(), etembrague.getText().toString(), etconfmotor.getText().toString(),
                            etprecio.getText().toString(), uri.toString());

                    MotosMJReference.child("motos").child(autogeneratekey(etmarca.getText().toString(), etmodelo.getText().toString()))
                            .setValue(producto);
                    Limpiar();
                }
            });
        }

    public String autogeneratekey(String marca, String modelo) {
        String key = marca+"-"+modelo;
        return key;
    }

    @Override
        public void read (View v){

        }

        @Override
        public void update (View v){

        }

        @Override
        public void delete (View v){
            MotosMJReference.child("motos").child(autogeneratekey(etmarca.getText().toString(), etmodelo.getText().toString())).removeValue();
        }

        public void Limpiar(){
            etmarca.setText("");
            etmodelo.setText("");
            etembrague.setText("");
            etchasis.setText("");
            etcolor.setText("");
            ettipcombustile.setText("");
            etembrague.setText("");
            etprecio.setText("");
            etconfmotor.setText("");
        }
    }
