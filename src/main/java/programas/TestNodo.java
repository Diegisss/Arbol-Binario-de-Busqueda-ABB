package programas;
import clases.Nodo;
/**
 *
 * @author diego jaimes escobar
 */
public class TestNodo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Nodo a, b, c;
        a = new Nodo("Erick");
        b = new Nodo("Yael");
        c = new Nodo("Diego", a, b);
        
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        
        Nodo d = new Nodo("ITZ", c, null);
        System.out.println("d = " + d);
    }
    
}
