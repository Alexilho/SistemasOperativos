package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Data;

public class Ventana extends JFrame {

    JLabel etiquetaTiempo;

    private JLabel lblId;
    private JTextField txtId;
      private JLabel lblNombre;
    private JTextField txtNombre;

    private JLabel lblTRafaga;
    private JTextField txtTRafaga;
    
    private JLabel lblPrioridad;
    private JTextField txtPrioridad;

    private JButton btnAgregar;
    private JButton btnGenerar;
    private JButton btnEjecutar;
    private JPanel panelOpciones;
    //private JButton btnAtender;

    private static final Object[] columnaNombres = {"Id", "Proceso", "T. Llegada", "T. Rafaga", "Prioridad", "T. Comienzo", "T. Final", "T. Retorno", "T. Espera"};
    private JTable tabla;
    private DefaultTableModel dtmProcesos;
    private JScrollPane jspProcesos;

    private Lienzo lienzo;
    private JPanel panelLienzo;

    private Data d;

    public Ventana() {
        setLayout(new BorderLayout(0, 0));
        setSize(1200, 750);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Planificación Prioridad");

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
        
        lblPrioridad = new JLabel("Prioridad: ");
        panelOpciones.add(lblPrioridad);
        
        txtPrioridad = new JTextField();
        txtPrioridad.setColumns(5);
        panelOpciones.add(txtPrioridad);

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

        return panelOpciones;
    }

    public void initComponents() {
        add(panelOpciones(), BorderLayout.NORTH);

        dtmProcesos = new DefaultTableModel(null, columnaNombres);
        tabla = new JTable(dtmProcesos);
        tabla.setModel(dtmProcesos);
        jspProcesos = new JScrollPane(tabla);

        lienzo = new Lienzo(false);
        lienzo.setSize(getWidth(), getHeight());

        add(jspProcesos, BorderLayout.SOUTH);
        add(lienzo, BorderLayout.CENTER);
        // FIXME : dsabilitar en pruebas pack();
    }

    public void mostarMenssaje(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void rellenarDatos(int id, String nom, int tLlegdada, int tRafaga) {
        txtId.setText(Integer.toString(id));
        txtNombre.setText(nom);
        //txtTLlegada.setText(Integer.toString(tLlegdada));
        txtTRafaga.setText(Integer.toString(tRafaga));
    }

    public void refrescar(Object[][] datos, boolean  isRunnable) {
        dtmProcesos.setRowCount(0);
        dtmProcesos.setDataVector(datos, columnaNombres);
        if(isRunnable){
            getLienzo().setData(datos);
            getLienzo().setEjecuta(true);
            getLienzo().repaint();
        }
    }
    public  void actualizarProcesoActual(int t, int p, int all ) {
            String tiempo ="  Rafaga: " + t  + "Procesos : " + p + " de " + all;
            etiquetaTiempo.setText(tiempo);
    }
    public void actualizarTiempoActual(int t){
        String tiempo="Tiempo: "+t;
        etiquetaTiempo.setText(tiempo);
    }

    public Lienzo getLienzo() {
        return lienzo;
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

    public JTextField getTxtPrioridad() {
        return txtPrioridad;
    }
    

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
}
