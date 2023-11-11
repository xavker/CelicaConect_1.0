package com.example.celicaconect_10.Navegador.Menu.RegistroMascota;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celicaconect_10.Navegador.Menu.Noticias.AdapterFeed;
import com.example.celicaconect_10.Navegador.Menu.Noticias.ModelFeed;
import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador.Adaptador_mascotas;
import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador.ModeloMascotas;
import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Provider.FirebaseMascotas;
import com.example.celicaconect_10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class Mascotas extends Fragment {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<ModeloMascotas> modelMascota = new ArrayList<>();
    Adaptador_mascotas adapter;
    String url;
    FirebaseMascotas firebaseMascotas;
    ModeloMascotas modeloMascotas;
    ProgressBar progressBar;
    TextView cargando;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_mascotas2, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView1);
        progressBar=v.findViewById(R.id.progress_mascotas);
        cargando=v.findViewById(R.id.cargando);
        firebaseMascotas=new FirebaseMascotas();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference("mascotas");
        final ArrayList<ModeloMascotas> mascotas_list=new ArrayList<>();
        adapter = new Adaptador_mascotas(getContext(), mascotas_list);

        progressBar.getIndeterminateDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        progressBar.setVisibility(View.VISIBLE);
        cargando.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1:snapshot.getChildren()){
                    if(dataSnapshot1.exists()){
                        modeloMascotas=dataSnapshot1.getValue(ModeloMascotas.class);
                        mascotas_list.add(modeloMascotas);
                        Toast.makeText(getContext(),"datos "+mascotas_list.get(0).getNombre(),Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getContext(),"no hay datos...",Toast.LENGTH_SHORT).show();
                    }
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                cargando.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //mascotas_list=obtener_datos();
        adapter = new Adaptador_mascotas(getContext(), mascotas_list);
        recyclerView.setAdapter(adapter);
        return v; }

    private ArrayList<ModeloMascotas> obtener_datos() {
        ArrayList<ModeloMascotas> mascotas=new ArrayList<>();
        mascotas=firebaseMascotas.CargarDatos(getContext(),adapter);
        return mascotas;
    }

}