package org.maicol.login.models;

public class Producto {
    private int idProducto;
    private String nombre;
    private String categoria;
    private String descripcion;
    private double precio;

    public Producto(int idProducto, String nombre,String categoria,String descripcion, double precio){
        this.idProducto=idProducto;
        this.nombre=nombre;
        this.categoria=categoria;
        this.descripcion=descripcion;
        this.precio=precio;
    }
    // Métodos set
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Métodos get
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
}
