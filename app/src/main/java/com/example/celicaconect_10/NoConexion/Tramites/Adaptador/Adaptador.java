package com.example.celicaconect_10.NoConexion.Tramites.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celicaconect_10.NoConexion.Tramites.Modelo.Items;
import com.example.celicaconect_10.R;

public class Adaptador extends BaseAdapter{
    Context context;


    public Adaptador(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return Items.ITEMS.length;
    }

    @Override
    public Items getItem(int i) {
        return Items.ITEMS[i];
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getImagen();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.card_view_menu_tramites, viewGroup, false);
        }
        ImageView itemImage =(ImageView) view.findViewById(R.id.imagen);
        TextView itemTitle = (TextView) view.findViewById(R.id.titulo);

        final Items item = getItem(i);
        itemImage.setImageResource(item.getImagen());
        itemTitle.setText(item.getTitulo());




        return view;
    }

}
