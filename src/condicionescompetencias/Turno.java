
package condicionescompetencias;

public class Turno {
    private int[] bandera;
    private int turno;
    
    public Turno(){
        this.bandera = new int[2];
        this.turno = 0;
        
        for (int i = 0; i < this.bandera.length; i++) {
            this.bandera[i]=0;
        }
    }

    public int[] getBandera() {
        return bandera;
    }

    public void setBandera(int[] bandera) {
        this.bandera = bandera;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
}
