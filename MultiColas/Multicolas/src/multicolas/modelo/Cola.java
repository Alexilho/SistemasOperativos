package multicolas.modelo;

import java.util.ArrayList;

public class Cola {

    private Nodo ultimo;
    private Nodo primero;
    private int tamano;
    //prioridad de toda la cola. Para ordenar las colas según prioridad. Puede que no lo usemos
    private int prioridad;
    //Tiempo máximo que un proceso puede esperar en la cola. Actual no lo usa
    private  int limiteEnvejece;
    int idx;

    public Cola(int priori,int limite) {
        tamano = 0;

        //simepre sera la cabeza
        primero = new Nodo();
        primero.setNombreProceso("cabeza");
        primero.setSig(ultimo);

        ultimo = primero;
        ultimo.setSig(primero);
        idx = 0;
        
        this.prioridad=priori;
        this.limiteEnvejece=limite;
    }

    /**
     * Implementacion del algortinmo *
     */
    public String insertar(int id, String nombre, int tLlegada, int tRafaga, int prioridad) {

        String msg = "Se ha insertado el proceso";

        // rompemos el proceso 
        /*if (ultimo.gettFinal() > tLlegada) {
            msg = " El proceso se ha demorado en llegar tiempo actual :  " + Integer.toString(ultimo.gettFinal()) + "  sera atendido de inmediato";
        }*/

        Nodo q = new Nodo();
        q.setIdProceso(idx);
        q.setNombreProceso(nombre);
        q.settLlegada(tLlegada);
        q.addRafaga(tRafaga);
        q.setPrioridad(prioridad);

        q.setSig(primero);
        
        q.addtComienzo(0);
        q.addtFinal(0);
        q.addtRetorno(0);
        q.addtEspera(0);

        primero.settFinal(q.gettFinal());

        ultimo.setSig(q);
        ultimo = q;

        idx++;
        tamano++;
        return msg;
    }
    public String insertar(Nodo nodo) {

        String msg = "Se ha insertado el proceso";

        // rompemos el proceso 
        /*if (ultimo.gettFinal() > tLlegada) {
            msg = " El proceso se ha demorado en llegar tiempo actual :  " + Integer.toString(ultimo.gettFinal()) + "  sera atendido de inmediato";
        }*/

        Nodo q = new Nodo();
        q.setIdProceso(nodo.getIdProceso());
        q.setNombreProceso(nodo.getNombreProceso());
        q.settLlegada(nodo.gettLlegada());
        q.settRafaga(nodo.gettRafaga());
        q.setPrioridad(nodo.getPrioridad());
        
        q.settComienzo(nodo.gettComienzo());
        q.settFinal(nodo.gettFinal());
        q.settRetorno(nodo.gettRetorno());
        q.settEspera(nodo.gettEspera());
        q.setSig(nodo.getSig());

        q.setSig(primero);

        primero.settFinal(q.gettFinal());

        ultimo.setSig(q);
        ultimo = q;

        idx++;
        tamano++;
        return msg;
    }

 /*   public void atender(Nodo q) {
        // forzamos para que empiece el primero de una 
        if (ultimo.gettFinal() == 0) {
            q.settComienzo(q.gettLlegada());
        } else {
            q.settComienzo(ultimo.gettFinal());
        }

        //calculos basicos
        q.settFinal(q.gettRafaga() + q.gettComienzo());
        q.settRetorno(q.gettFinal() - q.gettLlegada());
        q.settEspera(q.gettRetorno() - q.gettRafaga());

        //Forzando no datos negativos 
        if (q.gettEspera() < 0) {
            int offset = -1 * q.gettEspera();
            q.settEspera(0);
            q.settComienzo(q.gettComienzo() + offset);
            q.settFinal(q.gettFinal() + offset);
            q.settRetorno(q.gettRetorno() + offset);
        }

        q.setSig(primero);
        primero.settFinal(q.gettFinal());
        ultimo.setSig(q);
        ultimo = q;
        
        tamano++;
        //tamano--;
    }*/

    public Nodo retirar() {
        Nodo aux = primero.getSig();
        if (aux != primero) {
            primero.setSig(aux.getSig());
            if (aux == ultimo) {
                ultimo = aux.getSig();
            }
            aux.setSig(null);
            tamano--;
        }
        else{
            aux=null;
        }
        return aux;

        /*if(aux==p){
            return aux;
        }
        if(aux!=cab){  
            p.setSig(aux.getSig());
            aux.setSig(null);
            return aux;
        }
        else{
            cab=aux.getSig();
        }*/
    }

    public boolean isVacia() {
        boolean val = false;
        if (primero == ultimo) {
            val = true;
        }
        return val;
    }

    public Nodo getPrimero() {
        return primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }
    

    public int getTamano() {
        return tamano;
    }

    
    private void printcola(){
     Nodo aux = primero.getSig();
            
     
        System.out.println("\n\n\n///////////////////Cola actual//////////////////////////");
        for (int x = 0; x < tamano; x++) {
            System.out.println("---------- " + x + " ---------");
            print(aux);
            aux = aux.getSig();
        }
        System.out.println("///////////////////Cola end//////////////////////////\n\n\n");
       
    }
    public void ordenarLLegadaMinToMAX() {
        
        //printcola();
        //proceso 
        

        //auxiliar es el nodo a comparar
        Nodo actual= primero.getSig();
        //Siguiente a auxiliar
        Nodo breakNode = primero.getSig().getSig();

        int y=0;
        for (int x = 0; x < tamano; x++) {
                //actual = actual.getSig();
                //breakNode=actual.getSig();
                y=x+1;
                while(y<tamano){
                    if (actual.gettRafaga().get(actual.gettRafaga().size()-1) < breakNode.gettRafaga().get(breakNode.gettRafaga().size()-1)) {
                        //No reordena
                    }
                    else{
                        //reordena
                        intercambia(actual, breakNode);
                    }
                    breakNode=breakNode.getSig();
                    y++;
                }
                actual = actual.getSig();
                breakNode=actual.getSig();
           /* for (int y = x+1; y < tamano; y++) {
                
                // valoar anteior es menor entonce dejelo  
                System.out.println("Odenando " + actual.getIdProceso());
                if (breakNode.gettLlegada() <= actual.gettLlegada()) {
                    System.out.println("Comparamos" + breakNode.getIdProceso() + " con " + actual.getIdProceso());
                    System.out.println("No se cambia" + breakNode.gettLlegada() + " < " + actual.gettLlegada());
                                      
                } else {
                    
                    System.out.println("Comparamos" + breakNode.getIdProceso() + " con " + actual.getIdProceso());
                    System.out.println("Se cambia " + breakNode.gettLlegada() + " < " + actual.gettLlegada());
                   
                   System.out.println("------------------ a analizar --------------------\n\n");
                    
                    System.out.println("temp ==--aux.sig");
                    //temp = (aux.getSig().gettLlegada()==-99)? aux.getSig().getSig():aux.getSig();
                    Nodo temp = actual.getSig();
                    print(temp);***/
                    /*
                    System.out.println("--break");
                    print(breakNode);
                   
                    System.out.println("--break.sig");
                    print(breakNode.getSig());
                    */
                    
                    /*
                    System.out.println("--breakNode.sig=tem");
                    breakNode.setSig(temp);
                    print(breakNode.getSig());
                    
                    
                    System.out.println(" aux.sig= breaKnode");
                    actual.setSig(breakNode);
                    print(actual.getSig());
                    //verificar si es tem o temp.sig
                 
                    
                    
                   temp.setSig(actual);
                }
                printcola();
                //paso a ordenar el sigueinte elemnto
               
                breakNode=actual;
                actual = actual.getSig();  
                ***/
                /**
                if(aux.getSig().gettLlegada()==-99){
                breakNode=aux.getSig();
                aux = aux.getSig().getSig();
                }else{
                 breakNode=aux;
                aux =
                 * aux.getSig();
                }
                **/
                
                //aux = (aux.getSig().gettLlegada()==-99)? aux.getSig().getSig():aux.getSig();
                           
            /*}***/
            //System.out.println(" numero ubicado\n\n\n\n" + x);
        }
        /*Nodo aux2=primero.getSig();
        for(int i=0;i<tamano;i++){
            System.out.println("id: "+aux2.getIdProceso()+"; nombre: "+aux2.getNombreProceso()
                    +"; T. Comienzo: "+aux2.gettLlegada()+"; T. rafaga: "+aux2.gettRafaga());
            aux2=aux2.getSig();
        }*/
        //printcola();

    }
    public void intercambia(Nodo actual, Nodo breakNode){
       int idActual=actual.getIdProceso(),prioridActual=actual.getPrioridad(),
               tLlegActual=actual.gettLlegada();
    ArrayList<Integer> tRafActual=actual.gettRafaga(), tComActual=actual.gettComienzo(),
            tFinActual=actual.gettFinal(), tRetActual=actual.gettRetorno(), 
            tEspActual=actual.gettEspera();
                        String nomActual=actual.getNombreProceso();
                        
                        int idBreak=breakNode.getIdProceso(),tLlegBreak=breakNode.gettLlegada(),
                                 prioriBreak=breakNode.getPrioridad();
                        
                        ArrayList<Integer> tRafBreak=breakNode.gettRafaga(), tComBreak=breakNode.gettComienzo(),
                                tFinBreak=breakNode.gettFinal(), tRetBreak=breakNode.gettRetorno(),
                                tEspBreak=breakNode.gettEspera();
                        String nomBreak=breakNode.getNombreProceso();
                        
                        actual.setIdProceso(idBreak);
                        actual.setNombreProceso(nomBreak);
                        actual.settLlegada(tLlegBreak);
                        actual.setPrioridad(prioriBreak);
                        
                        actual.settRafaga(tRafBreak);
                        actual.settComienzo(tComBreak);
                        actual.settFinal(tFinBreak);
                        actual.settRetorno(tRetBreak);
                        actual.settEspera(tEspBreak);
                        
                        
                        breakNode.setIdProceso(idActual);
                        breakNode.setNombreProceso(nomActual);
                        breakNode.settLlegada(tLlegActual);
                        breakNode.setPrioridad(prioridActual);
                        
                        breakNode.settRafaga(tRafActual);
                        breakNode.settComienzo(tComActual);
                        breakNode.settFinal(tFinActual);
                        breakNode.settRetorno(tRetActual);
                        breakNode.settEspera(tEspActual);
    }
    public void print(Nodo n) {
        System.out.println("Id: " + n.getIdProceso()+ "\tidsig : " +n.getSig().getIdProceso() + "\t Nombre: " + n.getNombreProceso()+" \tLLegdada: " + n.gettLlegada());
        //System.out.println("Rafaga: " + n.gettRafaga());
    }

    public int getLimiteEnvejece() {
        return limiteEnvejece;
    }
    
}
