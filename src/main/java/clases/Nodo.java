package clases;

/**
 *
 * @author diego jaimes escobar
 */
public class Nodo {
    public Object dato;
    //Los cambie a publicos pero eran protegidos
    public Nodo izq, der;
    
    public Nodo (Object dato){
        this.dato = dato;
        //izq = null;
        //der = null;
    }
    
    public Nodo (Object dato, Nodo izq, Nodo der){
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public Object getDato() {
        return dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public Nodo getDer() {
        return der;
    }
    
    @Override
    public String toString(){
        return "" + dato + "(" + izq + ", " + der + ")";
    }
    
    public void insertar(Object insertar){
        if (insertar.toString().compareTo(this.dato.toString()) < 0){
            if (this.izq == null) //Subarbol vacio
                this.izq = new Nodo(insertar);
            else //avanza por la derecha
                izq.insertar(insertar);
        }
        else if (insertar.toString().compareTo(this.dato.toString()) > 0){
            if (this.der == null)
                this.der = new Nodo(insertar);
            else
                der.insertar(insertar);
        }
    }
}
