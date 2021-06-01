package multicolas.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.table.DefaultTableModel;
import multicolas.modelo.DataRR;

public class Ventana extends JFrame {

    JLabel etiquetaTiempo;

    private JLabel lblId;
    private JTextField txtId;
    private JLabel lblNombre;
    private JTextField txtNombre;

    private JLabel lblTRafaga;
    private JTextField txtTRafaga;

    private JLabel lblQuantum;
    //private JTextField txtPrioridad;

    private JButton btnAgregar;
    private JButton btnGenerar;
    private JButton btnEjecutar;
    private JPanel panelOpciones;
    //private JButton btnAtender;

    private static final Object[] columnaNombres = {"Id", "Proceso", "T. Llegada", "T. Rafaga", "Prioridad", "T. Comienzo", "T. Final", "T. Retorno", "T. Espera"};
    private JTable tabla1,tabla2,tabla3;
    public DefaultTableModel dtmProcesos1,dtmProcesos2,dtmProcesos3;
    private JScrollPane jspProcesos1, jspProcesos2,jspProcesos3;

    public LienzoRR lienzo1, lienzo2, lienzo3 ;
    private JPanel panelLienzo;
    private JPanel panelTabla;
    private JScrollPane jspProcesosG;
    
    private DataRR d;
   

    public Ventana() {
        setLayout(new BorderLayout(0, 0));
        setSize(1200, 750);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Planificación Multicolas");

        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    public JPanel panelOpciones() {
        panelOpciones = new JPanel();

        lblId = new JLabel("Id: ");
        panelOpciones.add(lblId);

        txtId = new JTextField();
        txtId.setColumns(5);
        panelOpciones.add(txtId);

        lblNombre = new JLabel("Nombre: ");
        panelOpciones.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        panelOpciones.add(txtNombre);

        /*lblTLlegada = new JLabel("Tiempo Llegada: ");
        panelOpciones.add(lblTLlegada);

        txtTLlegada = new JTextField();
        txtTLlegada.setColumns(5);
        panelOpciones.add(txtTLlegada);*/
        lblTRafaga = new JLabel("Tiempo Ráfaga: ");
        panelOpciones.add(lblTRafaga);

        txtTRafaga = new JTextField();
        txtTRafaga.setColumns(5);
        panelOpciones.add(txtTRafaga);

        /*txtPrioridad = new JTextField();
        txtPrioridad.setColumns(5);
        panelOpciones.add(txtPrioridad);*/
        btnGenerar = new JButton("Generar");
        panelOpciones.add(btnGenerar);

        btnAgregar = new JButton("Agregar");
        panelOpciones.add(btnAgregar);

        btnEjecutar = new JButton("Ejecutar");
        panelOpciones.add(btnEjecutar);

        etiquetaTiempo = new JLabel();
        etiquetaTiempo.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        etiquetaTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTiempo.setText("  Tiempo: Nada Ejecutado ");
        panelOpciones.add(etiquetaTiempo);

        lblQuantum = new JLabel("Quantum: ");
        panelOpciones.add(lblQuantum);

        return panelOpciones;

    }

    public void initComponents() {
        add(panelOpciones(), BorderLayout.NORTH);

        lienzo1 = new LienzoRR(false);
        lienzo1.setSize(getWidth()/3, getHeight());
        lienzo1.setName("SRTF");

        lienzo2 = new LienzoRR(false);
        lienzo2.setSize(getWidth()/3, getHeight());
        lienzo2.setName("Round Robin");

        lienzo3 = new LienzoRR(false);
        lienzo3.setSize(getWidth()/3, getHeight());
        lienzo3.setName("Prioridad");

        
        
        panelLienzo = new JPanel();
        panelLienzo.setLayout(new BoxLayout(panelLienzo,BoxLayout.LINE_AXIS));
        panelLienzo.add(lienzo1);
        panelLienzo.add(lienzo2);
        panelLienzo.add(lienzo3);
        
        
        
        dtmProcesos1 = new DefaultTableModel(null, columnaNombres);
        tabla1 = new JTable(dtmProcesos1);
        tabla1.setModel(dtmProcesos1);
        
        dtmProcesos2 = new DefaultTableModel(null, columnaNombres);
        tabla2 = new JTable(dtmProcesos2);
        tabla2.setModel(dtmProcesos2);
        
        dtmProcesos3 = new DefaultTableModel(null, columnaNombres);
        tabla3 = new JTable(dtmProcesos3);
        tabla3.setModel(dtmProcesos3);
        
        
        panelTabla = new JPanel();
        panelTabla.setLayout(new BoxLayout(panelTabla,BoxLayout.LINE_AXIS));
        
        
        jspProcesos1 = new JScrollPane(tabla1);
        panelTabla.add(jspProcesos1);
        jspProcesos2 = new JScrollPane(tabla2);
        panelTabla.add(jspProcesos2);
        jspProcesos3 = new JScrollPane(tabla3);
        panelTabla.add(jspProcesos3);
         
        panelTabla.setBackground(Color.red);
        
        
         //jspProcesosG = new JScrollPane(panelLienzo);
         //jspProcesosG.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(panelLienzo, BorderLayout.CENTER);
        panelLienzo.setPreferredSize(new Dimension(500, 200));
        add(panelTabla, BorderLayout.SOUTH);
        // FIXME : dsabilitar en pruebas pack();
    }

    public void mostarMenssaje(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public String selccionarItem(String men, String[] items) {

        JList list = new JList(items);
        ListDialog dialog = new ListDialog(men, list);
        //dialog.setOnOk(e -> System.out.println("Chosen item: " + dialog.getSelectedItem()));
        dialog.show();
        return (String) dialog.getSelectedItem();
    }

    public void rellenarDatos(int id, String nom, int tRafaga) {
        txtId.setText(Integer.toString(id));
        txtNombre.setText(nom);
        txtTRafaga.setText(Integer.toString(tRafaga));
    }

    public void refrescar(Object[][] datos, LienzoRR l, boolean isRunnable) {
        
        DefaultTableModel m = null; 
        if (l.equals(lienzo1)) {
            m=dtmProcesos1;
        }
        if (l.equals(lienzo2)) {
            m=dtmProcesos2;
        }
        if (l.equals(lienzo3)) {
            m=dtmProcesos3;
        }
        
        m.setRowCount(0);
        m.setDataVector(datos, columnaNombres);
        
        if (isRunnable) {
            l.setData(datos);
            l.setEjecuta(true);
            l.repaint();
        }
    }

    public void actualizarProcesoActual(int t, int p, int all) {
        String tiempo = "  Rafaga: " + t + "Procesos : " + p + " de " + all;
        etiquetaTiempo.setText(tiempo);
    }

    public void actualizarTiempoActual(int t) {
        String tiempo = "Tiempo: " + t;
        etiquetaTiempo.setText(tiempo);
    }
 
    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtTRafaga() {
        return txtTRafaga;
    }

    /*public JTextField getTxtPrioridad() {
        return txtPrioridad;
    }*/
    public void changeRunableBtn(boolean b) {
        //btnAgregar.setEnabled(!b);
        if (b) {
            btnEjecutar.setText("Pausar");
        } else {
            btnEjecutar.setText("Ejecutar");
        }
    }

    public void addBtnAgregar(ActionListener l) {
        btnAgregar.addActionListener(l);
    }

    public void addBtnGenerar(ActionListener l) {
        btnGenerar.addActionListener(l);
    }

    public void addBtnEjecutar(ActionListener l) {
        btnEjecutar.addActionListener(l);
    }

    public void setLblQuantum(int Quantum) {
        this.lblQuantum.setText("Quantum: " + Quantum);
    }

}
