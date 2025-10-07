/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public abstract class Vehiculo {
    
    private long idTicket;
    private static long id =0;
    
   private LocalDateTime hEntrada;
   private LocalDateTime hSalida;

    public Vehiculo() {
        id++;
        this.idTicket=id;
    }

    public Vehiculo(LocalDateTime hEntrada, LocalDateTime hSalida) {
        this();
        this.hEntrada = hEntrada;
        this.hSalida = hSalida;
    }

    public long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        Vehiculo.id = id;
    }

    public LocalDateTime gethEntrada() {
        return hEntrada;
    }

    public void sethEntrada(LocalDateTime hEntrada) {
        this.hEntrada = hEntrada;
    }

    public LocalDateTime gethSalida() {
        return hSalida;
    }

    public void sethSalida(LocalDateTime hSalida) {
        this.hSalida = hSalida;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "idTicket=" + idTicket + ", hEntrada=" + hEntrada + ", hSalida=" + hSalida + 
                ", horas=" + this.duracionHoras()+ '}';
    }
    
    public long duracionHoras(){
        Duration duracion = Duration.between(this.gethEntrada(), this.gethSalida());
        long horas = duracion.toHours();
        return horas;
    }
   
   public abstract double calcularCostoParqueo();
   
}
