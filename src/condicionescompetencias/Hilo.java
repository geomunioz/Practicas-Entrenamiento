package condicionescompetencias;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextArea;

public class Hilo extends Thread{
    private JTextArea area;
    private RCompartido rc;
    private boolean dead = false;
    Lock mutexConcurrent;
    private final static int init = 1995;
    private final static int fin = 2000;
    private boolean band;
    boolean pausar;
    boolean detener;
    String aux = "Esta ocupado..";

    Hilo(JTextArea area, RCompartido rc){
        this.area = area;
        this.rc = rc;
        mutexConcurrent = new ReentrantLock();
        this.band = false;
    }
    
    public void run(){
        //Seccion Critica
        while(band){
            /*try{*/
                System.out.println("metodo: "+rc.getMetodo());
                switch(rc.getMetodo()){
                    case 1:
                        dInterrupciones();
                        break;
                    case 2:
                        vCerradura();
                        break;
                    case 3:
                        dekker();
                        break;
                    case 4:
                        dijsktra();
                        break;
                    case 5:
                        condicionesCompetencia();
                        break;
                    case 6:
                        exclusionMutua();
                        break;
                    case 7:
                        mutexConcurrente();
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
                
            /*}catch(Exception e){
                e.printStackTrace();
            }*/
        }
    }
    
    public void dInterrupciones(){
        try{
            if(rc.isEntra()){
                rc.bloquea();
                rc.setDatoCompartido("Perrito " + this.getName());
                area.append(rc.getDatoCompartido()+"\n");
                Thread.sleep((int)(init+Math.random()*fin)); //2 segundos
                if(dead){this.stop();}else{
                rc.desbloquea();}
            }else area.append(aux+"\n");
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
            
    }
    
    public void condicionesCompetencia(){
        try{
            rc.setDatoCompartido("Perrito " + this.getName());
            area.append(rc.getDatoCompartido()+"\n");
            Thread.sleep((int)(init+Math.random()*fin)); //2 segundos
            if(dead){this.stop();}
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void vCerradura(){
        try{
            if(!rc.getCerradura().isCerradura()){
                rc.getCerradura().cerrar();
                rc.setDatoCompartido("Perrito " + this.getName());
                area.append(rc.getDatoCompartido()+"\n");
                Thread.sleep((int)(init+Math.random()*fin)); //2 segundos
                if(dead){this.stop();}else{
                rc.getCerradura().abrir();}
            }else area.append(aux+"\n");
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void dijsktra(){
        try{
            int i = Integer.parseInt(this.getName()) -1;
            int j = 0;

            rc.getDijkstra().getB()[i] = false;
            while(rc.getDijkstra().getK()!=i){
                rc.getDijkstra().getC()[i] = true;
                if(rc.getDijkstra().getB() [rc.getDijkstra().getK()]){
                    rc.getDijkstra().setK(i);
                }else{
                    rc.getDijkstra().getC()[i] = false;
                    for (j = 0; j < rc.getDijkstra().getN(); j++)
                        if (j != i && !rc.getDijkstra().getC()[j])
                            break;
                }
            }
            /*Entramos a seccion critica*/
            rc.getDijkstra().getB()[i] = true;
            rc.getDijkstra().getC()[i] = true;
            rc.setDatoCompartido("Perrito " + this.getName());
            area.append(rc.getDatoCompartido()+"\n");  
            Thread.sleep((int)(init+Math.random()*fin)); //2 segundos
            if(dead){this.stop();}
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void dekker(){
        try{
            int i = Integer.parseInt(this.getName()) - 1;
            int a=10, b=10;
            int z;
            //Dekker entre  1 y 2
            if(i==0 || i==1){
                rc.getTurno().getBandera() [i] = 1;
                while(rc.getTurno().getBandera() [1-i]==1){
                    if (rc.getTurno().getTurno()== 1-i) {
                        rc.getTurno().getBandera() [i] = 0;
                        while(rc.getTurno().getTurno() == (1-i)){
                            rc.getTurno().getBandera() [i] = 1;
                        }
                    }
                }

                rc.getTurno().setTurno(1-i);
                rc.getTurno().getBandera() [i] = 0;
                a=i;    //Guardo hilo   
            }
            //Dekker entre  3 y 4
            if(i==2 || i==3){
                if(i==2){z=0;}else{z=1;}
                rc.getTurno().getBandera() [z] = 1;
                while(rc.getTurno().getBandera() [1-z]==1){
                    if (rc.getTurno().getTurno()== 1-z) {
                        rc.getTurno().getBandera() [z] = 0;
                        while(rc.getTurno().getTurno() == (1-z)){
                            rc.getTurno().getBandera() [z] = 1;
                        }
                    }
                }

                rc.getTurno().setTurno(1-z);
                rc.getTurno().getBandera() [z] = 0;
                b=i;      //Guardo hilo
            }
            //Dekker final entre el primero y el segundo a,b
            if(a!=10 || b!=10){
                if(a==0 || a ==1){z=0;}else{z=1;}
                rc.getTurno().getBandera() [z] = 1;
                while(rc.getTurno().getBandera() [1-z]==1){
                    if (rc.getTurno().getTurno()== 1-z) {
                        rc.getTurno().getBandera() [z] = 0;
                        while(rc.getTurno().getTurno() == (1-z)){
                            rc.getTurno().getBandera() [z] = 1;
                        }
                    }
                }

                rc.getTurno().setTurno(1-z);
                rc.getTurno().getBandera() [z] = 0;
                rc.setDatoCompartido("Perrito " + this.getName());
                area.append(rc.getDatoCompartido()+"\n"); 
            }
            Thread.sleep((int)(init+Math.random()*fin)); //2 segundos
            if(dead){this.stop();}
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
        
    }
    
    public void exclusionMutua(){
        try{
            if(!rc.getMutex().isPase()){
                rc.getMutex().lock();
                rc.setDatoCompartido("Perrito " + this.getName());
                area.append(rc.getDatoCompartido()+"\n");
                Thread.sleep((int)(init+Math.random()*fin)); //2 segundos
                if(dead){this.stop();}else{
                rc.getMutex().unlock();}
            }else area.append(aux+"\n");
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
        
    }
    
    public void mutexConcurrente(){
        try{
            if(mutexConcurrent.tryLock()){
                mutexConcurrent.lock();
                rc.setDatoCompartido("Perrito " + this.getName());
                area.append(rc.getDatoCompartido()+"\n");
                Thread.sleep((int)(init+Math.random()*fin)); //2 segundos
                if(dead){this.stop();}else{
                mutexConcurrent.unlock();}
            }else area.append(aux+"\n");
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isBand() {
        return band;
    }

    public void setBand(boolean band) {
        this.band = band;
    }
}
