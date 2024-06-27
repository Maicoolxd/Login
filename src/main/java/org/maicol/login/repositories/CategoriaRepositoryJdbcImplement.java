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
    public Connection getConnection() throws SQLException {
        return this.conn;
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
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categoria WHERE idcategoria = ?")) {
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
        String sql = "INSERT INTO categoria (nombre, descripcion, condicion) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getDescripcion());
            pstmt.setInt(3, categoria.getCondicion());

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo insertar la categoría, ninguna fila afectada.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    categoria.setIdCategoria(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para la categoría.");
                }
            }
        }
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
