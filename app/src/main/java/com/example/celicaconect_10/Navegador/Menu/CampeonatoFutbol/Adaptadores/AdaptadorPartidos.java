package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones.PartidosColeccions;
import com.example.celicaconect_10.R;

import java.util.ArrayList;

public class AdaptadorPartidos extends RecyclerView.Adapter<AdaptadorPartidos.MyViewHolder> {
    Context context;
    ArrayList<PartidosColeccions> partidos;

    public AdaptadorPartidos(Context context, ArrayList<PartidosColeccions> partidos) {
        this.context = context;
        this.partidos = partidos;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardvs,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.equipo1.setText(partidos.get(i).getEquipo1());
        myViewHolder.equipo2.setText(partidos.get(i).getEquipo2());
        myViewHolder.fecha.setText(partidos.get(i).getFecha());
        myViewHolder.nfecha.setText("Fecha "+partidos.get(i).getNfecha());

    }

    @Override
    public int getItemCount() {
        return partidos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView equipo1,equipo2,fecha,nfecha;
        public MyViewHolder( View itemView) {
            super(itemView);
            equipo1=itemView.findViewById(R.id.equipo1);
            equipo2=itemView.findViewById(R.id.equipo2);
            fecha=itemView.findViewById(R.id.fechapartido);
            nfecha=itemView.findViewById(R.id.nfecha);

        }
    }
}
