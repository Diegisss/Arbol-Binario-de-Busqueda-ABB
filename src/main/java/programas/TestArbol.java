
package programas;
import clases.ArbolBinario;

/**
 *
 * @author diego jaimes escobar
 */
public class TestArbol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolBinario unArbol = new ArbolBinario();
        for (String dato : args) {
            unArbol.insertar(dato);
        }
        System.out.println("" + unArbol);
        unArbol.preorden();
        unArbol.postorden();
        unArbol.inorden();
    }
    
}
