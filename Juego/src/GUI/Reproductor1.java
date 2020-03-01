package GUI;

import java.io.FileInputStream;
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
public class Reproductor1 extends Reproductor {

    @Override
    public void run() {
        if (cancion==0) {
            Player apl;
            try {
                apl = new Player(new FileInputStream("Recursos\\Music\\mario.mp3"));
                apl.play();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Reproductor1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JavaLayerException ex) {
                Logger.getLogger(Reproductor1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            successor.run(cancion);
            successor.start();
        }
    }
}
