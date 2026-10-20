package com.victoria.trabajo_final;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.time.*;

public class DBManager {
    // Las credenciales NO están en el código: se leen de db.properties
    // (raíz del proyecto, ignorado por Git) o de las variables de entorno
    // DB_URL, DB_USER y DB_PASSWORD. Plantilla: db.properties.example
    private static final String CONFIG_FILE = "db.properties";
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/gestion_contratos?serverTimezone=UTC";
    private static final Properties CONFIG = cargarConfig();
    
    private static final String SQL_INSERT = "INSERT INTO contrato " + "(nif_adjudicatario, adjudicatario, objeto_generico, objeto_detalle, fecha_adjudicacion, importe, proveedores_consultados, tipo_contrato)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static Properties cargarConfig(){
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(CONFIG_FILE)){
            props.load(in);
        }catch (IOException e){
            System.out.println("Aviso: no se encontró " + CONFIG_FILE + ", se usarán variables de entorno");
        }
        return props;
    }

    private static String valor(String clave, String porDefecto){
        String env = System.getenv(clave);
        if (env != null && !env.isEmpty()){
            return env;
        }
        return CONFIG.getProperty(clave, porDefecto);
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(
                valor("DB_URL", DEFAULT_URL),
                valor("DB_USER", ""),
                valor("DB_PASSWORD", ""));
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


