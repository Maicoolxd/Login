package org.maicol.login.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    //parametros genericos "T" y "t" el parametro que va a tomar
    List<T> listar() throws SQLException;
    T porId(Integer id) throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Integer id) throws SQLException;
    T activar(Integer id);
    T desactivar(Integer id);
}
