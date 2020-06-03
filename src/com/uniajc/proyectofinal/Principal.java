/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.proyectofinal;

import com.uniajc.controlador.GestionDocumento;
import com.uniajc.modelo.Pila;
import com.uniajc.modelo.canonico;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lfcr0843
 */
public class Principal {

    static Pila cabeza;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
  
       
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArrayList<canonico> ruta_nombre = new ArrayList<>();
        Pila alta = new Pila();
        Pila media = new Pila();
        Pila baja = new Pila();
        GestionDocumento gest = new GestionDocumento();
        ruta_nombre = gest.ApilarDocumento();
        
       
        
        //gest.LeerCsv("SOLCANMA_05052020195003.csv");

    }

}
