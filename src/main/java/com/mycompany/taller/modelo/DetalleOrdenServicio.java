/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.modelo;

/**
 *
 * @author Auder
 */
public class DetalleOrdenServicio {
    private int idDetalleServicio;
    private int idOrden;
    private Servicio servicio; // Composición
    private double costoAplicado;

    public DetalleOrdenServicio() {}

    public DetalleOrdenServicio(int idDetalleServicio, int idOrden, Servicio servicio, double costoAplicado) {
        this.idDetalleServicio = idDetalleServicio;
        this.idOrden = idOrden;
        this.servicio = servicio;
        this.costoAplicado = costoAplicado;
    }

    public int getIdDetalleServicio()
    { return idDetalleServicio; 
    }
    public void setIdDetalleServicio(int idDetalleServicio) 
    {
        this.idDetalleServicio = idDetalleServicio;
    }

    public int getIdOrden() 
    { 
        return idOrden; 
    }
    public void setIdOrden(int idOrden)
    {
        this.idOrden = idOrden;
    }

    public Servicio getServicio() 
    { 
        return servicio; 
    }
    public void setServicio(Servicio servicio)
    { 
        this.servicio = servicio; 
    }

    public double getCostoAplicado() 
    { 
        return costoAplicado;
    }
    public void setCostoAplicado(double costoAplicado) 
    { 
        this.costoAplicado = costoAplicado;
    }
}
