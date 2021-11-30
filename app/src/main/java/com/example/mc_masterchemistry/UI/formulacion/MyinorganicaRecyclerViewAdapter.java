package com.example.mc_masterchemistry.UI.formulacion;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.db.Entities.InorganicaEntity;


import java.util.List;


public class MyinorganicaRecyclerViewAdapter extends RecyclerView.Adapter<MyinorganicaRecyclerViewAdapter.ViewHolder>  {

    private final Context ctx;
    private final List<InorganicaEntity> mValues;
    private String nombre;

    public MyinorganicaRecyclerViewAdapter(Context context, List<InorganicaEntity> items) {
        ctx=context;
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_inorganica, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {

        holder.mItem = mValues.get(position);
        holder.Nombre.setText(holder.mItem.getNombre());
        Glide.with(ctx).
                load(holder.mItem.getURL_Imagen_nombre()).
                into(holder.viewImageURlimagen);
        holder.Nombre.setOnClickListener(v -> {
            nombre = mValues.get(position).getNombre();
            Intent prueba = new Intent(ctx, ExplicacionCompuestos.class);
            prueba.putExtra("Datos",mValues.get(position).getDatos());
            prueba.putExtra("Nombre",nombre);
            ctx.startActivity(prueba);
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final View mView;
        public final ImageView viewImageURlimagen;
        public final Button Nombre;
        public InorganicaEntity mItem;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            viewImageURlimagen = view.findViewById(R.id.ImageViewTipo);
            Nombre= view.findViewById(R.id.BT_mostrar);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + Nombre.getText().toString() + "'";
        }

    }
}
