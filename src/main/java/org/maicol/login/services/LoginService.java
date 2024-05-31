package org.maicol.login.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {
    //obtenemos los datos del usuario
    Optional <String> getUsername(HttpServletRequest request);

}
