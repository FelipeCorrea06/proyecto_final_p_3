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
public class Pila {

    String nombre;
    Pila siguiente, anterior;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pila getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Pila siguiente) {
        this.siguiente = siguiente;
    }

    public Pila getAnterior() {
        return anterior;
    }

    public void setAnterior(Pila anterior) {
        this.anterior = anterior;
    }

    public Pila cabeza;

    public boolean ListaVacia() {
        if (cabeza == null) {
            return true;
        } else {
            return false;
        }
    }
    
     public String Extraer()
        {
            if (cabeza != null)
            {
                String informacion = cabeza.getNombre();
                cabeza = cabeza.siguiente;
                return informacion;
            }
            else
            {
                return "Nada";
            }
        }

    public void AgregarElementoAlInicio(String value) {
        Pila nuevo = new Pila();
        nuevo.setNombre(value);
        if (cabeza == null) {
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
            cabeza = nuevo;
        } else {
            Pila ultimo = cabeza.getAnterior();
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(ultimo);
            cabeza.setAnterior(nuevo);
            ultimo.setSiguiente(nuevo);
            cabeza = nuevo;
        }

    }

    public void AgregarElementoAlFinal(String value) {
        try {
             Pila nuevo = new Pila();
        nuevo.setNombre(value);
        if (cabeza == null) {
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
            cabeza = nuevo;
        } else {
            Pila ultimo = cabeza.getAnterior();
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(ultimo);
            cabeza.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
        }
            
        } catch (Exception e) {
            System.out.println("Error en agregar al final en PilarArchivos: "+e.getMessage());
        }
       
    }

    public void Imprimir() {
        if (!ListaVacia()) {
            Pila reco = cabeza;
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
            Pila reco = cabeza;
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
            Pila reco = cabeza;
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
                Pila reco = cabeza;
                int can = CantidadElementos();
                for (int f = 1; f <= can - 1; f++)
                {
                if (pos == cabeza.ID)
                {
                    cabeza = reco.Anterior;
                    Pila anterior1 = reco.Anterior;
                    reco = reco.Siguiente;
                    anterior1.Siguiente = reco;
                    reco.Anterior = anterior1;

                }

                reco = reco.Siguiente;
                    if (pos == reco.ID)
                    {
                        
                        cabeza = reco.Anterior;
                        Pila anterior = reco.Anterior;
                        reco = reco.Siguiente;
                        anterior.Siguiente = reco;
                        reco.Anterior = anterior;
                     
                    }

                }
        }
     */
    public Pila EncontrarElemento(int pos, int sentido) {
        Pila reco = cabeza;
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
