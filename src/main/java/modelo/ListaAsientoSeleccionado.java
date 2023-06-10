
package modelo;

import java.util.ArrayList;


public class ListaAsientoSeleccionado {
    private ArrayList<AsientoSeleccionado> asientosSeleccionados;
    
    public ListaAsientoSeleccionado(){
        this.asientosSeleccionados = new ArrayList<>();
    }
    
    public void add(AsientoSeleccionado asiento){
        asientosSeleccionados.add(asiento);
    }
    
    public ArrayList<AsientoSeleccionado> lista(){
        return asientosSeleccionados;
    }
    
    public int cantidadAsientos(){
        return asientosSeleccionados.size();
    }
    
    public void limpia(){
        asientosSeleccionados.clear();
    }
}
