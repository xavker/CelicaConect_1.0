package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones.HistoriaColeccion;
import com.example.celicaconect_10.R;


import java.util.ArrayList;

public class AdaptadorHistoria extends RecyclerView.Adapter<AdaptadorHistoria.MyViewHolder> {
    Context context;
    ArrayList<HistoriaColeccion> historiaColeccions;

    public AdaptadorHistoria(Context context, ArrayList<HistoriaColeccion> historiaColeccions) {
        this.context = context;
        this.historiaColeccions = historiaColeccions;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardviewhistoria,viewGroup,false));
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int i) {
        myViewHolder.ano.setText(historiaColeccions.get(i).getAno());
        myViewHolder.equipo.setText(historiaColeccions.get(i).getEquipo());
    }

    @Override
    public int getItemCount() {
        return historiaColeccions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView equipo,ano;
        public MyViewHolder(View itemView) {
            super(itemView);
            equipo=itemView.findViewById(R.id.equipohistoria);
            ano=itemView.findViewById(R.id.anohistoria);
        }
    }
}
