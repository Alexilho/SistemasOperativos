package vista;

import javax.swing.JOptionPane;


public class Vista
	{
		VentaPrincipal ventaPrincipal = null; 
		NuevoPacientePanel nuevoPacientePanel = null; 
		
		public Vista()
			{
			ventaPrincipal = new VentaPrincipal(); 
			 nuevoPacientePanel = new NuevoPacientePanel(); 
			}
		
		
		public void showMensage(String message ){
			
			JOptionPane.showMessageDialog(null, message);
		}

		public VentaPrincipal getVentaPrincipal()
			{
				return ventaPrincipal;
			}

		public void setVentaPrincipal(VentaPrincipal ventaPrincipal)
			{
				this.ventaPrincipal = ventaPrincipal;
			}

		public NuevoPacientePanel getNuevoPacientePanel()
			{
				return nuevoPacientePanel;
			}

		public void setNuevoPacientePanel(NuevoPacientePanel nuevoPacientePanel)
			{
				this.nuevoPacientePanel = nuevoPacientePanel;
			}
		
		
		
	}
