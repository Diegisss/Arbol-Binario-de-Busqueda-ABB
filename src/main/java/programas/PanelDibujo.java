package programas;

import clases.ArbolBinario;
import clases.Nodo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author diego jaimes escobar
 */
public class PanelDibujo extends javax.swing.JPanel {

    private ArbolBinario arbolito;
    private String resaltar = "";
    /**
     * Creates new form PanelDibujo
     */
    public PanelDibujo() {
        initComponents();
        this.arbolito = new ArbolBinario();
        this.setBackground(Color.WHITE);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(
        new Color(0x000000), 4, true // color, grosor, redondeado
        ));//HAy tanto aqui como en el constructor, pero los puse porque sino no dejaba
        //poner el borde en negrita
    }

    public PanelDibujo(ArbolBinario arbol) {
        initComponents();
        this.arbolito = arbol;
        this.setBackground(Color.WHITE);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(
        new Color(0x000000), 4, true // color, grosor, redondeado
        ));
    }

    public void setArbol(ArbolBinario arbol) {
        this.arbolito = arbol;
        repaint();
    }

    public ArbolBinario getArbol() {
        return arbolito;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        dibujarArbol(g2);
    }
     public void resaltarRuta(String ruta) {
        this.resaltar = ruta;
        repaint();
    }
    
    public void limpiar(){
        this.resaltar = "";
        repaint();
    }

    // Aqui calculamos el ancho de el subarbol
    private int calcularAnchoSubarbol(Nodo nodo) {
        if (nodo == null) { // aplicamos recursividad, si es null, no existe
            return 0;
        }
        int anchoIzq = calcularAnchoSubarbol(nodo.izq);
        int anchoDer = calcularAnchoSubarbol(nodo.der);
        return Math.max(1, anchoIzq + anchoDer); //Si no hay hijos pues retorna 1 y si si pues suma los anchos
    }

    private void dibujarArbol(Graphics2D g) {
        //Pinto mi panel de blanco
        g.setColor(new Color(0xCCFFCC));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //Si no existe el arbol que agregue de color rojo ARBOL VACIO
        if (arbolito == null || arbolito.getRaiz() == null) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            String cad = "ARBOL VACIO";
            FontMetrics k = g.getFontMetrics();
            int ancho = k.stringWidth(cad);
            int x = (getWidth() - ancho) / 2;
            int y = getHeight() / 2;
            g.drawString(cad, x, y);
            return;
        }

        // Aqui llamamos al metodo y y centramos horizontalmente y lo ponemos en una posicion de 50
        dibujarNodoBalanceadoMejorado(g, arbolito.getRaiz(), getWidth() / 2, 50);

        //Se coloca la ALTURA y PESO en la esquina superior izquierda
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        String obtenido = "ALTURA: " + arbolito.altura() + " | PESO: " + arbolito.peso();
        g.drawString(obtenido, 10, 20);
    }

    // Aqui se controla todo para que no se haga un desorden
    private void dibujarNodoBalanceadoMejorado(Graphics2D g, Nodo nodo, int x, int y) {
        //Si no existe el nodo no dibujamos
        if (nodo == null) {
            return;
        }

        int r = 20;
        int diam = r * 2;
        int espacioVertical = 65; //Este es para la separacion de niveles en el arbolito

        // Esto es lo que hace que los nodos no choquen o se amontonen
        int anchoIzq = calcularAnchoSubarbol(nodo.izq);
        int anchoDer = calcularAnchoSubarbol(nodo.der);

        int separacionBase = 40;
        //entre mas ancho mas se separan las hojas e hijos
        int offsetIzq = Math.max(anchoIzq * separacionBase, 50);
        //50 para que no choquen entre ellos
        int offsetDer = Math.max(anchoDer * separacionBase, 50);

        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(2));

        // Si existe el nodo izq pues calculamos la posicion, se mueve a la izquierda y baja un nivel
        if (nodo.izq != null) {
            int xH = x - offsetIzq;
            int yH = y + espacioVertical;

            // Importante para calcular la direccion del niño respecto a su papa
            double angulo = Math.atan2(yH - y, xH - x);
            //Punto donde sale la linea, sale del borde
            int x0 = (int) (x + r * Math.cos(angulo));
            int y0 = (int) (y + r * Math.sin(angulo));
            //Punto donde llega al borde
            int x1 = (int) (xH - r * Math.cos(angulo));
            int y1 = (int) (yH - r * Math.sin(angulo));
            
            //Se dibuja la linea hacia el hijo
            g.drawLine(x0, y0, x1, y1);
            //Dibuja el subarbol izq con las mismas reglas
            dibujarNodoBalanceadoMejorado(g, nodo.izq, xH, yH);
        }

        //Aplica lo mismo que con el izquierdo
        if (nodo.der != null) {
            int xH = x + offsetDer;
            int yH = y + espacioVertical;

            double angulo = Math.atan2(yH - y, xH - x);

            int x0 = (int) (x + r * Math.cos(angulo));
            int y0 = (int) (y + r * Math.sin(angulo));
            int x1 = (int) (xH - r * Math.cos(angulo));
            int y1 = (int) (yH - r * Math.sin(angulo));

            g.drawLine(x0, y0, x1, y1);
            dibujarNodoBalanceadoMejorado(g, nodo.der, xH, yH);
        }
        
        String Nodo = nodo.dato.toString();
        boolean ruta = resaltar.contains(Nodo);
        boolean finalR = resaltar.endsWith(Nodo);
        
        if (finalR || ruta) {
            g.setColor(new Color(0xFFD700)); //amarillo para finalR
        } else {
            g.setColor(new Color(0xF2F0E9)); // Color que ya traen por defecto (no blancos)
        }
        g.fillOval(x - r, y - r, diam, diam);

        // BORDE del nodo
        if (finalR || ruta) {
            g.setColor(new Color(0xFF6B35)); //borde de color naranja
            g.setStroke(new BasicStroke(4)); // bordeado un poco mas ancho para el nodo final
        } else {
            g.setColor(Color.RED);           // Bordeado para los nodos normales
            g.setStroke(new BasicStroke(3)); // bordeado normalito
        }
        g.drawOval(x - r, y - r, diam, diam);

        // esto es para centrar la letra o el texto
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        String texto = nodo.dato.toString();
        //Obtiene las medidas de la fuente
        FontMetrics fm = g.getFontMetrics();
        //calcula el ancho del texto
        int ancho = fm.stringWidth(texto);

        int textoX = x - ancho / 2; //Lo centra
        int textoY = y + 4; //lo baja un poquito

        g.drawString(texto, textoX, textoY);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
