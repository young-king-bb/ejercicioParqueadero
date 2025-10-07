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
public class Bicicleta extends Vehiculo {
    
    private String tipo;
    private boolean tieneCambios;

    public Bicicleta() {
    }

    public Bicicleta(String tipo, boolean tieneCambios, LocalDateTime hEntrada, LocalDateTime hSalida) {
        super(hEntrada, hSalida);
        this.tipo = tipo;
        this.tieneCambios = tieneCambios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isTieneCambios() {
        return tieneCambios;
    }

    public void setTieneCambios(boolean tieneCambios) {
        this.tieneCambios = tieneCambios;
    }

    @Override
    public String toString() {
        return "Bicicleta{"+super.toString() + ", tipo=" + tipo + ", tieneCambios=" +
                tieneCambios +", costoParqueo=" + this.calcularCostoParqueo() + '}';
    }

    @Override
    public double calcularCostoParqueo() {
        
        return this.duracionHoras() * 300;
        
    }
    
}
