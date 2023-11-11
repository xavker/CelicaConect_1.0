package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones;

import java.util.Comparator;

public class Equipos {
    private String nombre;
    private String pj;
    private String pg;
    private String pe;
    private String pp;
    private String gf;
    private String ge;
    private String puntos;

    public Equipos(String nombre, String pe, String pj, String pg, String pp, String gf, String ge, String puntos) {
        this.nombre = nombre;
        this.pj = pj;
        this.pg = pg;
        this.pp = pp;
        this.gf = gf;
        this.ge = ge;
        this.pe = pe;
        this.puntos = puntos;
    }

    public Equipos() {

    }

    public static final Comparator<Equipos> BY_NAME_ALPHABETICAL=new Comparator<Equipos>() {
        @Override
        public int compare(Equipos o1, Equipos o2) {
            return o2.puntos.compareTo(o1.puntos);
        }
    };
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPj() {
        return pj;
    }

    public void setPj(String pj) {
        this.pj = pj;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getGf() {
        return gf;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public String getGe() {
        return ge;
    }

    public void setGe(String ge) {
        this.ge = ge;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}
