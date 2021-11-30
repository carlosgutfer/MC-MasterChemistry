package com.example.mc_masterchemistry.UI.terciarios;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mc_masterchemistry.R;

import java.util.ArrayList;

public class dialoge_reto extends DialogFragment {



    public interface OnInputListener{

    }
    public OnInputListener mOnInputListener;

    public static TextView  mActionCancel;

    private Retos retotipo = new Retos();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialoge_reto, container, false);
        mActionCancel = view.findViewById(R.id.Informacion);
        mActionCancel.setText(retotipo.TipoReto());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){

        }
    }
}
