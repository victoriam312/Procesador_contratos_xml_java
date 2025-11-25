package com.victoria.trabajo_final;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;

public class Main {
    
    public static void main (String[] args){
        System.out.println("--- INICIO DEL PROCESO ---");
        
        //1. Parte SAX -> leer XML e insterar en BBDD
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ContratosHandler handler = new ContratosHandler();
            
            File file = new File("contratos.xml");
            saxParser.parse(file, handler);
            
            System.out.println("--> Datos cargados en MySQL correctamente");
            
            //2. Parte DOM -> generar XML de salida (sin tipoContrato)
            generarXmlSalida ("contratos.xml", "contratos_procesados.xml");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //Método para la parte de salida
    public static void generarXmlSalida (String entrada, String salida){
        try{
            //Leemos el XML original con DOM
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(entrada));
            doc.getDocumentElement().normalize();
            
            //Obtenemos la lista de nodos <contrato>
            NodeList nList = doc.getElementsByTagName("contrato");
            
            //Recorremos los contratos y borramos la etiqueta tipoContrato
            for (int i = 0; i < nList.getLength(); i++){
                Node nodo = nList.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element) nodo;
                    //Buscar la etiqueta 'tipo contrato' dentro de este contrato
                    NodeList listaTipos = elemento.getElementsByTagName("tipoContrato");
                    //Si existe la borramos
                    if(listaTipos.getLength()>0){
                        Node nodoBorrar = listaTipos.item(0);
                        elemento.removeChild(nodoBorrar);
                    }
                }
            }
            
            //Escribimos el nuevo fichero
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(salida));
            
            transformer.transform(source,result);
            System.out.println("--> Fichero XML de salida generado: " + salida);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
