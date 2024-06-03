package org.maicol.login.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceImplement implements LoginService {

    @Override

    //esta clase extrae los datos de una clase padre
    public Optional<String> getUsername(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username!=null){
            //retornamos el nombre del usuario
            return Optional.of(username);
        }
        //retorna vacio
        return Optional.empty();
    }

}
