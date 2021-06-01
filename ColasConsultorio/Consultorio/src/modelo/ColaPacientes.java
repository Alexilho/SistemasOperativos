package modelo;

public class ColaPacientes implements INodo {

    Nodo frente;
    Nodo ultimo;
    Nodo doctor;

    Paciente p;

    public ColaPacientes() {
        doctor= getDoctor();
        frente = ultimo = doctor;
        p = null;
    }

    @Override
    public void insertar(Paciente x) {
        
        Nodo nuevo;
        nuevo = new Nodo(x, doctor);
        
        if (this.isVacia()) {
          frente = nuevo;
          ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
    }

    @Override
    public Paciente removerFrente() {
        Paciente p = null;      
        boolean s = isVacia();
        if(s){
            System.out.println("Cola Vacia");
        }else{
          p = frente.p;
          frente = frente.siguiente;
        }
        return p;
    }

    public Nodo getNodo(Nodo apuntador) {
        {
            if (apuntador != null) {
                return apuntador.siguiente;
            }
            return frente;
        }
    }

    
    private Nodo getDoctor(){
        return ( doctor== null ) ? new Nodo(new Paciente("Doctor",0,"Bien"),null):doctor;
    }
      private boolean isVacia(){
        return  frente == doctor;
    }
}
