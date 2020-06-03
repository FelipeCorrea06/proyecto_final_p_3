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

    public String[] EliminarIdentacion(String linea) {
        String[] identacion = null;
        try {
            if (linea.equals("\t\t")) {
                identacion = linea.split("\t\t");
            } else {
                identacion = linea.split("\t\t\t");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Identación: "+e.getMessage());
        }

        return identacion;
    }

    public String ObtenerClave(String propiedad) {
        String[] clave = propiedad.split("<");
        String[] llave = clave[1].split(">");
        return llave[0];
    }

    public String EliminarVersionXml(String formato_xml) {
        String tag_open = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        formato_xml = formato_xml.replace(tag_open, "");
        return formato_xml;
    }

    public String EliminarEtiqueta(String etiqueta, String formato_xml) {
        String tag_open = "<" + etiqueta + ">";
        String tag_close = "</" + etiqueta + ">";

        formato_xml = formato_xml.replace(tag_open, "");
        formato_xml = formato_xml.replace(tag_close, "");
        return formato_xml;
    }
}
