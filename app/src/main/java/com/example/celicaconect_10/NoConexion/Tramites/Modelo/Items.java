package com.example.celicaconect_10.NoConexion.Tramites.Modelo;

import com.example.celicaconect_10.R;

public class Items {
    private String titulo;
    private int imagen;
    public Items() {

    }
    public Items(String titulo, int imagen) {
        this.titulo = titulo;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
    public static  Items[] ITEMS = {
            new Items("Agua Potable",R.mipmap.s_potable ),
            new Items("Calles",R.mipmap.s_calles),
            new Items("Alcantarillado",R.mipmap.s_alcantarillas),
            new Items("Mascotas",R.mipmap.s_mascotas),
            new Items("Ruido",R.mipmap.s_ruido),
            new Items("Seguridad",R.mipmap.s_seguridad),
            new Items("Otros",R.mipmap.s_predio),
            };
}
