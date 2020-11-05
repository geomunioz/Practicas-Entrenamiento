
package condicionescompetencias;


public class Dijkstra {
    private boolean b[];
    private boolean c[];
    private int N;
    private int k; 
    
    public Dijkstra(int n, int k){
        this.N = n;
        this.k = k;
        this.b = new boolean[n];
        this.c = new boolean[n];
    }
    
    public boolean[] getB() {
        return b;
    }

    public void setB(boolean[] b) {
        this.b = b;
    }

    public boolean[] getC() {
        return c;
    }

    public void setC(boolean[] c) {
        this.c = c;
    }


    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
    
}
