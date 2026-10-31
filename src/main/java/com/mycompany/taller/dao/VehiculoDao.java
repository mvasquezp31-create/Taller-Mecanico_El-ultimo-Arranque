

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.dao;
import com.mycompany.taller.config.Conexiontaller;
import com.mycompany.taller.modelo.Cliente;
import com.mycompany.taller.modelo.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDao {

    public boolean insertar(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (placa, marca, modelo, anio, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setInt(4, vehiculo.getAnio());
            // Guardamos el ID del cliente propietario sacándolo del objeto compuesto
            ps.setInt(5, vehiculo.getClientePropitario().getId());
            
            boolean insertado = ps.executeUpdate() > 0;
            
            System.out.println("--- RESULTADO DE INSERCIÓN ---");
            if (insertado) {
                System.out.println(">>> ¡Vehículo con placa " + vehiculo.getPlaca() + " insertado con éxito! <<<");
            } else {
                System.out.println(">>> No se pudo insertar el vehículo. <<<");
            }
            System.out.println("------------------------------");
            
            return insertado;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar vehículo: " + e.getMessage());
            return false;
        }
    }

    public Vehiculo buscarPorId(int idVehiculo) {
        // Hacemos un JOIN con clientes para poder reconstruir el objeto Cliente propietario completo
        String sql = "SELECT v.*, c.nombres, c.apellidos, c.telefono, c.direccion, c.correo " +
                     "FROM vehiculos v INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
                     "WHERE v.id_vehiculo = ?";
        Vehiculo vehiculo = null;
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idVehiculo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Reconstruimos el cliente propietario
                    Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("correo")
                    );
                    
                    // Reconstruimos el vehículo con su cliente dentro
                    vehiculo = new Vehiculo(
                        rs.getInt("id_vehiculo"),
                        rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        cliente
                    );
                }
            }
            
            System.out.println("--- RESULTADO DE BÚSQUEDA POR ID ---");
            if (vehiculo != null) {
                System.out.println(">>> ¡Vehículo encontrado con éxito! <<<");
                System.out.println("ID Vehículo: " + vehiculo.getIdVehiculo());
                System.out.println("Placa: " + vehiculo.getPlaca());
                System.out.println("Marca/Modelo: " + vehiculo.getMarca() + " " + vehiculo.getModelo() + " (" + vehiculo.getAnio() + ")");
                System.out.println("Propietario: " + vehiculo.getClientePropitario().getNombres() + " " + vehiculo.getClientePropitario().getApellidos());
            } else {
                System.out.println(">>> No se encontró ningún vehículo con el ID " + idVehiculo + " <<<");
            }
            System.out.println("------------------------------------");
            
        } catch (SQLException e) {
            System.err.println("Error al buscar vehículo por ID: " + e.getMessage());
        }
        
        return vehiculo;
    }

    public boolean actualizar(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET placa = ?, marca = ?, modelo = ?, anio = ?, id_cliente = ? WHERE id_vehiculo = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setInt(4, vehiculo.getAnio());
            ps.setInt(5, vehiculo.getClientePropitario().getId());
            ps.setInt(6, vehiculo.getIdVehiculo());
            
            boolean actualizado = ps.executeUpdate() > 0;
            
            System.out.println("--- RESULTADO DE ACTUALIZACIÓN ---");
            if (actualizado) {
                System.out.println(">>> ¡Vehículo con ID " + vehiculo.getIdVehiculo() + " actualizado con éxito! <<<");
            } else {
                System.out.println(">>> No se pudo actualizar. Es posible que el ID " + vehiculo.getIdVehiculo() + " no exista. <<<");
            }
            System.out.println("-----------------------------------");
            
            return actualizado;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar vehículo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idVehiculo) {
        String sql = "DELETE FROM vehiculos WHERE id_vehiculo = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idVehiculo);
            boolean eliminado = ps.executeUpdate() > 0;
            
            System.out.println("--- RESULTADO DE ELIMINACIÓN ---");
            if (eliminado) {
                System.out.println(">>> ¡Vehículo con ID " + idVehiculo + " eliminado con éxito! <<<");
            } else {
                System.out.println(">>> No se pudo eliminar. Es posible que el ID " + idVehiculo + " no exista. <<<");
            }
            System.out.println("---------------------------------");
            
            return eliminado;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar vehículo: " + e.getMessage());
            return false;
        }
    }

    public List<Vehiculo> listarTodos() {
        String sql = "SELECT v.*, c.nombres, c.apellidos, c.telefono, c.direccion, c.correo " +
                     "FROM vehiculos v INNER JOIN clientes c ON v.id_cliente = c.id_cliente";
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("correo")
                );
                
                Vehiculo vehiculo = new Vehiculo(
                    rs.getInt("id_vehiculo"),
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("anio"),
                    cliente
                );
                listaVehiculos.add(vehiculo);
            }
            
            System.out.println("--- LISTANDO DE VEHÍCULOS ---");
            if (listaVehiculos.isEmpty()) {
                System.out.println(">>> No hay vehículos registrados en la base de datos. <<<");
            } else {
                System.out.println(">>> Se encontraron " + listaVehiculos.size() + " vehículo(s): <<<\n");
                for (Vehiculo v : listaVehiculos) {
                    System.out.println("ID Vehículo: " + v.getIdVehiculo());
                    System.out.println("Placa: " + v.getPlaca());
                    System.out.println("Marca/Modelo: " + v.getMarca() + " " + v.getModelo() + " (" + v.getAnio() + ")");
                    System.out.println("Propietario: " + v.getClientePropitario().getNombres() + " " + v.getClientePropitario().getApellidos());
                    System.out.println("-----------------------------------");
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar vehículos: " + e.getMessage());
        }
        
        return listaVehiculos;
    }
}