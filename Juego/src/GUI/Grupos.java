package GUI;

import Logica.Personaje;
import Logica.Poblacion;

/**
 *
 * @author Mateo
 */
public class Grupos implements Observador {

    Poblacion grupo = new Poblacion("Grupo1");
    Poblacion grupo2 = new Poblacion("Grupo2");

    @Override
    public void update() {

    }

    public void update(Personaje p) {
        if (grupo.isHere(p)) {
            grupo.deletePerson(p);
            grupo2.addPersonaje(p);
        } else {
            grupo2.deletePerson(p);
            grupo.addPersonaje(p);

        }
    }

}
