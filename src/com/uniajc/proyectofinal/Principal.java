/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.proyectofinal;

import com.uniajc.controlador.GestionDocumento;
import com.uniajc.modelo.PilaArchivosLlegada;
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

    static PilaArchivosLlegada cabeza;

    /**
     * @param args the command line arguments
     */
    


 
    

    

        /*
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
         }*/
    

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        GestionDocumento gest = new GestionDocumento();
        gest.ApilarDocumento();
        //gest.LeerCsv();

    }

}
