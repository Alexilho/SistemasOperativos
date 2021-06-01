package multicolas.modelo;

import java.util.ArrayList;

public class Nodo {

    private int idProceso;
    private String nombreProceso;
    //private int nServicios;
    private int  tLlegada;
    private ArrayList<Integer> tRafaga;
    private ArrayList<Integer> tComienzo;
    private ArrayList<Integer> tFinal;
    private ArrayList<Integer> tRetorno;
    private ArrayList<Integer> tEspera;
    private int prioridad;
    private Nodo sig;
    private int tEnvejecimiento;

    public Nodo() {
        this.idProceso = -99;
        this.nombreProceso = "Nada";
        //this.nServicios=0;
        this.sig = null;

        this.tLlegada = -99;
        this.tRafaga = new ArrayList();
        this.tComienzo = new ArrayList();
        this.tFinal = new ArrayList();
        this.tRetorno = new ArrayList();
        this.tEspera = new ArrayList(); 
        this.prioridad=0;
        this.tEnvejecimiento=0;
}

    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    public int gettLlegada() {
        return tLlegada;
    }

    public void settLlegada(int tLlegada) {
        this.tLlegada = tLlegada;
    }

    public ArrayList<Integer> gettRafaga() {
        return tRafaga;
    }

    public void settRafaga(ArrayList<Integer> tRafaga) {
        this.tRafaga = tRafaga;
    }

    public ArrayList<Integer> gettComienzo() {
        return tComienzo;
    }

    public void settComienzo(ArrayList<Integer> tComienzo) {
        this.tComienzo = tComienzo;
    }

    public ArrayList<Integer> gettFinal() {
        return tFinal;
    }

    public void settFinal(ArrayList<Integer> tFinal) {
        this.tFinal = tFinal;
    }

    public ArrayList<Integer> gettRetorno() {
        return tRetorno;
    }

    public void settRetorno(ArrayList<Integer> tRetorno) {
        this.tRetorno = tRetorno;
    }

    public ArrayList<Integer> gettEspera() {
        return tEspera;
    }

    public void settEspera(ArrayList<Integer> tEspera) {
        this.tEspera = tEspera;
    }



    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    public void addRafaga(int dato){
        this.tRafaga.add(dato);
    }
    public void addtComienzo(int dato){
        this.tComienzo.add(dato);
    }
    public void addtFinal(int dato){
        this.tFinal.add(dato);
    }
    public void addtRetorno(int dato){
        this.tRetorno.add(dato);
    }
    public void addtEspera(int dato){
        this.tEspera.add(dato);
    }

    public int gettEnvejecimiento() {
        return tEnvejecimiento;
    }

    public void settEnvejecimiento(int tEnvejecimiento) {
        this.tEnvejecimiento = tEnvejecimiento;
    }
    
}
