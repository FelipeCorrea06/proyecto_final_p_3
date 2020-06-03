/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.modelo;

/**
 *
 * @author Felipe Medel
 */
public class Nodo {

    private String nombre;
    private Nodo siguiente, anterior;
    public Nodo cabeza;

    public boolean ListaVacia() {
        boolean response = false;
        if (cabeza == null) {
            response = true;
        }
        return response;
    }

    public void AgregarAlInicio(String value) {
        Nodo nuevo = new Nodo();
        nuevo.setNombre(value);
        if (cabeza == null) {
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
            cabeza = nuevo;
        } else {
            Nodo ultimo = cabeza.getAnterior();
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(ultimo);
            cabeza.setAnterior(nuevo);
            ultimo.setSiguiente(nuevo);
            cabeza = nuevo;
        }
    }

    public void AgregarAlFinal(String value) {
        Nodo nuevo = new Nodo();
        nuevo.setNombre(value);
        if (cabeza == null) {
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
            cabeza = nuevo;
        } else {
            Nodo ultimo = cabeza.getAnterior();
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(ultimo);
            cabeza.setAnterior(anterior);
            ultimo.setSiguiente(nuevo);
        }
    }

    public boolean ValidarNombre(String nombre, int cant) {
        boolean exist = false;
        if (!ListaVacia()) {
            Nodo reco = cabeza;
            for (int i = 0; i < cant; i++) {
                if (reco.getNombre().equals(nombre)) {
                    reco = null;
                    return exist = true;
                }
            }
        } else {
            System.out.println("Nodo - Validar Nombre: Objeto vacio");
        }
        return exist;
    }

    public void Imprimir() {
        if (!ListaVacia()) {
            Nodo reco = cabeza;
            while (reco != null) {
                System.out.print(reco.getNombre() + " - ");
                reco = reco.getSiguiente();
            }
            System.out.println("");
        } else {
            System.out.println("Nodo: Objecto Vacio");
        }
    }

    public int CantidadElementos() {
        int cant = 0;
        if (!ListaVacia()) {
            Nodo reco = cabeza;
            while (reco != null) {
                cant++;
                reco = reco.getSiguiente();
            }
        }
        return cant;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo Siguiente) {
        this.siguiente = Siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo Anterior) {
        this.anterior = Anterior;
    }

}
