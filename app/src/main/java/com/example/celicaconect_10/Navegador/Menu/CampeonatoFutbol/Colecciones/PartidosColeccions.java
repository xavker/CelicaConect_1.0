package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.Colecciones;

public class PartidosColeccions {
   private String equipo1;
   private String equipo2;
   private String nfecha;
   private String fecha;

    public PartidosColeccions(String equipo1, String equipo2, String nfecha, String fecha) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.nfecha = nfecha;
        this.fecha = fecha;
    }
    public PartidosColeccions(){

    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public String getNfecha() {
        return nfecha;
    }

    public void setNfecha(String nfecha) {
        this.nfecha = nfecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
