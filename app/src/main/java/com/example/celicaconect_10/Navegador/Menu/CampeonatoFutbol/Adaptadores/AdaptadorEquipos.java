package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Adaptadores;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones.Equipos;
import com.example.celicaconect_10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.MyViewHolder> {
    Context context;
    ArrayList<Equipos>equipos;
    DatabaseReference databaseReference;
    TextView nombrein;
    EditText pjin,ppin,pein,pgin,gfin,gein,puntosin;
    Button actualizar;
    ImageView imagen;
    Button buttonminpj,buttonminpg,buttonminpe,buttonminpp,buttonmingf,buttonminge,buttonminp;
    Button buttonmaxpj,buttonmaxpg,buttonmaxpe,buttonmaxpp,buttonmaxgf,buttonmaxge,buttonmaxp;
    Equipos equiposactualizados=new Equipos();

    public AdaptadorEquipos(Context context, ArrayList<Equipos> equipos) {
        this.context = context;
        this.equipos = equipos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cvequipos,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        databaseReference= FirebaseDatabase.getInstance().getReference().child("equipos");
        myViewHolder.nombre.setText(equipos.get(i).getNombre());
        myViewHolder.pj.setText(equipos.get(i).getPj());
        myViewHolder.pe.setText(equipos.get(i).getPe());
        myViewHolder.pp.setText(equipos.get(i).getPp());
        myViewHolder.pg.setText(equipos.get(i).getPg());
        myViewHolder.gf.setText(equipos.get(i).getGf());
        myViewHolder.ge.setText(equipos.get(i).getGe());
        myViewHolder.puntos.setText(equipos.get(i).getPuntos());
        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombrex=equipos.get(i).getNombre();
                String pjx=equipos.get(i).getPj();
                String pgx=equipos.get(i).getPg();
                String pex=equipos.get(i).getPe();
                String ppx=equipos.get(i).getPp();
                String gfx=equipos.get(i).getGf();
                String gex=equipos.get(i).getGe();
                String px=equipos.get(i).getPuntos();


                agregarabono(nombrex,pex,pjx,pgx,ppx,gfx,gex,px);
                }
        });

    }

    private void agregarabono(final String nombrex, String pex, String pjx, String pgx, String ppx, String gfx, String gex, String px) {
        final Dialog d = new Dialog(context);
        d.setTitle("Guardar en Base de Datos");
        d.setContentView(R.layout.entradadedatos);
        imagen=d.findViewById(R.id.imageView);
        imagen.setImageResource(R.drawable.football);
        d.show();
        nombrein = d.findViewById(R.id.nombre1);
        pjin=d.findViewById(R.id.pj1);
        pein = d.findViewById(R.id.pe1);
        pgin=d.findViewById(R.id.pg1);
        ppin = d.findViewById(R.id.pp1);
        gfin = d.findViewById(R.id.gf1);
        gein =d.findViewById(R.id.ge1);
        puntosin =d.findViewById(R.id.puntos1);
        nombrein.setText(nombrex);
        pjin.setText(pjx);
        pgin.setText(pgx);
        pein.setText(pex);
        ppin.setText(ppx);
        gfin.setText(gfx);
        gein.setText(gex);
        puntosin.setText(px);


        buttonminpj=d.findViewById(R.id.buttonmminpj);
        buttonminpg=d.findViewById(R.id.buttonmminpg);
        buttonminpe=d.findViewById(R.id.buttonmminpe);
        buttonminpp=d.findViewById(R.id.buttonmminpp);
        buttonmingf=d.findViewById(R.id.buttonmmingf);
        buttonminge=d.findViewById(R.id.buttonmminge);
        buttonminp=d.findViewById(R.id.buttonmminp);

        buttonmaxpj=d.findViewById(R.id.buttonmaxpj);
        buttonmaxpg=d.findViewById(R.id.buttonmaxpg);
        buttonmaxpe=d.findViewById(R.id.buttonmaxpe);
        buttonmaxpp=d.findViewById(R.id.buttonmaxpp);
        buttonmaxgf=d.findViewById(R.id.buttonmaxgf);
        buttonmaxge=d.findViewById(R.id.buttonmaxge);
        buttonmaxp=d.findViewById(R.id.buttonmaxp);

        buttonminpj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(pjin.getText().toString())-1;
                pjin.setText(String.valueOf(i));
            }
        });
        buttonmaxpj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(pjin.getText().toString())+1;
                pjin.setText(String.valueOf(i));
            }
        });
        buttonminpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(ppin.getText().toString())-1;
                ppin.setText(String.valueOf(i));
            }
        });
        buttonmaxpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(ppin.getText().toString())+1;
                ppin.setText(String.valueOf(i));
            }
        });
        buttonminpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(pein.getText().toString())-1;
                pein.setText(String.valueOf(i));
            }
        });
        buttonmaxpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(pein.getText().toString())+1;
                pein.setText(String.valueOf(i));
            }
        });
        buttonminpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(pgin.getText().toString())-1;
                pgin.setText(String.valueOf(i));
            }
        });
        buttonmaxpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(pgin.getText().toString())+1;
                pgin.setText(String.valueOf(i));
            }
        });
        buttonmingf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(gfin.getText().toString())-1;
                gfin.setText(String.valueOf(i));
            }
        });
        buttonmaxgf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(gfin.getText().toString())+1;
                gfin.setText(String.valueOf(i));
            }
        });
        buttonminge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(gein.getText().toString())-1;
                gein.setText(String.valueOf(i));
            }
        });
        buttonmaxge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(gein.getText().toString())+1;
                gein.setText(String.valueOf(i));
            }
        });
        buttonminp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(puntosin.getText().toString())-1;
                puntosin.setText(String.valueOf(i));
            }
        });
        buttonmaxp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(puntosin.getText().toString())+1;
                puntosin.setText(String.valueOf(i));
            }
        });

        actualizar = d.findViewById(R.id.actualizar);
        actualizar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                final String nombrein1=nombrein.getText().toString();
                final String pjin1=pjin.getText().toString();
                final String pgin1=pgin.getText().toString();
                final String pein1=pein.getText().toString();
                final String ppin1=ppin.getText().toString();
                final String gfin1=gfin.getText().toString();
                final String gein1=gein.getText().toString();
                final String puntosin1=puntosin.getText().toString();

                equiposactualizados.setNombre(nombrein1);
                equiposactualizados.setPj(pjin1);
                equiposactualizados.setPe(pein1);
                equiposactualizados.setPp(ppin1);
                equiposactualizados.setPg(pgin1);
                equiposactualizados.setGf(gfin1);
                equiposactualizados.setGe(gein1);
                equiposactualizados.setPuntos(puntosin1);

                Query q=databaseReference.orderByChild("nombre").equalTo(nombrex);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       for(DataSnapshot ds1:dataSnapshot.getChildren()){
                            String key=ds1.getKey();
                            Toast.makeText(context,"Nombre key: "+ key,Toast.LENGTH_SHORT).show();
                            databaseReference.child(key).setValue(equiposactualizados);
                       }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                Toast.makeText(context, "Actualizando Datos", Toast.LENGTH_SHORT).show();
                d.dismiss();
            }

        });
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       TextView nombre,pj,pp,pe,gf,ge,pg,puntos;
       LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombre);
            pj=itemView.findViewById(R.id.pj);
            pp=itemView.findViewById(R.id.pp);
            pg=itemView.findViewById(R.id.pg);
            pe=itemView.findViewById(R.id.pe);
            gf=itemView.findViewById(R.id.gf);
            ge=itemView.findViewById(R.id.ge);
            linearLayout=itemView.findViewById(R.id.linearequipos);
            puntos=itemView.findViewById(R.id.puntos);
        }
    }
}
