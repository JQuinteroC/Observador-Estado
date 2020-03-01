package Logica;

import java.awt.event.KeyEvent;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public abstract class EstrategiaControl {

    KeyEvent evento;
    Personaje personaje;
    int accion;

    public EstrategiaControl(KeyEvent evento, Personaje personaje) {
        this.evento = evento;
        this.personaje = personaje;
    }

    public void operar() {
        identificarEvento();
        if (accion == 1) {
            personaje.desplazamiento = 39;
            personaje.mover();
        }
        if (accion == 2) {
            personaje.desplazamiento = 38;
            personaje.mover();
        }
        if (accion == 3) {
            personaje.desplazamiento = 37;
            personaje.mover();
        }
        if (accion == 4) {
            personaje.desplazamiento = 40;
            personaje.mover();
        }
        if (accion == 5) {
            personaje.atacar();
        }
        if (accion == 6) {
            personaje.morir();
        }
        if (accion == 7) {
            personaje.saltar();
        }
        if (accion == 8) {
            personaje.interrumpir();
            
        }
    }

    public void identificarEvento() {
    }
}
