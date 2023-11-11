package com.example.celicaconect_10.Navegador.Menu.Turismo.Colecciones;

public class Lugares {
    private String nombre;
    private String descripcion;
    private String imagen;

    private String url;

    public Lugares() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Lugares(String nombre, String descripcion, String imagen, String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.url=url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
