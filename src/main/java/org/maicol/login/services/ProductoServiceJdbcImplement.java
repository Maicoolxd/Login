package org.maicol.login.services;

import org.maicol.login.models.Categoria;
import org.maicol.login.models.Producto;
import org.maicol.login.repositories.CategoriaRepositoryJdbcImplement;
import org.maicol.login.repositories.ProductoRepositoryJdbcImpl;
import org.maicol.login.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImplement implements ProductoService {
    private Repository<Producto> repositoryJdbc;
    private Repository<Categoria> categoriaRepositoryJdbc;

    public ProductoServiceJdbcImplement(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.categoriaRepositoryJdbc = new CategoriaRepositoryJdbcImplement(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        }
        catch (SQLException throwables){
            throw  new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    //nos agrega al carrito
    public Optional<Producto> porId(Integer id) {
        try{
            return Optional.ofNullable(repositoryJdbc.porId(id));
        }catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage());
        }
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Categoria> listarCategorias() {
        try{
            return categoriaRepositoryJdbc.listar();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Integer id) {
        try {
            return Optional.ofNullable(categoriaRepositoryJdbc.porId(id));
        } catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }
}
