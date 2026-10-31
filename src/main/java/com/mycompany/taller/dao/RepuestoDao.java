/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package com.mycompany.taller.dao;


import com.mycompany.taller.config.Conexiontaller; //
import com.mycompany.taller.modelo.Repuesto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepuestoDao implements CrudDao<Repuesto> {

    @Override
   public boolean insertar(Repuesto repuesto) {
        String sql = "INSERT INTO repuestos (nombre, descripcion, precio, existencia) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, repuesto.getNombre());
            ps.setString(2, repuesto.getDescripcion());
            ps.setDouble(3, repuesto.getPrecio());
            ps.setInt(4, repuesto.getExistencia());
            
            int filasAfectadas = ps.executeUpdate(); // Verificamos si afectó filas
            System.out.println("Filas insertadas en BD: " + filasAfectadas);
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            // error de la base de datos
            System.err.println("--- ERROR SQL DETALLADO ---");
            e.printStackTrace(); 
            return false;
        }
    }

    @Override
    public boolean actualizar(Repuesto repuesto) {
        String sql = "UPDATE repuestos SET nombre = ?, descripcion = ?, precio = ?, existencia = ? WHERE id_repuesto = ?";
        try (Connection con = Conexiontaller.IniciarConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, repuesto.getNombre());
            ps.setString(2, repuesto.getDescripcion());
            ps.setDouble(3, repuesto.getPrecio());
            ps.setInt(4, repuesto.getExistencia());
            ps.setInt(5, repuesto.getIdRepuesto());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar repuesto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM repuestos WHERE id_repuesto = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar repuesto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Repuesto buscarPorId(int id) {
        String sql = "SELECT * FROM repuestos WHERE id_repuesto = ?";
        Repuesto repuesto = null;
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    repuesto = new Repuesto(
                        rs.getInt("id_repuesto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("existencia")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar repuesto por ID: " + e.getMessage());
        }
        return repuesto;
    }

    @Override
public List<Repuesto> listarTodos() {
    String sql = "SELECT * FROM repuestos";
    List<Repuesto> listaRepuestos = new ArrayList<>();
    
    try (Connection con = Conexiontaller.IniciarConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            Repuesto repuesto = new Repuesto(
                rs.getInt("id_repuesto"), // variable real en la tabla repuestos
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("existencia")
            );
            listaRepuestos.add(repuesto);
        }
        
       
        System.out.println("--- LISTANDO DE REPUESTOS ---");
        if (listaRepuestos.isEmpty()) {
            System.out.println(">>> No hay repuestos registrados en la base de datos. <<<");
        } else {
            System.out.println(">>> Se encontraron " + listaRepuestos.size() + " repuesto(s): <<<\n");
            for (Repuesto r : listaRepuestos) {
                System.out.println("ID: " + r.getIdRepuesto());
                System.out.println("Nombre: " + r.getNombre());
                System.out.println("Descripción: " + r.getDescripcion());
                System.out.println("Precio: Q" + r.getPrecio());
                System.out.println("Existencia: " + r.getExistencia());
                System.out.println("-----------------------------------");
            }
        }
        
    } catch (SQLException e) {
        System.err.println("Error al listar repuestos: " + e.getMessage());
    }
   
    return listaRepuestos;

    }
}