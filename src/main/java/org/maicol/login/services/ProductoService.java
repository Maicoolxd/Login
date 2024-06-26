package org.maicol.login.services;

import org.maicol.login.models.Categoria;
import org.maicol.login.models.Producto;

import java.security.ProtectionDomain;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    //una interfaz en JAVA un conjunto de modelos
    //implementamos un metodo apra obtner el producto
    //por id
    Optional<Producto> porId(Integer id);

    //implementamos un metodo para guardar
    void guardar (Producto producto);

    void actualizar(Producto producto) throws SQLException;

    //implementamos un metodo para eliminar
    void eliminar(Integer idProducto) throws SQLException;

    //implementamos un metodo para listar la categoriaL
    List<Categoria> listarCategorias();

    //implementamos un metodo para obtener el id de la categoria
    Optional<Categoria> porIdCategoria(Integer id);

}
