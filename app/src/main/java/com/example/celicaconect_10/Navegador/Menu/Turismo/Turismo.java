package com.example.celicaconect_10.Navegador.Menu.Turismo;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.celicaconect_10.Navegador.Menu.Turismo.Adaptadores.AdaptadorTurismo;
import com.example.celicaconect_10.Navegador.Menu.Turismo.Colecciones.Lugares;
import com.example.celicaconect_10.Navegador.Menu.Turismo.Listener.IFirebaseturismo;
import com.example.celicaconect_10.Navegador.Menu.Turismo.Transformer.DepthPageTransformer;
import com.example.celicaconect_10.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Turismo extends Fragment implements IFirebaseturismo {
ViewPager viewPager;
AdaptadorTurismo adaptadorTurismo;
DatabaseReference databaseturismo;
IFirebaseturismo iFirebaseturismo;
ProgressBar progressBar;
TextView cargando;

//private List<Lugares>lugar=new ArrayList<>();
    public Turismo() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //inicio firebase
        View view=inflater.inflate(R.layout.fragment_turismo, container, false);
        //inicializoevento
        databaseturismo= FirebaseDatabase.getInstance().getReference("turismo");
        iFirebaseturismo=this;
        //lugares= FirebaseFirestore.getInstance().collection("turismo");
        progressBar=view.findViewById(R.id.progress_turismo);
        cargando=view.findViewById(R.id.cargandot);

        progressBar.getIndeterminateDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        progressBar.setVisibility(View.VISIBLE);
        cargando.setVisibility(View.VISIBLE);

        cargarLugares();
        viewPager=view.findViewById(R.id.viewpageturismos);
        viewPager.setPageTransformer(true,new DepthPageTransformer());

        return  view;
    }

    private void cargarLugares() {
        databaseturismo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Lugares> lugaresList=new ArrayList<>();
                if (snapshot.exists()){
                    for (DataSnapshot snapshot1:snapshot.getChildren()){
                        //final Lugares l=snapshot1.getValue(Lugares.class);
                        lugaresList.add(snapshot1.getValue(Lugares.class));
                        //Log.i("fatal1",lugaresList.getNombre());
                    }
                   iFirebaseturismo.onFirebaseloadSuccesss(lugaresList);
                }else{
                    Toast.makeText(getContext(),"no hay datos...",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"no hay datos...",Toast.LENGTH_SHORT).show();

            }
        });
    /*          lugares.get().addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        iFirebaseturismo.onFirebaseLoadFailed(e.getMessage());
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                           List<Lugares>lugar=new ArrayList<>();
                            for(QueryDocumentSnapshot qs1:task.getResult()){
                                Lugares l=qs1.toObject(Lugares.class);
                                lugar.add(l);
                            }
                            iFirebaseturismo.onFirebaseloadSuccesss(lugar);
                    }
                }
                });*/
    }

    public void onFirebaseloadSuccesss(List<Lugares> lugaresList) {
        adaptadorTurismo=new AdaptadorTurismo(getContext(),lugaresList);
        viewPager.setAdapter(adaptadorTurismo);
        progressBar.setVisibility(View.GONE);
        cargando.setVisibility(View.GONE);
    }

    @Override
    public void onFirebaseLoadFailed(String mensage) {
        Toast.makeText(getContext(),mensage,Toast.LENGTH_SHORT).show();
    }
}
