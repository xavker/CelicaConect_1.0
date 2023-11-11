package com.example.celicaconect_10.Navegador.Menu.Noticias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.celicaconect_10.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;

public class Noticias extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ModelFeed> modelFeedArrayList = new ArrayList<>();
    AdapterFeed adapterFeed;
    String url;

    private RequestQueue mqueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mqueue=MySingleton.getInstance(getActivity()).getRequestQueue();
        url="https://gadcelica.gob.ec/";
        View v=inflater.inflate(R.layout.fragment_noticias, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapterFeed = new AdapterFeed(getActivity(), modelFeedArrayList);
        recyclerView.setAdapter(adapterFeed);

        populateRecyclerView();

        //obtener_datos();
        return v;
    }

    private void obtener_datos() {
        Log.d("TAG", url);
        StringRequest mRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Document doc=  Jsoup.parse(response);
                //Elements itemElement=doc.select("div.efbl-thumbnail-col.efbl-col-sm-6");
                //itemElement .get(0).select("a").first().attr("href");
                //Elements blogs = doc.select("a").attr("href");;
                Elements blogs = doc.getElementsByClass("efbl-thumbnail-col");
                Log.d("TAG", blogs.text());
                //Log.d("TAG", itemElement.text());
                /*for (Element blog : blogs) {

                }
                for(int i=0;i<itemElement.size();i++){
                    Element item=itemElement.get(i);
                    String titulo=item.text();
                    //String titulo=item.attr("data-imagelink");
                    Log.d("TAG", titulo);
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage());

            }
        });
        MySingleton.getInstance(getActivity()).addToRequestQueue(mRequest);

    }

    private void populateRecyclerView() {

        ModelFeed modelFeed = new ModelFeed(1, 9, 2, R.drawable.ic_propic1, R.drawable.r1,
                "Sajin Maharjan", "2 hrs", "RECONSTRUIMOS JUNTOS NUESTRA HISTÓRICA PLAZA SUCRE. Hemos tomado la importante decisión de reconstruir La Plaza Sucre, lugar histórico para todos nuestros celicanos, lugar de intercambio.");
        modelFeedArrayList.add(modelFeed);
        modelFeed = new ModelFeed(2, 26, 6, R.drawable.ic_propic1, R.drawable.r2,
                "Karun Shrestha", "9 hrs", "SIMULACRO NACIONAL POR EL FENOMENO DEL NIÑO Junto a los integrantes del COE cantonal y Directores de GADM-Celica, participamos de la convocatoria realizada por la");
        modelFeedArrayList.add(modelFeed);
        modelFeed = new ModelFeed(3, 17, 5, R.drawable.ic_propic1, R.drawable.r3,
                "Lakshya Ram", "13 hrs", "FORTALECEMOS LA VIALIDAD RURAL \uD83D\uDEA7\uD83D\uDE9C Personal y maquinaria de la Alcaldía de Celica trabajan arduamente en la limpieza de cunetas, bacheo y rasanteo en la");
        modelFeedArrayList.add(modelFeed);

        adapterFeed.notifyDataSetChanged();
    }
}