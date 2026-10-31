/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.modelo;

/**
 *
 * @author Auder
 */
public class Mecanico extends Persona {
    private String especialidad;

    public Mecanico() {
        super();
    }

    public Mecanico(int id, String nombres, String apellidos, String telefono, String especialidad) {
        super(id, nombres, apellidos, telefono);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() 
    { 
        return especialidad;
    }
    public void setEspecialidad(String especialidad) 
    { 
        this.especialidad = especialidad;
    }

    @Override
    public String obtenerInformacionDetallada() {
        return "Mecánico [ID: " + id + "] " + nombres + " " + apellidos + " - Especialidad: " + especialidad;
    }
}
