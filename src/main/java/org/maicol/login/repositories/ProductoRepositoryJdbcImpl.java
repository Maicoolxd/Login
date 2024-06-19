package org.maicol.login.repositories;

import org.maicol.login.models.Producto;
import org.maicol.login.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Producto>{
    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try(Statement smt = conn.createStatement();
                ResultSet rs = smt.executeQuery("SELECT articulo.idarticulo,categoria.nombre AS nombre_categoria,articulo.idcategoria,articulo.codigo,articulo.nombre,articulo.stock, articulo.descripcion,articulo.imagen,articulo.condicion,articulo.precio FROM articulo INNER JOIN categoria ON articulo.idcategoria = categoria.idcategoria;")){
            while(rs.next()){
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Integer id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement smt = conn.prepareStatement("SELECT  p.*, c.nombre AS categoria FROM articulo AS p INNER JOIN categoria AS c ON p.idcategoria = c.idcategoria WHERE p.idarticulo=?;")){
            smt.setInt(1,id);
            try (ResultSet rs= smt.executeQuery()){
                if (rs.next()){
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if(producto.getIdProducto() != null & producto.getIdProducto()>0){
            sql= "UPDATE articulo SET nombre=?, categoria=?, descripcion=?, precio=? WHERE idarticulo=?";
        }else {
            sql ="INSERT INTO articulo (nombre, categoria, descripcion, precio, condicion) VALUES (?,?,?,?,1)";
        }
        try(PreparedStatement smt = conn.prepareStatement(sql)){
            smt.setString(1,producto.getNombre());
            smt.setInt(2,producto.getCategoria().getIdCategoria());
            smt.setString(3,producto.getDescripcion());
            smt.setDouble(4,producto.getPrecio());
            if(producto.getIdProducto() != null && producto.getIdProducto()>0){
                smt.setInt(5,producto.getIdProducto());
            }//else {
                //smt.setDate(5, Date.valueOf(producto.getFecha()));
            // }
            smt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String sql = "delete from articulo WHERE idarticulo=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.executeUpdate();

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
        p.setDescripcion(rs.getString("descripcion"));
        p.setCondicion(rs.getInt("condicion"));
        p.setStock(rs.getInt("stock"));
        p.setImagen(rs.getString("imagen"));
        p.setPrecio(rs.getInt("precio"));
        return p;
    }
}
