package com.example.mc_masterchemistry.UI.MejorPuntuacion;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.db.Entities.Users;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Users> mValues;

    public MyItemRecyclerViewAdapter(List<Users> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mejor_puntuacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.gemas.setText(String.valueOf(mValues.get(position).getGemas()));
        holder.nick.setText(mValues.get(position).getNick());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nick;
        public final TextView gemas;
        public Users mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            gemas = view.findViewById(R.id.TV_numeroGemasRanking);
            nick = view.findViewById(R.id.TV_nombreUsuario);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nick.getText() + "'";
        }
    }
}