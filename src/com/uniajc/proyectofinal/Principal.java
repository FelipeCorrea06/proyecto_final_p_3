/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.proyectofinal;

import com.uniajc.controlador.ColaPrioridad;
import com.uniajc.controlador.GestionDocumento;
import com.uniajc.modelo.Cola;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lfcr0843
 */
public class Principal {

    static Cola cabeza;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArrayList<String> ruta_nombre = new ArrayList<>();

        GestionDocumento gest = new GestionDocumento();
       // ruta_nombre = gest.ApilarDocumento();

        gest.colaPrioridad("SOLCANMA_canonico_20052020114957.xml");
        gest.colaPrioridad("SOLCREES_canonico_20052020114957.xml");
        gest.colaPrioridad("SOLGRA_canonico_20052020114957.xml");
        gest.colaPrioridad("SOLI_canonico_20052020114947.xml");
        gest.colaPrioridad("SOLI_canonico_20052020114957.xml");
        gest.colaPrioridad("SOLI_canonico_20052020114958.xml");
        gest.colaPrioridad("SOLMAAC_canonico_20052020114957.xml");
        gest.colaPrioridad("SOLMAFI_canonico_20052020114957.xml");
        System.out.println("Cola alta: ------>");
        gest.alta.Imprimir();
        System.out.println("Cola media: ------>");
        gest.media.Imprimir();
        System.out.println("Cola baja: ------>");
        gest.baja.Imprimir();

        /*  ColaPrioridad hilo = new ColaPrioridad();
         hilo.capturarValoresLista(ruta_nombre);
        
        
         hilo.start();
         try {
         //Thread.sleep(30000);
         //hilo.stop();
         } catch (Exception e) {
         System.out.println("Hilo principal error: " + e.getMessage());
         }*/
        //gest.LeerCsv("SOLCANMA_05052020195003.csv");
    }

}
