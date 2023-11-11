package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Adaptadores.AdaptadorHistoria;
import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones.HistoriaColeccion;
import com.example.celicaconect_10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;



public class HistoriaCampeonato extends Fragment {
    DatabaseReference databasehistorias;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerViewh;
    ArrayList list;
    AdaptadorHistoria adaptadorHistoria;
    public HistoriaCampeonato() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_historia_campeonato, container, false);
        if (firebaseDatabase != null) {
            firebaseDatabase= FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }
        recyclerViewh=view.findViewById(R.id.recyclerhistoria);
        recyclerViewh.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<HistoriaColeccion>();

        databasehistorias= FirebaseDatabase.getInstance().getReference().child("historia");
        databasehistorias.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d1:dataSnapshot.getChildren()) {
                    HistoriaColeccion historiaColeccion = d1.getValue(HistoriaColeccion.class);
                    list.add(historiaColeccion);
                }
                adaptadorHistoria=new AdaptadorHistoria(getContext(),list);
                recyclerViewh.setAdapter(adaptadorHistoria);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Error al leer base de datos",Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
