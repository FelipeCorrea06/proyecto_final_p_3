/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.controlador;

import com.uniajc.modelo.PilaArchivosLlegada;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author lfcr0843
 */
public class GestionDocumento {

    public static final String separador = ";";
    public static final String quote = "\"";
    public static int cuenta = 0;

    private PilaArchivosLlegada cabeza;
    BufferedReader br = null;
    String[][] dimension = null;
    int cant = 0;
    String nombre = "SOLCANMA";

    public void ApilarDocumento() throws IOException {
        PilaArchivosLlegada pilar = new PilaArchivosLlegada();
        String rutaLlegada = "C:\\Users\\lfcr0843\\Documents\\Proyecto final programacion 3\\Proyectofinal\\src\\DocumentoLlegda\\CarpetaComun";
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

            //pilar.Imprimir();
            LeerCsv();
        }
    }

    public void LeerCsv() throws IOException {
        //PilaArchivosLlegada pilar = new PilaArchivosLlegada();
        String nombrecompleto = "SOLCANMA03052020162445.csv";
        //String nombrecorto = String.valueOf(nombrecompleto.split("_"));

        try {
            br = new BufferedReader(new FileReader("src\\DocumentoLlegda\\CarpetaComun\\" + nombrecompleto));
            String linea = br.readLine();
            String[] headers = linea.split(separador);

            //PilaArchivosLlegada cabecera = new PilaArchivosLlegada();
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
            String formato_xml = "<project>";
            // eliminamos la primera fila que contiene la cabecera
            removeIndex(data_file, 0);
            for (int i = 0; i < data_file.length - 1; i++) {
                data_show = data_file[i].split(separador);
                formato_xml += "\n\t";
                for (int j = 0; j < headers.length; j++) {
                    formato_xml += "\n\t<" + headers[j] + ">" + data_show[j] + "</" + headers[j] + ">";
                }
                //System.out.println(headers[i]);
                //System.out.println(data_file[i]);

            }
            formato_xml += "\n</project>";
            System.out.println(formato_xml);

//            while (null != linea) {
//                String[] cabeza;
//                cabeza = linea.split(separador);
//
//                for (int i = 0; i < cabeza.length; i++) {
//                    cabecera.AgregarElementoAlInicio(cabeza[i].toString());
//                }
//
//                linea = br.readLine();
//                cabecera.setSiguiente(cabecera);
//                cuenta++;
//
//                if (ListaVacia()) {
//                    cabecera = cabecera;
//                } else {
//                    cabecera.setAnterior(cabecera);
//                    cabecera = cabecera;
//                }
//            }
            //cabecera.Imprimir();
            //cant = Cantidad();
            // convertirAXML(nombre, nuevo, cant);
        } catch (Exception e) {
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

    public int quantityLines(BufferedReader file) {

        return 0;
    }

    public boolean ListaVacia() {
        if (cabeza == null) {
            return true;
        } else {
            return false;
        }
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

    public void convertirAXML(String nombreArchivo, PilaArchivosLlegada filas, int cant) {
        Date fecha = new Date();

        for (int i = 0; i < cant; i++) {
            System.out.println(" Creación XML: ");
            System.out.println(filas.getNombre());
            filas.setAnterior(cabeza);
        }
    }

}
