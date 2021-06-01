package multicolas.controlador;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.swing.Timer;
import multicolas.modelo.Cola;
import multicolas.modelo.DataRR;
import multicolas.modelo.DataSRTF;
import multicolas.modelo.DataPriority;
import multicolas.modelo.IDataBuild;
import multicolas.modelo.Nodo;
import multicolas.vista.Ventana;

public class Gestor {

    private Ventana ventana;
    private DataRR dataRR;

    private DataSRTF dataSRTF;
    private DataPriority dataPriority;

    private boolean isRunnable;
    int MIN_TURNO = 1;
    int MAX_TURNO = 10;
    int SEG_PER_PROCESS = 5;
    public static int QUANTUM = 4;

    private Timer t;
    private int p;
    private int tActual;
    int conteo;
    
    /*
         prioridad  ENVEJE 
    SRT 3           30
    RR  2           7
    PRIORIDAD   1   15
    
    
    */

    public Gestor() {
        tActual = 0;
        isRunnable = false;
        this.dataRR = new DataRR();
        this.dataSRTF = new DataSRTF();
        this.dataPriority = new DataPriority();
        this.ventana = new Ventana();

        t = new Timer(1000, acciones);

        ventana.addBtnAgregar(new ListenerBtnInsertar());
        ventana.addBtnGenerar(new ListenerBtnGenerar());
        ventana.addBtnEjecutar(new ListenerBtnEjecutar());

        ventana.setLblQuantum(QUANTUM);
        conteo = 0;
    }

    public void agregarProceso(int id, String nombre, int tLlegada, int tRafaga, int prioridad) {

        String queCola = ventana.selccionarItem("Seleccion la cola para ingresar el proceso ",new String[]{"SRTF", "RR", "Prioridad"});
        System.out.println("controlador.Gestor.agregarProceso() " + queCola);

        String ms = "No se ha insertado "; 
        switch (queCola) {

            //srtf lienxzo 1,
            // round robin leinzo 2 
            // prioridad linezo 3 
            
            // estoy cogiendo cada modeloo y lo insero dentro del elemtno
            
            case "SRTF":
                ms = dataSRTF.getCola().insertar(id, "S-"+nombre, tLlegada, tRafaga, prioridad);
                dataSRTF.agregarSRTF(tActual);
                //Ordenar 
                ventana.mostarMenssaje(ms);
                ventana.refrescar(dataSRTF.buildDataAll(dataSRTF.getCola(), dataSRTF.colaAtedidos, dataSRTF.actual), ventana.lienzo1, false);
                break;

            case "RR":
                ms = dataRR.getCola().insertar(id, "R-"+nombre, tLlegada, tRafaga, prioridad);

                //Ordenar 
                ventana.mostarMenssaje(ms);
                ventana.refrescar(dataRR.buildDataAll(dataRR.getCola(), dataRR.colaAtedidos, dataRR.actual), ventana.lienzo2, false);
                break;
             case "Prioridad":
               
                int p = Integer.parseInt(ventana.selccionarItem("selccione Prioridad  1 mas  ??? y 5  mas  ??? ", new String[]{"1","2","3","4","5"}));
                 ms = dataPriority.getCola().insertar(id, "P-"+nombre, tLlegada, tRafaga, p);
                 dataPriority.agregarPrioridad(tActual);
                //Ordenar 
                ventana.mostarMenssaje(ms);
                ventana.refrescar(dataPriority.buildDataAll(dataPriority.getCola(), dataPriority.colaAtedidos, dataPriority.actual), ventana.lienzo3, false);
                break;

            default:
                ventana.mostarMenssaje(ms);
        }

    }

    public void ejecutarProceso() {
        // FIXME : no se puede de usna sola

        ventana.refrescar(dataRR.buildDataAll(dataRR.getCola(), dataRR.colaAtedidos, dataRR.actual), ventana.lienzo1, true);
        ventana.refrescar(dataSRTF.buildDataAll(dataSRTF.getCola(), dataSRTF.colaAtedidos, dataSRTF.actual), ventana.lienzo2, true);
        ventana.refrescar(dataPriority.buildDataAll(dataPriority.getCola(), dataPriority.colaAtedidos, dataPriority.actual), ventana.lienzo3, true);
    }

    public int generarNumero(int inicio, int fin) {
        int valorEntero = (int) Math.floor(Math.random() * (fin - inicio + 1) + inicio);
        return valorEntero;
    }

    public DataRR getData() {
        return dataRR;
    }

    ///// acciones al ejecutar el timer ////////////////////////
    private final ActionListener acciones = new ActionListener() {

        public void actionPerformed(ActionEvent ae) {

            if ((!dataSRTF.getCola().isVacia() || dataSRTF.actual!=null) || (!dataRR.getCola().isVacia() || dataRR.actual!=null)||(!dataPriority.getCola().isVacia() || dataPriority.actual!=null)){
                
                //ventana.actualizarProcesoActual(tActual, p, data.getCola().getTamano() + data.colaAtedidos.getTamano());
                //ventana.actualizarTiempoActual(tActual);
                
                //Analiza si el proceso debe cambiarse de cola
                analisisEnvejecimiento(dataPriority.getCola(), dataRR.getCola());
                
                
                 System.err.println(".actionPerformed() usual " + dataSRTF.getCola().getTamano());
                if(analisisEnvejecimiento2(dataRR.getCola(), dataSRTF.getCola())){
                    System.err.println(".actionPerformed() cuando se inserta" + dataSRTF.getCola().getTamano());
                    System.err.println(".actionPerformed() cuando se inserta colas vacia" + dataSRTF.getCola().isVacia());
                    dataSRTF.agregarSRTF(tActual);
                    
                }
          
                //ejeSRTF(dataSRTF);
                //ejeRR(dataRR);
                //ejePrio(dataPriority);
                
               ejecutarColasPrioridad();
                        
                actualizarEnvejecimiento(dataSRTF.getCola().getPrimero());
                actualizarEnvejecimiento(dataRR.getCola().getPrimero());
                actualizarEnvejecimiento(dataPriority.getCola().getPrimero());

                tActual++;
                conteo++;

            } else {
                // cuando se acben los procesos
                t.stop();
                isRunnable = !isRunnable;
                ventana.changeRunableBtn(isRunnable);
                ventana.mostarMenssaje("Se han acabado los procesos");
                ventana.lienzo1.repaint();
                ventana.lienzo2.repaint();
                ventana.lienzo3.repaint();
                //tActual--;
            }
            ventana.refrescar(dataSRTF.buildDataAll(dataSRTF.getCola(), dataSRTF.colaAtedidos, dataSRTF.actual), ventana.lienzo1, true);
            ventana.refrescar(dataRR.buildDataAll(dataRR.getCola(), dataRR.colaAtedidos, dataRR.actual), ventana.lienzo2, true);
            ventana.refrescar(dataPriority.buildDataAll(dataPriority.getCola(), dataPriority.colaAtedidos, dataPriority.actual), ventana.lienzo3, true);
            //
            ventana.actualizarTiempoActual(tActual);
        }
    };
    public void analisisEnvejecimiento(Cola miCola, Cola nueva){
        Nodo aux=miCola.getPrimero().getSig();
        if(aux!=null && aux.gettEnvejecimiento()>=miCola.getLimiteEnvejece()){
            nueva.insertar(miCola.retirar());
            nueva.getUltimo().settEnvejecimiento(0);
            
        }
    }
    
    public boolean analisisEnvejecimiento2(Cola miCola, Cola nueva){
        boolean val=false;
        Nodo aux=miCola.getPrimero().getSig();
        if(aux!=null && aux.gettEnvejecimiento()>=miCola.getLimiteEnvejece()){
            nueva.insertar(miCola.retirar());
            nueva.getUltimo().settEnvejecimiento(0);
            val=true;
        }
        return val;
    }

    public void ejeSRTF(DataSRTF data) {
        int val = 0;

        if (!data.getCola().isVacia() || (data.actual != null)) {
          
            //ventana.actualizarProcesoActual(tActual, p, data.getCola().getTamano() + data.colaAtedidos.getTamano());
            ventana.actualizarTiempoActual(tActual);
            //tomo el proceo 
            if (data.actual == null) {
                data.actual = data.getCola().retirar();
                data.actual.settEnvejecimiento(0);
                val = data.actual.gettComienzo().size() - 1;
                data.actual.gettComienzo().set(val, tActual);
                data.actual.gettEspera().set(val, data.actual.gettRetorno().get(val) - (data.actual.gettRafaga().get(0)));
            } /*else if(data.colaListos.getPrimero().getPrioridad()>data.actual.getPrioridad()){
                                Nodo aux=data.actual;
                                data.actual=data.colaListos.retirar();
                                data.colaListos.insertar(aux);
                            }*/ 
            else if ((tActual - data.actual.gettComienzo().get(data.actual.gettComienzo().size() - 1)) == data.actual.gettRafaga().get(data.actual.gettRafaga().size() - 1) && tActual != 0) {
                
                data.calculaValores(data.actual, false, tActual);
                val = data.actual.gettComienzo().size() - 1;
                data.actual.gettEspera().set(val, data.actual.gettRetorno().get(val) - (data.actual.gettRafaga().get(0)));
                System.err.println("Proceso de srtf  " + data.actual.getNombreProceso() + " se ha terminado");
                data.colaAtedidos.insertar(data.actual);
                data.actual = null;

                if (!data.getCola().isVacia()) {
                    data.actual = data.getCola().retirar();
                    val = data.actual.gettComienzo().size() - 1;
                    //data.actual.addtComienzo(tActual);
                    data.actual.gettComienzo().set(data.actual.gettComienzo().size() - 1, tActual);
                    data.actual.gettEspera().set(val, data.actual.gettRetorno().get(val) - (data.actual.gettRafaga().get(0)));
                    data.actual.settEnvejecimiento(0);
                }

            }
        }
    }
    
    public void actualizarEnvejecimiento(Nodo cabeza){
        Nodo aux=cabeza.getSig();
        while(aux!=cabeza){
            aux.settEnvejecimiento(aux.gettEnvejecimiento()+1);
            aux=aux.getSig();
        }
    }

    public void ejeRR(DataRR dataRR) {
        int val = 0;
        if (!dataRR.getCola().isVacia() || (dataRR.actual != null)) {
            //tomo el proceo 
            if (dataRR.actual == null) {
                dataRR.actual = dataRR.getCola().retirar();

                dataRR.actual.gettComienzo().set(dataRR.actual.gettComienzo().size() - 1, tActual);
                val = dataRR.actual.gettComienzo().size() - 1;
            } else if ((tActual - dataRR.actual.gettComienzo().get(dataRR.actual.gettComienzo().size() - 1)) == dataRR.actual.gettRafaga().get(dataRR.actual.gettRafaga().size() - 1) && tActual != 0) {
                conteo = 0;
                val = dataRR.actual.gettComienzo().size() - 1;

                dataRR.calculaValores(dataRR.actual, false, tActual);
                dataRR.actual.gettEspera().set(val, dataRR.actual.gettRetorno().get(val) - (dataRR.actual.gettRafaga().get(0)));
                System.out.println("Proceso " + dataRR.actual.getNombreProceso() + " se ha terminado");
                dataRR.colaAtedidos.insertar(dataRR.actual);
                dataRR.actual = null;

                if (!dataRR.getCola().isVacia()) {
                    dataRR.actual = dataRR.getCola().retirar();
                    //data.actual.addtComienzo(tActual);
                    dataRR.actual.gettComienzo().set(dataRR.actual.gettComienzo().size() - 1, tActual);
                }

            } else if (conteo == 4 && tActual != 0) {
                conteo = 0;
                Nodo aux = dataRR.actual;

                //Falta agregar valores al arraylist
                dataRR.calculaValores(aux, true, tActual);
                //aux.gettEspera().set(val, aux.gettRetorno().get(val)-(aux.gettRafaga().get(0)));

                dataRR.actual = null;
                if (dataRR.getCola() != null) {
                    try {
                        dataRR.actual = dataRR.getCola().retirar();

                        dataRR.actual.gettComienzo().set(dataRR.actual.gettComienzo().size() - 1, tActual);
                    } catch (NullPointerException e) {

                    }

                }
                dataRR.getCola().insertar(aux);
                System.out.println("Intercambio en tiempo: " + tActual);

            }
        }
    }

    void ejecutarColasPrioridad(){
                 //evaluar actual de cada data
              if(!dataSRTF.getCola().isVacia() || dataSRTF.actual!=null){
                  
                  ejeSRTF(dataSRTF);
                  System.out.println("Estoy en SRTF");
                  if(dataRR.actual!=null){
                      dataRR.getCola().insertar(dataRR.actual);
                      dataRR.actual=null;
                  }
                  if(dataPriority.actual!=null){
                      dataPriority.getCola().insertar(dataPriority.actual);
                      dataPriority.actual=null;
                  }
              }
              
              else if(!dataRR.getCola().isVacia() || dataRR.actual!=null){
                  ejeRR(dataRR);
                  System.out.println("Estoy en RR");
                  if(dataPriority.actual!=null){
                      dataPriority.getCola().insertar(dataPriority.actual);
                      dataPriority.actual=null;
                  }
              }
              
              else if(!dataPriority.getCola().isVacia() || dataPriority.actual!=null){
                  ejePrio(dataPriority);
                  System.out.println("Estoy en Prioridad");
              }
              else{
              //termina
              System.out.println("Estoy en ??");
              }
        
    }
   
    boolean hayProceso(IDataBuild i){
        return (!i.getCola().isVacia()|| i.actual!=null); 
    }
   
    public void ejePrio(DataPriority data){
        
        int val=0;
            if (!data.getCola().isVacia() || (data.actual!=null)) {
                int qProcess = data.getCola().getTamano() + data.colaAtedidos.getTamano();
                //ventana.actualizarProcesoActual(tActual, p, data.getCola().getTamano() + data.colaAtedidos.getTamano());
                ventana.actualizarTiempoActual(tActual);
                //tomo el proceo 
                if (data.actual == null) {
                    data.actual = data.getCola().retirar();
                    
                    data.actual.gettComienzo().set(data.actual.gettComienzo().size()-1, tActual);
                    val=data.actual.gettComienzo().size()-1;
                }

                /*else if(data.colaListos.getPrimero().getPrioridad()>data.actual.getPrioridad()){
                                Nodo aux=data.actual;
                                data.actual=data.colaListos.retirar();
                                data.claListos.insertar(aux);
                            }*/
                else if ((tActual - data.actual.gettComienzo().get(data.actual.gettComienzo().size() - 1)) == data.actual.gettRafaga().get(data.actual.gettRafaga().size() - 1) && tActual!=0) {
                    data.calculaValores(data.actual, false,tActual);
                    val=data.actual.gettComienzo().size()-1;
                    data.actual.gettEspera().set(val, data.actual.gettRetorno().get(val)-(data.actual.gettRafaga().get(0)));
                    System.err.println("Proceso de priordad  "+data.actual.getNombreProceso()+" se ha terminado");
                    data.colaAtedidos.insertar(data.actual);
                    data.actual = null;
                    
                    if (!data.getCola().isVacia()) {
                        data.actual = data.getCola().retirar();
                        //data.actual.addtComienzo(tActual);
                        data.actual.gettComienzo().set(data.actual.gettComienzo().size()-1, tActual);
                    } 

                }
            }
    
    }
    
///////////////////////////////////////Listeners///////////////////////////////////
    class ListenerBtnInsertar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent a) {
            try {
                agregarProceso(Integer.parseInt(ventana.getTxtId().getText()), ventana.getTxtNombre().getText(),
                        tActual, Integer.parseInt(ventana.getTxtTRafaga().getText()),
                        0);
                //ventana.actualizarProcesoActual(tActual, p, data.getCola().getTamano()+data.colaAtedidos.getTamano());
                ventana.actualizarTiempoActual(tActual);
            } catch (NumberFormatException e) {
                ventana.mostarMenssaje("Datos mal ingresados");
            }
        }
    }

    class ListenerBtnGenerar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent a) {
            ventana.rellenarDatos(generarNumero(MIN_TURNO, MAX_TURNO), String.valueOf((char) generarNumero(65, 91)), generarNumero(MIN_TURNO, MAX_TURNO));
        }
    }

    class ListenerBtnEjecutar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent a) {
            isRunnable = !isRunnable;
            ventana.changeRunableBtn(isRunnable);
            if (isRunnable) {
                //data.getCola().ordenarLLegadaMinToMAX();
                t.start();
                ventana.actualizarTiempoActual(tActual);
            } else {
                t.stop();
            }
        }
    }
}
