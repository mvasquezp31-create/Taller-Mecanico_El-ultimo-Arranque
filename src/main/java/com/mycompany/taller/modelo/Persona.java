/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.modelo;

/**
 *
 * @author Auder
 */
public abstract class Persona {
    protected int id;
    protected String nombres;
    protected String apellidos;
    protected String telefono;

    public Persona() {}

    public Persona(int id, String nombres, String apellidos, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public int getId()
    { 
        return id;
    }
    public void setId(int id) 
    { 
        this.id = id;
    }

    public String getNombres()
    {
        return nombres; 
    }
    public void setNombres(String nombres) 
    { 
        this.nombres = nombres; 
    }

    public String getApellidos() 
    { 
        return apellidos;
    }
    public void setApellidos(String apellidos) 
    {
        this.apellidos = apellidos;
    }

    public String getTelefono() 
    {
        return telefono;
    }
    public void setTelefono(String telefono) 
    {
        this.telefono = telefono;
    }

    // Método abstracto para obligar a las clases hijas a implementar un comportamiento polimórfico
    public abstract String obtenerInformacionDetallada();
}
