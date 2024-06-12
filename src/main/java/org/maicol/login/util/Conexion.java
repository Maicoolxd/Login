package org.maicol.login.util;

import jakarta.servlet.http.PushBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //creamos un metodo para obtener la conexion mediante el pool de conexiones
    private static String url ="jdbc:mysql://localhost_3306/mydb?serverTimezone=America/Quito";
    private static String username = "root";
    private static String password = "";
    //genera el driver y llama a los parametros
    public static  Connection getConecction() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }

}
