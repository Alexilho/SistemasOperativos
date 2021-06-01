package modelo;

public class Nodo

	{
		public Nodo siguiente;
		public Paciente p;

		public Nodo(Paciente p,Nodo enlace)
			{

				this.p = p;
				this.siguiente = enlace;
			}
	}