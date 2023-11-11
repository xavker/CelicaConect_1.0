package com.example.celicaconect_10.Navegador.Menu.ConsultaAgua;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celicaconect_10.Navegador.Menu.Noticias.Noticias;
import com.example.celicaconect_10.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Pagoagua extends Fragment {

    private TextView resultado;
    private TextInputLayout dato;
    private LinearLayout resultado_linear;
    private Button consultar, pagar;

;

    DatabaseReference database;

    private String nombre1;
    private String direccion1;
    private String fecha1;
    private String cedula1;
    private String medidor1;
    private String valor1;
    private String estado1;
    private String valor_anterior1;

    private Bundle result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_pagoagua, container, false);
        database= FirebaseDatabase.getInstance().getReference();
        result=new Bundle();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        consultar=v.findViewById(R.id.consulta_agua);
        dato=v.findViewById(R.id.id_cedula);
        pagar=v.findViewById(R.id.pagar_agua);
        resultado=v.findViewById(R.id.resultado_texto);
        resultado_linear=v.findViewById(R.id.resultado_agua);
        nombre1="";
        direccion1="";
        fecha1="";
        estado1="";
        valor_anterior1="";
        valor1="";

        resultado_linear.setVisibility(View.GONE);


        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dato.getEditText().getText().toString().isEmpty()){
                    consultar(dato.getEditText().getText().toString());
                    resultado_linear.setVisibility(View.VISIBLE);


                }else {
                    Toast.makeText(getContext(),"Campo cedula esta vacio",Toast.LENGTH_SHORT).show();
                }

            }
        });
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.setFragmentResult("key",result);
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new CobroAgua()).commit();
            }
        });
        return v;
    }

    public void consultar(String cedula) {
        //agua_list=new ArrayList<>();
        database.child("1XLTjS8DrB9EG5s0vqlVmiVzK-XSL5VX5KGiluXSeAo0").child("Agua").child(cedula).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    nombre1=snapshot.child("nombre").getValue().toString();
                    direccion1=snapshot.child("direccion").getValue().toString();
                    cedula1=snapshot.child("cedula").getValue().toString();
                    fecha1=snapshot.child("fecha").getValue().toString();
                    medidor1=snapshot.child("medidor").getValue().toString();
                    valor1=snapshot.child("valor").getValue().toString();
                    estado1=snapshot.child("estado").getValue().toString();
                    valor_anterior1=snapshot.child("valor_anterior").getValue().toString();
                    resultado.setText(ordenar_reporte());
                    result.putString("valor","$ "+valor1);
                    result.putString("nombre",cedula1);

                } else {
                    Toast.makeText(getContext(), "no se encontro los datos solicitados.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

    }
    public  String ordenar_reporte(){
        //final Modelo datos_reporte = datos.get(1);
        if(estado1.equals("debe")){
            estado1="Pendiente de pago.";
        } else if (estado1.equals("pagado")) {
            estado1="Pagado.";
        }else if (estado1.equals("proceso")) {
            estado1="Procesando pago.";
        }
        String reporte="      Reporte de Agua del mes \n" +"\n"+
                "Nombre: "+nombre1+". \n"+
                "Dirección: "+direccion1+ "\n"+
                "Numero del medidor: "+medidor1+ "\n"+
                "Numero de documento: "+cedula1+"."+ "\n"+
                "Fecha: "+fecha1+ "\n"+
                "Valor a pagar: "+valor1+ "\n"+
                "Valor anterior a pagar: "+valor_anterior1+ "\n"+
                "Estado: "+estado1+ "\n";
        return  reporte;

    }
}