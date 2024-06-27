package org.maicol.login.services;

import org.maicol.login.models.Categoria;
import org.maicol.login.models.Producto;
import org.maicol.login.repositories.CategoriaRepositoryJdbcImplement;
import org.maicol.login.repositories.ProductoRepositoryJdbcImpl;
import org.maicol.login.repositories.Repository;
import org.maicol.login.services.ServiceJdbcException;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        } catch (SQLException throwables) {
            throw new ServiceJdbcException("Error al listar productos", throwables);
        }
    }

    @Override
    public Optional<Producto> porId(Integer id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException("Error al obtener producto por ID", throwables);
        }
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
        // Implementar la lógica para eliminar un producto por su ID
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return categoriaRepositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException("Error al listar categorías", throwables);
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Integer id) {
        try {
            return Optional.ofNullable(categoriaRepositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException("Error al obtener categoría por ID", throwables);
        }
    }
}
