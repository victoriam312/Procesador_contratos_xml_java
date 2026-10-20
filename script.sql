-- Esquema de la base de datos del Procesador de Contratos XML.
-- Columnas y tipos según lo que inserta DBManager (SQL_INSERT).
-- Uso: mysql -u tu_usuario -p < script.sql

CREATE DATABASE IF NOT EXISTS gestion_contratos
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_spanish_ci;

USE gestion_contratos;

CREATE TABLE IF NOT EXISTS contrato (
    id                      INT AUTO_INCREMENT PRIMARY KEY,
    nif_adjudicatario       VARCHAR(20)    NOT NULL,
    adjudicatario           VARCHAR(255)   NOT NULL,
    objeto_generico         VARCHAR(255),
    objeto_detalle          TEXT,
    fecha_adjudicacion      DATETIME       NULL,
    importe                 DECIMAL(12, 2),
    proveedores_consultados TEXT,
    tipo_contrato           VARCHAR(50)
);
