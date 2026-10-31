/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.config;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Auder
 */
public class Conexiontaller {
    
    private static final String URL = "jdbc:mysql://localhost:3306/taller";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "Mxva2026$$";
    
    private static Connection conexion = null;

    private Conexiontaller(){
    }
    
    public static Connection IniciarConexion(){
        try{
            if(conexion == null || conexion.isClosed()){
               conexion = DriverManager.getConnection(URL,USUARIO,PASSWORD); 
                System.out.println("Conexion exitosa");
            }
        }catch(SQLException e){
            System.err.println("La conexion fallo");
            e.printStackTrace();
        }
        return conexion;
    }
    
    public static boolean ejecutarInstruccion(String sql){
        try{
            Connection conn = IniciarConexion();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("La insstruccion se ejecuto correcamente");
            return true;
        }catch(SQLException e){
            System.err.println("Error al momento de ejecutar la instruccion");
            e.printStackTrace();
            return false;
        }
    }
    
    public static ResultSet ejecutarConsulta(String sql){
        ResultSet rs = null;
        try{
            Connection conn = IniciarConexion();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        }catch(SQLException e){
            System.err.println("Error al momento de ejecutar la instruccion");
            e.printStackTrace();
        }
        return rs;
    }
    
    public static void cerrarConexion(){
        try{
            if(conexion != null && !conexion.isClosed()){
                conexion.close();
                System.out.println("La conexion se cerro correctamente");
            }
        }catch(SQLException e){
            System.err.println("Error al momento de cerrar la conexion");
            e.printStackTrace();
        }
    }
    
    
}


