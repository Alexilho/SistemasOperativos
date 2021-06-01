/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicolas.modelo;

/**
 *
 * @author LENOVO
 */
public interface IDataBuild {
    Cola cola=null;
    Nodo actual=null;
    public Object[][] buildDataAll(Cola a, Cola b, Nodo actual);  
    
    public Cola getCola();
    //public Nodo getActual();
     
}
