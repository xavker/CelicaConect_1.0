package com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.celicaconect_10.R;

import java.util.ArrayList;

public class Adaptador_mascotas extends RecyclerView.Adapter<Adaptador_mascotas.MyViewHolder> {

    Context context;
    ArrayList<ModeloMascotas> modelArrayList = new ArrayList<>();
    RequestManager glide;
    public Adaptador_mascotas(Context context, ArrayList<ModeloMascotas> modelFeedArrayList) {
        this.context = context;
        this.modelArrayList = modelFeedArrayList;
        glide = Glide.with(context);
    }

    @NonNull
    @Override
    public Adaptador_mascotas.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mascota, parent, false);
        Adaptador_mascotas.MyViewHolder viewHolder = new Adaptador_mascotas.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador_mascotas.MyViewHolder holder, int position) {
        final ModeloMascotas modelmascota = modelArrayList.get(position);

        holder.nombre.setText(modelmascota.getNombre());
        holder.propietario.setText("Propietario: " + modelmascota.getPropietario());
        holder.raza.setText("Raza: " +modelmascota.raza);
        holder.direccion.setText("Direccion: "+modelmascota.getDireccion());
        holder.telefono.setText("Contacto: "+ modelmascota.telefono);

        String estado=modelmascota.getEstado();

        if(estado.equals("perdido")){
            glide.load(R.drawable.icons8_lost_60).into(holder.estado);
        }
        if(estado.equals("encasa")){
            glide.load(R.drawable.icons8_pet_48).into(holder.estado);
        }
        holder.estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estado.equals("perdido")){
                    Toast.makeText(context,"Su mascota esta desaparecida te ayudaremos a encontrarla",Toast.LENGTH_SHORT).show();
                }
                if(estado.equals("encasa")){
                    Toast.makeText(context,"Su mascota esta en casa",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*glide.load(modelFeed).into(holder.imgView_proPic);

        if (modelFeed.getPostpic() == 0) {
            holder.imgView_postPic.setVisibility(View.GONE);
        } else {
            holder.imgView_postPic.setVisibility(View.VISIBLE);
            glide.load(modelFeed.getPostpic()).into(holder.imgView_postPic);
        }*/
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, propietario,direccion, telefono,raza;
        ImageView foto, estado;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foto =  itemView.findViewById(R.id.imgView_postPic2);
            estado =  itemView.findViewById(R.id.m_estado);

            nombre =  itemView.findViewById(R.id.m_nombre);
            propietario =  itemView.findViewById(R.id.m_propietario);
            direccion =  itemView.findViewById(R.id.m_direccion);
            telefono = itemView.findViewById(R.id.m_telefono);
            raza = itemView.findViewById(R.id.m_raza);
        }
    }
}
