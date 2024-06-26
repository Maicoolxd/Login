package org.maicol.login.repositories;

import org.maicol.login.models.Producto;
import org.maicol.login.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Producto> {
    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    public Connection getConnection() throws SQLException {
        return this.conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM articulo")) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Integer id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT p.*, categoria.nombre " +
                        "FROM articulo AS p " +
                        "INNER JOIN categoria ON categoria.idcategoria = p.idcategoria " +
                        "WHERE p.idarticulo = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getIdProducto() != null && producto.getIdProducto() > 0) {
            // Actualizar producto existente
            sql = "UPDATE articulo SET codigo=?, nombre=?, stock=?, idcategoria=?, descripcion=?, imagen=?, condicion=?, precio=? WHERE idarticulo=?";
        } else {
            // Insertar nuevo producto
            sql = "INSERT INTO articulo (codigo, nombre, stock, idcategoria, descripcion, imagen, condicion, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            pstmt.setInt(3, producto.getStock());
            pstmt.setInt(4, producto.getCategoria().getIdCategoria());
            pstmt.setString(5, producto.getDescripcion());
            pstmt.setString(6, producto.getImagen());
            pstmt.setInt(7, producto.getCondicion());
            pstmt.setDouble(8, producto.getPrecio());

            if (producto.getIdProducto() != null && producto.getIdProducto() > 0) {
                // Setear el ID del producto para la cláusula WHERE en la actualización
                pstmt.setInt(9, producto.getIdProducto());
            }

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo guardar el producto, ninguna fila afectada.");
            }

            if (producto.getIdProducto() == null || producto.getIdProducto() == 0) {
                // Obtener el ID generado si es una inserción nueva
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        producto.setIdProducto(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("No se pudo obtener el ID generado para el producto.");
                    }
                }
            }
        }
    }



    @Override
    public void eliminar(int idProducto) throws SQLException {

    }

    @Override
    public void actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE articulo SET codigo = ?, nombre = ?, stock = ?, idcategoria = ?, descripcion = ?, imagen = ?, condicion = ?, precio = ? WHERE idarticulo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, producto.getCodigo());
            stmt.setString(2, producto.getNombre());
            stmt.setInt(3, producto.getStock());
            stmt.setInt(4, producto.getCategoria().getIdCategoria());
            stmt.setString(5, producto.getDescripcion());
            stmt.setString(6, producto.getImagen());
            stmt.setInt(7, producto.getCondicion());
            stmt.setDouble(8, producto.getPrecio());
            stmt.setInt(9, producto.getIdProducto());
            stmt.executeUpdate();
        }
    }

    /*@Override
    public void eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM articulo WHERE idarticulo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }*/

    @Override
    public void eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM articulo WHERE idarticulo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se encontró ningún producto con idarticulo: " + id);
            }
        }
    }


    @Override
    public Producto activar(Integer id) {
        return null;
    }

    @Override
    public Producto desactivar(Integer id) {
        return null;
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("idarticulo"));
        p.setCodigo(rs.getInt("codigo"));
        p.setNombre(rs.getString("nombre"));
        p.setStock(rs.getInt("stock"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setImagen(rs.getString("imagen"));
        p.setCondicion(rs.getInt("condicion"));
        p.setPrecio(rs.getDouble("precio"));

        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("idcategoria"));
        c.setNombre(rs.getString("nombre"));
        p.setCategoria(c);

        return p;
    }
}
