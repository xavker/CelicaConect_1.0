package com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Provider;


import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador.Adaptador_mascotas;
import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador.ModeloMascotas;
import com.example.celicaconect_10.Navegador.Menu.Turismo.Colecciones.Lugares;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseMascotas {
    DatabaseReference database;
    ModeloMascotas modeloMascotas;
    Context context;
    Adaptador_mascotas adaptador_mascotas;

    public FirebaseMascotas() {
        database=FirebaseDatabase.getInstance().getReference("mascotas");
        modeloMascotas=new ModeloMascotas();
    }

    public void Guardar_mascota(ModeloMascotas modeloMasccotas){
        database.push().setValue(modeloMasccotas);
    }

    public ArrayList<ModeloMascotas> CargarDatos(Context c,Adaptador_mascotas a) {
        ArrayList<ModeloMascotas> mascotas_list=new ArrayList<>();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1:snapshot.getChildren()){
                    if(dataSnapshot1.exists()){
                        modeloMascotas=dataSnapshot1.getValue(ModeloMascotas.class);
                        mascotas_list.add(modeloMascotas);
                        //Toast.makeText(c, "datos "+mascotas_list.get(0).getNombre(),Toast.LENGTH_SHORT).show();

                       // mascotas_list.add(dataSnapshot1.getValue(ModeloMascotas.class));
                    }else{
                        Toast.makeText(c, "no hay datos...",Toast.LENGTH_SHORT).show();
                    }
                }
                a.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context,"no hay datos...",Toast.LENGTH_SHORT).show();

            }
        });

        return mascotas_list;
    }
}
