package GUI;

/**
 *
 * @author Mateo
 */
public abstract class Reproductor extends Thread{

    Reproductor successor;
    int cancion;

    public void run(int cancion){}

    public Reproductor getSuccessor() {
        return successor;
    }

    public void setSuccessor(Reproductor successor) {
        this.successor = successor;
    }
}