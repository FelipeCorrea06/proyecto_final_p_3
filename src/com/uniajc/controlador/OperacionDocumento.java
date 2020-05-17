/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.controlador;

/**
 *
 * @author Felipe Medel
 */
public class OperacionDocumento {
    
    public String[] EliminarIdentacion(String linea){
        String [] identacion = linea.split("\t\t");
        return identacion;
    }
    
    public String ObtenerClave(String propiedad){
        String [] clave = propiedad.split("<");
        String[] llave = clave[1].split(">");
        return llave[0];
    }
    
    public String EliminarEtiqueta(String etiqueta, String formato_xml){
        String tag_open = "<" + etiqueta + ">";        
        String tag_close = "</" + etiqueta + ">";

        formato_xml = formato_xml.replace(tag_open, "");        
        formato_xml = formato_xml.replace(tag_close, "");
        return formato_xml;
    }
}
