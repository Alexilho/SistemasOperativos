package controlador;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.swing.Timer;
import modelo.Cola;
import modelo.Data;
import modelo.Nodo;
import vista.Ventana;

public class Gestor {

    private Ventana ventana;
    private Data data;

    private boolean isRunnable;
    int MIN_TURNO = 1;
    int MAX_TURNO = 10;
    int SEG_PER_PROCESS = 5;
    public static int QUANTUM=4;

    private Timer t;
    private int p;
    private int tActual;
    int conteo;
    public Gestor() {
        tActual=0;
        isRunnable = false;
        this.data = new Data();
        this.ventana = new Ventana();

        t = new Timer(1000, acciones);

        ventana.addBtnAgregar(new ListenerBtnAtender());
        ventana.addBtnGenerar(new ListenerBtnGenerar());
        ventana.addBtnEjecutar(new ListenerBtnEjecutar());
        
        ventana.setLblQuantum(QUANTUM);
        conteo=0;
    }

    public void agregarProceso(int id, String nombre, int tLlegada, int tRafaga, int prioridad) {

        String ms = data.getCola().insertar(id, nombre, tLlegada, tRafaga, prioridad);
       /* data.getCola().ordenarLLegadaMinToMAX();
        if(data.actual!=null){
            //System.out.println("prioridad"+data.getCola().getPrimero().getSig().getPrioridad());
            int n=data.getCola().getPrimero().getSig().gettRafaga().size();
            int m=data.actual.gettRafaga().size();
            if(data.getCola().getPrimero().getSig().gettRafaga().get(n-1) <data.actual.gettRafaga().get(m-1)){
                Nodo aux=data.actual;
                
                //Falta agregar valores al arraylist
                calculaValores(aux,true);
                
                data.actual=data.getCola().retirar();
                //
                data.actual.gettComienzo().set(data.actual.gettComienzo().size()-1, tActual);
                
                data.getCola().insertar(aux);
                System.out.println("Intercambio");
                //Re ordenamiento
                data.getCola().ordenarLLegadaMinToMAX();
            }
            System.out.println("Ejecutando: "+data.actual.getNombreProceso());
            System.out.println("Ultimo cola: "+data.getCola().getUltimo().getNombreProceso());
        }*/
        

        //Ordenar 
        ventana.mostarMenssaje(ms);
        ventana.refrescar(data.buildDataAll(data.getCola(), data.colaAtedidos,data.actual), false);
    }
    public void calculaValores(Nodo aux,boolean sigue){
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

    public void ejecutarProceso() {
        // FIXME : no se puede de usna sola
        //data.atenderProceso();

        //ventana.refrescar(data.buildData(data.colaAtedidos), true);
        ventana.refrescar(data.buildDataAll(data.getCola(), data.colaAtedidos, data.actual), true);
    }

    public int generarNumero(int inicio, int fin) {
        int valorEntero = (int) Math.floor(Math.random() * (fin - inicio + 1) + inicio);
        return valorEntero;
    }

    public Data getData() {
        return data;
    }

    ///// acciones al ejecutar el timer ////////////////////////
    private ActionListener acciones = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
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
                                data.colaListos.insertar(aux);
                            }*/
                
                else if ((tActual - data.actual.gettComienzo().get(data.actual.gettComienzo().size() - 1)) == data.actual.gettRafaga().get(data.actual.gettRafaga().size() - 1) && tActual!=0) {
                    conteo=0;
                    val=data.actual.gettComienzo().size()-1;
                    
                    calculaValores(data.actual, false);
                    data.actual.gettEspera().set(val, data.actual.gettRetorno().get(val)-(data.actual.gettRafaga().get(0)));
                    System.out.println("Proceso "+data.actual.getNombreProceso()+" se ha terminado");
                    data.colaAtedidos.insertar(data.actual);
                    data.actual = null;
                    
                    if (!data.getCola().isVacia()) {
                        data.actual = data.getCola().retirar();
                        //data.actual.addtComienzo(tActual);
                        data.actual.gettComienzo().set(data.actual.gettComienzo().size()-1, tActual);
                    } 

                }
                else if(conteo==4 && tActual!=0){
                    conteo=0;
                    Nodo aux=data.actual;
                
                    //Falta agregar valores al arraylist
                    calculaValores(aux,true);
                    //aux.gettEspera().set(val, aux.gettRetorno().get(val)-(aux.gettRafaga().get(0)));
                    
                    data.actual=null;
                    if(data.getCola()!=null){
                        try{
                        data.actual=data.getCola().retirar();
                    //
                        data.actual.gettComienzo().set(data.actual.gettComienzo().size()-1, tActual);
                        }
                        catch(NullPointerException e){
                            
                        }

                    }
                        data.getCola().insertar(aux);
                        System.out.println("Intercambio en tiempo: "+tActual);
                    
                }
                tActual++;
                conteo++;
            } else {
                // cuando se acben los procesos
                t.stop();
                isRunnable = !isRunnable;
                ventana.changeRunableBtn(isRunnable);
                ventana.mostarMenssaje("Se han acabado los procesos");
                ventana.getLienzo().repaint();
            }
            ventana.refrescar(data.buildDataAll(data.getCola(), data.colaAtedidos,data.actual), true);
            
        }
    };



///////////////////////////////////////Listeners///////////////////////////////////
    class ListenerBtnAtender implements ActionListener {

        public void actionPerformed(ActionEvent a) {
            /*if(Integer.parseInt(ventana.getTxtTLlegada().getText())<data.getCola().getUltimo().gettLlegada()){
                ventana.mostarMenssaje("No se pudo insertar");
                return;
            }*/

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

        public void actionPerformed(ActionEvent a) {
            ventana.rellenarDatos(generarNumero(MIN_TURNO, MAX_TURNO), String.valueOf((char) generarNumero(65, 91)), generarNumero(MIN_TURNO, MAX_TURNO), generarNumero(MIN_TURNO, MAX_TURNO));
        }
    }

    /*ejecucion del programa  */
    /*Este método ejecuta cuando se hace clic en el boton ejecutar
     
     * Lo que hace es hacer el ordenamiento de los procesos e iniciar el hilo.
     * 
     * Luego el hilo llama a acciones cada cierto tiempo y ahí se ejecutan los procesos
     */
    class ListenerBtnEjecutar implements ActionListener {

        public void actionPerformed(ActionEvent a) {
            isRunnable = !isRunnable;
            ventana.changeRunableBtn(isRunnable);
            if (isRunnable) {
                //data.getCola().ordenarLLegadaMinToMAX();
                t.start();
            }else{
                t.stop();
            }
        }
    }
}
