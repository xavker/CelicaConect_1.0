package com.example.celicaconect_10.Navegador.Menu.Atencion;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.celicaconect_10.R;

public class Pantalla_confirmacion extends Fragment {

    private TextView resume;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_pantalla_confirmacion, container, false);
        resume=v.findViewById(R.id.h_resume);
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String t_resume=bundle.getString("datos");
                resume.setText(t_resume);
            }
        });
        return v;
    }
}