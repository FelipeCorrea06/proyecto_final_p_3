/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.controlador;

import com.uniajc.modelo.Nodo;
import com.uniajc.modelo.Pila;
import com.uniajc.modelo.canonico;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author lfcr0843
 */
public class GestionDocumento {

    public static final String separador = ";";
    //public static final String quote = "\"";
    //public static int cuenta = 0;

    private Pila cabeza;
    BufferedReader br = null;
    String[][] dimension = null;
    int cant = 0;
    String nombre = "SOLCANMA";
    private String ruta_salida = "src\\DocumentoSalida";
    private String ruta_comun = "src\\DocumentoLlegda\\CarpetaComun";

    public ArrayList<canonico> ApilarDocumento() throws IOException {
        ArrayList<String> ruta_nombre = new ArrayList<>();
        Pila pilar = new Pila();
        File carpeta = new File(ruta_comun);
        File[] listado = carpeta.listFiles();
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        } else {
            for (int i = 0; i < listado.length; i++) {

                String nombre = listado[i].getName();
                pilar.AgregarElementoAlInicio(nombre);

            }
           // ruta_nombre = enviarNombre(pilar);
            enviarNombre(pilar);
            //pilar.Imprimir();

        }
        return null;
    }

    public ArrayList<String> enviarNombre(Pila pilar) throws IOException {
        int cant = pilar.CantidadElementos();
        ArrayList<String> ruta_nombre = new ArrayList<>();
        for (int i = 0; i < cant; i++) {
            String nombreCompleto = pilar.Extraer();
            String nombreCarpeta = nombreCompleto;
            String[] nombreCar = nombreCarpeta.split("_");
            switch (nombreCar[0]) {
                case "SOLI":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLI";
                    //ruta_nombre.add(ruta_salida);
                    break;
                case "SOLMAFI":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLMAFI";
                    //ruta_nombre.add(ruta_salida);
                    break;
                case "SOLMAAC":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLMAAC";
                    //ruta_nombre.add(ruta_salida);
                    break;
                case "SOLGRA":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLGRA";
                    //ruta_nombre.add(ruta_salida);
                    break;
                case "SOLCREES":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLCREES";
                    //ruta_nombre.add(ruta_salida);
                    break;
                case "SOLCANMA":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLCANMA";
                    //ruta_nombre.add(ruta_salida);
                    break;
                default:
                    // code block
            }
            LeerCsv(nombreCompleto);
        }
        return ruta_nombre;
    }

    public void LeerCsv(String nombrecompleto) throws IOException {
        try {
            br = new BufferedReader(new FileReader(ruta_comun + "\\" + nombrecompleto));
            String linea = br.readLine();
            String[] headers = linea.split(separador);
            int numLines = 0;
            while (linea != null) {
                linea = br.readLine();
                numLines++;
            }

            String[] data_file = new String[numLines];
            int cont = 0;
            br = new BufferedReader(new FileReader(ruta_comun + "\\" + nombrecompleto));
            linea = br.readLine();
            while (linea != null) {
                data_file[cont] = linea.toString();
                linea = br.readLine();
                cont++;
            }
            String[] data_show = new String[headers.length];
            String formato_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
            //Se va a comentar el += para quitar la linea de arriba temporalmente
            formato_xml = "<documento>";
            // eliminamos la primera fila que contiene la cabecera
            removeIndex(data_file, 0);
            for (int i = 0; i < data_file.length - 1; i++) {
                data_show = data_file[i].split(separador);
                if (i == 0) {
                    formato_xml += "\n\t<detalle>";
                } else {
                    formato_xml += "\n\t</detalle>\n\t<detalle>";
                }
                for (int j = 0; j < headers.length; j++) {
                    formato_xml += "\n\t\t<" + headers[j] + ">" + data_show[j] + "</" + headers[j] + ">";
                }
            }
            formato_xml += "\n\t</detalle>\n</documento>";
            //System.out.println("Contenido del Archivo:\n" + formato_xml);
            String ruta_dos = ruta_salida + "\\" + nombrecompleto;
            CrearArchivoXML(formato_xml, ruta_dos);
            // crear archivo xml canonico
            CrearXMLCanonicoFijo(formato_xml);
        } catch (IOException e) {
            System.out.println("Error LeerCSV: " + e.getMessage());
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    public ArrayList<String> CrearXMLCanonicoFijo(String formato_xml) {
        ArrayList<String> ruta_nombre = new ArrayList<>();
        try {
            OperacionDocumento od = new OperacionDocumento();
            formato_xml = od.EliminarEtiqueta("documento", formato_xml);
            formato_xml = od.EliminarEtiqueta("detalle", formato_xml);
            ArrayList<String> key = new ArrayList<>();
            String[] lineas_xml = formato_xml.split("\n");
            for (int i = 0; i < formato_xml.length(); i++) {
                if (!lineas_xml[i].equals("") || !lineas_xml[i].equals("		")) {
                    key.add(od.ObtenerClave(lineas_xml[i]));
                    System.out.println(key.get(i));
                }
            }

            ArrayList<String> etiquetas = new ArrayList<>();
            etiquetas.add("Identificacion");
            etiquetas.add("Nombres");
            etiquetas.add("Apellidos");
            etiquetas.add("Celular");
            etiquetas.add("Correo");
            etiquetas.add("Semestre");
            etiquetas.add("Ciudad");
            etiquetas.add("Direccion");
            etiquetas.add("Natalicio");
            etiquetas.add("Sede");
            etiquetas.add("AnioGrado");
            etiquetas.add("Falcultad");
            etiquetas.add("MateriaCantidad");
            etiquetas.add("Homologacion");
            etiquetas.add("Valor");
            etiquetas.add("Descuentos");

        } catch (Exception e) {
            System.out.println("Error en el canónico fijo: " + e.getMessage());
        }
        return ruta_nombre;

    }

    public void CrearXMLCanonico(String formato_xml) {
        try {

            Pila nodo = new Pila();
            OperacionDocumento od = new OperacionDocumento();
            // Eliminamos el tag para los registros
            formato_xml = formato_xml.replace("\n", "");
            formato_xml = od.EliminarVersionXml(formato_xml);
            formato_xml = od.EliminarEtiqueta("documento", formato_xml);
            formato_xml = od.EliminarEtiqueta("detalle", formato_xml);
            // separar llaves xml
            String[] lineas_xml = formato_xml.split("\n");
            String[] xml_sin_identacion = null;
            int pos = 0;
            for (int i = 0; i < lineas_xml.length; i++) {
                xml_sin_identacion = od.EliminarIdentacion(lineas_xml[i]);
                pos++;
            }

            try {
                // obtenemos las claves
                String[] claves_xml = new String[xml_sin_identacion.length];
                for (int i = 0; i < xml_sin_identacion.length; i++) {
                    claves_xml[i] = od.ObtenerClave(xml_sin_identacion[i]);
                    if (!nodo.ListaVacia()) {
                        boolean validar = false;
                        int cant = nodo.CantidadElementos();
                        validar = nodo.ValidarNombre(claves_xml[i], cant);
                        if (!validar) {
                            nodo.AgregarElementoAlFinal(claves_xml[i]);
                        }
                    } else {
                        String value = od.ObtenerClave(xml_sin_identacion[i]);
                        nodo.AgregarElementoAlInicio(value);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al obtener las claves canonico: " + e.getMessage());
            }

            // guardamos las titulos en el nodo
            // armamos el contenido del archivo canonico
            String canonico = lineas_xml[0];
            System.out.println("Estructura Canonico XML");
            canonico += "\n<canonico>";
            canonico += "\n\t<detalle>";
            int resultado = 0;
            Pila reco = nodo.cabeza;
            for (int i = 0; i < lineas_xml.length; i++) {
                if (i < lineas_xml.length) {
                    resultado = lineas_xml[i + 3].indexOf(reco.getNombre());
                } else {
                    resultado = -1;
                }

                if (resultado != -1) {
                    canonico += "\n" + lineas_xml[i + 3];
                } else {
                    canonico += "\n\t\t<" + reco.getNombre() + "></" + reco.getNombre() + ">";
                }
                reco = reco.getSiguiente();
            }
            canonico += "\n\t<detalle>";
            canonico += "\n<canonico>";

            System.out.println(canonico);
        } catch (Exception e) {
            System.out.println("Error en el canonico general: " + e.getMessage());
        }

    }

    public void removeIndex(String[] array, int index) {
        int i = index;
        for (; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[i] = null;
    }

    public boolean ListaVacia() {
        return cabeza == null;
    }

    //Hay un error en este método porque siempre muestra el último nodo
    //y se queda infinitamente en el while.
    public int Cantidad() {
        int cant = 0;
        Pila reco = cabeza;
        while (reco != null) {
            reco = reco.getSiguiente();
            cant++;
        }
        return cant;
    }
//Eliminar este métdo porque no se usa

    public void CrearArchivoXML(String contenido, String ruta) {
        try {
            ruta = ruta.replace("csv", "xml");
            File file = new File(ruta);
            // si no existe el archivo lo creamos
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(contenido);
                System.out.println("Archivo creado correctamente Ruta: " + ruta);
            }
        } catch (IOException e) {
            System.out.println("Error CrearArchivoXML: " + e.getMessage());
        }
    }

}
