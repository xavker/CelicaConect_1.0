package com.example.celicaconect_10.Navegador.Menu.Turismo.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.celicaconect_10.Navegador.Menu.Turismo.Colecciones.Lugares;
import com.example.celicaconect_10.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


import java.util.List;

public class AdaptadorTurismo extends PagerAdapter {
    Context context;
    List<Lugares>lugares;
    LayoutInflater layoutInflater;

    public AdaptadorTurismo(Context context, List<Lugares> lugares) {
        this.context = context;
        this.lugares = lugares;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lugares.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view==o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=layoutInflater.inflate(R.layout.cardviewturismo,container,false);

        ImageView imagenturismo=view.findViewById(R.id.lugar);
        TextView titulolugar=view.findViewById(R.id.titulo_lugar);
        TextView descripcionlugar=view.findViewById(R.id.descripcion_lugar);
        FloatingActionButton bfavlugar=view.findViewById(R.id.favlugar);
        //setdata
        Picasso.with(context).load(lugares.get(position).getImagen()).into(imagenturismo);
        titulolugar.setText(lugares.get(position).getNombre());
        descripcionlugar.setText(lugares.get(position).getDescripcion());
        String url=lugares.get(position).getUrl();

        //evento
        bfavlugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"buscando mejor ruta en el mapa...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+"-4.102959451403984, -79.9559415810126"+"&daddr="+
                        url)); //o la direccion/consulta que quiera "http://maps.google.com/maps?q="+ myLatitude  +"," + myLongitude +"("+ labLocation + ")&iwloc=A&hl=es"
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                context.startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Visita Celica",Toast.LENGTH_SHORT).show();

            }
        });
        container.addView(view);
        return view;
    }
}
