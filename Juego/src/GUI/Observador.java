package GUI;

import Logica.Personaje;

/**
 *
 * @author Mateo
 */
public interface Observador {
    
    public void update();
    public void update(Personaje p);
    
}
