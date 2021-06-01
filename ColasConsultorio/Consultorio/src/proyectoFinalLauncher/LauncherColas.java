package proyectoFinalLauncher;

import modelo.Modelo;
import controlador.Controller;
import vista.Vista;

public class LauncherColas
	{
		public static void main(String[] args)
			{
				new Controller(new Vista(), new Modelo());
			}
	}
