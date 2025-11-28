package clases;

/**
 *
 * @author diego123
 */
public class ArbolBinario {

    private Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }
    
    public Nodo getRaiz(){
        return raiz;
    }

    public void insertar(Object elDato) {
        if (raiz == null) {
            raiz = new Nodo(elDato);
        } else {
            raiz.insertar(elDato);
        }
    }
    
    public Busqueda BuscarNodo(Object dato) throws Exception{
        if (raiz == null) throw new Exception("No existe el arbol");
        return BuscarNodo(raiz, dato, "", 0);
    }
    
    public Busqueda BuscarNodo(Nodo nodo, Object dato, String ruta, int nivel) throws Exception{
        if (nodo == null){
            throw new Exception("Dato " + dato + " no se encontro");
        }
    
        //Si ruta esta vacia entonces dame los datos del nodo (el toString) sino muestrame la ruta + los datos
        String RutaN = ruta.isEmpty() ? nodo.dato.toString(): ruta + " " + nodo.dato.toString();
        int c = dato.toString().compareTo(nodo.dato.toString());
        
        if (c == 0) return new Busqueda(RutaN, nivel);
        else if (c < 0) return BuscarNodo(nodo.izq, dato, RutaN, nivel + 1);
        else return BuscarNodo(nodo.der, dato, RutaN, nivel + 1);
    }

    //recorrer PREorden
    private void preorden(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        System.out.printf("%s ", nodo.dato); //raiz
        preorden(nodo.izq);
        preorden(nodo.der);
    }

    public void preorden() {
        System.out.println("RECORRIDO EN PREORDEN: ");
        this.preorden(raiz);
        System.out.println();

    }

    //recorrer POSTorden
    private void postorden(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        postorden(nodo.izq);
        postorden(nodo.der);
        System.out.printf("%s ", nodo.dato); //raiz
    }

    public void postorden() {
        System.out.println("RECORRIDO EN POSTORDEN:");
        this.postorden(raiz);
        System.out.println();
    }

    //recorrer INorden
    private void inorden(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        inorden(nodo.izq);
        System.out.printf("%s ", nodo.dato);
        inorden(nodo.der);
    }

    public void inorden() {
        System.out.println("RECORRIDO EN INORDEN:");
        this.inorden(raiz);
        System.out.println();
    }

    public void mostrar(Nodo nodo, int profundidad) {
        for (int i = 0; i < profundidad; i++) {
            System.out.println("\t");
        }
        if (nodo == null) {
            System.out.println("---->" + nodo);
            return;
        }
        System.out.printf("----> %s\n", nodo.dato);
        mostrar(nodo.izq, profundidad + 1);
        mostrar(nodo.der, profundidad + 1);
    }

    public void mostrar() {
        System.out.println("NIVELES DEL ARBOL");
        this.mostrar(raiz, 0);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (isLeaf(nodo)) {
            return 1;
        }
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    public int altura() {
        return altura(raiz);
    }

    protected Nodo eliminar(Nodo raizSub, Object dato) throws Exception {
        if (raizSub == null) {
            throw new Exception("No se encontro ningun dato");
        } else if (dato.toString().compareTo(raizSub.dato.toString()) < 0) {
            Nodo iz = eliminar(raizSub.izq, dato);
            raizSub.izq = iz;
        } else if (dato.toString().compareTo(raizSub.dato.toString()) > 0) {
            Nodo dr = eliminar(raizSub.der, dato);
            raizSub.der = dr;
        } else {
            Nodo q = raizSub;
            if (q.izq == null) {
                raizSub = q.der;
            } else if (q.der == null) {
                raizSub = q.izq;
            } else {
                q = reemplazar(q);
            }
            q = null;
        }
        return raizSub;
    }

    public void eliminar(Object valor) throws Exception {
        raiz = eliminar(raiz, valor);
    }

    private Nodo reemplazar(Nodo act) {
        Nodo sucesor, padre;
        padre = act;
        sucesor = act.izq;

        while (sucesor.der != null) {
            padre = sucesor;
            sucesor = sucesor.der;
        }
        act.dato = sucesor.dato;
        if (padre == act)
            padre.izq = sucesor.izq;
        else 
            padre.der = sucesor.izq;
        return sucesor;
    }
    
    public int peso(){
        return peso(raiz);
    }
    
    private int peso(Nodo s){
        if (s == null) return 0;
        if (isLeaf(s)) return 1;
        return 1 + peso(s.izq) + peso(s.der);
    }
    
    public boolean isRoot(Nodo nodo){
        return raiz == nodo;
    }
    
    public boolean isLeaf(Nodo nodo){
        return nodo.izq == null && nodo.der == null;
    }
    
    public boolean isInternal(Nodo nodo){
        return !isLeaf(nodo);
    }
    
    @Override
    public String toString(){
        return "" + raiz;
    }
    
    public void recorrer(){
        this.preorden();
        this.postorden();
        this.inorden();
    }

}
