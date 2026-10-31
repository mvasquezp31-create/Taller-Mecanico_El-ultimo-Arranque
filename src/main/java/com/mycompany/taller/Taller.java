/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package com.mycompany.taller;


import com.mycompany.taller.dao.MecanicoDao;
import com.mycompany.taller.dao.OrdenTrabajoDao;
import com.mycompany.taller.dao.ServicioDao;
import com.mycompany.taller.dao.VehiculoDao;
import com.mycompany.taller.modelo.Cliente;
import com.mycompany.taller.modelo.Mecanico;
import com.mycompany.taller.modelo.Servicio;
import com.mycompany.taller.modelo.Vehiculo;
import java.sql.SQLException;

public class Taller {
    public static void main(String[] args) {
OrdenTrabajoDao ordenDao = new OrdenTrabajoDao();
        
        // Ejecutamos el listado completo
        ordenDao.listarTodos();
    }

    }
