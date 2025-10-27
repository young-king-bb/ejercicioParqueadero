/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datos;

import dominio.Vehiculo;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface IBDVehiculo {
    
    public void adicionarVehiculo(Vehiculo v);
    public ArrayList<Vehiculo> listarTodos();
    public Vehiculo buscar(String placa);
    
}
