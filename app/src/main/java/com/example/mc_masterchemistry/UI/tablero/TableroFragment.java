package com.example.mc_masterchemistry.ui.tablero;


import androidx.lifecycle.ViewModelProvider;



import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class TableroFragment extends Fragment  {


    private com.example.mc_masterchemistry.ui.tablero.TableroViewModel mViewModel;

    public static TableroFragment newInstance() {
        return new TableroFragment();
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(com.example.mc_masterchemistry.ui.tablero.TableroViewModel.class);
        // TODO: Use the ViewModel


    }






}
