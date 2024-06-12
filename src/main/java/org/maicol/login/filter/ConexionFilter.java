package org.maicol.login.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maicol.login.util.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.maicol.login.services.ServiceJdbcException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        //genero la conexion a mi base de datos
        try(Connection conn = Conexion.getConecction()){
            if(conn.getAutoCommit()){
                //retornamos la conexion
                conn.setAutoCommit(false);
            }
            try {
                request.setAttribute("conn", conn);
                chain.doFilter(request,response);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e){
                //puntos de salvacion o restauracion
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        }catch (SQLException throwables){

        }
    }

}
