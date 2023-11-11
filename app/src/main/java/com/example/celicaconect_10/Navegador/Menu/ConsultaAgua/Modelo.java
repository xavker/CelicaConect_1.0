package com.example.celicaconect_10.Navegador.Menu.ConsultaAgua;

import java.util.ArrayList;

public class Modelo {
    String fecha, nombre, direccion, estado;
    String medidor, cedula;
    String valor, valor_anterior;


    public Modelo(String fecha, String nombre, String direccion, String estado, String medidor, String cedula, String valor, String valor_anterior) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
        this.medidor = medidor;
        this.cedula = cedula;
        this.valor = valor;
        this.valor_anterior = valor_anterior;
    }
    public Modelo(){

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMedidor() {
        return medidor;
    }

    public void setMedidor(String medidor) {
        this.medidor = medidor;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor_anterior() {
        return valor_anterior;
    }

    public void setValor_anterior(String valor_anterior) {
        this.valor_anterior = valor_anterior;
    }
}