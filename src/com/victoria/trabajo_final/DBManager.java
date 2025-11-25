package com.victoria.trabajo_final;

import java.sql.*;
import java.time.*;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_contratos?serverTimezone=UCT";
    private static final String USER = "root";
    private static final String PASS = "Malagacf.25";
    
    private static final String SQL_INSERT = "INSERT INTO contrato " + "(nif_adjudicatario, adjudicatario, objeto_generico, objeto_detalle, fecha_adjudicacion, importe, proveedores_consultados, tipo_contrato)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    public void insertarContrato(Contrato c){
        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT)){
            
            ps.setString(1, c.getNif());
            ps.setString(2, c.getAdjudicatorio());
            ps.setString(3, c.getObjetoGenerico());
            ps.setString(4, c.getObjeto());
            
            //CONVERSIÓN DE FECHA (STRING XML -> MYSQL DateTime
            if (c.getFechaAdjudicacion() != null && !c.getFechaAdjudicacion().isEmpty()){
                LocalDateTime fecha = LocalDateTime.parse(c.getFechaAdjudicacion());
                ps.setTimestamp (5, Timestamp.valueOf(fecha));
            }else{
                ps.setNull(5, Types.TIMESTAMP);
            }
            
            ps.setDouble(6, c.getImporte());
            ps.setString(7, c.getProveedoresConsultados());
            ps.setString(8, c.getTipoContrato());
            
            ps.executeUpdate();
            System.out.println("Insertado contrato de: " + c.getAdjudicatorio());
            
        }catch (SQLException e){
            System.err.println("Error insertando: " + e.getMessage());
            
        }
    }
}


