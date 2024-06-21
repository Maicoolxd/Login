package org.maicol.login.services;

import org.maicol.login.models.Categoria;
import org.maicol.login.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImplement implements ProductoService {
    //sobreescribimos el metodo
    @Override

    public List<Producto> listar(){
        return Arrays.asList();
       }

    @Override
    public Optional<Producto> porId(Integer id) {

        return listar().stream().filter(p-> p.getIdProducto().equals(id)).findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Categoria> listarCategorias() {
        return List.of();
    }

    @Override
    public Optional<Categoria> porIdCategoria(Integer id) {
        return Optional.empty();
    }

}
