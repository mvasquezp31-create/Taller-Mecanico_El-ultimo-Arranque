/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.modelo;

/**
 *
 * @author Auder
 */
public class DetalleOrdenRepuesto {
    private int idDetalleRepuesto;
    private int idOrden;
    private Repuesto repuesto; // Composición
    private int cantidad;
    private double precioUnitario;
    private double subtotal; // Calculado

    public DetalleOrdenRepuesto() {}

    public DetalleOrdenRepuesto(int idDetalleRepuesto, int idOrden, Repuesto repuesto, int cantidad, double precioUnitario) {
        this.idDetalleRepuesto = idDetalleRepuesto;
        this.idOrden = idOrden;
        this.repuesto = repuesto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal(); // Cálculo
    }

    // calculo subtotal
    public double calcularSubtotal() {
        return this.cantidad * this.precioUnitario;
    }

    public int getIdDetalleRepuesto()
    {
        return idDetalleRepuesto;
    }
    public void setIdDetalleRepuesto(int idDetalleRepuesto)
    { 
        this.idDetalleRepuesto = idDetalleRepuesto; 
    }

    public int getIdOrden() 
    { 
        return idOrden; 
    }
    public void setIdOrden(int idOrden)
    { 
        this.idOrden = idOrden;
    }

    public Repuesto getRepuesto() 
    { 
        return repuesto;
    }
    public void setRepuesto(Repuesto repuesto)
    { 
        this.repuesto = repuesto; 
    }

    public int getCantidad() 
    { 
        return cantidad; 
    }
    public void setCantidad(int cantidad) { 
        if (cantidad <= 0)
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal(); // evalua si cambia el todal
    }

    public double getPrecioUnitario()
    { 
        return precioUnitario; 
    }
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
    }

    public double getSubtotal()
    { 
        return subtotal;
    }
}
