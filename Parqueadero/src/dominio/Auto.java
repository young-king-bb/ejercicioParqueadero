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
public class Auto extends Vehiculo {
    
    private String placa;
    private String marca;
    private int modelo;
    private int nPuertas;

    public Auto() {
    }

    public Auto(String placa, String marca, int modelo, int nPuertas,
            LocalDateTime hEntrada, LocalDateTime hSalida) {
        super(hEntrada, hSalida);
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.nPuertas = nPuertas;
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

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getnPuertas() {
        return nPuertas;
    }

    public void setnPuertas(int nPuertas) {
        this.nPuertas = nPuertas;
    }

    @Override
    public String toString() {
        return "Auto{"+ super.toString() + ", placa=" + placa + ", marca=" + marca + ", modelo=" +
                modelo + ", nPuertas=" + nPuertas + ", costoParqueo=" + this.calcularCostoParqueo() + '}';
    }

    @Override
    public double calcularCostoParqueo() {
        
        return this.duracionHoras() * 2000;
        
    }
    
}
