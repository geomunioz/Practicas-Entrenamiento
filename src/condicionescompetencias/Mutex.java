
package condicionescompetencias;

public class Mutex {
    private boolean pase;
    
    Mutex(boolean pase){
        this.pase = pase;
    }
    
    synchronized void lock(){
        while(pase){
            try{
                wait();
            }catch(Exception e){
                System.out.println("Error: "+e.toString());
            }
        }
        this.pase = true;
    }
  
    synchronized void unlock(){
        this.pase = false;
        notify();
    }
    
    public boolean isPase() {
        return pase;
    }

    public void setPase(boolean pase) {
        this.pase = pase;
    }
}
