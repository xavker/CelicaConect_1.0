package com.example.celicaconect_10.Navegador.Menu.TipodeLicencia;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.celicaconect_10.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipoB extends Fragment {


    public TipoB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tipo_b, container, false);
        TextView textView = view.findViewById(R.id.tipob);
        textView.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}
