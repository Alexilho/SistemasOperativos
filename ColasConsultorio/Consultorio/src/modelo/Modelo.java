package modelo;

public class Modelo
	{

		private ColaPacientes cola;

		public Modelo()
			{
				cola = new ColaPacientes();
			}

		public ColaPacientes getCola()
			{
				return cola;
			}

		public void setCola(ColaPacientes cola)
			{
				this.cola = cola;
			}
	}
