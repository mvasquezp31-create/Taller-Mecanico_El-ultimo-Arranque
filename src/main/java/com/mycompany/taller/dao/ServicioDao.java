/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.dao;
import com.mycompany.taller.config.Conexiontaller;
import com.mycompany.taller.modelo.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Auder
 */
public class ServicioDao {

    public boolean insertar(Servicio servicio) {
        String sql = "INSERT INTO servicios (nombre_servicio, descripcion, costo_mano_obra) VALUES (?, ?, ?)";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, servicio.getNombreServicio());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getCostoManoObra());
            
            boolean insertado = ps.executeUpdate() > 0;
            
            System.out.println("--- RESULTADO DE INSERCIÓN ---");
            if (insertado) {
                System.out.println(">>> ¡Servicio '" + servicio.getNombreServicio() + "' insertado con éxito! <<<");
            } else {
                System.out.println(">>> No se pudo insertar el servicio. <<<");
            }
            System.out.println("------------------------------");
            
            return insertado;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar servicio: " + e.getMessage());
            return false;
        }
    }

    public Servicio buscarPorId(int idServicio) {
        String sql = "SELECT * FROM servicios WHERE id_servicio = ?";
        Servicio servicio = null;
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idServicio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    servicio = new Servicio(
                        rs.getInt("id_servicio"),
                        rs.getString("nombre_servicio"),
                        rs.getString("descripcion"),
                        rs.getDouble("costo_mano_obra")
                    );
                }
            }
            
            System.out.println("--- RESULTADO DE BÚSQUEDA POR ID ---");
            if (servicio != null) {
                System.out.println(">>> ¡Servicio encontrado con éxito! <<<");
                System.out.println("ID Servicio: " + servicio.getIdServicio());
                System.out.println("Nombre: " + servicio.getNombreServicio());
                System.out.println("Descripción: " + servicio.getDescripcion());
                System.out.println("Costo Mano de Obra: Q" + servicio.getCostoManoObra());
            } else {
                System.out.println(">>> No se encontró ningún servicio con el ID " + idServicio + " <<<");
            }
            System.out.println("------------------------------------");
            
        } catch (SQLException e) {
            System.err.println("Error al buscar servicio por ID: " + e.getMessage());
        }
        
        return servicio;
    }

    public boolean actualizar(Servicio servicio) {
        String sql = "UPDATE servicios SET nombre_servicio = ?, descripcion = ?, costo_mano_obra = ? WHERE id_servicio = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, servicio.getNombreServicio());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getCostoManoObra());
            ps.setInt(4, servicio.getIdServicio());
            
            boolean actualizado = ps.executeUpdate() > 0;
            
            System.out.println("--- RESULTADO DE ACTUALIZACIÓN ---");
            if (actualizado) {
                System.out.println(">>> ¡Servicio con ID " + servicio.getIdServicio() + " actualizado con éxito! <<<");
            } else {
                System.out.println(">>> No se pudo actualizar. Es posible que el ID " + servicio.getIdServicio() + " no exista. <<<");
            }
            System.out.println("-----------------------------------");
            
            return actualizado;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar servicio: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idServicio) {
        String sql = "DELETE FROM servicios WHERE id_servicio = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idServicio);
            boolean eliminado = ps.executeUpdate() > 0;
            
            System.out.println("--- RESULTADO DE ELIMINACIÓN ---");
            if (eliminado) {
                System.out.println(">>> ¡Servicio con ID " + idServicio + " eliminado con éxito! <<<");
            } else {
                System.out.println(">>> No se pudo eliminar. Es posible que el ID " + idServicio + " no exista. <<<");
            }
            System.out.println("---------------------------------");
            
            return eliminado;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar servicio: " + e.getMessage());
            return false;
        }
    }

    public List<Servicio> listarTodos() {
        String sql = "SELECT * FROM servicios";
        List<Servicio> listaServicios = new ArrayList<>();
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Servicio servicio = new Servicio(
                    rs.getInt("id_servicio"),
                    rs.getString("nombre_servicio"),
                    rs.getString("descripcion"),
                    rs.getDouble("costo_mano_obra")
                );
                listaServicios.add(servicio);
            }
            
            System.out.println("--- LISTANDO DE SERVICIOS ---");
            if (listaServicios.isEmpty()) {
                System.out.println(">>> No hay servicios registrados en la base de datos. <<<");
            } else {
                System.out.println(">>> Se encontraron " + listaServicios.size() + " servicio(s): <<<\n");
                for (Servicio s : listaServicios) {
                    System.out.println("ID Servicio: " + s.getIdServicio());
                    System.out.println("Nombre: " + s.getNombreServicio());
                    System.out.println("Descripción: " + s.getDescripcion());
                    System.out.println("Costo Mano de Obra: Q" + s.getCostoManoObra());
                    System.out.println("-----------------------------------");
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar servicios: " + e.getMessage());
        }
        
        return listaServicios;
    }
}
