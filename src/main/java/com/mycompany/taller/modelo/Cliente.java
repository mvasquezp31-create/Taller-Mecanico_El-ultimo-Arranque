/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.modelo;

/**
 *
 * @author Auder
 */
public class Cliente extends Persona {
    private String direccion;
    private String correo;

    public Cliente() {
        super();
    }

    public Cliente(int id, String nombres, String apellidos, String telefono, String direccion, String correo) {
        super(id, nombres, apellidos, telefono);
        this.direccion = direccion;
        this.correo = correo;
    }

    public String getDireccion()
    { 
        return direccion;
    }
    public void setDireccion(String direccion)
    {
        this.direccion = direccion; 
    }

    public String getCorreo() 
    { 
        return correo;
    }
    public void setCorreo(String correo) 
    { 
        this.correo = correo;
    }

    @Override
    public String obtenerInformacionDetallada() {
        return "Cliente [ID: " + id + "] " + nombres + " " + apellidos + " - Tel: " + telefono;
    }
}
