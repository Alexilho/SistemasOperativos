package multicolas.vista;

import java.awt.*;
import java.awt.Graphics;

public class LienzoRR extends Canvas {

    // lugar donde inicia la linea del grafico  
    private int i;

    private boolean ejecuta;
    private int x, y;
    private Object[][] data;

    private String name; 
    int factorAncho;
    
    // segementos en cuanto se va a divir el grafico
    int longitudGrafico = 40;

    public LienzoRR(Boolean isRun) {
        i = 35;
        this.x = i;
        this.y = 10;
        this.ejecuta = isRun;
    }

    // crea trazado de grafico 
    public void dibujarLinea(Graphics g) {

       
        factorAncho = (getWidth() - i) / longitudGrafico;

        g.setColor(Color.RED);
        g.drawLine(x, y, x + longitudGrafico * factorAncho, y);
        g.setColor(Color.black);
        int fontSize = (getWidth() < 1300) ? 8 : 11;
        Font fuente = new Font("Calibri", Font.BOLD, fontSize);
        g.setFont(fuente);

        // aniade segmentacion y nnumeracion 
        for (int i = 0; i <= longitudGrafico; i++) {
            g.setColor(Color.RED);
            g.drawLine(x + (i * factorAncho), y, x + (i * factorAncho), y + 10);
            g.setColor(Color.black);
            int fontOffset = (getWidth() < 1300) ? 2 : 5;
            String t = (i < 10) ? "  " + i : "" + i;
            g.drawString(t, x + (i * factorAncho) - fontOffset, y + 20);
        }
        y += 25;

        //dibuaj cuadricual guia
        for (int i = 0; i <= longitudGrafico; i++) {
            Graphics2D g2 = (Graphics2D) g;
            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{1, 5}, 0);
            g2.setColor(Color.LIGHT_GRAY);
            g2.setStroke(bs1);
            g2.drawLine(x + (i * factorAncho), y, x + (i * factorAncho), getHeight());
        }
        x = 10;
    }

    public void graficaUnProceso(Graphics g, String nombre, int tLlegada, int[] tComienzo, int[] tFinal) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.drawString("" + nombre, x, y + 10);

        g2.setColor(Color.blue);
        // BasicStroke bs = new BasicStroke(5); buscar si no sirve eliminar 
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{5, 5, 5}, 0);
        g2.setStroke(bs1);
        g2.drawLine(i + (factorAncho * tLlegada), y + 5, i + (factorAncho * tComienzo[0]), y + 5);

        //Deberia poner los tiempos de espera
        for (int j = 1; j < tComienzo.length; j++) {
            g2.drawLine(i + (factorAncho * tFinal[j - 1]), y + 5, i + (factorAncho * tComienzo[j]), y + 5);
        }

        BasicStroke bs2 = new BasicStroke(0);
        g2.setStroke(bs2);

        //Palito vertical de cuando llegó
        g2.drawLine(i + (factorAncho * tLlegada), y + 1, i + (factorAncho * tLlegada), y + 9);
        for (int j = 0; j < tComienzo.length; j++) {
            //Linea horizontal de ejecución
            g2.drawLine(i + (factorAncho * tComienzo[j]), y + 5, i + (factorAncho * tFinal[j]), y + 5);

            //Palito vertical de tiempo comienzo
            g2.drawLine(i + (factorAncho * tComienzo[j]), y + 1, i + (factorAncho * tComienzo[j]), y + 9);
            //Palito vertical del tiempo final
            g2.drawLine(i + (factorAncho * tFinal[j]), y + 1, i + (factorAncho * tFinal[j]), y + 9);
        }

        y += 10;
    }

    @Override
    public void paint(Graphics g) {
        x = i;
        y = 10;
        
      
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

          g.setColor(Color.black);
        int fontSize = (getWidth() < 1300) ? 20 : 31;
        Font fuente = new Font("Calibri", Font.BOLD, fontSize);
        g.setFont(fuente);
        
         g.drawString(name, 20 , 20 );
        
        y = 30;
        dibujarLinea(g);

        // si no se ha ejecuado nunca 
        if (ejecuta) {
            dibujaProcesos(g, data);
        }
    }

    public void dibujaProcesos(Graphics g, Object[][] procesos) {

        for (int i = 0; i < procesos.length; i++) {
            // FIXME evita que que no halla nada en el proceo 
            try {
                String[] comienza = procesos[i][5].toString().split(","), fina = procesos[i][6].toString().split(",");
                int[] tComienza = new int[comienza.length], tFin = new int[fina.length];
                for (int j = 0; j < comienza.length; j++) {
                    tComienza[j] = Integer.parseInt(comienza[j]);
                    tFin[j] = Integer.parseInt(fina[j]);
                }
                graficaUnProceso(g, (String) procesos[i][1], (int) procesos[i][2], tComienza, tFin);
            } catch (Exception e) {
            }

        }
    }

    public void setEjecuta(boolean ejecuta) {
        this.ejecuta = ejecuta;
    }

    public void setData(Object[][] d) {
        data = d;
    }
    public void setName(String n){
     name= n; 
    }
}
