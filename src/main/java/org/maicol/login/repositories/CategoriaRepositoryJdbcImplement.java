package org.maicol.login.repositories;

import org.maicol.login.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImplement implements Repository <Categoria> {
    private Connection conn;

    public CategoriaRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from categoria");
            while (rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }
    @Override
    public Categoria porId(Integer id) throws SQLException {
        Categoria categoria =  null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from categoria WHERE idcategoria=?")) {
            stmt.setInt(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Integer id) throws SQLException {

    }

    @Override
    public Categoria activar(Integer id) {
        return null;
    }

    @Override
    public Categoria desactivar(Integer id) {
        return null;
    }

    private Categoria getCategoria(ResultSet rs) throws SQLException{
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setIdCategoria(rs.getInt("idcategoria"));
        return categoria;
    }
}
