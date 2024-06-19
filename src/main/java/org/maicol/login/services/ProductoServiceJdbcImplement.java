package org.maicol.login.services;

import org.maicol.login.models.Producto;
import org.maicol.login.repositories.ProductoRepositoryJdbcImpl;
import org.maicol.login.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImplement implements ProductoService {
    private Repository<Producto> repositoryJdbc;

    public ProductoServiceJdbcImplement(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
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
}
