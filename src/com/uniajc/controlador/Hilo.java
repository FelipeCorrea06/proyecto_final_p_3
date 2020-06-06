/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniajc.controlador;

import com.uniajc.modelo.ListaEnlazadaDoble;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lfcr0843
 */
public class Hilo extends Thread {

    public ListaEnlazadaDoble alta = new ListaEnlazadaDoble();
    public ListaEnlazadaDoble media = new ListaEnlazadaDoble();
    public ListaEnlazadaDoble baja = new ListaEnlazadaDoble();

    public Hilo() {
        super();
    }

    public void run() {

        while (true) {
            String nombre_archivo;
            if (!alta.ListaVacia() || !media.ListaVacia() || !baja.ListaVacia()) {
                if (!alta.ListaVacia()) {
                    nombre_archivo = alta.EliminarElementoDesdeLaCabeza();
                    Path origenPath = FileSystems.getDefault().getPath("src\\DocumentoXML\\XML_CANONICO\\" + nombre_archivo);
                    Path destinoPath = null;
                    if (!nombre_archivo.equals("")) {
                        String[] nombre_tipo_archivo = nombre_archivo.split("_");
                        switch (nombre_tipo_archivo[0]) {
                            case "SOLI":
                                destinoPath = FileSystems.getDefault().getPath("src\\DocumentoSalida\\OUT_SOLI\\" + nombre_archivo);
                                break;
                            case "SOLMAFI":
                                destinoPath = FileSystems.getDefault().getPath("src\\DocumentoSalida\\OUT_SOLMAFI\\" + nombre_archivo);
                                 try {
                            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            System.err.println("Hay un error al guardar el archivo final: "+e);
                        }
                                break;
                            case "SOLMAAC":
                                destinoPath = FileSystems.getDefault().getPath("src\\DocumentoSalida\\OUT_SOLMAAC\\" + nombre_archivo);
                                break;
                            case "SOLGRA":
                                destinoPath = FileSystems.getDefault().getPath("src\\DocumentoSalida\\OUT_SOLGRA\\" + nombre_archivo);
                                break;
                            case "SOLCREES":
                                destinoPath = FileSystems.getDefault().getPath("src\\DocumentoSalida\\OUT_SOLCREES\\" + nombre_archivo);
                                break;
                            case "SOLCANMA":
                                destinoPath = FileSystems.getDefault().getPath("src\\DocumentoSalida\\OUT_SOLCANMA\\" + nombre_archivo);
                                break;
                        }

                        try {
                            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            System.err.println("Hay un error al guardar el archivo final: "+e);
                        }
                    }
                }
            } else {
                break;
            }
        }

    }

    public void obtenerColas(ListaEnlazadaDoble alta, ListaEnlazadaDoble media, ListaEnlazadaDoble baja) {
        this.alta = alta;
        this.media = media;
        this.baja = baja;
    }

}
