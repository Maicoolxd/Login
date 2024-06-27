package org.maicol.login.util;

import jakarta.servlet.http.PushBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //creamos un metodo para obtener la conexion mediante el pool de conexiones
    private static String url ="jdbc:mysql://localhost:3306/mydb";
    private static String username = "root";
    private static String password = "";

    //genera el driver y llama a los parametros
    public static  Connection getConecction() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }
    public static void main(String[] args) {
        try {
            Connection connection = getConecction();
            if (connection != null) {
                System.out.println("Conexion exitosa");
            }
        } catch (SQLException e) {
            System.err.println("Fallo la conexion " + e.getMessage());
        }
    }
}




