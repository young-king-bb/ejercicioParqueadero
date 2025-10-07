/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package presentacion;

import datos.BDParqueo;
import dominio.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BDParqueo bd = new BDParqueo();
        
        Vehiculo auto = new Auto("ABC", "Toyota", 2020, 4, LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        bd.adicionar(auto);
        
        Vehiculo moto= new Moto("rge", "Suzuki", 501, LocalDateTime.now(), LocalDateTime.now().plusHours(2));        
        bd.adicionar(moto);
        
        Vehiculo cicla = new Bicicleta("Cross", true, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        bd.adicionar(cicla);
        
        imprimir(bd.listarTodos());
    }
    
    public static void imprimir(ArrayList<Vehiculo> lista){
        
        for(Vehiculo v: lista){
            System.out.println(v);
        }
       
    }
    
}
