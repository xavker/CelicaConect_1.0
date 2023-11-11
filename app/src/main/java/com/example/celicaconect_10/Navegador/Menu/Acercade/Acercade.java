package com.example.celicaconect_10.Navegador.Menu.Acercade;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.celicaconect_10.MainActivity;
import com.example.celicaconect_10.Navegador.Menu.ConsultarTelefono.Telefono;
import com.example.celicaconect_10.Navegador.Menu.Home.HomeFragment;
import com.example.celicaconect_10.R;


public class Acercade extends Fragment {


    FragmentManager fragmentManager;
    FragmentTransaction fm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager=getFragmentManager();
        //fragmentManager=MainActivity.this.getSupportFragmentManager();
        //transaction = fragmentManager.beginTransaction();
        //transaction.add(R.id.main_container,  new ProfileFragment()).commit();

        View v=inflater.inflate(R.layout.fragment_acercade, container, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new HomeFragment()).commit();
            }
        },5000);

        return v;
    }


}