package com.example.celicaconect_10.Navegador.Menu.ConsultaAgua;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador.Adaptador_mascotas;
import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador.ModeloMascotas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Provider_Agua {
    DatabaseReference database;
    Modelo modelo_agua;
    Context context;
    public ArrayList<Modelo> agua_list;

    public Provider_Agua() {
        database= FirebaseDatabase.getInstance().getReference();
        modelo_agua=new Modelo();

    }


    public  ArrayList<Modelo> datos(Context c,String cedula){
        agua_list=new ArrayList<>();
        database.child("1XLTjS8DrB9EG5s0vqlVmiVzK-XSL5VX5KGiluXSeAo0").child("Agua").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    if (dataSnapshot1.exists()) {
                        modelo_agua=dataSnapshot1.getValue(Modelo.class);
                        agua_list.add(modelo_agua);

                    } else {
                        Toast.makeText(c, "no hay datos...", Toast.LENGTH_SHORT).show();
                    }
                }
                Log.d("fatal",Integer.toString( agua_list.size()));
                Log.d("fatal",agua_list.get(0).getNombre());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return agua_list;
    }
}
