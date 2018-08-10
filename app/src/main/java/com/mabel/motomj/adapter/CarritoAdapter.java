package com.mabel.motomj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mabel.motomj.R;
import com.mabel.motomj.models.CarritoCompra;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {

    List<CarritoCompra> carritoCompras;

    public CarritoAdapter(List<CarritoCompra> productos) {
        this.carritoCompras = productos;
    }

    @Override
    public CarritoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false);
        CarritoViewHolder viewHolder = new CarritoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CarritoViewHolder holder, int position) {
        CarritoCompra carritoCompra = carritoCompras.get(position);
        holder.tvmarca.setText(carritoCompra.getMarca());
        holder.tvmodelo.setText(carritoCompra.getModelo());
        holder.tvarranque.setText(carritoCompra.getArranque());
        holder.tvchasis.setText(carritoCompra.getChasis());
        holder.tvcolor.setText(carritoCompra.getColor());
        holder.tvtipcombustible.setText(carritoCompra.getTip_combustible());
        holder.tvembrague.setText(carritoCompra.getEmbrague());
        holder.tvconfmotor.setText(carritoCompra.getConf_motor());
        holder.tvprecio.setText(carritoCompra.getPrecio());
        holder.tvurl.setText(carritoCompra.getUrl());

        Glide.with(holder.itemView.getContext()).load(carritoCompra.getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return carritoCompras.size();
    }

    public static class CarritoViewHolder extends RecyclerView.ViewHolder{

        TextView tvmarca, tvmodelo, tvarranque, tvchasis, tvcolor, tvtipcombustible, tvembrague, tvconfmotor, tvprecio, tvurl;
        ImageView imageView;

        public CarritoViewHolder(View v) {
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
            imageView = v.findViewById(R.id.imagen1);
        }
    }
}
