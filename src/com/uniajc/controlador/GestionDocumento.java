/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.controlador;

import com.uniajc.modelo.PilaArchivosLlegada;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author lfcr0843
 */
public class GestionDocumento {

    public static final String separador = ";";
    //public static final String quote = "\"";
    //public static int cuenta = 0;

    private PilaArchivosLlegada cabeza;
    BufferedReader br = null;
    String[][] dimension = null;
    int cant = 0;
    String nombre = "SOLCANMA";
    private String ruta_salida = "src\\DocumentoSalida";

    public void ApilarDocumento() throws IOException {
        PilaArchivosLlegada pilar = new PilaArchivosLlegada();
        String rutaLlegada = "src\\DocumentoLlegda\\CarpetaComun";
        File carpeta = new File(rutaLlegada);
        File[] listado = carpeta.listFiles();
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            return;
        } else {
            for (int i = 0; i < listado.length; i++) {

                String nombre = listado[i].getName();
                pilar.AgregarElementoAlInicio(nombre);

            }
            enviarNombre(pilar);
            //pilar.Imprimir();

        }
    }

    public void enviarNombre(PilaArchivosLlegada pilar) throws IOException {
        int cant = pilar.CantidadElementos();
        for (int i = 0; i < cant; i++) {
            String nombreCompleto = pilar.Extraer();
            String nombreCarpeta = nombreCompleto;
            String[] nombreCar = nombreCarpeta.split("_");
            switch (nombreCar[0]) {
                case "SOLI":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLI";
                    break;
                case "SOLMAFI":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLMAFI";
                    break;
                case "SOLMAAC":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLMAAC";
                    break;
                case "SOLGRA":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLGRA";
                    break;
                case "SOLCREES":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLCREES";
                    break;
                case "SOLCANMA":
                    // code block
                    ruta_salida = "src\\DocumentoSalida\\XML_SOLCANMA";
                    break;
                default:
                // code block
                    
            }
            LeerCsv(nombreCompleto);
        }
    }

    public void LeerCsv(String nombrecompleto) throws IOException {
        //String nombrecompleto = "SOLCANMA03052020162445.csv";
        try {
            br = new BufferedReader(new FileReader("src\\DocumentoLlegda\\CarpetaComun\\" + nombrecompleto));
            String linea = br.readLine();
            String[] headers = linea.split(separador);

            int numLines = 0;
            while (linea != null) {
                linea = br.readLine();
                numLines++;
            }

            String[] data_file = new String[numLines];
            int cont = 0;
            br = new BufferedReader(new FileReader("src\\DocumentoLlegda\\CarpetaComun\\" + nombrecompleto));
            linea = br.readLine();
            while (linea != null) {
                data_file[cont] = linea.toString();
                linea = br.readLine();
                cont++;
            }
            String[] data_show = new String[headers.length];
            String formato_xml = "<documento>";
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
            System.out.println("Contenido del Archivo:\n" + formato_xml);
            String ruta_dos = ruta_salida + "\\" + nombrecompleto;
            //ruta_salida += "\\" + nombrecompleto;
            CrearArchivoXML(formato_xml, ruta_dos);

        } catch (IOException e) {
            System.out.println("Error LeerCSV: " + e.getMessage());
        } finally {
            if (null != br) {
                br.close();
            }
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
        PilaArchivosLlegada reco = cabeza;
        while (reco != null) {
            reco = reco.getSiguiente();
            cant++;
        }
        return cant;
    }

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

    public void convertirAXML(String nombreArchivo, PilaArchivosLlegada filas, int cant) {
        Date fecha = new Date();

        for (int i = 0; i < cant; i++) {
            System.out.println(" Creación XML: ");
            System.out.println(filas.getNombre());
            filas.setAnterior(cabeza);
        }
    }

}
