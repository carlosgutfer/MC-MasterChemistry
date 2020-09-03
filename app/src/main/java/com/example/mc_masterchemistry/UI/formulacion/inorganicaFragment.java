package com.example.mc_masterchemistry.UI.formulacion;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.db.Entities.InorganicaEntity;
import com.example.mc_masterchemistry.tiposViewModel;

import java.util.ArrayList;
import java.util.List;


public class inorganicaFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    MyinorganicaRecyclerViewAdapter adapterFormulacion;
    private OnListFragmentInteractionListener mListener;

    List<InorganicaEntity> inorganicaEntityList;
    private tiposViewModel melementoviewModel;



    public inorganicaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inorganica_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics =context.getResources().getDisplayMetrics();
                float dpWith = displayMetrics.widthPixels/displayMetrics.density;
                int numeroColumnas= (int)(dpWith/180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numeroColumnas,StaggeredGridLayoutManager.VERTICAL));
            }


            inorganicaEntityList = new ArrayList<>();
            melementoviewModel = new ViewModelProvider(this).get(tiposViewModel.class);
            melementoviewModel.getAllElementos().observe(getViewLifecycleOwner(), new Observer<List<InorganicaEntity>>() {
                @Override
                public void onChanged(@Nullable List<InorganicaEntity> allInorganica){
                    setelementos(allInorganica);
                    adapterFormulacion = new MyinorganicaRecyclerViewAdapter(getActivity(), inorganicaEntityList,mListener);
                    recyclerView.setAdapter(adapterFormulacion);
                }
            });

        }
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setelementos(List<InorganicaEntity> items) {
        this.inorganicaEntityList=items;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(InorganicaEntity item);
    }
}
