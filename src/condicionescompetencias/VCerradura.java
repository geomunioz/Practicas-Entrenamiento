
package condicionescompetencias;

/**
 *
 * @author Jorge
 */
public class VCerradura {
    private boolean cerradura;
    
    VCerradura(){
        this.cerradura = false;
    }
    public void cerrar(){
        this.cerradura = true;
    }
    
    public void abrir(){
        this.cerradura = false;
    }

    public boolean isCerradura() {
        return cerradura;
    }
}
