package com.example.celicaconect_10.Navegador.Menu.Conversor;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.celicaconect_10.R;


import java.util.ArrayList;


/**
 * Created by CHENAO on 13/07/2017.
 */

public class PaisesAdapter extends RecyclerView.Adapter<PaisesAdapter.PersonajeViewHolder>{
    public double factor[]={3.31,0.30,3230.70,0.00031};
    ArrayList<DatosConversor> datosConversors;

    public PaisesAdapter(ArrayList<DatosConversor> listaPersonaje) {
        this.datosConversors =listaPersonaje;
    }

    @Override
    public PersonajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_conversor,null,false);
        return new PersonajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PersonajeViewHolder holder, final int position) {
        holder.txtNombre.setText(datosConversors.get(position).getNombre());
        holder.txtInformacion.setText(datosConversors.get(position).getInfo());
        holder.foto.setImageResource(datosConversors.get(position).getImagenId());

        holder.valor_in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String convertido;
                if(!holder.valor_in.getText().toString().isEmpty()) {
                     convertido=calcular(holder.valor_in.getText().toString(),position);

                }else {
                    convertido="0.00";
                }
                datosConversors.get(position).setValor_in(convertido);
                holder.valor_out.setText(datosConversors.get(position).getValor_in());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private String calcular(String aux,int i) {
        String nuevo=String.valueOf(String.format("%.2f", Double.parseDouble(aux)*factor[i]));
        return nuevo;
    }

    @Override
    public int getItemCount() {
        return datosConversors.size();
    }

    public class PersonajeViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre,txtInformacion,valor_out;
        ImageView foto;
        EditText valor_in;
        public PersonajeViewHolder(View itemView) {
            super(itemView);
            txtNombre=  itemView.findViewById(R.id.titulo);
            txtInformacion=itemView.findViewById(R.id.informacion);
            foto=  itemView.findViewById(R.id.imagenconversor);
            valor_in=itemView.findViewById(R.id.valor_in);
            valor_out=itemView.findViewById(R.id.resultado);


        }
    }
}
