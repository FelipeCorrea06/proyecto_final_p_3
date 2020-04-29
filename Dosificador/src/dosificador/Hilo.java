/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dosificador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lfcr0843
 */
public class Hilo extends Thread {

    public Hilo() {
        super();
    }

    public void run() {

        String rutaDosificador = "C:\\Users\\lfcr0843\\Documents\\Proyecto final programacion 3\\Proyectofinal\\src\\DocumentosDosificador";
        String rutaLlegada = "C:\\Users\\lfcr0843\\Documents\\Proyecto final programacion 3\\Proyectofinal\\src\\DocumentoLlegda\\CarpetaComun";
        while (true) {
            File carpeta = new File(rutaDosificador);
            File[] listado = carpeta.listFiles();
            if (listado == null || listado.length == 0) {
                System.out.println("No hay elementos dentro de la carpeta actual");
                return;
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < listado.length; i++) {
                    try {
                        
                        Date fecha = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
                        InputStream in = new FileInputStream(listado[i]);
                        String nombre = listado[i].getName();
                        nombre = nombre.replace(".", formatter.format(fecha) + ".");
                        File archivoNuevo = new File(nombre);
                        OutputStream out = new FileOutputStream(rutaLlegada + "\\" + archivoNuevo);

                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    } 
                }
            }
        }
    }

}
