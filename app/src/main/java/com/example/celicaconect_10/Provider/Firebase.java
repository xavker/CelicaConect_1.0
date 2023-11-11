package com.example.celicaconect_10.Provider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Firebase(FirebaseDatabase database) {
        this.database = database;
        //database = database.getReference().child("Administracion/Pedidos");

    }

}
