/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 * @author diego123
 */
public class ModificacionesJframe {

    private ArbolBinario arbolito;

    public ModificacionesJframe(ArbolBinario arbolito) {
        this.arbolito = arbolito;
    }

    public String obtenerPreorden() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream viejo = System.out;
        System.setOut(ps);
        arbolito.preorden();
        System.out.flush();
        System.setOut(viejo);
        return baos.toString();
    }
    public String obtenerPostorden() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream viejo = System.out;
        System.setOut(ps);
        arbolito.postorden();
        System.out.flush();
        System.setOut(viejo);
        return baos.toString();
    }
    public String obtenerInorden() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream viejo = System.out;
        System.setOut(ps);
        arbolito.inorden();
        System.out.flush();
        System.setOut(viejo);
        return baos.toString();
    }

}
