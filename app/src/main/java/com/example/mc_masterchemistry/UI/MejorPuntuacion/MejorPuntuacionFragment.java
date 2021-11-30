package com.example.mc_masterchemistry.UI.MejorPuntuacion;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.db.Entities.Users;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MejorPuntuacionFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private List<Users> alluser;
    private MyItemRecyclerViewAdapter adapter;
    private FirebaseFirestore firestore;

    public MejorPuntuacionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firestore=FirebaseFirestore.getInstance();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mejor_puntuacion_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            firestore.collection("users")
                    .orderBy("gemas", Query.Direction.DESCENDING)
                    .get()
                    .addOnCompleteListener(task -> {
                        alluser=new ArrayList<>();
                        for (DocumentSnapshot document: Objects.requireNonNull(task.getResult())){
                            Users userItem = document.toObject(Users.class);
                            alluser.add(userItem);
                            adapter=new MyItemRecyclerViewAdapter(alluser);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
        return view;
    }

    public interface OnListFragmentInteractionListener {
    }
}