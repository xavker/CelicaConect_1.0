package com.example.celicaconect_10.Navegador.Menu.Conversor;

/**
 * Created by CHENAO on 13/07/2017.
 */

public class DatosConversor {

    private String nombre;
    private String info;
    private int imagenId;
    private String valor_in;
    private String valor_out;



    public DatosConversor(String nombre, String info, int imagenId,String valor_in, String valor_out) {
        this.nombre = nombre;
        this.info = info;
        this.imagenId = imagenId;
        this.valor_in = valor_in;
        this.valor_out = valor_out;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
    public String getValor_in() {
        return valor_in;
    }

    public void setValor_in(String valor_in) {
        this.valor_in = valor_in;
    }

    public String getValor_out() {
        return valor_out;
    }

    public void setValor_out(String valot_out) {
        this.valor_out = valot_out;
    }
    }
