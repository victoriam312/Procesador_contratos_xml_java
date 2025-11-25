/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victoria.trabajo_final;

public class Contrato {
    private String nif;
    private String adjudicatorio;
    private String objetoGenerico;
    private String objeto;
    private String fechaAdjudicacion;
    private double importe;
    private String proveedoresConsultados;
    private String tipoContrato;
    
    //Constructor vacío
    public Contrato(){        
    }

    //Getters y setters
    public String getNif (){
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAdjudicatorio() {
        return adjudicatorio;
    }
    public void setAdjudicatorio(String adjudicatorio) {
        this.adjudicatorio = adjudicatorio;
    }

    public String getObjetoGenerico() {
        return objetoGenerico;
    }
    public void setObjetoGenerico(String objetoGenerico) {
        this.objetoGenerico = objetoGenerico;
    }

    public String getObjeto() {
        return objeto;
    }
    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getFechaAdjudicacion() {
        return fechaAdjudicacion;
    }
    public void setFechaAdjudicacion(String fechaAdjudicacion) {
        this.fechaAdjudicacion = fechaAdjudicacion;
    }

    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getProveedoresConsultados() {
        return proveedoresConsultados;
    }
    public void setProveedoresConsultados(String proveedoresConsultados) {
        this.proveedoresConsultados = proveedoresConsultados;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }
    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    
    @Override
    public String toString(){
        return "Contrato{" + "adjudicatorio=" + adjudicatorio + ", importe=" + importe + '}';
    }
}
