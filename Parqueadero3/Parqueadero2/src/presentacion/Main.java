/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package presentacion;

import datos.BDVehiculo;
import datos.IBDVehiculo;
import dominio.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import logica.ParkingManager;

/**
 *
 * @author Usuario
 */
public class Main {
    
    private static IBDVehiculo bd;
    private static ParkingManager parqueadero;
    private static Scanner scanner;
    private static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        bd = new BDVehiculo();
        parqueadero = new ParkingManager();
        scanner = new Scanner(System.in);
        
        menuPrincipal();
        
    }
    
    private static void menuPrincipal() {
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    generarTicketParqueo();
                    break;
                case 2:
                    listarVehiculosRegistrados();
                    break;
                case 3:
                    buscarVehiculoPorPlaca();
                    break;
                case 4:
                    mostrarEstadisticas();
                    break;
                case 0:
                    continuar = false;
                    mostrarDespedida();
                    break;
                default:
                    System.out.println("\nOpcion invalida. Intente nuevamente.");
            }
            
            if (continuar) {
                pausar();
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        limpiarPantalla();
        System.out.println(" ---------------------------------------------------");
        System.out.println("|     SISTEMA DE GESTION DE PARQUEADERO             |");
        System.out.println("|---------------------------------------------------|");
        System.out.println("|                                                   |");
        System.out.println("|  1. Generar Ticket de Cobro de Parqueo            |");
        System.out.println("|  2. Listar Vehiculos Registrados                  |");
        System.out.println("|  3. Buscar Vehiculo por Placa                     |");
        System.out.println("|  4. Ver Estadisticas                              |");
        System.out.println("|  0. Salir                                         |");
        System.out.println("|                                                   |");
        System.out.println(" ---------------------------------------------------");
        System.out.print("\nSeleccione una opcion: ");
    }
    
    private static void generarTicketParqueo() {
        limpiarPantalla();
        System.out.println(" ---------------------------------------------------");
        System.out.println("|        GENERAR TICKET DE COBRO DE PARQUEO         |");
        System.out.println(" ---------------------------------------------------\n");
        
        // Solicitar placa del vehículo
        System.out.print("Ingrese la placa del vehiculo: ");
        String placa = scanner.nextLine().trim().toUpperCase();
        
        if (placa.isEmpty()) {
            System.out.println("\nLa placa no puede estar vacia.");
            return;
        }
        
        // Buscar si el vehículo ya existe en la BD
        Vehiculo vehiculo = bd.buscar(placa);
        
        if (vehiculo != null) {
            System.out.println("\nVehiculo encontrado en la base de datos:");
            System.out.println("   Tipo: " + vehiculo.getType());
            System.out.println("   " + vehiculo.toString());
            System.out.print("\n¿Desea generar ticket para este vehiculo? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            
            if (!respuesta.equals("s")) {
                System.out.println("\nOperacion cancelada.");
                return;
            }
        } else {
            System.out.println("\nVehiculo no encontrado en la base de datos.");
            System.out.println("   Se procedera a registrar un nuevo vehiculo.\n");
            
            vehiculo = registrarNuevoVehiculo(placa);
            
            if (vehiculo == null) {
                System.out.println("\nError al registrar el vehiculo.");
                return;
            }
            
            bd.adicionarVehiculo(vehiculo);
            System.out.println("\nVehiculo registrado exitosamente en la base de datos.");
        }
        
        // Solicitar hora de entrada
        LocalDateTime horaEntrada = solicitarFechaHora("entrada");
        if (horaEntrada == null) return;
        
        // Solicitar hora de salida
        LocalDateTime horaSalida = solicitarFechaHora("salida");
        if (horaSalida == null) return;
        
        // Validar que la hora de salida sea posterior a la de entrada
        if (horaSalida.isBefore(horaEntrada) || horaSalida.isEqual(horaEntrada)) {
            System.out.println("\nError: La hora de salida debe ser posterior a la hora de entrada.");
            return;
        }
        
        // Generar el ticket
        Ticket ticket = parqueadero.generarTicket(horaEntrada, horaSalida, vehiculo);
        
        // Imprimir el ticket
        System.out.println("\n ---------------------------------------------------");
        System.out.println("|              TICKET DE COBRO GENERADO             |");
        System.out.println(" ---------------------------------------------------");
        parqueadero.imprimirTicket(ticket);
    }
    
    private static Vehiculo registrarNuevoVehiculo(String placa) {
        System.out.println("Seleccione el tipo de vehiculo:");
        System.out.println("1. Automovil");
        System.out.println("2. Motocicleta");
        System.out.println("3. Bicicleta");
        System.out.print("\nTipo: ");
        
        int tipo = leerOpcion();
        
        switch (tipo) {
            case 1:
                return crearAutomovil(placa);
            case 2:
                return crearMotocicleta(placa);
            case 3:
                return crearBicicleta(placa);
            default:
                System.out.println("\nTipo de vehiculo invalido.");
                return null;
        }
    }
    
    private static Vehiculo crearAutomovil(String placa) {
        System.out.println("\n--- Datos del Automovil ---");
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo (año): ");
        int modelo = leerOpcion();
        System.out.print("Numero de puertas: ");
        int puertas = leerOpcion();
        
        return new Auto(placa, marca, modelo, puertas);
    }
    
    private static Vehiculo crearMotocicleta(String placa) {
        System.out.println("\n--- Datos de la Motocicleta ---");
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Cilindraje (cc): ");
        int cilindraje = leerOpcion();
        
        return new Moto(placa, marca, cilindraje);
    }
    
    private static Vehiculo crearBicicleta(String placa) {
        System.out.println("\n--- Datos de la Bicicleta ---");
        System.out.print("Tipo (montaña/ruta/urbana): ");
        String tipo = scanner.nextLine();
        System.out.print("¿Tiene cambios? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        boolean tieneCambios = respuesta.equals("s");
        
        return new Bicicleta(placa, tipo, tieneCambios);
    }
    
    private static LocalDateTime solicitarFechaHora(String tipo) {
        System.out.println("\n--- Hora de " + tipo + " ---");
        System.out.println("Opciones:");
        System.out.println("1. Usar hora actual");
        System.out.println("2. Ingresar fecha y hora manualmente");
        System.out.print("Opcion: ");
        
        int opcion = leerOpcion();
        
        if (opcion == 1) {
            return LocalDateTime.now();
        } else if (opcion == 2) {
            return ingresarFechaHoraManual(tipo);
        } else {
            System.out.println("\nOpcion invalida.");
            return null;
        }
    }
    
    private static LocalDateTime ingresarFechaHoraManual(String tipo) {
        System.out.println("\nIngrese la fecha y hora de " + tipo);
        System.out.println("Formato: dd/MM/yyyy HH:mm (Ejemplo: 15/10/2024 14:30)");
        System.out.print("Fecha y hora: ");
        
        String fechaStr = scanner.nextLine();
        
        try {
            return LocalDateTime.parse(fechaStr, formatoFecha);
        } catch (DateTimeParseException e) {
            System.out.println("\nFormato de fecha invalido.");
            System.out.print("¿Desea intentar nuevamente? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            
            if (respuesta.equals("s")) {
                return ingresarFechaHoraManual(tipo);
            }
            return null;
        }
    }
    
    private static void listarVehiculosRegistrados() {
        limpiarPantalla();
        System.out.println(" ---------------------------------------------------");
        System.out.println("|          VEHICULOS REGISTRADOS EN BD              |");
        System.out.println(" ---------------------------------------------------\n");
        
        ArrayList<Vehiculo> vehiculos = bd.listarTodos();
        
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados en la base de datos.");
            return;
        }
        
        System.out.println("Total de vehiculos: " + vehiculos.size() + "\n");
        System.out.println("-----------------------------------------------------");
        
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            System.out.println((i + 1) + ". " + v.toString());
        }
        
        System.out.println("-----------------------------------------------------");
    }
    
    private static void buscarVehiculoPorPlaca() {
        limpiarPantalla();
        System.out.println(" ---------------------------------------------------");
        System.out.println("|           BUSCAR VEHICULO POR PLACA               |");
        System.out.println(" ---------------------------------------------------\n");
        
        System.out.print("Ingrese la placa a buscar: ");
        String placa = scanner.nextLine().trim().toUpperCase();
        
        Vehiculo vehiculo = bd.buscar(placa);
        
        if (vehiculo == null) {
            System.out.println("\nVehiculo con placa '" + placa + "' no encontrado.");
        } else {
            System.out.println("\nVehiculo encontrado:");
            System.out.println("-----------------------------------------------------");
            System.out.println("Tipo: " + vehiculo.getType());
            System.out.println("Tarifa por hora: $" + vehiculo.getTarifa());
            System.out.println("Recargo: $" + vehiculo.getRecargo());
            System.out.println("Detalles: " + vehiculo.toString());
            System.out.println("-----------------------------------------------------");
        }
    }
    
    private static void mostrarEstadisticas() {
        limpiarPantalla();
        System.out.println(" ---------------------------------------------------");
        System.out.println("|              ESTADISTICAS DEL SISTEMA             |");
        System.out.println(" ---------------------------------------------------\n");
        
        ArrayList<Vehiculo> vehiculos = bd.listarTodos();
        
        int totalAutos = 0;
        int totalMotos = 0;
        int totalBicis = 0;
        
        for (Vehiculo v : vehiculos) {
            if (v instanceof Auto) {
                totalAutos++;
            } else if (v instanceof Moto) {
                totalMotos++;
            } else if (v instanceof Bicicleta) {
                totalBicis++;
            }
        }
        
        System.out.println("Total de vehiculos registrados: " + vehiculos.size());
        System.out.println("-----------------------------------------------------");
        System.out.println("Automoviles:  " + totalAutos + " (" + 
            (vehiculos.size() > 0 ? String.format("%.1f%%", (totalAutos * 100.0 / vehiculos.size())) : "0.0%") + ")");
        System.out.println("Motocicletas: " + totalMotos + " (" + 
            (vehiculos.size() > 0 ? String.format("%.1f%%", (totalMotos * 100.0 / vehiculos.size())) : "0.0%") + ")");
        System.out.println("Bicicletas:   " + totalBicis + " (" + 
            (vehiculos.size() > 0 ? String.format("%.1f%%", (totalBicis * 100.0 / vehiculos.size())) : "0.0%") + ")");
        System.out.println("-----------------------------------------------------");
    }
    
    public static void imprimir(ArrayList<Vehiculo> lista) {
        for (Vehiculo v : lista) {
            System.out.println(v);
        }
    }
    
    private static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            return opcion;
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer en caso de error
            return -1;
        }
    }
    
    private static void pausar() {
        System.out.print("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }
    
    private static void limpiarPantalla() { //simula limpieza
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    private static void mostrarDespedida() {
        limpiarPantalla();
        System.out.println(" ---------------------------------------------------");
        System.out.println("|        GRACIAS POR USAR EL SISTEMA!                |");
        System.out.println("|         PARQUEADERO UNIVERSITARIO                 |");
        System.out.println(" ---------------------------------------------------");
    }
    
}
