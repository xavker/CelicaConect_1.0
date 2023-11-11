package com.example.celicaconect_10.NoConexion.Tramites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.celicaconect_10.NoConexion.Tramites.Adaptador.Adaptador;
import com.example.celicaconect_10.NoConexion.Tramites.Modelo.Items;
import com.example.celicaconect_10.R;

import java.util.ArrayList;


public class MenuTramites extends Fragment {
    GridView gridView;
    ArrayList menulist=new ArrayList<>();
    Items i;
    //FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_menu_tramites, container, false);
        gridView =  v.findViewById(R.id.idGrid);
        gridView.setDrawSelectorOnTop(true);
        i=new Items();
        Fragment nuevofragmento=new Enviardenuncia();
        FragmentTransaction transaction= getFragmentManager().beginTransaction();

        Adaptador myAdapter=new Adaptador(getActivity().getApplicationContext());
        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent intent = new Intent(MenuTramites.this, SecondActivity.class);
                //intent.putExtra("image", logos[position]); // put image data in Intent
                //startActivity(intent); // start Intent
                Toast.makeText(getContext(),"Abriendo camara...",Toast.LENGTH_SHORT).show();
                transaction.replace(R.id.nav_host_fragment_content_main,nuevofragmento);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }
}