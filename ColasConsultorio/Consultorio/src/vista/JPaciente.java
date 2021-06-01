package vista;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Paciente;


public class JPaciente extends JPanel
	{

		private static final long serialVersionUID = 1L;
		private JLabel lblNombre;
		private JLabel lblTiempo;

		public JPaciente(Paciente p)
			{

				if (p != null)
					{

						JLabel nombre = new JLabel("Nombre :");
						add(nombre);

						lblNombre = new JLabel(p.getNombre());
						add(lblNombre);

						JLabel tiempo = new JLabel("Tiempo :");
						add(tiempo);

						lblTiempo = new JLabel(Integer.toString(p.getTiempoAtencion()));
						add(lblTiempo);

						JLabel lblIcono = new JLabel(p.getIcono());
						add(lblIcono);
					} else
					{

						JLabel mensaje = new JLabel("No hay nadie en cola");
						add(mensaje);
					}
			}
	}
