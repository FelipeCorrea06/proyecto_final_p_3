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
public class Cola {

    String nombre;
    Cola siguiente, anterior;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Cola siguiente) {
        this.siguiente = siguiente;
    }

    public Cola getAnterior() {
        return anterior;
    }

    public void setAnterior(Cola anterior) {
        this.anterior = anterior;
    }

    public Cola cabeza;

    public boolean ListaVacia() {
        if (cabeza == null) {
            return true;
        } else {
            return false;
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

    public void EliminarElementoDesdeLaCabeza() {
        if (!ListaVacia()) {
            cabeza = cabeza.getSiguiente();
        }
    }

    public void EliminarElementoDesdeElFinal() {
        int counter = 0;
        if (!ListaVacia()) {
            Cola recorrido = cabeza;
            Cola referencia = new Cola();
            while (recorrido != null) {
                if (recorrido.getSiguiente() != null) {
                    referencia = recorrido;
                }
                recorrido = recorrido.getSiguiente();
                counter++;
            }
            if (counter == 1) {
                cabeza = null;
            } else {
                referencia.setSiguiente(null);
                recorrido = referencia;
            }
        }
    }

    public void AgregarElementoAlInicio(String value) {
        Cola nuevo = new Cola();
        nuevo.setNombre(value);
        if (cabeza == null) {
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
            cabeza = nuevo;
        } else {
            Cola ultimo = cabeza.getAnterior();
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(ultimo);
            cabeza.setAnterior(nuevo);
            ultimo.setSiguiente(nuevo);
            cabeza = nuevo;
        }

    }

    public void AgregarElementoAlFinal(String value) {
        try {
            Cola nuevo = new Cola();
            nuevo.setNombre(value);
            if (cabeza == null) {
                nuevo.setSiguiente(nuevo);
                nuevo.setAnterior(nuevo);
                cabeza = nuevo;
            } else {
                Cola ultimo = cabeza.getAnterior();
                nuevo.setSiguiente(cabeza);
                nuevo.setAnterior(ultimo);
                cabeza.setAnterior(nuevo);
                ultimo.setSiguiente(nuevo);
            }

        } catch (Exception e) {
            System.out.println("Error en agregar al final en ColarArchivos: " + e.getMessage());
        }

    }

    public void Imprimir() {
        if (!ListaVacia()) {
            Cola reco = cabeza;
            do {
                System.out.println(reco.getNombre() + "->");
                reco = reco.getSiguiente();
            } while (reco != cabeza);
            System.out.println("");
        }
    }

    public int CantidadElementos() {
        int cant = 0;
        if (!ListaVacia()) {
            Cola reco = cabeza;
            do {
                cant++;
                reco = reco.getSiguiente();
            } while (reco != cabeza);
        }
        return cant;
    }

    public boolean ValidarNombre(String nombre, int cant) {
        boolean exist = false;
        if (!ListaVacia()) {
            Cola reco = cabeza;
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

    /* Se comento porque de aquí vamos a sacar el código de eliminar 
     public void EliminarPorPosicion(int pos)
     {
     Cola reco = cabeza;
     int can = CantidadElementos();
     for (int f = 1; f <= can - 1; f++)
     {
     if (pos == cabeza.ID)
     {
     cabeza = reco.Anterior;
     Cola anterior1 = reco.Anterior;
     reco = reco.Siguiente;
     anterior1.Siguiente = reco;
     reco.Anterior = anterior1;

     }

     reco = reco.Siguiente;
     if (pos == reco.ID)
     {
                        
     cabeza = reco.Anterior;
     Cola anterior = reco.Anterior;
     reco = reco.Siguiente;
     anterior.Siguiente = reco;
     reco.Anterior = anterior;
                     
     }

     }
     }
     */
    public Cola EncontrarElemento(int pos, int sentido) {
        Cola reco = cabeza;
        for (int i = 0; i < pos; i++) {
            if (sentido == 0) {
                reco = reco.getSiguiente();
            } else {
                reco = reco.getAnterior();
            }
        }
        System.out.println("Eliminado del Juego el Doctor " + reco.getNombre());
        return reco;
    }

}
