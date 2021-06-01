package vista;



import javax.swing.JLabel;

import modelo.ManejadorFechas;



public class WigetReloj extends Thread
{
	private JLabel	lblFechaHora;

	public WigetReloj(JLabel lblFechaHora2)
	{

		lblFechaHora = new JLabel();
		this.lblFechaHora = lblFechaHora2;

	}

	@Override
	public void run()
	{
		while (true)
		{
			{

				ManejadorFechas fechaHora = new ManejadorFechas();

				lblFechaHora.setText(ManejadorFechas.getHoraActual() + " " + fechaHora.getFechaActual());
				try
				{
					sleep(1000);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}
