package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Mateo
 */
public class Reproductor2 extends Reproductor {

    public void run() {
        while (true) {
            Player apl;
            try {
                apl = new Player(new FileInputStream("Recursos\\Music\\sonic.mp3"));
                apl.play();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Reproductor1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JavaLayerException ex) {
                Logger.getLogger(Reproductor1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
