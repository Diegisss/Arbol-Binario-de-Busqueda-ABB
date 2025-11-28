package programas;
import clases.Nodo;
/**
 *
 * @author diego jaimes escobar
 */
public class TestInsertar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Nodo arbol = new Nodo("ISC");
        arbol.insertar("Diego");
        arbol.insertar("Angel");
        arbol.insertar("Cristobal");
        arbol.insertar("Luis");
        arbol.insertar("Kevin");
        arbol.insertar("Jose");
        
        System.out.println("arbol = " + arbol);
    }
    
}
