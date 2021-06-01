package multicolas.modelo;

import java.util.Queue;

public class DataSRTF implements IDataBuild{

    // espetando proceso 
    private Cola cola;
    
    // los que ya terminaron proceso
    public Cola colaAtedidos;
    
    public Nodo actual;

    public DataSRTF() {
        this.cola = new Cola(3,30);
        this.colaAtedidos = new Cola(0,0);
    }
    
    private Object centinela99(int n) {
        return (n == -99) ? " " : n;
    }

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
            nodo = nodo.getSig();
            tRafaga=""; tComienzo="";tFinal="";tRetorno="";tEspera="";
        }
        return datos;
    }
    
    
     public void calculaValores(Nodo aux,boolean sigue, int tActual){
        int ultim=aux.gettRafaga().size()-1;
        aux.gettFinal().set(ultim, tActual);
        aux.gettRetorno().set(ultim, aux.gettFinal().get(ultim)-aux.gettLlegada());
        aux.gettEspera().set(ultim, aux.gettRetorno().get(ultim)-(aux.gettFinal().get(ultim)-aux.gettComienzo().get(ultim)));

        if(sigue){
            aux.addRafaga(aux.gettRafaga().get(ultim) -(aux.gettFinal().get(ultim) -aux.gettComienzo().get(ultim)));
            aux.addtComienzo(0);
            aux.addtFinal(0);
            aux.addtRetorno(0);
            aux.addtEspera(0);
        }

    }
    public void agregarSRTF(int tActual){
       getCola().ordenarLLegadaMinToMAX();
       if(actual!=null){
            //System.out.println("prioridad"+data.getCola().getPrimero().getSig().getPrioridad());
            int n=getCola().getPrimero().getSig().gettRafaga().size();
            int m=actual.gettRafaga().size();
            if(getCola().getPrimero().getSig().gettRafaga().get(n-1) <actual.gettRafaga().get(m-1)){
                Nodo aux=actual;
                
                //Falta agregar valores al arraylist
                calculaValores(aux,true,tActual);
                
                actual=getCola().retirar();
                //
                actual.gettComienzo().set(actual.gettComienzo().size()-1, tActual);
                
                getCola().insertar(aux);
                System.out.println("Intercambio");
                //Re ordenamiento
                getCola().ordenarLLegadaMinToMAX();
            }
            System.out.println("Ejecutando: "+actual.getNombreProceso());
            System.out.println("Ultimo cola: "+getCola().getUltimo().getNombreProceso());
        }
        

    }

    public Cola getCola() {
        return cola;
    }

}
