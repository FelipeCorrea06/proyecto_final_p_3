/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.proyectofinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author lfcr0843
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static final String separador = ";";
    public static final String quote = "\"";
    public static int cuenta = 0;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader br = null;
        String[] filas = null;
        String[][] dimension = null;

        try {
            String nombre = "SOLCANMA";
            br = new BufferedReader(new FileReader("src\\DocumentoLlegda\\CarpetaComun\\SOLCANMA.csv"));
            String linea = br.readLine();

            while (null != linea) {
                
                filas = linea.split(separador);
                System.out.println(Arrays.toString(filas));                
                /*Se debe usar si el archivo viene con "\"
                 fields = removeTrailingQuotes(fields);
                 System.out.println(Arrays.toString(fields));*/
                linea = br.readLine();
                cuenta++;
            }
            dimension = new String[cuenta][filas.length];
            br = new BufferedReader(new FileReader("src\\DocumentoLlegda\\CarpetaComun\\SOLCANMA.csv"));
            String linea2 = br.readLine();
             while (null != linea2) {
                
                               
                for (int i = 0; i < cuenta; i++) {
                    filas = linea2.split(separador);
                    for (int j = 0; j < filas.length; j++) {
                        
                        dimension[i][j] = filas[j];
                       
                    }
                    linea2 = br.readLine();
                }
                cuenta++;
            }
           

            convertirAXML(nombre, dimension);

        } catch (Exception e) {
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    public static void convertirAXML(String nombreArchivo, String[][] filas) {
        Date fecha = new Date();
        try {
            PrintWriter writer = new PrintWriter("src\\DocumentoXML\\nombreArchivo.xml", "UTF-8");
            int cuenta = 0;
            for (int i = 0; i < filas.length; i++) {
                for (int j = 0; j < filas.length; j++) {
                    writer.println(filas[i]);
                }

                System.out.println("Datos: " + Arrays.toString(filas));
            }
            System.out.println("Datos 2: " + filas[0]);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error genarl: " + e.getMessage());
        }

    }

    /*Se debe usar si el archivo viene con "\" 
     private static String[] removeTrailingQuotes(String[] fields) {

     String result[] = new String[fields.length];

     for (int i=0;i<result.length;i++){
     result[i] = fields[i].replaceAll("^"+quote, "").replaceAll(quote+"$", "");
     }
     return result;
     }*/
}
