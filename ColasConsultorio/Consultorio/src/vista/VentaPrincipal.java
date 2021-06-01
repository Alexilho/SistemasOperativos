package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import modelo.Paciente;
import javax.swing.ScrollPaneConstants;

public class VentaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JMenuItem itmInfo;
    private JMenuItem itmReinicio;
    private JButton btnNewPaciente;
    private JPanel paneDoctor;
    private JPanel paneFila;
    private JScrollPane JsFila;
   

    ArrayList<JPaciente> JpanePaciente;

    /* constructor */
    public VentaPrincipal() {

        JpanePaciente = new ArrayList<>();
        /* layout */
        setContentPane(new JPanel(new BorderLayout()));

        getContentPane().add(mnBar(), BorderLayout.NORTH);
        getContentPane().add(butonNewPaciente(), BorderLayout.SOUTH);

        getContentPane().add(paneCentro(), BorderLayout.CENTER);

        /* caracteristcas de jpane */
        setTitle("Consultorio");
        setSize(300, 600);
        // setResizable(false);
        setLocationRelativeTo(null);;
        setVisible(true);
        
       

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }

    /* metodos doctor */
    public void pasarConsultorio(Paciente p) {
        paneDoctor.removeAll();
        paneDoctor.add(new JPaciente(p));
        paneDoctor.updateUI();

    }

    /* metodos fila */
    public void actualizar() {

        paneFila.removeAll();

        for (JPaciente jPaciente : JpanePaciente) {

            paneFila.add(jPaciente);

        }
        paneFila.updateUI();
        JpanePaciente.clear();

    }

    public void indexarJpanePacientes(Paciente[] p, int numPacientes) {
        for (int i = 0; i <= numPacientes; i++) {
            System.out.println("encola");

            JpanePaciente.add(new JPaciente(p[i]));
        }

        actualizar();

        JpanePaciente.clear();

    }

    /* Creadores de componentes */
    private JButton butonNewPaciente() {
        btnNewPaciente = new JButton("Nuevo Paciente");
        return btnNewPaciente;
    }

    public JLabel paneReloj() {

        JLabel lblWiget = new JLabel("               ");
        lblWiget.setForeground(Color.BLUE);
        WigetReloj hiloReloj = new WigetReloj(lblWiget);
        hiloReloj.start();
        return lblWiget;
    }

    private JPanel paneCentro() {

        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        
         JsFila = new JScrollPane(paneFila());
         JsFila.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.add(paneDoctor());
        pane.add(paneReloj());
        
        
        
        pane.add(JsFila);
        

        return pane;
    }

    private JPanel paneDoctor() {

        paneDoctor = new JPanel();

        paneDoctor.setBorder(new TitledBorder("Consultorio"));

        JLabel doctor = new JLabel("Doctor");
        paneDoctor.add(doctor);

        return paneDoctor;
    }

    private JPanel paneFila() {

        paneFila = new JPanel();

        paneFila.setBorder(new TitledBorder("Fila"));
        paneFila.setLayout(new BoxLayout(paneFila, BoxLayout.Y_AXIS));
        paneFila.add(new JPaciente(null));

        return paneFila;
    }

    private JMenuBar mnBar() {

        JMenuBar mnBar = new JMenuBar();
        JMenu mnInfo = new JMenu("Informacion");

        itmInfo = new JMenuItem("Acerca");
        itmReinicio = new JMenuItem("siguiente Paciente");

        mnInfo.add(itmReinicio);
        mnInfo.add(itmInfo);

        mnBar.add(mnInfo);

        return mnBar;
    }

    /* listenners */
    public void addBtnNewPacinteListener(ActionListener listenerForNewPaciente) {

        btnNewPaciente.addActionListener(listenerForNewPaciente);
    }

    public void addItmInfo(ActionListener listenerForInfo) {

        itmInfo.addActionListener(listenerForInfo);
    }

    public void addItmReiniciar(ActionListener listenerForRestart) {

        itmReinicio.addActionListener(listenerForRestart);
    }

    public void indexarJpanePacientes(Paciente p) {

        JpanePaciente.add(new JPaciente(p));

    }

}
