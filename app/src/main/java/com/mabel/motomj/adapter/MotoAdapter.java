package com.mabel.motomj.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mabel.motomj.MainActivity;
import com.mabel.motomj.R;
import com.mabel.motomj.models.CarritoCompra;
import com.mabel.motomj.models.Producto;

import java.util.List;

public class MotoAdapter extends RecyclerView.Adapter<MotoAdapter.MotoViewHolder> {

    List<Producto> productos;

    public MotoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public MotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false);
        MotoViewHolder viewHolder = new MotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MotoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.tvmarca.setText(producto.getMarca());
        holder.tvmodelo.setText(producto.getModelo());
        holder.tvarranque.setText(producto.getArranque());
        holder.tvchasis.setText(producto.getChasis());
        holder.tvcolor.setText(producto.getColor());
        holder.tvtipcombustible.setText(producto.getTip_combustible());
        holder.tvembrague.setText(producto.getEmbrague());
        holder.tvconfmotor.setText(producto.getConf_motor());
        holder.tvprecio.setText(producto.getPrecio());
        holder.tvurl.setText(producto.getMotourl());
        holder.setOnClickListener();

        Glide.with(holder.itemView.getContext()).load(producto.getMotourl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class MotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvmarca, tvmodelo, tvarranque, tvchasis, tvcolor, tvtipcombustible, tvembrague, tvconfmotor, tvprecio, tvurl;
        ImageButton btnfavorito, iBeraser, iBsave;
        TextInputLayout tilcantidad;
        FirebaseDatabase MotosMJBD;
        DatabaseReference MotosReference;
        Context context;
        String coleccioncarrito = "carrito";
        ImageView imageView;

        public MotoViewHolder(View v) {
            super(v);
            tvmarca = v.findViewById(R.id.tvmarca);
            tvmodelo = v.findViewById(R.id.tvmodelo);
            tvarranque = v.findViewById(R.id.tvarranque);
            tvchasis = v.findViewById(R.id.tvchasis);
            tvcolor = v.findViewById(R.id.tvcolor);
            tvtipcombustible = v.findViewById(R.id.tvtipcombus);
            tvembrague = v.findViewById(R.id.tvembrague);
            tvconfmotor = v.findViewById(R.id.tvconfmotor);
            tvprecio = v.findViewById(R.id.tvprecio);
            tvurl = v.findViewById(R.id.tvurl);
            btnfavorito = v.findViewById(R.id.ibfavorito);
            iBeraser = v.findViewById(R.id.iBderaser);
            iBsave = v.findViewById(R.id.iBsave);
            tilcantidad = v.findViewById(R.id.tilcantidad);
            imageView = v.findViewById(R.id.imagen1);
            context = v.getContext();
        }

        public void setOnClickListener() {
            btnfavorito.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ibfavorito:
                    MotosMJBD = FirebaseDatabase.getInstance();
                    MotosReference = MotosMJBD.getReference();

                    int cantidad = 2;//Integer.parseInt(tilcantidad.getEditText().getText().toString());
                    Double total = cantidad * Double.valueOf(tvprecio.getText().toString());

                    CarritoCompra carritoCompra = new CarritoCompra(tvmarca.getText().toString(), tvmodelo.getText().toString(),
                            tvarranque.getText().toString(), tvchasis.getText().toString(), tvcolor.getText().toString(),
                            tvtipcombustible.getText().toString(), tvembrague.getText().toString(), tvconfmotor.getText().toString(),
                            tvprecio.getText().toString(), cantidad, total, tvurl.getText().toString());
                    MainActivity mainActivity = new MainActivity();
                    MotosReference.child(coleccioncarrito).child(mainActivity.autogeneratekey(tvmarca.getText().toString(), tvmodelo.getText().toString())).setValue(carritoCompra);

                    break;
                case R.id.iBsave:
                    break;
                case R.id.iBderaser:
                    break;
            }
        }
    }
}
