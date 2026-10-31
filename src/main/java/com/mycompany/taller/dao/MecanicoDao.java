/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.dao;

import com.mycompany.taller.config.Conexiontaller;
import com.mycompany.taller.modelo.Mecanico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MecanicoDao {

    public boolean insertar(Mecanico mecanico) {
        String sql = "INSERT INTO mecanicos (nombres, apellidos, telefono, especialidad) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, mecanico.getNombres());
            ps.setString(2, mecanico.getApellidos());
            ps.setString(3, mecanico.getTelefono());
            ps.setString(4, mecanico.getEspecialidad());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar mecánico: " + e.getMessage());
            return false;
        }
    }

    public Mecanico buscarPorId(int id) {
        String sql = "SELECT * FROM mecanicos WHERE id_mecanico = ?";
        Mecanico mecanico = null;
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mecanico = new Mecanico(
                        rs.getInt("id_mecanico"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("especialidad")
                    );
                }
            }
            
            System.out.println("--- RESULTADO DE BÚSQUEDA POR ID ---");
            if (mecanico != null) {
                System.out.println(">>> ¡Mecánico encontrado con éxito! <<<");
                System.out.println("ID: " + mecanico.getId());
                System.out.println("Nombre Completo: " + mecanico.getNombres() + " " + mecanico.getApellidos());
                System.out.println("Teléfono: " + mecanico.getTelefono());
                System.out.println("Especialidad: " + mecanico.getEspecialidad());
            } else {
                System.out.println(">>> No se encontró ningún mecánico con el ID " + id + " <<<");
            }
            System.out.println("------------------------------------");
            
        } catch (SQLException e) {
            System.err.println("Error al buscar mecánico por ID: " + e.getMessage());
        }
        
        return mecanico;
    }

    public boolean actualizar(Mecanico mecanico) {
        String sql = "UPDATE mecanicos SET nombres = ?, apellidos = ?, telefono = ?, especialidad = ? WHERE id_mecanico = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, mecanico.getNombres());
            ps.setString(2, mecanico.getApellidos());
            ps.setString(3, mecanico.getTelefono());
            ps.setString(4, mecanico.getEspecialidad());
            ps.setInt(5, mecanico.getId());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar mecánico: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM mecanicos WHERE id_mecanico = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar mecánico: " + e.getMessage());
            return false;
        }
    }

    public List<Mecanico> listarTodos() {
        String sql = "SELECT * FROM mecanicos";
        List<Mecanico> listaMecanicos = new ArrayList<>();
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Mecanico mecanico = new Mecanico(
                    rs.getInt("id_mecanico"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("especialidad")
                );
                listaMecanicos.add(mecanico);
            }
            
            System.out.println("--- LISTANDO DE MECÁNICOS ---");
            if (listaMecanicos.isEmpty()) {
                System.out.println(">>> No hay mecánicos registrados en la base de datos. <<<");
            }
            else 
            {
                System.out.println(">>> Se encontraron " + listaMecanicos.size() + " mecánico(s): <<<\n");
                for (Mecanico m : listaMecanicos) {
                    System.out.println("ID: " + m.getId());
                    System.out.println("Nombre Completo: " + m.getNombres() + " " + m.getApellidos());
                    System.out.println("Teléfono: " + m.getTelefono());
                    System.out.println("Especialidad: " + m.getEspecialidad());
                    System.out.println("-----------------------------------");
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar mecánicos: " + e.getMessage());
        }
        
        return listaMecanicos;
    }
}
