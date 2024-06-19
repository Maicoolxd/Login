package org.maicol.login.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    //creamos una variable en el cual inicailizamos la lista
    //de productos comprados que va a a estar en el carro
    private List<ItemCarro> items;

    //implemantamos un constructor sin ningun parametro
    public Carro(){
        this.items = new ArrayList<>();
    }

    //implementamos un metodo para añadir los productos a la
    //kusta o al carrito

    public void addItemCarro(ItemCarro itemCarro){
        if(items.contains(itemCarro)){
            Optional<ItemCarro> optionalItemCarro  = items.stream().filter(i-> i.equals(itemCarro)).findAny();
            if(optionalItemCarro.isPresent()){
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }
        }else {
            this.items.add(itemCarro);
        }
    }
    //obtenemos los productos de la clase ItemCarro
    public List<ItemCarro> getItems(){
        return items;
    }
    //los convierta a Double que coja todos los subototales, lo mapee y que de la clase ItemCarro y que los sume una a uno
    public double getTotal(){
        return items.stream().mapToDouble(ItemCarro::getImporte).sum();
    }
}
