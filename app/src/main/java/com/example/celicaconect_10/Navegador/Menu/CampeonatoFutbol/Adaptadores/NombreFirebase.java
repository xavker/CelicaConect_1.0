package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Adaptadores;


import androidx.annotation.NonNull;

import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones.Equipos;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class NombreFirebase {
DatabaseReference db;
Boolean saved=null;

    public NombreFirebase(DatabaseReference db) {
        this.db = db;
    }
    public  Boolean save(Equipos equipos){
        if(equipos==null)  {
            saved=false;
        }else {
            try  {
                db.child("equipos").push().setValue(equipos);
                saved=true;
            }catch (DatabaseException e){
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }

    public ArrayList<String> retrieve(){
        final ArrayList<String> listanoombreequipos=new ArrayList<>();

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot,listanoombreequipos);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot,listanoombreequipos);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        return listanoombreequipos;
    }

    private void fetchData(DataSnapshot snapshot, ArrayList<String> listadenombre)
    {
        listadenombre.clear();
        for (DataSnapshot ds:snapshot.getChildren())
        {
            String name=ds.getValue(Equipos.class).getNombre();
            listadenombre.add(name);
        }
    }
}
