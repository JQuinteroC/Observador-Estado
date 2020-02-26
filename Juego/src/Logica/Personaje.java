package Logica;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Mateo
 */
public class Personaje extends JComponent implements Cloneable, Composite {

    protected ImageIcon[] caminar;
    protected ImageIcon[] saltar;
    protected ImageIcon[] morir;
    protected ImageIcon[] atacar;
    public int desplazamiento;
    public int tempDesplazamiento;
    int x = 0; // Variable de identificación de animación
    int numero = 0;
    int ancho = 0;
    int alto = 0;
    int desplazamientoVertical = 0;
    int desplazamientoHorizontal = 0;
    boolean relacion = false; // Variable solo para mantener el aspecto en las animaciónes del Mago
    boolean animar = false; // Controla la ejecución de la animación
    static JPanel panel = null;
    public Thread hilo;
    protected EstrategiaControl control;

    // CONSTRUCTOR
    public Personaje() {
        // Se limpian todas las varibales para el cambio de personaje
        hilo = null;
        caminar = null;
        saltar = null;
        morir = null;
        atacar = null;
        x = 0;
        numero = 0;
        ancho = 0;
        alto = 0;
        relacion = false;
        panel = null;
    }

    // SET AND GET
    public boolean isRelacion() {
        return relacion;
    }

    public void setDesplazamientoVertical(int desplazamiento) {
        this.desplazamientoVertical = desplazamiento;
    }

    public void setDesplazamientoHorizontal(int desplazamiento) {
        this.desplazamientoHorizontal = desplazamiento;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setHilo(int mover, int saltar, int morir, int atacar, int sleep) {
        this.hilo = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        switch (x) {
                            case 0:
                                numero++;
                                switch (desplazamiento) { //Personaje Individual
                                    case 39:
                                        desplazamientoHorizontal += 24;
                                        tempDesplazamiento = desplazamiento;
                                        desplazamiento = 0;
                                        break;
                                    case 38:
                                        desplazamientoVertical -= 24;
                                        tempDesplazamiento = desplazamiento;
                                        desplazamiento = 0;
                                        break;
                                    case 37:
                                        desplazamientoHorizontal -= 24;
                                        tempDesplazamiento = desplazamiento;
                                        desplazamiento = 0;
                                        break;
                                    case 40:
                                        desplazamientoVertical += 24;
                                        tempDesplazamiento = desplazamiento;
                                        desplazamiento = 0;
                                        break;
                                    default:
                                        break;
                                }
                                numero = numero % mover;
                                panel.repaint();
                                hilo.sleep(sleep);
                                break;
                            case 1:
                                numero++;
                                numero = numero % saltar;
                                panel.repaint();
                                hilo.sleep(sleep);
                                break;
                            case 2:
                                numero++;
                                numero = numero % morir;
                                panel.repaint();
                                hilo.sleep(sleep);
                                break;
                            case 3:
                                numero++;
                                numero = numero % atacar;
                                panel.repaint();
                                hilo.sleep(sleep);
                                break;
                            default:
                                break;
                        }
                    }
                } catch (java.lang.InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
    }

    public void setPanel(JPanel panel) {
        Personaje.panel = panel;
        setBounds(0, 0, panel.getWidth(), panel.getHeight());
    }

    public ImageIcon[] getCaminar() {
        return caminar;
    }

    public void setCaminar(ImageIcon[] caminar) {
        this.caminar = caminar;
    }

    public ImageIcon[] getSaltar() {
        return saltar;
    }

    public void setSaltar(ImageIcon[] saltar) {
        this.saltar = saltar;
    }

    public ImageIcon[] getMorir() {
        return morir;
    }

    public void setMorir(ImageIcon[] morir) {
        this.morir = morir;
    }

    public ImageIcon[] getAtacar() {
        return atacar;
    }

    public void setAtacar(ImageIcon[] atacar) {
        this.atacar = atacar;
    }

    // CLONE
    @Override
    public Personaje clone() {
        Personaje PersonajeClonado = null;
        try {
            PersonajeClonado = (Personaje) super.clone();
            if (PersonajeClonado.isRelacion()) {
                PersonajeClonado.setHilo(5, 5, 5, 5, 130);
            } else {
                PersonajeClonado.setHilo(24, 18, 15, 12, 50);
            }
        } catch (CloneNotSupportedException e) {
        }
        return PersonajeClonado;
    }

    // PINTAR EN PANEL
    @Override
    public void paint(Graphics g) {
        try {
            switch (x) {
                case 0:
                    g.drawImage(caminar[numero].getImage(), 50 + desplazamientoHorizontal, 0 + desplazamientoVertical, ancho, alto, null);
                    break;
                case 1:
                    if (relacion) {
                        g.drawImage(saltar[numero].getImage(), 50 + desplazamientoHorizontal, -42 + desplazamientoVertical, ancho - 17, alto + 30, null);
                    } else {
                        g.drawImage(saltar[numero].getImage(), 50 + desplazamientoHorizontal, 0 + desplazamientoVertical, ancho, alto, null);
                    }
                    break;
                case 2:
                    if (relacion) {
                        g.drawImage(morir[numero].getImage(), 65 + desplazamientoHorizontal, 20 + desplazamientoVertical, ancho - 38, alto - 18, null);
                    } else {
                        g.drawImage(morir[numero].getImage(), 50 + desplazamientoHorizontal, 0 + desplazamientoVertical, ancho, alto, null);
                    }
                    break;
                case 3:
                    if (relacion) {
                        g.drawImage(atacar[numero].getImage(), 50 + desplazamientoHorizontal, -30 + desplazamientoVertical, ancho + 150, alto + 55, null);
                    } else {
                        g.drawImage(atacar[numero].getImage(), 50 + desplazamientoHorizontal, 0 + desplazamientoVertical, ancho, alto, null);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {

        }
    }

    // ANIMACIONES
    public void mover() {
        x = 0;
        if (!((tempDesplazamiento > 36) & (tempDesplazamiento < 41))) {
            numero = 0;
        }
        if (!hilo.isAlive()) {
            hilo.start();
        }
    }

    public void saltar() {
        x = 1;
        numero = 0;
        if (!hilo.isAlive()) {
            hilo.start();
        }
    }

    public void morir() {
        x = 2;
        numero = 0;
        if (!hilo.isAlive()) {
            hilo.start();
        }
    }

    public void atacar() {
        x = 3;
        numero = 0;
        if (!hilo.isAlive()) {
            hilo.start();
        }
    }

    //operacion necesaria para el manejo de poblaciones
    @Override
    public void operar(KeyEvent evento) {
        control = new FlechasControl(evento, this);
        control.operar();
    }

    public void interrumpir() {
        try {
            hilo.interrupt();
        } catch (Exception ex) {
            System.out.println("hilo " + hilo.getName() + " no interrumpido");
        }
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getDesplazamientoHorizontal() {
        return desplazamientoHorizontal;
    }

    public int getDesplazamientoVertical() {
        return desplazamientoVertical;
    }
}
