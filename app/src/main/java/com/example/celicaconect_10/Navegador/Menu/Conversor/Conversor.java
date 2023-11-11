package com.example.celicaconect_10.Navegador.Menu.Conversor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.celicaconect_10.R;

import java.util.ArrayList;


public class Conversor extends Fragment {
    RecyclerView recyclerPersonajes;
    ArrayList<DatosConversor> listaPersonaje;

    public Conversor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_conversor, container, false);
        listaPersonaje=new ArrayList<>();
        recyclerPersonajes=  vista.findViewById(R.id.recyclerId);
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();
        PaisesAdapter adapter=new PaisesAdapter(listaPersonaje);
        recyclerPersonajes.setAdapter(adapter);
        return vista;
    }

    private void llenarLista() {
        listaPersonaje.add(new DatosConversor("Dolares a Soles","Un Dolar = 3.31 Soles",R.drawable.ecuador_peru,"","0"));
        listaPersonaje.add(new DatosConversor("Soles a Dolares","Un Sol = 0.30 Dolares",R.drawable.peru_ecuador,"","0"));
        listaPersonaje.add(new DatosConversor("Dolares a Pesos Colombianos","Un Dolar = 3230.70 Pesos Colombianos.",R.drawable.ecuador_colombia,"","0"));
        listaPersonaje.add(new DatosConversor("Pesos a Dolares Colombianos","Un Peso Colombiano = 0.00031 Dolares.",R.drawable.colombia_ecuador,"","0"));

    }

}
