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
public class PilaArchivosLlegada {

    String nombre;
    PilaArchivosLlegada siguiente, anterior;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PilaArchivosLlegada getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(PilaArchivosLlegada siguiente) {
        this.siguiente = siguiente;
    }

    public PilaArchivosLlegada getAnterior() {
        return anterior;
    }

    public void setAnterior(PilaArchivosLlegada anterior) {
        this.anterior = anterior;
    }

   
        public PilaArchivosLlegada cabeza;

        public boolean ListaVacia()
        {
            if (cabeza == null)
                return true;
            else
                return false;
        }

        public void AgregarElementoAlInicio(String value)
        {
            PilaArchivosLlegada nuevo = new PilaArchivosLlegada();
            nuevo.setNombre(value);
            if (cabeza == null)
            {
                nuevo.setSiguiente(nuevo);
                nuevo.setAnterior(nuevo);
                cabeza = nuevo;
            }
            else
            {
                PilaArchivosLlegada ultimo = cabeza.getAnterior();
                nuevo.setSiguiente(cabeza);
                nuevo.setAnterior(ultimo);
                cabeza.setAnterior(nuevo);
                ultimo.setSiguiente(nuevo);
                cabeza = nuevo;
            }

        }

        public void AgregarElementoAlFinal(String value)
        {
            PilaArchivosLlegada nuevo = new PilaArchivosLlegada();
            nuevo.setNombre(value);
            if (cabeza == null)
            {
                nuevo.setSiguiente(nuevo);
                nuevo.setAnterior(nuevo);
                cabeza = nuevo;
            }
            else
            {
                PilaArchivosLlegada ultimo = cabeza.getAnterior();
                nuevo.setSiguiente(cabeza);
                nuevo.setAnterior(ultimo);
                cabeza.setAnterior(anterior);
                ultimo.setSiguiente(nuevo);
            }
        }
        public void Imprimir()
        {
            if (!ListaVacia())
            {
                PilaArchivosLlegada reco = cabeza;
                do
                {
                    System.out.println(reco.getNombre() + "->");
                    reco = reco.getSiguiente();
                } while (reco != cabeza);
                System.out.println("");
            }
        }
        public int CantidadElementos()
        {
            int cant = 0;
            if (!ListaVacia())
            {
                PilaArchivosLlegada reco = cabeza;
                do
                {
                    cant++;
                    reco = reco.getSiguiente();
                } while (reco != cabeza);
            }
            return cant;
        }

     /* Se comento porque de aquí vamos a sacar el código de eliminar 
        public void EliminarPorPosicion(int pos)
        {
                PilaArchivosLlegada reco = cabeza;
                int can = CantidadElementos();
                for (int f = 1; f <= can - 1; f++)
                {
                if (pos == cabeza.ID)
                {
                    cabeza = reco.Anterior;
                    PilaArchivosLlegada anterior1 = reco.Anterior;
                    reco = reco.Siguiente;
                    anterior1.Siguiente = reco;
                    reco.Anterior = anterior1;

                }

                reco = reco.Siguiente;
                    if (pos == reco.ID)
                    {
                        
                        cabeza = reco.Anterior;
                        PilaArchivosLlegada anterior = reco.Anterior;
                        reco = reco.Siguiente;
                        anterior.Siguiente = reco;
                        reco.Anterior = anterior;
                     
                    }

                }
        }
*/
            public PilaArchivosLlegada EncontrarElemento(int pos, int sentido)
        {
            PilaArchivosLlegada reco = cabeza;
            for (int i =0; i < pos; i++)
            {
                if (sentido == 0)
                    reco = reco.getSiguiente();
                else
                    reco = reco.getAnterior();
            }
            System.out.println("Eliminado del Juego el Doctor "+reco.getNombre());
            return reco;
        }
        
    

}
