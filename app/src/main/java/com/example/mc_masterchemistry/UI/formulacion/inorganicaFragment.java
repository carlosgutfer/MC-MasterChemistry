package com.example.mc_masterchemistry.UI.formulacion;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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


    private MyinorganicaRecyclerViewAdapter adapterFormulacion;
    private OnListFragmentInteractionListener mListener;
    private List<InorganicaEntity> inorganicaEntityList;

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
        if (view instanceof RecyclerView)
        {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            DisplayMetrics displayMetrics =context.getResources().getDisplayMetrics();
            float dpWith = displayMetrics.widthPixels/displayMetrics.density;
            int numeroColumnas= (int)(dpWith/450);
            if(numeroColumnas < 1)
                numeroColumnas = 1;
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numeroColumnas,StaggeredGridLayoutManager.VERTICAL));
            inorganicaEntityList = new ArrayList<>();
                tiposViewModel melementoviewModel = new ViewModelProvider(this).get(tiposViewModel.class);
            melementoviewModel.getAllElementos().observe(getViewLifecycleOwner(), allInorganica -> {
                setelementos(allInorganica);
                adapterFormulacion = new MyinorganicaRecyclerViewAdapter(getActivity(), inorganicaEntityList);
                recyclerView.setAdapter(adapterFormulacion);
        });

        }
        return view;
    }



    @Override
    public void onAttach(@NonNull Context context) {
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
    }
}
