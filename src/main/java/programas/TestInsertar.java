/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
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
