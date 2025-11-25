package com.victoria.trabajo_final;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContratosHandler extends DefaultHandler {
    
    private DBManager dbManager;
    private Contrato contratoActual;
    private StringBuilder buffer; //Para guardar el texto entre etiquetas
    
    public ContratosHandler(){
        this.dbManager = new DBManager();
    }
    
    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException{
        buffer = new StringBuilder(); //Limpiamos buffer al inicio de cada etiqueta
        if (qName.equalsIgnoreCase("contrato")){
            contratoActual = new Contrato();
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        buffer.append(ch, start, length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        if (contratoActual != null){
            String valor = buffer.toString().trim(); //trim() quita los espacios en blanco que sobran
            
            switch (qName){
                case "nif": contratoActual.setNif(valor);break;
                case "adjudicatario": contratoActual.setAdjudicatorio(valor); break;
                case "objetoGenerico": contratoActual.setObjetoGenerico(valor); break;
                case "objeto": contratoActual.setObjeto(valor); break;
                case "fechaAdjudicacion": contratoActual.setFechaAdjudicacion(valor); break;
                case "importe":
                    //El XML viene como "110,50€". Hay que limpiarlo
                    try{
                        //1. Quitar el simbolo € y espacios
                        String limpio = valor.replace("€", "").trim();
                        //2. Cambiar coma decimal por punto
                        limpio = limpio.replace(",", ".");
                        //3. Parsear a double
                        contratoActual.setImporte(Double.parseDouble(limpio));
                    }catch (NumberFormatException e){
                        System.err.println("Error parseando importe: " + valor);
                        contratoActual.setImporte(0.0);
                    }
                    break;
                case "proveedoresConsultados": contratoActual.setProveedoresConsultados(valor);break;
                case "tipoContrato": contratoActual.setTipoContrato(valor);break;
                
                case "contrato":
                    //Fin del contrato, guardamos en BBDD
                    dbManager.insertarContrato(contratoActual);
                    break;
            }
        }
    }
}
