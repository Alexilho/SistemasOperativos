package modelo;

import java.util.Queue;

public class Data {

    // espetando proceso 
    private Cola cola;
    
    // los que ya terminaron proceso
    public Cola colaAtedidos;
    
    public Nodo actual;

    public Data() {
        this.cola = new Cola();
        this.colaAtedidos = new Cola();
    }

    /*public void atenderProceso() {
        if (!cola.isVacia()) {
            colaAtedidos.atender(cola.retirar());
        }
    }*/

    private Object centinela99(int n) {
        return (n == -99) ? " " : n;
    }

    /*public Object[][] buildData(Cola c) {
        // datos de tabla 
        Object[][] datos;
        if (!c.isVacia()) {

            datos = new Object[c.getTamano()][9];
            Nodo nodo = c.getPrimero().getSig();

            // organizar 
            for (int i = 0; i < c.getTamano(); i++) {
                datos[i][0] = nodo.getIdProceso();
                datos[i][1] = nodo.getNombreProceso();
                datos[i][2] = nodo.gettLlegada();
                datos[i][3] = nodo.gettRafaga();
                datos[i][4] = nodo.getPrioridad();
                datos[i][5] = centinela99(nodo.gettComienzo());
                datos[i][6] = centinela99(nodo.gettFinal());
                datos[i][7] = centinela99(nodo.gettRetorno());
                datos[i][8] = centinela99(nodo.gettEspera());
                nodo = nodo.getSig();
            }
        } else {
            System.out.println("Cola vacia");
            datos = new Object[1][8];
            for (int i = 0; i < 9; i++) {
                datos[0][i] = "";
            }
        }
        return datos;
    }*/

    /*
    public Object[][] buildDataAll(Cola[] c) {
        // datos de tabla 
        Object[][] datos;
        
            
        int tamanioAll=0;
        for (int y = 0; y < 2; y++) {
            tamanioAll=+c[y].getTamano();
        }
        
        System.out.println("planificacion.modelo.Data.buildDataAll()" + tamanioAll);
        datos = new Object[tamanioAll][8];
      

        for (int y = 0; y < c.length; y++) {
              Nodo nodo = c[y].getPrimero().getSig();
        
        // organizar 
        for (int i = 0; i < c[y].getTamano(); i++) {
            datos[i][0] = nodo.getIdProceso();
            datos[i][1] = nodo.getNombreProceso();
            datos[i][2] = nodo.gettLlegada();
            datos[i][3] = nodo.gettRafaga();
            datos[i][4] =  centinela99(nodo.gettComienzo());
            datos[i][5] = centinela99(nodo.gettFinal());
            datos[i][6] = centinela99(nodo.gettRetorno());
            datos[i][7] = centinela99(nodo.gettEspera());  
            nodo = nodo.getSig();
        }}
        return datos;
    } 
     */
    public Object[][] buildDataAll(Cola a, Cola b, Nodo actual) {
        // datos de tabla 
        Object[][] datos;
        
        String tRafaga="",tComienzo="",tFinal="",tRetorno="",tEspera="";

        int tamanioAll = a.getTamano() + b.getTamano();

        
        

        Nodo nodo = a.getPrimero().getSig();

        // organizar 
        int i,k=0;
        if(this.actual!=null){
            k=1;
            datos = new Object[tamanioAll+k][9];
            for(int j=0;j<actual.gettRafaga().size();j++){
                tRafaga+=centinela99(actual.gettRafaga().get(j));
                tComienzo+=centinela99(actual.gettComienzo().get(j));
                tFinal+=centinela99(actual.gettFinal().get(j));
                tRetorno+=centinela99(actual.gettRetorno().get(j));
                tEspera+=centinela99(actual.gettEspera().get(j));
                if(j<actual.gettRafaga().size()-1){
                    tRafaga+=",";
                    tComienzo+=",";
                    tFinal+=",";
                    tRetorno+=",";
                    tEspera+=",";
                }
            }
            datos[0][0] = actual.getIdProceso();
            datos[0][1] = actual.getNombreProceso();
            datos[0][2] = actual.gettLlegada();
            datos[0][3] = tRafaga;
            datos[0][4] = actual.getPrioridad();
            datos[0][5] = tComienzo;
            datos[0][6] = tFinal;
            datos[0][7] = tRetorno;
            datos[0][8] = tEspera;
            
        }
        else{
            datos = new Object[tamanioAll][9];
        }
        tamanioAll+=k;
        System.out.println("planificacion.modelo.Data.buildDataAll() cantidad Tootal de datos:" + tamanioAll);
         tRafaga="";tComienzo="";tFinal="";tRetorno="";tEspera="";
        
        
        for (i = k; i < a.getTamano()+k; i++) {
            for(int j=0;j<nodo.gettRafaga().size();j++){
                tRafaga+=centinela99(nodo.gettRafaga().get(j));
                tComienzo+=centinela99(nodo.gettComienzo().get(j));
                tFinal+=centinela99(nodo.gettFinal().get(j));
                tRetorno+=centinela99(nodo.gettRetorno().get(j));
                tEspera+=centinela99(nodo.gettEspera().get(j));
                if(j<nodo.gettRafaga().size()-1){
                    tRafaga+=",";
                    tComienzo+=",";
                    tFinal+=",";
                    tRetorno+=",";
                    tEspera+=",";
                }
            }
            datos[i][0] = nodo.getIdProceso();
            datos[i][1] = nodo.getNombreProceso();
            datos[i][2] = nodo.gettLlegada();
            datos[i][3] = tRafaga;
            datos[i][4] = nodo.getPrioridad();
            datos[i][5] = tComienzo;
            datos[i][6] = tFinal;
            datos[i][7] = tRetorno;
            datos[i][8] = tEspera;
            nodo = nodo.getSig();
            tRafaga=""; tComienzo="";tFinal="";tRetorno="";tEspera="";
        }
        nodo = b.getPrimero().getSig();

        tRafaga=""; tComienzo="";tFinal="";tRetorno="";tEspera="";
        
        // organizar 
        for (i = i; i < tamanioAll; i++) {
            for(int j=0;j<nodo.gettRafaga().size();j++){
                tRafaga+=centinela99(nodo.gettRafaga().get(j));
                tComienzo+=centinela99(nodo.gettComienzo().get(j));
                tFinal+=centinela99(nodo.gettFinal().get(j));
                tRetorno+=centinela99(nodo.gettRetorno().get(j));
                tEspera+=centinela99(nodo.gettEspera().get(j));
                if(j<nodo.gettRafaga().size()-1){
                    tRafaga+=",";
                    tComienzo+=",";
                    tFinal+=",";
                    tRetorno+=",";
                    tEspera+=",";
                }
            }
            datos[i][0] = nodo.getIdProceso();
            datos[i][1] = nodo.getNombreProceso();
            datos[i][2] = nodo.gettLlegada();
            datos[i][3] = tRafaga;
            datos[i][4] = nodo.getPrioridad();
            datos[i][5] = tComienzo;
            datos[i][6] = tFinal;
            datos[i][7] = tRetorno;
            datos[i][8] = tEspera;
            /*datos[i][5] = centinela99(nodo.gettComienzo());
            datos[i][6] = centinela99(nodo.gettFinal());
            datos[i][7] = centinela99(nodo.gettRetorno());
            datos[i][8] = centinela99(nodo.gettEspera());*/
            nodo = nodo.getSig();
            tRafaga=""; tComienzo="";tFinal="";tRetorno="";tEspera="";
        }
        return datos;
    }
   public void ordenarCola(){
        /**
         Nodo aux = cola.retirar()
         
         for(int i = 0; i < tamano ; i ++){
             
         }
         
         */
         
    }
    
    public Cola getCola() {
        return cola;
    }

}
