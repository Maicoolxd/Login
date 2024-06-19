package org.maicol.login.controlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.maicol.login.models.Carro;
import org.maicol.login.models.ItemCarro;
import org.maicol.login.models.Producto;
import org.maicol.login.services.ProductoService;
import org.maicol.login.services.ProductoServiceImplement;
import org.maicol.login.services.ProductoServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //obtenemos el parametro del Prodcuto
        Integer idProducto = Integer.parseInt(req.getParameter("id"));
        Connection conn =(Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        Optional<Producto> producto = service.porId(idProducto);
        if (producto.isPresent()){
            //crea un nuevo objeto en base a loq eu trae del producto
            ItemCarro item = new ItemCarro(1, producto.get());
            //obtenemos la sesion
            HttpSession session = req.getSession();
            Carro carro;
            if(session.getAttribute("carro")==null){
                //si no hay session geenra un nuevo objeto
                carro = new Carro();
                session.setAttribute("carro", carro);
            } else {
                //caso contrario obtenemos los atributos del carro
                //obtiene el vector y lo trasnforma en carro
                carro = (Carro) session.getAttribute("carro");
            }
            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath()+"/ver-carro");
    }
}
