/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.dao;


import com.mycompany.taller.config.Conexiontaller;
import com.mycompany.taller.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    public boolean insertar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombres, apellidos, telefono, direccion, correo) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCorreo());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }
public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        Cliente cliente = null;
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("correo")
                    );
                }
            }
            
         
            System.out.println("--- RESULTADO DE BÚSQUEDA POR ID ---");
            if (cliente != null) {
                System.out.println(">>> ¡Cliente encontrado con éxito! <<<");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nombre Completo: " + cliente.getNombres() + " " + cliente.getApellidos());
                System.out.println("Teléfono: " + cliente.getTelefono());
                System.out.println("Dirección: " + cliente.getDireccion());
                System.out.println("Correo: " + cliente.getCorreo());
            } else {
                System.out.println(">>> No se encontró ningún cliente con el ID " + id + " <<<");
            }
            System.out.println("------------------------------------");
            
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por ID: " + e.getMessage());
        }
        
        return cliente;
    }
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombres = ?, apellidos = ?, telefono = ?, direccion = ?, correo = ? WHERE id_cliente = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCorreo());
            ps.setInt(6, cliente.getId());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> listaClientes = new ArrayList<>();
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("correo")
                );
                listaClientes.add(cliente);
            }
            
            System.out.println("--- LISTANDO DE CLIENTES ---");
            if (listaClientes.isEmpty()) {
                System.out.println(">>> No hay clientes registrados en la base de datos. <<<");
            }
            else 
            {
                System.out.println(">>> Se encontraron " + listaClientes.size() + " cliente(s): <<<\n");
                for (Cliente c : listaClientes) {
                    System.out.println("ID: " + c.getId());
                    System.out.println("Nombre Completo: " + c.getNombres() + " " + c.getApellidos());
                    System.out.println("Teléfono: " + c.getTelefono());
                    System.out.println("Dirección: " + c.getDireccion());
                    System.out.println("Correo: " + c.getCorreo());
                    System.out.println("-----------------------------------");
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        
        return listaClientes;
    }
}
