/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.modelo;

/**
 *
 * @author Auder
 */
public class Vehiculo {
    private int idVehiculo;
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private Cliente clientePropitario; // Composición: Objeto Cliente dentro de Vehiculo

    public Vehiculo() {}

    public Vehiculo(int idVehiculo, String placa, String marca, String modelo, int anio, Cliente clientePropitario) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.clientePropitario = clientePropitario;
    }

    // Getters y Setters
    public int getIdVehiculo()
    { 
        return idVehiculo;
    }
    public void setIdVehiculo(int idVehiculo) 
    { 
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() 
    { 
        return placa;
    }
    public void setPlaca(String placa) 
    { 
        this.placa = placa;
    }

    public String getMarca() 
    {
        return marca;
    }
    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    public String getModelo()
    { 
        return modelo; 
    }
    public void setModelo(String modelo) 
    {
        this.modelo = modelo;
    }

    public int getAnio() 
    { 
        return anio;
    }
    public void setAnio(int anio) 
    { 
        this.anio = anio; 
    }

    public Cliente getClientePropitario() 
    { 
        return clientePropitario;
    }
    public void setClientePropitario(Cliente clientePropitario) 
    { 
        this.clientePropitario = clientePropitario; 
    }
}

