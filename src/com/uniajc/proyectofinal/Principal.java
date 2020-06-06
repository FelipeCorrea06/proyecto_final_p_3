/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.proyectofinal;

import com.uniajc.controlador.ColaPrioridad;
import com.uniajc.controlador.GestionDocumento;
import com.uniajc.controlador.Hilo;
import com.uniajc.modelo.Cola;
import com.uniajc.modelo.ListaEnlazadaDoble;
import com.uniajc.modelo.Nodo;
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

        GestionDocumento gest = new GestionDocumento();
        gest.ApilarDocumento();
        System.out.println("Cola Alta= ");
        gest.alta.imprimir();
        System.out.println("Cola Media= ");
        gest.media.imprimir();
        System.out.println("Cola Baja= ");
        gest.baja.imprimir();

        Hilo hilo = new Hilo();
        hilo.obtenerColas(gest.alta, gest.media, gest.baja);

        hilo.start();
        try {
            Thread.sleep(10000);
            hilo.stop();
        } catch (Exception e) {
            System.out.println("Hilo principal del proyecto error : " + e.getMessage());
        }

    }

}
