package vista;


import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import modelo.Nombres;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NuevoPacientePanel extends JFrame {

    int MIN_TURNO = 1;
    int MAX_TURNO = 10;
    
    String[] tiposConsulta =new String[]{"Herido", "Pediatria", "Dolor Cabeza", "Chequeo"};

    private static final long serialVersionUID = 1L;
    private JButton btnEncolar;
    private JSpinner jsTimer;
    private JTextField txtNombre;
    private JLabel lblMotivo;
    private JComboBox<?> comboBox;
    private JLabel lblImageselecionada;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public NuevoPacientePanel() {

        setTitle("Nuevo Paciente ");

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblNombre = new JLabel("Nombre ");
        getContentPane().add(lblNombre);

        txtNombre = new JTextField(30);
        txtNombre.setHorizontalAlignment(JTextField.CENTER);
        getContentPane().add(txtNombre);

        JLabel lblTiempo = new JLabel("Tiempo ");
        getContentPane().add(lblTiempo);

        jsTimer = new JSpinner();
        jsTimer.setModel(new SpinnerNumberModel(1, 1, 45, 1));
        getContentPane().add(jsTimer);

        lblMotivo = new JLabel("Motivo");
        getContentPane().add(lblMotivo);

        comboBox = new JComboBox(new DefaultComboBoxModel(tiposConsulta));
        getContentPane().add(comboBox);

        lblImageselecionada = new JLabel("");
        getContentPane().add(lblImageselecionada);

        btnEncolar = new JButton("Encolar");
        getContentPane().add(btnEncolar);

        setLocationRelativeTo(null);;
        pack();
        
        Limpiar();
    }

    public void addComboxListener(ActionListener listenerForCbox) {

        comboBox.addActionListener(listenerForCbox);

    }

    public void addBtnEncolarListener(ActionListener listenerForEncolar) {

        btnEncolar.addActionListener(listenerForEncolar);
    }

    public JSpinner getJsTimer() {
        return jsTimer;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JComboBox<?> getComboBox() {
        return comboBox;
    }

    public void setLblImagenSelecionada(String path) {
        this.lblImageselecionada.setText("Motivo : " + path);
        this.lblImageselecionada.setIcon(new ImageIcon("Imagenes/" + path + ".png"));
        pack();

    }

    public void Limpiar() {
        txtNombre.setText(new Nombres().generarnombre());
        jsTimer.setValue(this.generarNumero(MIN_TURNO, MAX_TURNO));
        //comboBox.setSelectedItem("");
        comboBox.setSelectedIndex(this.generarNumero(0, 3));
        lblImageselecionada.setIcon(null);
        

    }

    public int generarNumero(int inicio, int fin) {
        //Random ran= new Random();
        //int num=Random.classran.nextInt(fin)+inicio;

        int valorEntero = (int) Math.floor(Math.random() * (fin - inicio + 1) + inicio);
        return valorEntero;
    }
}
