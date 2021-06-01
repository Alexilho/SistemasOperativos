package modelo;


import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Paciente
	{
		String nombre;
		int tiempoAtencion;
		Icon icono;

		public Paciente(String nombre,int tiempo,String ruta)
			{

				setNombre(nombre);
				setTiempoAtencion(tiempo);
				setIcono(new ImageIcon(ruta));
			}

		public String getNombre()
			{
				return nombre;
			}

		public void setNombre(String nombre)
			{
				this.nombre = nombre;
			}

		public int getTiempoAtencion()
			{
				return tiempoAtencion;
			}

		public void setTiempoAtencion(int tiempoAtencion)
			{
				this.tiempoAtencion = tiempoAtencion;
			}

		public Icon getIcono()
			{
				return icono;
			}

		public void setIcono(Icon icono)
			{
				this.icono = icono;
			}

	}
