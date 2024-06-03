package org.maicol.login.services;

import org.maicol.login.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImplement implements ProductoService {
    //sobreescribimos el metodo
    @Override

    public List<Producto> listar(){
        return Arrays.asList(new Producto(1,"Laptop","Computacion","HP i7",650.25),
                new Producto(2,"Mouse","Computacion", "Gamer",20.50),
                new Producto(3,"TV","Electrodomestico","55 Pulgadas",500.30));



       }

}
