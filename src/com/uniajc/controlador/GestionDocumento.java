/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.controlador;


import com.uniajc.modelo.Cola;
import com.uniajc.modelo.ListaEnlazadaDoble;
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
    public ListaEnlazadaDoble alta = new ListaEnlazadaDoble();
    public ListaEnlazadaDoble media = new ListaEnlazadaDoble();
    public ListaEnlazadaDoble baja = new ListaEnlazadaDoble();
    //public static final String quote = "\"";
    //public static int cuenta = 0;

    private Cola cabeza;
    BufferedReader br = null;
    String[][] dimension = null;
    int cant = 0;
    private String ruta_xml = "src\\DocumentoXML";
    private String ruta_canonico = "src\\DocumentoXML\\XML_CANONICO";
    private String ruta_comun = "src\\DocumentoLlegda\\CarpetaComun";

    public void ApilarDocumento() throws IOException {
        ArrayList<String> ruta_nombre = new ArrayList<>();
        Cola pilar = new Cola();
        File carpeta = new File(ruta_comun);
        File[] listado = carpeta.listFiles();
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        } else {
            for (int i = 0; i < listado.length; i++) {

                String nombre = listado[i].getName();
                pilar.AgregarElementoAlInicio(nombre);

            }
            enviarNombre(pilar);
            pilar.Imprimir();

        }
    }

    public void enviarNombre(Cola pilar) throws IOException {
        int cant = pilar.CantidadElementos();
        for (int i = 0; i < cant; i++) {
            String nombreCompleto = pilar.Extraer();
            String nombreCarpeta = nombreCompleto;
            String[] nombreCar = nombreCarpeta.split("_");
            switch (nombreCar[0]) {
                case "SOLI":
                    // code block
                    ruta_xml = "src\\DocumentoXML\\XML_SOLI";
                    if (alta.ListaVacia()) {
                        alta.AgregarElementoAlInicio(nombreCompleto.replace("csv", "xml"));
                    }else
                         alta.AgregarElementoAlFinal(nombreCompleto.replace("csv", "xml"));
                    break;
                case "SOLMAFI":
                    // code block
                    ruta_xml = "src\\DocumentoXML\\XML_SOLMAFI";
                    if (alta.ListaVacia()) {
                        alta.AgregarElementoAlInicio(nombreCompleto.replace("csv", "xml"));
                    }else
                         alta.AgregarElementoAlFinal(nombreCompleto.replace("csv", "xml"));
                    break;
                case "SOLMAAC":
                    // code block
                    ruta_xml = "src\\DocumentoXML\\XML_SOLMAAC";
                    if (alta.ListaVacia()) {
                        alta.AgregarElementoAlInicio(nombreCompleto.replace("csv", "xml"));
                    }else
                         alta.AgregarElementoAlFinal(nombreCompleto.replace("csv", "xml"));
                    break;
                case "SOLGRA":
                    // code block
                    ruta_xml = "src\\DocumentoXML\\XML_SOLGRA";
                    if (media.ListaVacia()) {
                        media.AgregarElementoAlInicio(nombreCompleto.replace("csv", "xml"));
                    }else
                         media.AgregarElementoAlFinal(nombreCompleto.replace("csv", "xml"));
                    break;
                case "SOLCREES":
                    // code block
                    ruta_xml = "src\\DocumentoXML\\XML_SOLCREES";
                    if (media.ListaVacia()) {
                        media.AgregarElementoAlInicio(nombreCompleto.replace("csv", "xml"));
                    }else
                         media.AgregarElementoAlFinal(nombreCompleto.replace("csv", "xml"));
                    break;
                case "SOLCANMA":
                    // code block
                    ruta_xml = "src\\DocumentoXML\\XML_SOLCANMA";
                    if (baja.ListaVacia()) {
                        baja.AgregarElementoAlInicio(nombreCompleto.replace("csv", "xml"));
                    }else
                         baja.AgregarElementoAlFinal(nombreCompleto.replace("csv", "xml"));
                    break;
                default:
                // code block
            }
            LeerCsv(nombreCompleto);
        }
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
            String ruta_completa_xml = ruta_xml + "\\" + nombrecompleto;
            String ruta_completa_salida = ruta_canonico + "\\" + nombrecompleto;
            CrearArchivoXML(formato_xml, ruta_completa_xml);
            // crear archivo xml canonico
            CrearXMLCanonicoFijo(formato_xml,ruta_completa_salida);
        } catch (IOException e) {
            System.out.println("Error LeerCSV: " + e.getMessage());
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    public void CrearXMLCanonicoFijo(String formato_xml, String nombrecompleto) {
        String formato_xml_canonico = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
        formato_xml_canonico += "<documento>";
        formato_xml_canonico += "\n\t<detalle>";
        formato_xml_canonico += "\n\t\t<id>1</id>";
        formato_xml_canonico += "\n\t</detalle>";
        formato_xml_canonico += "\n</documento>";

        System.out.println("Formato XML: " + formato_xml_canonico);

        // separar llaves xml
        System.out.println("Separar Lineas XML: ");
        String[] lineas_xml = formato_xml_canonico.split("\n");
        // omitimos las 3 primeras lineas
        System.out.println(lineas_xml[3]);
        // eliminamos las identaciones del formato
        String[] xml_identacion = new String[lineas_xml.length - 5];
        int pos = 0;
        for (int i = 3; i < lineas_xml.length - 2; i++) {
            xml_identacion[pos] = EliminarIdentacion(lineas_xml[i]);
            pos++;
        }
        // obtenemos las claves
        String[] claves_xml = new String[4];
        claves_xml[0] = "id";
        claves_xml[1] = "nombre";
        claves_xml[2] = "apellido";
        claves_xml[3] = "edad";

        System.out.println("Estructura Canonico XML");
        String canonico = lineas_xml[0];
        canonico += "\n<canonico>";
        canonico += "\n\t<detalle>";
        String resp = "";
        for (int i = 0; i < claves_xml.length; i++) {
            if (lineas_xml.length > i + 3) {
                resp = AgregarLinea(claves_xml, lineas_xml[i + 3]);
                if (resp != "") {
                    canonico += resp;
                }
            } else {
                break;
            }

        }

        canonico += AgregarLlaveFaltante(claves_xml, canonico);

        canonico += "\n\t<detalle>";
        canonico += "\n<canonico>";

        System.out.println(canonico);
        CrearArchivoXML(canonico,nombrecompleto);
        

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
        Cola reco = cabeza;
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
    
    
     public static String EliminarIdentacion(String linea) {
        String[] identacion = linea.split("\t");
        if (identacion.length > 2) {
            return identacion[2];
        } else {
            return identacion[1];
        }
    }
     public static String ObtenerClave(String propiedad) {
        String[] clave = propiedad.split("<");
        String[] llave = clave[1].split(">");
        return llave[0];
    }

    public static String AgregarLinea(String llave[], String linea) {
        String canonico = "";
        int resultado = -1;
        String llave_fail = "";
        for (int i = 0; i < llave.length; i++) {
            resultado = linea.indexOf(llave[i]);
            if (resultado != -1) {
                break;
            }
        }
        if (resultado != -1) {
            canonico += "\n" + linea;
        } else {
            canonico = "";
        }
        return canonico;
    }

    public static String AgregarLlaveFaltante(String llave[], String canonico) {

        int resultado = -1;
        String resp = "";
        for (int i = 0; i < llave.length; i++) {
            resultado = canonico.indexOf(llave[i]);
            if (resultado == -1) {
                resp += "\n\t\t<" + llave[i] + "></" + llave[i] + ">";
            }
        }

        return resp;
    }


}
