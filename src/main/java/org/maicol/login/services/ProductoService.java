package org.maicol.login.services;

import org.maicol.login.models.Producto;

import java.security.ProtectionDomain;
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

    //implementamos un metodo para eliminar
    void eliminar(Integer id);
}
