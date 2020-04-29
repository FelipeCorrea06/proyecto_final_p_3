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

    static Nodo cabeza;

    /**
     * @param args the command line arguments
     */
    public static final String separador = ";";
    public static final String quote = "\"";
    public static int cuenta = 0;

    public static class Nodo {

        String fila[];
        Nodo siguiente, anterior;

        public String[] getFila() {
            return fila;
        }

        public void setFila(String[] fila) {
            this.fila = fila;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo anterior) {
            this.anterior = anterior;
        }
    }

    public static class GestionDocumento {

        private Nodo cabeza;
        BufferedReader br = null;
        String[][] dimension = null;
        int cant = 0;
        String nombre = "SOLCANMA";

        public void LeerCsv() throws IOException {
            try {
                br = new BufferedReader(new FileReader("src\\DocumentoLlegda\\CarpetaComun\\SOLCANMA.csv"));
                String linea = br.readLine();
                Nodo nuevo = new Nodo();
                while (null != linea) {
                    nuevo.fila = linea.split(separador);
                    System.out.println(Arrays.toString(nuevo.fila));

                    linea = br.readLine();
                    nuevo.siguiente = cabeza;
                    cuenta++;

                    if (ListaVacia()) {
                        cabeza = nuevo;
                    } else {
                        cabeza.anterior = nuevo;
                        cabeza = nuevo;
                    }
                }

                cant = Cantidad();

                convertirAXML(nombre, nuevo, cant);

            } catch (Exception e) {
            } finally {
                if (null != br) {
                    br.close();
                }
            }
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
        Nodo reco = cabeza;
        while (reco != null) {
            reco = reco.siguiente;
            cant++;
        }
        return cant;
    }
    }

    

    public static void convertirAXML(String nombreArchivo, Nodo filas, int cant) {
        Date fecha = new Date();

        for (int i = 0; i < cant; i++) {
            System.out.println(" Creación XML: ");
            System.out.println(Arrays.toString(filas.fila));
            filas.anterior = cabeza;
        }

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
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        GestionDocumento ges = new GestionDocumento();
        ges.LeerCsv();

    }

}
