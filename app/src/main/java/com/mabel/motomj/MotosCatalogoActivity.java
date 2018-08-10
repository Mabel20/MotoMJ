package com.mabel.motomj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mabel.motomj.adapter.MotoAdapter;
import com.mabel.motomj.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class MotosCatalogoActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private List<Producto> productoList;
    private MotoAdapter motoAdapter;

    private FirebaseDatabase MotosMJBD;
    private DatabaseReference MotosMJReference;
    private String coleccionProductos= "motos";

    private Button btncompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motos_catalogo);

        MotosMJBD = FirebaseDatabase.getInstance();
        MotosMJReference = MotosMJBD.getReference();

        btncompras = findViewById(R.id.btnmiscompras);
        recycler = findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        productoList = new ArrayList<>();

        motoAdapter = new MotoAdapter(productoList);
        recycler.setAdapter(motoAdapter);

        MotosMJReference.child(coleccionProductos).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productoList.removeAll(productoList);
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    Producto producto = snap.getValue(Producto.class);
                    productoList.add(producto);
                }
                motoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MotosCatalogoActivity.this, "Tiempo de espera acabado, revise su conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });

        btncompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotosCatalogoActivity.this, CarritoActivity.class);
                startActivity(intent);
            }
        });
    }
}
