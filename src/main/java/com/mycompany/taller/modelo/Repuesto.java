/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.modelo;

/**
 *
 * @author Auder
 */
public class Repuesto {
    private int idRepuesto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int existencia;

    public Repuesto() {}

    public Repuesto(int idRepuesto, String nombre, String descripcion, double precio, int existencia) {
        this.idRepuesto = idRepuesto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        setExistencia(existencia); // Valida mediante el setter
    }

    public int getIdRepuesto() 
    { 
        return idRepuesto; 
    }
    public void setIdRepuesto(int idRepuesto)
    {
        this.idRepuesto = idRepuesto; 
    }

    public String getNombre() 
    { 
        return nombre;
    }
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getDescripcion() 
    { 
        return descripcion;
    }
    public void setDescripcion(String descripcion) 
    { 
        this.descripcion = descripcion;
    }

    public double getPrecio()
    { 
        return precio;
    }
    public void setPrecio(double precio) 
    {
        this.precio = precio; 
    }

    public int getExistencia()
    { 
        return existencia;
    }
    
    // Manejo de Excepciones (no existencias negativas)
    public void setExistencia(int existencia) {
        if (existencia < 0) {
            throw new IllegalArgumentException("La existencia de un repuesto no puede ser negativa.");
        }
        this.existencia = existencia;
    }
}

