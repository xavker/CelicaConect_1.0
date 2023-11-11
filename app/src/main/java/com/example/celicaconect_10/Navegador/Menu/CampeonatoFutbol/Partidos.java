package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Adaptadores.AdaptadorPartidos;
import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Adaptadores.NombreFirebase;
import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones.PartidosColeccions;
import com.example.celicaconect_10.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Partidos extends Fragment {
    DatabaseReference databaseReferencepartidos,databaseReferenceequipos;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    ArrayList list,equipo;
    long a;
    private Calendar fechaf,fechai;
    AdaptadorPartidos adaptadorPartidos;
    TextView nfechain,fechain1,equipo1int,equipo2int;
    Spinner equipo1in,equipo2in;
    Button actualizarpartidos,nfechamas,nfechamenos,fechainb;
    ImageView imageView;
    private int diain,mesin,anoin,horain,minutoin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_partidos, container, false);

        if (firebaseDatabase != null) {
            firebaseDatabase= FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }
        FloatingActionButton fab = view.findViewById(R.id.fabpartido);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                displayInputDialogPartido();
                Snackbar.make(view, "Agrega un nuevo partido", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        recyclerView=view.findViewById(R.id.recyclerpartidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<PartidosColeccions>();
        databaseReferencepartidos= FirebaseDatabase.getInstance().getReference().child("partidos");
        databaseReferencepartidos.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d1:dataSnapshot.getChildren()){
                    PartidosColeccions e=d1.getValue(PartidosColeccions.class);
                    list.add(e);
                }
               // Collections.sort(list,Equipos.BY_NAME_ALPHABETICAL);
                adaptadorPartidos=new AdaptadorPartidos(getContext(),list);
                recyclerView.setAdapter(adaptadorPartidos);
               // Toast.makeText(getContext(),"en lista: "+dataSnapshot.getChildrenCount(),Toast.LENGTH_SHORT).show();
                //list.clear();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Error al leer base de datos",Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }

    private void displayInputDialogPartido() {
        final Dialog d = new Dialog(getContext());
        d.setTitle("Guardar en Base de Datos");
        d.setContentView(R.layout.entradadepartidos);
        final PartidosColeccions partidosColeccions=new PartidosColeccions();

        databaseReferenceequipos= FirebaseDatabase.getInstance().getReference();
        NombreFirebase nombreFirebase=new NombreFirebase(databaseReferenceequipos);

  //      imageView=d.findViewById(R.id.imageView);
//        imageView.setImageResource(R.drawable.football);
        d.show();

        List<String>categorias=new ArrayList<>();
        categorias.add(0,"Escoge un equipo");
        categorias.add("Wolf's");
        categorias.add("Good People");
        categorias.add("Uvas");
        categorias.add("Bi 19 Carchi");
        categorias.add("Punisher");
        categorias.add("Pozul");
        categorias.add("Santa Teresita");
        categorias.add("Nueva Generacion");
        categorias.add("Dragartos");
        categorias.add("5 de septiembre");
        nfechain=d.findViewById(R.id.nfechain);
        equipo1in=d.findViewById(R.id.spinner);
        equipo2in = d.findViewById(R.id.spinner2);
        nfechamas=d.findViewById(R.id.buttonmaxnfecha);
        nfechamenos=d.findViewById(R.id.buttonmminnfecha);
        fechain1=d.findViewById(R.id.fechain1);
        actualizarpartidos = d.findViewById(R.id.actualizarpartido);

        nfechamenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(nfechain.getText().toString())-1;
                nfechain.setText(String.valueOf(i));
            }
        });
        nfechamas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(nfechain.getText().toString())+1;
                nfechain.setText(String.valueOf(i));
            }
        });

        final String[] equipo1 = new String[1];
        final String[] equipo2 = new String[1];
        //List<String> listanombres = nombreFirebase.retrieve();
        ArrayAdapter<String>adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        equipo1in.setAdapter(adapter);
        equipo1in.setPrompt("Selecciona un Equipo");
        equipo1in.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(parent.getContext(), "El Elemento seleccionado es posición número: "+position +" El String es: "+equipo1in.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                equipo1[0] =equipo1in.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        equipo2in.setAdapter(adapter);
        equipo2in.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(parent.getContext(), "El Elemento seleccionado es posición número: "+position +" El String es: "+equipo2in.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                equipo2[0] =equipo2in.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fechainb=d.findViewById(R.id.fechainb);
        fechainb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c= Calendar.getInstance();
                diain=c.get(Calendar.DAY_OF_MONTH);
                mesin=c.get(Calendar.MONTH);
                anoin=c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fechain1.setText(dayOfMonth+ "/"+(month+1)+"/"+year);
                        horain=c.get(Calendar.HOUR_OF_DAY);
                        minutoin=c.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String fecha2=fechain1.getText().toString();
                                fechain1.setText(fecha2  + " - "+ hourOfDay + ":"+minute);
                            }
                        },horain,minutoin,true);
                        timePickerDialog.show();
                    }
                }
                        ,anoin,mesin,diain);
                datePickerDialog.show();


                //Toast.makeText(getContext(),"resta: "+ Objects.toString(a),Toast.LENGTH_SHORT).show();
            }
        });


        actualizarpartidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nfecha=nfechain.getText().toString();
                Log.d("fecha1",nfecha);
                final String fecha=fechain1.getText().toString();
                Log.d("fecha2",fecha);
                partidosColeccions.setNfecha(nfecha);
                partidosColeccions.setFecha(fecha);
                partidosColeccions.setEquipo1(equipo1[0]);
                partidosColeccions.setEquipo2(equipo2[0]);

                String id=databaseReferencepartidos.push().getKey();
                if (!TextUtils.isEmpty(fecha)) {
                    //Datos datosin=new Datos(nombrein1,deudain1,fechainicionin1,fechainicionin1);
                    databaseReferencepartidos.child(id).setValue(partidosColeccions);
                    Toast.makeText(getContext(), "Registrado nuevo encuentro.", Toast.LENGTH_SHORT).show();
                    list.clear();
                    d.dismiss();
                } else {
                    Toast.makeText(getContext(), "Fecha no debe estar vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
