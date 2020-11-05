
package condicionescompetencias;

import java.util.ArrayList;
//import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class RCompartido {
    private String datoCompartido;
    private VCerradura cerradura;
    private ArrayList<Interrupcion> interrupciones;
    private Dijkstra dijkstra;
    private Turno turno;
    private Mutex mutex;
    private int metodo; 
    
    RCompartido(int opcion){
        datoCompartido = "";
        cerradura = new VCerradura();
        interrupciones = new ArrayList<Interrupcion>();
        turno = new Turno();
        for(int i = 0; i<10; i++){
            interrupciones.add(new Interrupcion(true));
        }
        dijkstra = new Dijkstra(4, (int) (Math.random()*4));
        mutex = new Mutex(false);
        //mutexConcurrent = new ReentrantLock();
        this.metodo = opcion;        
    }
    public String getDatoCompartido() {

        return datoCompartido;
    }

    public void setDatoCompartido(String datoCompartido) {
        this.datoCompartido = datoCompartido;
    }

    public VCerradura getCerradura() {
        return cerradura;
    }

    public void setCerradura(VCerradura cerradura) {
        this.cerradura = cerradura;
    }

    public int getMetodo() {
        return metodo;
    }

    public void setMetodo(int metodo) {
        this.metodo = metodo;
    }
    
    public ArrayList<Interrupcion> getInterrupciones() {
        return interrupciones;
    }

    public void setInterrupciones(ArrayList<Interrupcion> interupciones) {
        this.interrupciones = interupciones;
    }

    public void bloquea (){
        for(Interrupcion inter:interrupciones)
            inter.setInter(false);
    }
    
    public void desbloquea(){
        for(Interrupcion inter:interrupciones)
            inter.setInter(true);
    }
    
    public boolean isEntra(){
        boolean band = false;
        for(Interrupcion i:interrupciones)
            if(i.getInter())
                band = true;
            else return false;
        return band;
    }

    public Dijkstra getDijkstra() {
        return dijkstra;
    }


    public void setDijkstra(Dijkstra dijkstra) {
        this.dijkstra = dijkstra;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Mutex getMutex() {
        return mutex;
    }

    public void setMutex(Mutex mutex) {
        this.mutex = mutex;
    }

    /*public Lock getMutexConcurrent() {
        return mutexConcurrent;
    }

    public void setMutexConcurrent(Lock mutexConcurrent) {
        this.mutexConcurrent = mutexConcurrent;
    }*/

}
