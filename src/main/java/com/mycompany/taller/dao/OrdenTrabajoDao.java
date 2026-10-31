/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.dao;
import com.mycompany.taller.config.Conexiontaller;
import com.mycompany.taller.modelo.Mecanico;
import com.mycompany.taller.modelo.OrdenTrabajo;
import com.mycompany.taller.modelo.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Auder
 */public class OrdenTrabajoDao {

    public boolean insertar(OrdenTrabajo orden) {
        String sql = "INSERT INTO ordenes_trabajo (id_vehiculo, id_mecanico, fecha_ingreso, estado, descripcion_problema, costo_total) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
        {
            ps.setInt(1, orden.getVehiculo().getIdVehiculo());
            ps.setInt(2, orden.getMecanico().getId());//mecanico existente 
            if (orden.getFechaIngreso() != null) {
                ps.setDate(3, new java.sql.Date(orden.getFechaIngreso().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }
            
            ps.setString(4, orden.getEstado());
            ps.setString(5, orden.getdescripcionProblema());
            ps.setDouble(6, orden.getCostoTotal());
            
            int filasAfectadas = ps.executeUpdate();
            boolean insertado = filasAfectadas > 0;
            
            System.out.println("--- RESULTADO DE INSERCIÓN ---");
            if (insertado) {
                // Opcional: Recuperar el ID generado para la orden si se requiere
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orden.setIdOrden(generatedKeys.getInt(1));
                    }
                }
                System.out.println(">>> ¡Orden de Trabajo #" + orden.getIdOrden() + " insertada con éxito! <<<");
            } else {
                System.out.println(">>> No se pudo insertar la orden de trabajo. <<<");
            }
            System.out.println("------------------------------");
            
            return insertado;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar orden de trabajo: " + e.getMessage());
            return false;
        }
    }

    public OrdenTrabajo buscarPorId(int idOrden) {
        // Usamos INNER JOIN para traer los datos del vehículo y mecánico asociados
        String sql = "SELECT o.*, v.placa, v.marca, v.modelo, v.anio, " +
                     "m.nombres AS mech_nombres, m.apellidos AS mech_apellidos " +
                     "FROM ordenes_trabajo o " +
                     "INNER JOIN vehiculos v ON o.id_vehiculo = v.id_vehiculo " +
                     "INNER JOIN mecanicos m ON o.id_mecanico = m.id_mecanico " +
                     "WHERE o.id_orden = ?";
                     
        OrdenTrabajo orden = null;
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idOrden);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Mapeamos el Vehículo
                    Vehiculo vehiculo = new Vehiculo();
                    vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
                    vehiculo.setPlaca(rs.getString("placa"));
                    vehiculo.setMarca(rs.getString("marca"));
                    vehiculo.setModelo(rs.getString("modelo"));
                    vehiculo.setAnio(rs.getInt("anio"));
                    
                    // Mapeamos el Mecánico 
                    Mecanico mecanico = new Mecanico();
                    mecanico.setId(rs.getInt("id_mecanico"));//id del mecanico existente
                    mecanico.setNombres(rs.getString("mech_nombres"));
                    mecanico.setApellidos(rs.getString("mech_apellidos"));
                    
                    orden = new OrdenTrabajo();
                    orden.setIdOrden(rs.getInt("id_orden"));
                    orden.setVehiculo(vehiculo);
                    orden.setMecanico(mecanico);
                    orden.setFechaIngreso(rs.getDate("fecha_ingreso"));
                    orden.setEstado(rs.getString("estado"));
                    orden.setdescripcionProblema(rs.getString("descripcion_problema"));
                    // costoTotal se puede asignar directamente o recalcular
                }
            }
            
            System.out.println("--- RESULTADO DE BÚSQUEDA POR ID ---");
            if (orden != null) {
                System.out.println(">>> ¡Orden de Trabajo encontrada con éxito! <<<");
                System.out.println("ID Orden: " + orden.getIdOrden());
                System.out.println("Estado: " + orden.getEstado());
                System.out.println("Vehículo Placa: " + orden.getVehiculo().getPlaca());
                System.out.println("Mecánico: " + orden.getMecanico().getNombres() + " " + orden.getMecanico().getApellidos());
                System.out.println("Problema: " + orden.getdescripcionProblema());
            } else {
                System.out.println(">>> No se encontró ninguna orden con el ID " + idOrden + " <<<");
            }
            System.out.println("------------------------------------");
            
        } catch (SQLException e) {
            System.err.println("Error al buscar orden por ID: " + e.getMessage());
        }
        
        return orden;
    }

    public boolean actualizar(OrdenTrabajo orden) {
        String sql = "UPDATE ordenes_trabajo SET id_vehiculo = ?, id_mecanico = ?, fecha_ingreso = ?, estado = ?, descripcion_problema = ?, costo_total = ? WHERE id_orden = ?";
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, orden.getVehiculo().getIdVehiculo());
            ps.setInt(2, orden.getMecanico().getId());
            
            if (orden.getFechaIngreso() != null) {
                ps.setDate(3, new java.sql.Date(orden.getFechaIngreso().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }
            
            ps.setString(4, orden.getEstado());
            ps.setString(5, orden.getdescripcionProblema());
            ps.setDouble(6, orden.getCostoTotal());
            ps.setInt(7, orden.getIdOrden());
            
            boolean actualizado = ps.executeUpdate() > 0;
            
            System.out.println("--- RESULTADO DE ACTUALIZACIÓN ---");
            if (actualizado) {
                System.out.println(">>> ¡Orden de Trabajo #" + orden.getIdOrden() + " actualizada con éxito! <<<");
            } else {
                System.out.println(">>> No se pudo actualizar. Es posible que el ID " + orden.getIdOrden() + " no exista. <<<");
            }
            System.out.println("-----------------------------------");
            
            return actualizado;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar orden de trabajo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idOrden) {
        String sql = "DELETE FROM ordenes_trabajo WHERE id_orden = ?";
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idOrden);
            boolean eliminado = ps.executeUpdate() > 0;
            
            System.out.println("--- RESULTADO DE ELIMINACIÓN ---");
            if (eliminado) {
                System.out.println(">>> ¡Orden de Trabajo #" + idOrden + " eliminada con éxito! <<<");
            } else {
                System.out.println(">>> No se pudo eliminar. Es posible que el ID " + idOrden + " no exista. <<<");
            }
            System.out.println("---------------------------------");
            
            return eliminado;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar orden de trabajo: " + e.getMessage());
            return false;
        }
    }

    public List<OrdenTrabajo> listarTodos() {
        String sql = "SELECT o.*, v.placa, v.marca, v.modelo, v.anio, " +
                     "m.nombres AS mech_nombres, m.apellidos AS mech_apellidos " +
                     "FROM ordenes_trabajo o " +
                     "INNER JOIN vehiculos v ON o.id_vehiculo = v.id_vehiculo " +
                     "INNER JOIN mecanicos m ON o.id_mecanico = m.id_mecanico";
                     
        List<OrdenTrabajo> listaOrdenes = new ArrayList<>();
        
        try (Connection con = Conexiontaller.IniciarConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
                vehiculo.setPlaca(rs.getString("placa"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setAnio(rs.getInt("anio"));
                
                Mecanico mecanico = new Mecanico();
                mecanico.setId(rs.getInt("id_mecanico"));
                mecanico.setNombres(rs.getString("mech_nombres"));
                mecanico.setApellidos(rs.getString("mech_apellidos"));
                
                OrdenTrabajo orden = new OrdenTrabajo();
                orden.setIdOrden(rs.getInt("id_orden"));
                orden.setVehiculo(vehiculo);
                orden.setMecanico(mecanico);
                orden.setFechaIngreso(rs.getDate("fecha_ingreso"));
                orden.setEstado(rs.getString("estado"));
                orden.setdescripcionProblema(rs.getString("descripcion_problema"));
                
                listaOrdenes.add(orden);
            }
            
            System.out.println("--- LISTANDO ÓRDENES DE TRABAJO ---");
            if (listaOrdenes.isEmpty()) {
                System.out.println(">>> No hay órdenes de trabajo registradas en la base de datos. <<<");
            } else {
                System.out.println(">>> Se encontraron " + listaOrdenes.size() + " orden(es): <<<\n");
                for (OrdenTrabajo o : listaOrdenes) {
                    System.out.println("ID Orden: " + o.getIdOrden());
                    System.out.println("Estado: " + o.getEstado());
                    System.out.println("Vehículo: " + o.getVehiculo().getPlaca());
                    System.out.println("Mecánico: " + o.getMecanico().getNombres() + " " + o.getMecanico().getApellidos());
                    System.out.println("Problema: " + o.getdescripcionProblema());
                    System.out.println("-----------------------------------");
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar órdenes de trabajo: " + e.getMessage());
        }
        
        return listaOrdenes;
    }
}
