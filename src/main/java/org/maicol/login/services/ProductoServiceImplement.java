package org.maicol.login.services;

import org.maicol.login.models.Categoria;
import org.maicol.login.models.Producto;
import org.maicol.login.repositories.CategoriaRepositoryJdbcImplement;
import org.maicol.login.repositories.ProductoRepositoryJdbcImpl;
import org.maicol.login.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImplement implements ProductoService {
    private Repository<Producto> repositoryJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;

    public ProductoServiceImplement(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryJdbcImplement(connection);
    }

    public List<Producto> listar(){
        return Arrays.asList();
       }

    @Override
    public Optional<Producto> porId(Integer id) {

        return listar().stream().filter(p-> p.getIdProducto().equals(id)).findAny();
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
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

