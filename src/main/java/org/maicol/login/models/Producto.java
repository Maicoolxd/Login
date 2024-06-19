package org.maicol.login.models;

import java.io.InputStream;

public class Producto {
    private Integer idProducto;
    private int codigo;
    private String nombre;
    private int stock;
    private Categoria categoria;
    private String descripcion;
    private String imagen;
    private int condicion;
    private double precio;


    public Producto(){

    }

    public Producto(Integer idProducto, int codigo, String nombre, int stock, Categoria categoria,String descripcion, String imagen, int condicion, double precio){
        this.idProducto=idProducto;
        this.codigo=codigo;
        this.nombre=nombre;
        this.stock=stock;
        this.categoria=categoria;
        this.descripcion=descripcion;
        this.imagen=imagen;
        this.condicion=condicion;
        this.precio=precio;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
