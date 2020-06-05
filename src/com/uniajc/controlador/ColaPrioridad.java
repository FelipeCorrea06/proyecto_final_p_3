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

    ArrayList<String> lista;

    public ColaPrioridad() {
        super();
    }

    public void run() {
       
    }

    public void capturarValoresLista(ArrayList<String> valor) {
        this.lista = valor;
    }

}
