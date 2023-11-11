package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Adaptadores.AdaptadorEquipos;
import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones.Equipos;
import com.example.celicaconect_10.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collections;

public class TabladePosiciones extends Fragment {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    ArrayList list;
    AdaptadorEquipos adaptadorEquipos;
    EditText nombrein, pjin,ppin,pein,gfin,gein,puntosin,pgin;
    Button actualizar;
    ImageView imagen;
    Equipos equipos=new Equipos();
    ProgressBar progressBar;
    TextView cargando;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragmenttabledeposiciones, container, false);
        progressBar=view.findViewById(R.id.progress_futbol);
        cargando=view.findViewById(R.id.cargandof);
        if (firebaseDatabase != null) {
            firebaseDatabase= FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                displayInputDialog();
                Snackbar.make(view, "Agrega un nuevo equipo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        recyclerView=view.findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<Equipos>();

        progressBar.getIndeterminateDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        progressBar.setVisibility(View.VISIBLE);
        cargando.setVisibility(View.VISIBLE);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("equipos");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d1:dataSnapshot.getChildren()){
                    Equipos e=d1.getValue(Equipos.class);
                    list.add(e);
                }
                Collections.sort(list,Equipos.BY_NAME_ALPHABETICAL);
                adaptadorEquipos=new AdaptadorEquipos(getContext(),list);
                recyclerView.setAdapter(adaptadorEquipos);
               // Toast.makeText(getContext(),"usuarios"+dataSnapshot.getChildrenCount(),Toast.LENGTH_SHORT).show();
                //list.clear();
                progressBar.setVisibility(View.GONE);
                cargando.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Error al leer base de datos",Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }


    private void displayInputDialog() {
        final Dialog d = new Dialog(getContext());
        d.setTitle("Guardar en Base de Datos");
        d.setContentView(R.layout.entradadedatosprincipal);
        imagen=d.findViewById(R.id.imageView);
        imagen.setImageResource(R.drawable.football);
        d.show();
        nombrein = d.findViewById(R.id.nombre1);
        pjin=d.findViewById(R.id.pj1);
        pgin=d.findViewById(R.id.pg1);
        pein = d.findViewById(R.id.pe1);
        ppin = d.findViewById(R.id.pp1);
        gfin = d.findViewById(R.id.gf1);
        gein =d.findViewById(R.id.ge1);
        puntosin =d.findViewById(R.id.puntos1);
        actualizar = d.findViewById(R.id.actualizar);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final String nombrein1=nombrein.getText().toString();
                final String pjin1=pjin.getText().toString();
                final String pgin1=pgin.getText().toString();
                final String pein1=pein.getText().toString();
                final String ppin1=ppin.getText().toString();
                final String gfin1=gfin.getText().toString();
                final String gein1=gein.getText().toString();
                final String puntosin1=puntosin.getText().toString();
                String id=databaseReference.push().getKey();
                equipos.setNombre(nombrein1);
                equipos.setPj(pjin1);
                equipos.setPe(pein1);
                equipos.setPp(ppin1);
                equipos.setPg(pgin1);
                equipos.setGf(gfin1);
                equipos.setGe(gein1);
                equipos.setPuntos(puntosin1);


                if (!TextUtils.isEmpty(nombrein1)) {
                    nombrein.setText("");
                    pjin.setText("");
                    pein.setText("");
                    ppin.setText("");
                    gfin.setText("");
                    gein.setText("");
                    puntosin.setText("");
                    //Datos datosin=new Datos(nombrein1,deudain1,fechainicionin1,fechainicionin1);
                    databaseReference.child(id).setValue(equipos);
                    Toast.makeText(getContext(), "Registrado nuevo usuario.", Toast.LENGTH_SHORT).show();
                    list.clear();
                    d.dismiss();
                } else {
                    Toast.makeText(getContext(), "Nombre no debe estar vacio", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}
