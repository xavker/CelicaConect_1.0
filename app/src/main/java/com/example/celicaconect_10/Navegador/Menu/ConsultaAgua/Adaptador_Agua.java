package com.example.celicaconect_10.Navegador.Menu.ConsultaAgua;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador.ModeloMascotas;
import com.example.celicaconect_10.R;

import java.util.ArrayList;

public class Adaptador_Agua extends RecyclerView.Adapter<Adaptador_Agua.MyViewHolder> {
    Context context;
    ArrayList<ModeloMascotas> modelArrayList = new ArrayList<>();

    public Adaptador_Agua(Context context, ArrayList<ModeloMascotas> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public Adaptador_Agua.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador_Agua.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
