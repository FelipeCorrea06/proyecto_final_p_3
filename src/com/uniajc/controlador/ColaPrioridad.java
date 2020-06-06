/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.controlador;

import com.uniajc.modelo.Cola;
import java.util.ArrayList;

/**
 *
 * @author lfcr0843
 */
public class ColaPrioridad extends Thread {

    Cola alta;
    Cola media;
    Cola baja;

    public ColaPrioridad() {
        super();
    }

    public void run() {
        String nombre_ruta = null;
        while (true) {

            if (!alta.ListaVacia()) {
                nombre_ruta = alta.Extraer();
            } else if (!media.ListaVacia()) {
                nombre_ruta = media.Extraer();
            } else if (!baja.ListaVacia()) {
                nombre_ruta = baja.Extraer();
            }
            System.out.println("Esta es la ruta que se saco de la cola: " + nombre_ruta);
        }

    }

    public void capturarValoresLista(Cola alta, Cola media, Cola baja) {
        this.alta = alta;
        this.media = media;
        this.baja = baja;
    }

}
