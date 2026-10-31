/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.modelo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Auder
 */
public class OrdenTrabajo {
    private int idOrden;
    private Vehiculo vehiculo; // Composición
    private Mecanico mecanico; // Composición
    private Date fechaIngreso;
    private String estado;     // PENDIENTE, EN PROCESO, FINALIZADA, CANCELADA
    private String descripcionProblema;
    private double costoTotal;

    //(Listas para almacenar los detalles de forma orientada a objetos)
    private List<DetalleOrdenServicio> listaServicios;
    private List<DetalleOrdenRepuesto> listaRepuestos;

    public OrdenTrabajo() {
        this.listaServicios = new ArrayList<>();
        this.listaRepuestos = new ArrayList<>();
        this.estado = "PENDIENTE";
    }

    // Método para agregar elementos a las colecciones y calcular totales en tiempo de ejecución
    public void agregarServicio(DetalleOrdenServicio servicio) {
        if (!this.estado.equals("EN PROCESO")) {
            throw new IllegalStateException("Solo se pueden agregar servicios a una orden en proceso.");
        }
        this.listaServicios.add(servicio);
    }

    public void agregarRepuesto(DetalleOrdenRepuesto repuesto) {
        if (!this.estado.equals("EN PROCESO")) {
            throw new IllegalStateException("Solo se pueden asignar repuestos a una orden en proceso.");
        }
        
        this.listaRepuestos.add(repuesto);
    }

    // Método para calcular el costo total sumando mano de obra y subtotales de repuesto
    public void calcularCostoTotalFinal() {
        double totalManoObra = listaServicios.stream().mapToDouble(DetalleOrdenServicio::getCostoAplicado).sum();
        double totalRepuestos = listaRepuestos.stream().mapToDouble(DetalleOrdenRepuesto::getSubtotal).sum();
        this.costoTotal = totalManoObra + totalRepuestos;
    }


    public int getIdOrden() 
    { 
        return idOrden;
    }
    public void setIdOrden(int idOrden) 
    { 
        this.idOrden = idOrden; 
    }

    public Vehiculo getVehiculo() 
    { 
        return vehiculo; 
    }
    public void setVehiculo(Vehiculo vehiculo) 
    { 
        this.vehiculo = vehiculo; 
    }

    public Mecanico getMecanico() 
    { 
        return mecanico;
    }
    public void setMecanico(Mecanico mecanico) 
    {
        this.mecanico = mecanico;
    }

    public Date getFechaIngreso()
    { 
        return fechaIngreso; 
    }
    public void setFechaIngreso(Date fechaIngreso)
    { 
        this.fechaIngreso = fechaIngreso; 
    }

    public String getEstado() 
    { 
        return estado; 
    }
  public void setEstado(String estado) { 
        // Si ya está finalizada o cancelada se bloquea por completo
        if (this.estado != null && (this.estado.equals("FINALIZADA") || this.estado.equals("CANCELADA"))) {
            throw new IllegalStateException("Las órdenes finalizadas o canceladas no admiten modificaciones posteriores.");
        }
        
        // Validación y asignación dentro del mismo bloque
        if (estado != null && (estado.equals("PENDIENTE") || estado.equals("EN PROCESO") || 
                               estado.equals("FINALIZADA") || estado.equals("CANCELADA"))) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado no válido. Los estados permitidos son: PENDIENTE, EN PROCESO, FINALIZADA, CANCELADA.");
        }
       
   }
    public String getdescripcionProblema() 
    {
        return descripcionProblema;
    }
    public void setdescripcionProblema(String descripcionProblema) 
    { 
        this.descripcionProblema = descripcionProblema;
    }

    public double getCostoTotal() 
    { 
        return costoTotal; 
    }

    public List<DetalleOrdenServicio> getListaServicios() { return listaServicios; }
    public List<DetalleOrdenRepuesto> getListaRepuestos() { return listaRepuestos; }
}
