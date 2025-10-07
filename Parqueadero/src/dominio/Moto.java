/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class Moto extends Vehiculo {
    
    private String placa;
    private String marca;
    private int cilindraje;

    public Moto() {
    }

    public Moto(String placa, String marca, int cilindraje, LocalDateTime hEntrada, LocalDateTime hSalida) {
        super(hEntrada, hSalida);
        this.placa = placa;
        this.marca = marca;
        this.cilindraje = cilindraje;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    @Override
    public String toString() {
        return "Moto{"+ super.toString() + ", placa=" + placa + ", marca=" + marca +
                ", cilindraje=" + cilindraje + ", costoParqueo=" + this.calcularCostoParqueo() + '}';
    }

    @Override
    public double calcularCostoParqueo() {
       
        double recargo;
        recargo = (this.cilindraje>500)?500:0;
        return this.duracionHoras() * 1000 + recargo;
        
    }
    
}
