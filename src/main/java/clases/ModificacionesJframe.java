package clases;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 * @author diego jaimes escobar
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
