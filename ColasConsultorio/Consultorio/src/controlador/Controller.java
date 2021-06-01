package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;
import modelo.Nodo;
import modelo.Paciente;
import vista.Vista;

public class Controller {

    private Vista theView;
    private Modelo theModel;

    public Controller(Vista theView, Modelo theModel) {
        this.theView = theView;
        this.theModel = theModel;

        theView.getVentaPrincipal().addBtnNewPacinteListener(new NewPacienteListner());
        theView.getVentaPrincipal().addItmInfo(new InfoListener());
        theView.getVentaPrincipal().addItmReiniciar(new ReiniciarListener());

        theView.getNuevoPacientePanel().addBtnEncolarListener(new EncolarPacienteListener());
        theView.getNuevoPacientePanel().addComboxListener(new CbListener());
    }

    public void actulizarFila() {

        Nodo a = theModel.getCola().getNodo(null);
        try {
            theView.getVentaPrincipal().indexarJpanePacientes(a.p);
        } catch (NullPointerException e) {
            // TODO: handle exception
        }

        a = theModel.getCola().getNodo(a);

        while (a != null) {
            theView.getVentaPrincipal().indexarJpanePacientes(a.p);

            Nodo aux = a;
            a = theModel.getCola().getNodo(aux);
        }
        theView.getVentaPrincipal().actualizar();

    }

    class NewPacienteListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theView.getNuevoPacientePanel().setVisible(true);
        }
    }

    class EncolarPacienteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = theView.getNuevoPacientePanel().getTxtNombre().getText();
            int tiempo = Integer.parseInt(String.valueOf(theView.getNuevoPacientePanel().getJsTimer()
                    .getValue()));
            String ruta = (String) theView.getNuevoPacientePanel().getComboBox().getSelectedItem();
            System.out.println(ruta);
            Paciente paciente = new Paciente(nombre, tiempo, "Imagenes/" + ruta + ".png");

            theModel.getCola().insertar(paciente);
            actulizarFila();
            theView.getNuevoPacientePanel().dispose();
            theView.getNuevoPacientePanel().Limpiar();
        }
    }

    class InfoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theView.showMensage("Autor Diego A Hernndez \n Colas, MVC e Inversion de depencias ");
        }
    }

    class ReiniciarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int TIMEATENCION = 3;
            Paciente p = theModel.getCola().removerFrente();
            theView.getVentaPrincipal().pasarConsultorio(p);

            actulizarFila();
            try {
                if (TIMEATENCION < p.getTiempoAtencion()) {
                    p.setTiempoAtencion(p.getTiempoAtencion() - TIMEATENCION);
                    theModel.getCola().insertar(p);
                }

            } catch (NullPointerException e2) {
                // TODO: handle exception
            }

        }

    }

    class CbListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String ruta = (String) theView.getNuevoPacientePanel().getComboBox().getSelectedItem();
            theView.getNuevoPacientePanel().setLblImagenSelecionada(ruta);
            theView.getNuevoPacientePanel().repaint();
        }

    }

}
