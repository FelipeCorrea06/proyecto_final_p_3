/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.modelo;

/**
 *
 * @author lfcr0843
 */
public class ListaEnlazadaDoble {

    private Nodo cabeza;

    public boolean ListaVacia() {
        if (cabeza == null) {
            return true;
        } else {
            return false;
        }
    }

    public void AgregarElementoAlInicio(String value) {
        Nodo nuevo = new Nodo();
        nuevo.setNombre(value);
        nuevo.setSiguiente(cabeza);
        if (ListaVacia()) {
            //cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
    }

    public void imprimir() {
        Nodo recorrido = cabeza;
        while (recorrido != null) {
            System.out.println(recorrido.getNombre() + "->");
            recorrido = recorrido.getSiguiente();
        }
        System.out.println("*\n");
    }

    public void AgregarElementoAlFinal(String value) {
        Nodo nuevo = new Nodo();
        nuevo.setNombre(value);
        if (ListaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo reco = cabeza;
            while (reco.getSiguiente() != null) {
                reco = reco.getSiguiente();
            }
            reco.setSiguiente(nuevo);
            nuevo.setAnterior(reco);
        }
    }

    public String Extraer() {
        if (cabeza != null) {
            String informacion = cabeza.getNombre();
            cabeza = cabeza.siguiente;
            return informacion;
        } else {
            return "Nada";
        }
    }

    public String EliminarElementoDesdeLaCabeza() {
        cabeza = cabeza.getSiguiente();
        String ruta_salida = "";
        if (!ListaVacia()) {
            ruta_salida = cabeza.getNombre();
            cabeza.setAnterior(null);
        }
        return ruta_salida;
    }

    public void EliminarElementoDesdeElFinal() {
        Nodo recorrido = cabeza;
        while (recorrido.getSiguiente() != null) {
            recorrido = recorrido.getSiguiente();
        }
        recorrido = recorrido.getAnterior();
        if (recorrido != null) {
            recorrido.setSiguiente(null);
        } else {
            cabeza = null;
        }
    }
}
