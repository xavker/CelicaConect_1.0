package com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador;

public class ModeloMascotas {

    int  propic;
    String id,nombre,raza,propietario,telefono,direccion,estado;

    public ModeloMascotas(int propic, String id, String nombre, String raza, String propietario, String telefono, String direccion, String estado) {
        this.propic = propic;
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.propietario = propietario;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
    }

    public ModeloMascotas() {

    }

    public int getPropic() {
        return propic;
    }

    public void setPropic(int propic) {
        this.propic = propic;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
}
