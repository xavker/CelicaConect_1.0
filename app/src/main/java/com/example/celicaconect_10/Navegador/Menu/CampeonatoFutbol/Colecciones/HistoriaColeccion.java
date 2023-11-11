package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones;

import java.util.Comparator;

public class HistoriaColeccion {
    private String equipo;
    private String ano;

    public HistoriaColeccion(String equipo, String ano) {
        this.equipo = equipo;
        this.ano = ano;
    }
    public static final Comparator<HistoriaColeccion> BY_NAME_ALPHABETICAL=new Comparator<HistoriaColeccion>() {
        @Override
        public int compare(HistoriaColeccion o1, HistoriaColeccion o2) {
            return  o2.ano.compareTo(o1.ano);
        }
    };
    public HistoriaColeccion() {

    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
