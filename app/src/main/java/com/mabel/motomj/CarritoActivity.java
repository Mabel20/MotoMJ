package com.mabel.motomj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mabel.motomj.adapter.CarritoAdapter;
import com.mabel.motomj.models.CarritoCompra;

import java.util.ArrayList;
import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private List<CarritoCompra> carritoCompras;
    private CarritoAdapter carritoAdapter;

    private FirebaseDatabase MotosMJBD;
    private DatabaseReference MotosMJReference;
    private String coleccioncarrito = "carrito";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        MotosMJBD = FirebaseDatabase.getInstance();
        MotosMJReference = MotosMJBD.getReference();

        recycler = findViewById(R.id.recyclercarrito);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        carritoCompras = new ArrayList<>();

        carritoAdapter = new CarritoAdapter(carritoCompras);
        recycler.setAdapter(carritoAdapter);

        MotosMJReference.child(coleccioncarrito).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                carritoCompras.removeAll(carritoCompras);
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    CarritoCompra carritoCompra = snap.getValue(CarritoCompra.class);
                    carritoCompras.add(carritoCompra);
                }
                carritoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(CarritoActivity.this, "Tiempo de espera acabado, revise su conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
